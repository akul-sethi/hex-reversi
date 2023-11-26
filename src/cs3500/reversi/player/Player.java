package cs3500.reversi.player;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.view.InputObserver;

/**
 * Represents a player in a game of Reversi. Necessary to describe the behavior of all types of
 * players (AI, Human, etc) which can play the game.
 */
public interface Player {

  /**
   * Returns this player as a string.
   */
  String toString();

  /**
   * Compares two players for equality.
   *
   * @param o Other object to compare.
   * @return True if both players are equal.
   */
  boolean equals(Object o);

  /**
   * Gets the move from the player given the boardState.
   *
   * @param readOnlyModel the readonly model to pass into the get move function, by which the
   *                      strategy will search to determine what move to make.
   * @return The best move determined by the player's strategy.
   * @throws IllegalStateException If this player does not use a strategy or it cannot find a move
   */
  LinearCoord getMove(ReadOnlyReversiModel readOnlyModel) throws IllegalStateException;

  void startTurn(ReadOnlyReversiModel model);

  void addObserver(InputObserver observer);

  boolean usesStrategy();
}
