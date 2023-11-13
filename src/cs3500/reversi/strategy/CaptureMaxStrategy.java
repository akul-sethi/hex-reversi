package cs3500.reversi.strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.Player;

public class CaptureMaxStrategy implements FallibleReversiStrategy {
  @Override
  public Optional<LinearCoord> chooseMove(ReadOnlyReversiModel model, Player forWhom) {
    int maxDifference = 0;
    ArrayList<LinearCoord> bestMoves = new ArrayList<>();
    ReversiModel testModel = model.getModel();
    int startScore = testModel.getPlayerScore(forWhom);
    ArrayList<LinearCoord> legalMoves = allLegalMoves(testModel);
    if (legalMoves.isEmpty()) {
      return Optional.empty();
    }
    for (LinearCoord move : legalMoves) {
      ReversiModel tempModel = model.getModel();
      tempModel.placePiece(move.row, move.col);
      int tempScore = testModel.getPlayerScore(forWhom);
      int difference = tempScore - startScore;
      if (difference > maxDifference) {
        bestMoves.clear();
        bestMoves.add(move);
      }
      else if (difference == maxDifference) {
        bestMoves.add(move);
      }
    }
    if (bestMoves.size() > 1) {
      bestMoves.sort(new upperLefterCoordComparer());
    }
    return Optional.of(bestMoves.get(0));
  }

  static class upperLefterCoordComparer implements Comparator<LinearCoord> {
    @Override
    public int compare(LinearCoord a, LinearCoord b) {
      int rowDiff = a.row - b.row;
      if (rowDiff != 0) {
        return a.col - b.col;
      }
      return rowDiff;
    }
  }

  private ArrayList<LinearCoord> allLegalMoves(ReversiModel model) {
    ArrayList<LinearCoord> allLegal = new ArrayList<>();
    for (int row = model.getTopRow(); row <= model.getBottomRow(); row += 1) {
      for (int col = model.getLeftCol(); col <= model.getRightCol(); col += 1) {
        if (model.validMove(row, col)) {
          allLegal.add(new LinearCoord(row, col));
        }
      }
    }
    return allLegal;
  }
}
