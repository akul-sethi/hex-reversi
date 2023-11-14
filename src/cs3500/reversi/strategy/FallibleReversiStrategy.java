package cs3500.reversi.strategy;

import java.util.ArrayList;
import java.util.Optional;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.player.Player;

/**
 * Represents a strategy that doesn't always return a move. The purpose of this is to make
 * strategies combinable without throwing errors.
 */
public interface FallibleReversiStrategy {
  /**
   * Returns a move if it can find one.
   * @param model The model to search for a move in.
   * @param forWhom The player to look for a move for.
   * @param legalMoves The list of legal moves to be passed into the strategy. In all of our
   *                   strategies, if this list is empty, the strategy looks again for any legal
   *                   moves. This is because when combining strategies, you have to pass the prev.
   *                   strategies moves to the next strategy, and sometimes a strategy can return
   *                   zero valid moves.
   * @return An optional list of moves
   */
  Optional<ArrayList<LinearCoord>> chooseMove(ReadOnlyReversiModel model, Player forWhom,
                                              ArrayList<LinearCoord> legalMoves);
}
