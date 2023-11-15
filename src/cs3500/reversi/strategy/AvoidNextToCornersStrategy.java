package cs3500.reversi.strategy;

import java.util.ArrayList;
import java.util.Optional;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.Player;

/**
 * To represent a strategy that avoids capturing tiles adjacent to corners.
 */
public class AvoidNextToCornersStrategy implements FallibleReversiStrategy {

  /**
   * Returns the moves from legalMoves that aren't adjacent to corners.
   *
   * @param model      The model to search for a move in.
   * @param forWhom    The player to look for a move for.
   * @param legalMoves The list of legal moves to be passed into the strategy. In all of our
   *                   strategies, if this list is empty, the strategy looks again for any legal
   *                   moves. This is because when combining strategies, you have to pass the prev.
   *                   strategies moves to the next strategy, and sometimes a strategy can return
   *                   zero valid moves.
   * @return The moves that aren't adjacent to corners from legalMoves. Or nothing.
   */
  @Override
  public Optional<ArrayList<LinearCoord>> chooseMove(ReadOnlyReversiModel model, Player forWhom,
                                                     ArrayList<LinearCoord> legalMoves) {
    ReversiModel testModel = model.getModel();
    if (legalMoves.isEmpty()) {
      legalMoves = Utils.allLegalMoves(testModel);
    }
    ArrayList<LinearCoord> goodMoves = new ArrayList<>();
    ArrayList<LinearCoord> nextToCorners = Utils.getNextToCorners(testModel);
    for (LinearCoord lm : legalMoves) {
      if (!nextToCorners.contains(lm)) {
        goodMoves.add(lm);
      }
    }
    if (goodMoves.isEmpty()) {
      return Optional.empty();
    }
    goodMoves.sort(new Utils.upperLefterCoordComparer());
    return Optional.of(goodMoves);
  }
}
