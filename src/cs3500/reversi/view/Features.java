package cs3500.reversi.view;

import cs3500.reversi.model.LinearCoord;

/**
 * Represents Features that a game of Reversi must have. The view can send game-level events
 * to this object when it determines the player is requesting them, and then the object can behave
 * accordingly based on its rules and the state of the game.
 */
public interface Features {

  /**
   * A request from the user to place a piece at the given coordinate.
   *
   * @param coord The coordinate where the user wants to move.
   */
  void moveHere(LinearCoord coord);

  /**
   * A request from the user to pass their turn.
   */
  void pass();

}
