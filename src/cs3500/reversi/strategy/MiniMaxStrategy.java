package cs3500.reversi.strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.Player;

public class MiniMaxStrategy implements FallibleReversiStrategy {

  @Override
  public Optional<ArrayList<LinearCoord>> chooseMove(ReadOnlyReversiModel model, Player forWhom,
                                                     ArrayList<LinearCoord> legalMoves) {
    ReversiModel testModel = model.getModel();
    int myScore = testModel.getPlayerScore(forWhom);
    testModel.pass();
    Player opponent = testModel.nextToPlay();
    int opponentScore = testModel.getPlayerScore(opponent);
    testModel.pass();
    int startDifference = myScore - opponentScore;
    int maxDifference = Integer.MIN_VALUE;
    ArrayList<LinearCoord> bestMoves = new ArrayList<>();
    ArrayList<LinearCoord> allMoves = legalMoves;
    if (allMoves.isEmpty()) {
      allMoves = Utils.allLegalMoves(testModel);
    }
    for (LinearCoord lm : allMoves) {
      ReversiModel tempModel = testModel.getModel();
      tempModel.placePiece(lm.row, lm.col);
      ArrayList<LinearCoord> opponentLegalMoves = Utils.allLegalMoves(tempModel);
      ArrayList<Integer> bestOpponentMoves = new ArrayList<>();
      for (LinearCoord olm : opponentLegalMoves) {
        ReversiModel tempTempModel = tempModel.getModel();
        tempTempModel.placePiece(olm.row, olm.col);
        int tempOpponentScore = tempTempModel.getPlayerScore(opponent);
        int tempMyScore = tempTempModel.getPlayerScore(forWhom);
        int tempDifference = tempMyScore - tempOpponentScore;
        bestOpponentMoves.add(tempDifference);
      }
      if (bestOpponentMoves.isEmpty()) {
         bestOpponentMoves.add(tempModel.getPlayerScore(forWhom));
      }
      bestOpponentMoves.sort(Comparator.naturalOrder());
      if (bestOpponentMoves.get(0) > maxDifference) {
        maxDifference = bestOpponentMoves.get(0);
        bestMoves.clear();
        bestMoves.add(lm);
      }
      else if (bestOpponentMoves.get(0) == maxDifference) {
        bestMoves.add(lm);
      }
    }
    bestMoves.sort(new Utils.upperLefterCoordComparer());
    if (bestMoves.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(bestMoves);
  }
}