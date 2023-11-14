package cs3500.reversi.strategy;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Optional;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.Player;

/**
 * Represents a MiniMax strategy. The strategy minimizes the max value of the opponents next move.
 * Value in this game we chose to be tiles captured.
 */
public class MiniMaxStrategy implements FallibleReversiStrategy {

  /**
   * The chooseMove function.
   * @param model The model to search for a move in.
   * @param forWhom The player to search for a move for.
   * @param legalMoves All the current legal moves to look through for the best move. If this is
   *                   empty, the strategy will re-find all the legal moves for the player
   *                   and sort through those.
   * @return An optional list of the best moves that the strategy given chooses.
   */
  @Override
  public Optional<ArrayList<LinearCoord>> chooseMove(ReadOnlyReversiModel model, Player forWhom,
                                                     ArrayList<LinearCoord> legalMoves) {
    //gets the opponent player
    ReversiModel testModel = model.getModel();
    testModel.pass();
    Player opponent = testModel.nextToPlay();
    testModel.pass();
    int maxDifference = Integer.MIN_VALUE;
    ArrayList<LinearCoord> bestMoves = new ArrayList<>();
    //if passed in moves is empty, re-get the legal moves.
    ArrayList<LinearCoord> allMoves = legalMoves;
    if (allMoves.isEmpty()) {
      allMoves = Utils.allLegalMoves(testModel);
    }
    //for each legal move, find the value for the opponents best responses
    //if a move leads to the best value, add it to the best moves list
    for (LinearCoord lm : allMoves) {
      //gets best val
      int bestMoveVal = getOpponentBestMoveValue(lm, testModel, opponent, forWhom);
      if (bestMoveVal > maxDifference) {
        maxDifference = bestMoveVal;
        bestMoves.clear();
        bestMoves.add(lm);
      } else if (bestMoveVal == maxDifference) {
        bestMoves.add(lm);
      }
    }
    bestMoves.sort(new Utils.upperLefterCoordComparer());
    if (bestMoves.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(bestMoves);
  }

  /**
   * Gets the best move value for the opponent, given a move for the current player.
   * @param lm a legal move for the current player
   * @param testModel the model to search for moves in
   * @param opponent the opponent
   * @param forWhom the current player
   * @return The best move value (score of the game) for the opponent, given a move.
   */
  private Integer getOpponentBestMoveValue(LinearCoord lm, ReversiModel testModel,
                                                      Player opponent, Player forWhom) {
    ReversiModel tempModel = testModel.getModel();
    tempModel.placePiece(lm);
    ArrayList<LinearCoord> opponentLegalMoves = Utils.allLegalMoves(tempModel);
    ArrayList<Integer> bestOpponentMoves = new ArrayList<>();
    for (LinearCoord olm : opponentLegalMoves) {
      ReversiModel tempTempModel = tempModel.getModel();
      tempTempModel.placePiece(olm);

      int tempOpponentScore = tempTempModel.getPlayerScore(opponent);
      int tempMyScore = tempTempModel.getPlayerScore(forWhom);
      int tempDifference = tempMyScore - tempOpponentScore;
      bestOpponentMoves.add(tempDifference);
    }
    if (bestOpponentMoves.isEmpty()) {
      bestOpponentMoves.add(tempModel.getPlayerScore(forWhom));
    }
    bestOpponentMoves.sort(Comparator.naturalOrder());
    return bestOpponentMoves.get(0);
  }
}
