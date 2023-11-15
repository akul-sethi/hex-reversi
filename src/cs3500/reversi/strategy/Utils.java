package cs3500.reversi.strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import cs3500.reversi.model.BasicPoint;
import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReversiModel;

/**
 * To store functions needed in most of the strategies.
 */
final class Utils {
  /**
   * Finds all the legal moves for the current player in the given reversiModel.
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
        }
        catch (Exception e) {
          // Do nothing!
        }
      }
    }
    return allInMap;
  }

  /**
   * Gets the corners of the given model. There are six corners in a hexagon, top left, top right,
   * bottom left, bottom right, leftmost, and rightmost.
   * @param model The model to get the corners from.
   * @return an ArrayList of coordinates with all of the corners.
   */
  static ArrayList<LinearCoord> getCorners(ReversiModel model) {
    ArrayList<LinearCoord> corners = new ArrayList<>();
    ArrayList<LinearCoord> allInMap = getAll(model);
    List<Comparator<LinearCoord>> comparators =
            Arrays.asList(new upperLefterCoordComparer(), new upperRighterCoordComparer(),
                    new lowerRighterCoordComparer(), new lowerLefterCoordComparer());
    for (Comparator<LinearCoord> comparator : comparators) {
      ArrayList<LinearCoord> allCopy = new ArrayList<>(allInMap);
      allCopy.sort(comparator);
      corners.add(allCopy.get(0));
    }
    corners.add(new BasicPoint(0, -5));
    corners.add(new BasicPoint(0, 5));
    return corners;
  }

  /**
   * Gets all the coordinates in the second row of a list of coordinates.
   * @param all an ArrayList of coordinates to search.
   * @return an ArrayList containing all the coordinates in the second row of the list given.
   */
  static ArrayList<LinearCoord> getSecondRow(ArrayList<LinearCoord> all) {
    all.sort(new upperLefterCoordComparer());
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

  /**
   * Gets all the coordinates in the second to last row of a list of coordinates.
   * @param all an ArrayList of coordinates to search.
   * @return an ArrayList containing all the coordinates in the
   * second to last row of the list given.
   */
  static ArrayList<LinearCoord> getSecondLastRow(ArrayList<LinearCoord> all) {
    all.sort(new lowerLefterCoordComparer());
    ArrayList<LinearCoord> inSecondLastRow = new ArrayList<>();
    int secondLastRow = all.get(0).row() - 1;
    for (LinearCoord lc : all) {
      if (lc.row() == secondLastRow) {
        inSecondLastRow.add(lc);
      }
    }
    return inSecondLastRow;
  }

  /**
   * This method returns an arrayList of coordinates next to the corners. This is used in the
   * avoidNextToCorners strategy. This method is very lengthy, but up until now, all the
   * methods work regardless of board layout. This method is only possible if we do assume that the
   * board is a perfect hexagon. Since this was a suggested strategy, we assumed that this was a
   * safe assumption. Hence, the long method.
   * @param model the model to scan for coordinates.
   * @return an ArrayList of coordinates that are next to the corners.
   */
  static ArrayList<LinearCoord> getNextToCorners(ReversiModel model) {
    ArrayList<LinearCoord> corners = getCorners(model);
    ArrayList<LinearCoord> surroundsCorners = new ArrayList<>();
    corners.sort(new lefterCoordComparer());
    LinearCoord leftCorner = corners.get(0);
    LinearCoord rightCorner = corners.get(5);
    //all the middle surrounding tiles
    surroundsCorners.add(new BasicPoint(leftCorner.row(), leftCorner.column() + 1));
    surroundsCorners.add(new BasicPoint(rightCorner.row(), rightCorner.column() - 1));
    surroundsCorners.add(new BasicPoint(leftCorner.row() - 1, leftCorner.column()));
    surroundsCorners.add(new BasicPoint(rightCorner.row() - 1, rightCorner.column() - 1));
    surroundsCorners.add(new BasicPoint(leftCorner.row() + 1, leftCorner.column()));
    surroundsCorners.add(new BasicPoint(rightCorner.row() + 1, rightCorner.column() - 1));
    //top and bottom left and right
    corners.sort(new upperLefterCoordComparer());
    LinearCoord topLeftCorner = corners.get(0);
    LinearCoord topRightCorner = corners.get(1);
    LinearCoord bottomLeftCorner = corners.get(4);
    LinearCoord bottomRightCorner = corners.get(5);
    surroundsCorners.add(new BasicPoint(topLeftCorner.row(), topLeftCorner.column() + 1));
    surroundsCorners.add(new BasicPoint(topRightCorner.row(), topRightCorner.column() - 1));
    surroundsCorners.add(new BasicPoint(bottomLeftCorner.row(), bottomLeftCorner.column() + 1));
    surroundsCorners.add(new BasicPoint(bottomRightCorner.row(), bottomRightCorner.column() - 1));
    //top and bottom other adjacent
    ArrayList<LinearCoord> all = getAll(model);
    ArrayList<LinearCoord> secondRow = getSecondRow(all);
    surroundsCorners.add(secondRow.get(0));
    surroundsCorners.add(secondRow.get(1));
    surroundsCorners.add(secondRow.get(secondRow.size() - 2));
    surroundsCorners.add(secondRow.get(secondRow.size() - 1));
    all = getAll(model);
    ArrayList<LinearCoord> secondLastRow = getSecondLastRow(all);
    surroundsCorners.add(secondLastRow.get(0));
    surroundsCorners.add(secondLastRow.get(1));
    surroundsCorners.add(secondLastRow.get(secondLastRow.size() - 2));
    surroundsCorners.add(secondLastRow.get(secondLastRow.size() - 1));
    return surroundsCorners;
  }

  /**
   * The following classes are a series of LinearCoord comparators.
   * The first is a comparator that sorts in order of top-left.
   */
  static class upperLefterCoordComparer implements Comparator<LinearCoord> {
    @Override
    public int compare(LinearCoord a, LinearCoord b) {
      int rowDiff = a.row() - b.row();
      if (rowDiff != 0) {
        return a.column() - b.column();
      }
      return rowDiff;
    }
  }

  /**
   * The second is a comparator that sorts in order of top-right.
   */
  static class upperRighterCoordComparer implements Comparator<LinearCoord> {
    @Override
    public int compare(LinearCoord a, LinearCoord b) {
      int rowDiff = a.row() - b.row();
      if (rowDiff != 0) {
        return b.column() - a.column();
      }
      return rowDiff;
    }
  }

  /**
   * The third is a comparator that sorts in order of bottom-left.
   */
  static class lowerLefterCoordComparer implements Comparator<LinearCoord> {
    @Override
    public int compare(LinearCoord a, LinearCoord b) {
      int rowDiff = b.row() - a.row();
      if (rowDiff != 0) {
        return a.column() - b.column();
      }
      return rowDiff;
    }
  }

  /**
   * The fourth is a comparator that sorts in order of bottom-right.
   */
  static class lowerRighterCoordComparer implements Comparator<LinearCoord> {
    @Override
    public int compare(LinearCoord a, LinearCoord b) {
      int rowDiff = b.row() - a.row();
      if (rowDiff != 0) {
        return b.column() - a.column();
      }
      return rowDiff;
    }
  }

  /**
   * The third is a comparator that sorts in order of rightmost first.
   */
  static class righterCoordComparer implements Comparator<LinearCoord> {
    @Override
    public int compare(LinearCoord a, LinearCoord b) {
      return b.column() - a.column();
    }
  }

  /**
   * The third is a comparator that sorts in order of leftmost first.
   */
  static class lefterCoordComparer implements Comparator<LinearCoord> {
    @Override
    public int compare(LinearCoord a, LinearCoord b) {
      return a.column() - b.column();
    }
  }
}
