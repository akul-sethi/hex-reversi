package cs3500.reversi.strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import cs3500.reversi.model.BasicPoint;
import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.Player;

/**
 * To store functions needed in most of the strategies.
 */
final class Utils {
  /**
   * Finds all the legal moves for the current player in the given reversiModel.
   *
   * @param model the reversiModel to scan.
   * @return an ArrayList of all legal moves that the current player can play.
   */
  static ArrayList<LinearCoord> allLegalMoves(ReversiModel model) {
    model.startGame();
    ArrayList<LinearCoord> allLegal = new ArrayList<>();
    int botRow = model.getBottomRow();
    int rightCol = model.getRightCol();
    int leftCol = model.getLeftCol();
    int topRow = model.getTopRow();
    for (int row = topRow; row <= botRow; row += 1) {
      for (int col = leftCol; col <= rightCol; col += 1) {
        LinearCoord tempPoint = new BasicPoint(row, col);
        if (model.validMove(tempPoint)) {
          allLegal.add(tempPoint);
        }
      }
    }
    return allLegal;
  }

  /**
   * Finds all the coordinates in the given model board.
   *
   * @param model The model to scan the board of.
   * @return an ArrayList of all coordinates in the model's board.
   */
  static ArrayList<LinearCoord> getAll(ReversiModel model) {
    model.startGame();
    ArrayList<LinearCoord> allInMap = new ArrayList<>();
    for (int row = model.getTopRow(); row <= model.getBottomRow(); row += 1) {
      for (int col = model.getLeftCol(); col <= model.getRightCol(); col += 1) {
        try {
          LinearCoord tempPoint = new BasicPoint(row, col);
          model.playerAt(tempPoint);
          allInMap.add(tempPoint);
        } catch (Exception e) {
          // Do nothing!
        }
      }
    }
    return allInMap;
  }

  /**
   * Gets the corners of the given model. There are six corners in a hexagon, top left, top right,
   * bottom left, bottom right, leftmost, and rightmost.
   *
   * @param model The model to get the corners from.
   * @return an ArrayList of coordinates with all the corners.
   */
  static ArrayList<LinearCoord> getCorners(ReversiModel model) {
    model.startGame();
    ArrayList<LinearCoord> corners = new ArrayList<>();
    ArrayList<LinearCoord> allInMap = getAll(model);
    List<Comparator<LinearCoord>> comparators =
            Arrays.asList(new LefterCoordComparer(), new UpperLefterCoordComparer(),
                    new UpperRighterCoordComparer(), new RighterCoordComparer(),
                    new LowerRighterCoordComparer(), new LowerLefterCoordComparer());
    for (Comparator<LinearCoord> comparator : comparators) {
      ArrayList<LinearCoord> allCopy = new ArrayList<>(allInMap);
      allCopy.sort(comparator);
      corners.add(allCopy.get(0));
    }
    return corners;
  }

  /**
   * Gets the adjacent, valid coordinates of the given LinearCoord.
   *
   * @param coord the coordinate to get the adjacent coords of.
   * @param model the model to check for coord legality in.
   * @return A list of adjacent coordinates to the one given.
   */
  static ArrayList<LinearCoord> getAdjacent(LinearCoord coord, ReversiModel model) {
    model.startGame();
    ArrayList<LinearCoord> adjacent = new ArrayList<>();
    int row = coord.row();
    int col = coord.column();
    adjacent.add(new BasicPoint(row, col + 1));
    adjacent.add(new BasicPoint(row, col - 1));
    adjacent.add(new BasicPoint(row + 1, col));
    adjacent.add(new BasicPoint(row - 1, col));
    if (row % 2 == 0) {
      adjacent.add(new BasicPoint(row - 1, col - 1));
      adjacent.add(new BasicPoint(row + 1, col - 1));
    } else {
      adjacent.add(new BasicPoint(row - 1, col + 1));
      adjacent.add(new BasicPoint(row + 1, col + 1));
    }
    ArrayList<LinearCoord> copyAdj = new ArrayList<>(adjacent);
    for (LinearCoord lc : copyAdj) {
      try {
        model.playerAt(lc);
      } catch (Exception e) {
        adjacent.remove(lc);
      }
    }
    return adjacent;
  }

  /**
   * Returns an arrayList of coordinates next to the corners.
   *
   * @param model the model to scan for coordinates.
   * @return an ArrayList of coordinates that are next to the corners.
   */
  static ArrayList<LinearCoord> getNextToCorners(ReversiModel model) {
    model.startGame();
    ArrayList<LinearCoord> corners = getCorners(model);
    ArrayList<LinearCoord> surroundsCorners = new ArrayList<>();
    for (LinearCoord corner : corners) {
      surroundsCorners.addAll(getAdjacent(corner, model));
    }
    return surroundsCorners;
  }

  static int getPlayerScore(ReversiModel model, Player forWhom, PointValue pointValue) {
    ArrayList<LinearCoord> allInMap = getAll(model);
    int score = 0;
    for (LinearCoord lc : allInMap) {
      score += pointValue.getPointValue(lc, model, forWhom);
    }
    return score;
  }

  /**
   * The following classes are a series of LinearCoord comparators.
   * The first is a comparator that sorts in order of top-left.
   */
  static class UpperLefterCoordComparer implements Comparator<LinearCoord> {
    @Override
    public int compare(LinearCoord a, LinearCoord b) {
      //System.out.println("UpLeftComp");
      //System.out.println(a + ", " + b);
      int rowDiff = a.row() - b.row();
      if (rowDiff == 0) {
        //System.out.println(a.column() - b.column());
        return a.column() - b.column();
      }
      //System.out.println(rowDiff);
      return rowDiff;
    }
  }

  /**
   * The second is a comparator that sorts in order of top-right.
   */
  static class UpperRighterCoordComparer implements Comparator<LinearCoord> {
    @Override
    public int compare(LinearCoord a, LinearCoord b) {
      int rowDiff = a.row() - b.row();
      if (rowDiff == 0) {
        return b.column() - a.column();
      }
      return rowDiff;
    }
  }

  /**
   * The third is a comparator that sorts in order of bottom-left.
   */
  static class LowerLefterCoordComparer implements Comparator<LinearCoord> {
    @Override
    public int compare(LinearCoord a, LinearCoord b) {
      int rowDiff = b.row() - a.row();
      if (rowDiff == 0) {
        return a.column() - b.column();
      }
      return rowDiff;
    }
  }

  /**
   * The fourth is a comparator that sorts in order of bottom-right.
   */
  static class LowerRighterCoordComparer implements Comparator<LinearCoord> {
    @Override
    public int compare(LinearCoord a, LinearCoord b) {
      int rowDiff = b.row() - a.row();
      if (rowDiff == 0) {
        return b.column() - a.column();
      }
      return rowDiff;
    }
  }

  /**
   * The fifth is a comparator that sorts in order of rightmost first.
   */
  static class RighterCoordComparer implements Comparator<LinearCoord> {
    @Override
    public int compare(LinearCoord a, LinearCoord b) {
      return b.column() - a.column();
    }
  }

  /**
   * The sixth is a comparator that sorts in order of leftmost middlemost first.
   */
  static class LefterCoordComparer implements Comparator<LinearCoord> {
    @Override
    public int compare(LinearCoord a, LinearCoord b) {
      int colDiff = a.column() - b.column();
      if (colDiff == 0) {
        return Math.abs(a.row()) - Math.abs(b.row());
      }
      return colDiff;
    }
  }
}
