package cs3500.reversi.strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.spi.LocaleNameProvider;

import javax.sound.sampled.Line;

import cs3500.reversi.model.BasicPoint;
import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReversiModel;

public class Utils {
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

  static ArrayList<LinearCoord> getCorners(ReversiModel model) {
    ArrayList<LinearCoord> corners = new ArrayList<>();
    ArrayList<LinearCoord> allInMap = getAll(model);
    List<Comparator<LinearCoord>> comparators =
            Arrays.asList(new upperLefterCoordComparer(), new upperRighterCoordComparer(),
                    new righterCoordComparer(), new lowerRighterCoordComparer(),
                    new lowerLefterCoordComparer(), new lefterCoordComparer());
    for (Comparator<LinearCoord> comparator : comparators) {
      ArrayList<LinearCoord> allCopy = new ArrayList<>(allInMap);
      allCopy.sort(comparator);
      corners.add(allCopy.get(0));
    }
    return corners;
  }

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

  //have:
  //middle row
  //top left, top right, and bottoms equiv.
  //need:
  //2nd to top left, 2nd to top right
  //2nd to top 2nd to left, 2nd to top 2nd to right
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

  static class righterCoordComparer implements Comparator<LinearCoord> {
    @Override
    public int compare(LinearCoord a, LinearCoord b) {
      return b.column() - a.column();
    }
  }

  static class lefterCoordComparer implements Comparator<LinearCoord> {
    @Override
    public int compare(LinearCoord a, LinearCoord b) {
      return a.column() - b.column();
    }
  }
}
