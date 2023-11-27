package cs3500.reversi.strategy;

import java.util.ArrayList;
import java.util.Optional;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.Player;

/**
 * To represent a strategy that always capture the max possible tiles it can.
 */
public class CaptureMaxStrategy implements FallibleReversiStrategy {

  /**
   * Chooses a move, does this by searching through all legal moves and returning the move(s) that
   * capture the most spaces.
   *
   * @param model      The model to search for a move in.
   * @param forWhom    The player to look for a move for.
   * @param legalMoves The list of legal moves to be passed into the strategy. In all of our
   *                   strategies, if this list is empty, the strategy looks again for any legal
   *                   moves. This is because when combining strategies, you have to pass the prev.
   *                   strategies moves to the next strategy, and sometimes a strategy can return
   *                   zero valid moves.
   * @return The best move (captures the most spaces)
   */
  @Override
  public Optional<ArrayList<LinearCoord>> chooseMove(ReadOnlyReversiModel model, Player forWhom,
                                                     ArrayList<LinearCoord> legalMoves) {
    int maxDifference = 0;
    ArrayList<LinearCoord> bestMoves = new ArrayList<>();
    ReversiModel testModel = model.getModel();
    testModel.startGame();
    int startScore = testModel.getPlayerScore(forWhom);
    //if legal moves is empty, gets all the legal moves again.
    if (legalMoves.isEmpty()) {
      System.out.println(Utils.allLegalMoves(testModel));
      legalMoves = Utils.allLegalMoves(testModel);
    }
    //if still empty, no possible moves exist, so return empty.
    if (legalMoves.isEmpty()) {
      return Optional.empty();
    }
    for (LinearCoord move : legalMoves) {
      ReversiModel tempModel = model.getModel();
      tempModel.startGame();
      tempModel.placePiece(move);
      int tempScore = tempModel.getPlayerScore(forWhom);
      int difference = tempScore - startScore;
      if (difference > maxDifference) {
        maxDifference = difference;
        bestMoves.clear();
        bestMoves.add(move);
      } else if (difference == maxDifference) {
        bestMoves.add(move);
      }
    }
    bestMoves.sort(new Utils.UpperLefterCoordComparer());
    return Optional.of(bestMoves);
  }
}
