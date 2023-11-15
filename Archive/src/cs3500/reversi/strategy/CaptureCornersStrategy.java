package cs3500.reversi.strategy;

import java.util.ArrayList;
import java.util.Optional;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.Player;

/**
 * To represent a strategy that will capture the corners if it can.
 */
public class CaptureCornersStrategy implements FallibleReversiStrategy {

  /**
   * Finds the moves that capture corners. If it can't find any, it returns the legalMoves passed
   * into the strategy, as to avoid destroying previous progress in narrowing down a move choice.
   *
   * @param model      The model to search for a move in.
   * @param forWhom    The player to look for a move for.
   * @param legalMoves The list of legal moves to be passed into the strategy. In all of our
   *                   strategies, if this list is empty, the strategy looks again for any legal
   *                   moves. This is because when combining strategies, you have to pass the prev.
   *                   strategies moves to the next strategy, and sometimes a strategy can return
   *                   zero valid moves.
   * @return The moves that capture corner pieces.
   */
  @Override
  public Optional<ArrayList<LinearCoord>> chooseMove(ReadOnlyReversiModel model, Player forWhom,
                                                     ArrayList<LinearCoord> legalMoves) {
    ReversiModel testModel = model.getModel();
    ArrayList<LinearCoord> bestMoves = new ArrayList<>();
    if (legalMoves.isEmpty()) {
      legalMoves = Utils.allLegalMoves(testModel);
    }
    if (legalMoves.isEmpty()) {
      return Optional.empty();
    }
    //if the legal move is a corner, add it to bestmoves
    ArrayList<LinearCoord> corners = Utils.getCorners(testModel);
    for (LinearCoord move : legalMoves) {
      if (corners.contains(move)) {
        bestMoves.add(move);
      }
    }
    //if there are some best moves (ones that capture corners), return them
    if (!bestMoves.isEmpty()) {
      bestMoves.sort(new Utils.upperLefterCoordComparer());
      return Optional.of(bestMoves);
    }
    //else, return the legal moves passed in
    return Optional.of(legalMoves);
  }
}
