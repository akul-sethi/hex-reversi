package cs3500.reversi.strategy;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.player.Player;

/**
 * Represents a reversi strategy that will always return a move if it can.
 * If unable to, throws an IllegalStateException.
 */
public interface InfallibleReversiStrategy {
  /**
   * Returns a move chosen by the strategy, or throws an IllegalStateException.
   *
   * @param model   the model to look for a move in
   * @param forWhom the player to look for a move for
   * @return A move (probably the best move)
   * @throws IllegalStateException if no move can be returned.
   */
  LinearCoord chooseMove(ReadOnlyReversiModel model, Player forWhom) throws IllegalStateException;
}
