package cs3500.reversi.strategy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Optional;
import java.util.spi.LocaleNameProvider;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.Player;

public class CaptureMaxStrategy implements FallibleReversiStrategy {
  @Override
  public Optional<ArrayList<LinearCoord>> chooseMove(ReadOnlyReversiModel model, Player forWhom,
                                                     ArrayList<LinearCoord> legalMoves) {
    int maxDifference = 0;
    ArrayList<LinearCoord> bestMoves = new ArrayList<>();
    ReversiModel testModel = model.getModel();
    int startScore = testModel.getPlayerScore(forWhom);
    if (legalMoves.isEmpty()) {
      legalMoves = Utils.allLegalMoves(testModel);
    }
    if (legalMoves.isEmpty()) {
      return Optional.empty();
    }
    for (LinearCoord move : legalMoves) {
      ReversiModel tempModel = model.getModel();
      tempModel.placePiece(move.row, move.col);
      int tempScore = tempModel.getPlayerScore(forWhom);
      int difference = tempScore - startScore;
      if (difference > maxDifference) {
        maxDifference = difference;
        bestMoves.clear();
        bestMoves.add(move);
      }
      else if (difference == maxDifference) {
        bestMoves.add(move);
      }
    }
    bestMoves.sort(new Utils.upperLefterCoordComparer());
    return Optional.of(bestMoves);
  }
}
