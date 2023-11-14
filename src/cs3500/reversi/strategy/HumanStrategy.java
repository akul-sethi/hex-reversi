package cs3500.reversi.strategy;

import java.util.ArrayList;
import java.util.Optional;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.player.Player;

/**
 * To represent a human player's strategy. For our purposes, the view will deal with any human
 * player's moves, so this strategy can always return empty.
 */
public class HumanStrategy implements FallibleReversiStrategy {

  /**
   * The chooseMove method, returns null.
   * @param model the model to look for a move in
   * @param forWhom the player to look for a move for
   * @param legalMoves the passed in legal moves to scan.
   * @return empty.
   */
  @Override
  public Optional<ArrayList<LinearCoord>> chooseMove(ReadOnlyReversiModel model, Player forWhom,
                                ArrayList<LinearCoord> legalMoves) {
    return Optional.empty();
  }
}
