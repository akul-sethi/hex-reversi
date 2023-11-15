package cs3500.reversi.strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.spi.LocaleNameProvider;

import cs3500.reversi.model.BasicPoint;
import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReversiModel;

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
   * @return an ArrayList of coordinates with all of the corners.
   */
  static ArrayList<LinearCoord> getCorners(ReversiModel model) {
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
   * Gets all the coordinates in the second row of a list of coordinates.
   *
   * @param all an ArrayList of coordinates to search.
   * @return an ArrayList containing all the coordinates in the second row of the list given.
   */
  static ArrayList<LinearCoord> getSecondRow(ArrayList<LinearCoord> all) {
    all.sort(new UpperLefterCoordComparer());
    ArrayList<LinearCoord> inSecondRow = new ArrayList<>();
    int secondRow = all.get(0).row() + 1;
    boolean started = false;
    for (LinearCoord lc : all) {
      if (lc.row() == secondRow) {
        inSecondRow.add(lc);
      }
    }
    return inSecondRow;
  }

  static ArrayList<LinearCoord> getAdjacent(LinearCoord coord, ReversiModel model) {
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
    }
    else {
      adjacent.add(new BasicPoint(row - 1, col + 1));
      adjacent.add(new BasicPoint(row + 1, col + 1));
    }
    ArrayList<LinearCoord> copyAdj = new ArrayList<>(adjacent);
    for (LinearCoord lc : copyAdj) {
      try {
        model.playerAt(lc);
      }
      catch (Exception e) {
        adjacent.remove(lc);
      }
    }
    return adjacent;
  }

  /**
   * Gets all the coordinates in the second to last row of a list of coordinates.
   *
   * @param all an ArrayList of coordinates to search.
   * @return an ArrayList containing all the coordinates in the
   *     second to last row of the list given.
   */
  static ArrayList<LinearCoord> getSecondLastRow(ArrayList<LinearCoord> all) {
    all.sort(new LowerLefterCoordComparer());
    ArrayList<LinearCoord> inSecondLastRow = new ArrayList<>();
    int secondLastRow = all.get(0).row() - 1;
    for (LinearCoord lc : all) {
      if (lc.row() == secondLastRow) {
        inSecondLastRow.add(lc);
      }
    }
    return inSecondLastRow;
  }

  // This method is very lengthy, but up until now, all the
  // methods work regardless of board layout. This method is only possible if we do assume
  // that the board is a perfect hexagon. Since this was a suggested strategy, we assumed
  // that this was a safe assumption. Hence, the long method.

  /**
   * Returns an arrayList of coordinates next to the corners.
   * @param model the model to scan for coordinates.
   * @return an ArrayList of coordinates that are next to the corners.
   */
  static ArrayList<LinearCoord> getNextToCorners(ReversiModel model) {
    ArrayList<LinearCoord> corners = getCorners(model);
    ArrayList<LinearCoord> surroundsCorners = new ArrayList<>();
    for (LinearCoord corner : corners) {

      surroundsCorners.addAll(getAdjacent(corner, model));
    }
    return surroundsCorners;
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
   * The third is a comparator that sorts in order of rightmost first.
   */
  static class RighterCoordComparer implements Comparator<LinearCoord> {
    @Override
    public int compare(LinearCoord a, LinearCoord b) {
      return b.column() - a.column();
    }
  }

  /**
   * The third is a comparator that sorts in order of leftmost first.
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
