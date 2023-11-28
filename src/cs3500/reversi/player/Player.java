package cs3500.reversi.player;

import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.controller.InputObserver;

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
   * Starts this player's turn. If it is a machine this may mean that it uses some strategy to
   * select a move and then requests it from its observer. For a Human this may do nothing.
   *
   * @param model the readonly model the Player can use to decide its move.
   */

  void startTurn(ReadOnlyReversiModel model);

  /**
   * Adds an InputObserver to this player so that it can request moves it wants to make.
   *
   * @throws NullPointerException If observer is <code>null</code>
   */
  void addObserver(InputObserver observer);
}
