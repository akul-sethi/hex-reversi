package cs3500.reversi;

/**
 * Represents a player in a game of Reversi.
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
