package cs3500.reversi.controller;

import cs3500.reversi.model.LinearCoord;

/**
 * Represents an Observer which observes input-sending components. This allows a View or Player to
 * send move requests whenever they want and then the observer can behave accordingly based on its
 * rules and the state of the game.
 */
public interface InputObserver {

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
