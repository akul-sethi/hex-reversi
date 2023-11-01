package cs3500.reversi;

/**
 * Represents a player in a game of Reversi. Necessary to describe the behavior of all types of
 * players (AI, Human, etc) which can play the game.
 */
public interface Player {

  /**
   * Returns this player as a string.
   */
  public String toString();

  /**
   * Compares two players for equality.
   *
   * @param o Other object to compare.
   * @return True if both players are equal.
   */
  public boolean equals(Object o);
}
