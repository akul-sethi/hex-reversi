package cs3500.reversi;

/**
 * To represent a Human player, has a string len 1 name.
 */
public final class HumanPlayer implements Player {
  private final String name;

  /**
   * Constructor for a Human Player.
   * @param name the name (str length 1) of the player, to represent their tiles in game.
   */
  public HumanPlayer(String name) {
    //Name can't be null
    assert name != null;
    //Name must be length 1
    assert name.length() == 1;
    this.name = name;
  }

  /**
   * Overrides the toString method.
   * @return The string len 1 name of this player.
   */
  @Override
  public String toString() {
    return this.name;
  }

  /**
   * Overrides the equality for players.
   * Players are equal if they have the same String name.
   * @param o other object to compare to.
   * @return True if equal objects
   */
  @Override
  public boolean equals(Object o) {
    if(!(o instanceof HumanPlayer)) {
      return false;
    }
    HumanPlayer h = (HumanPlayer) o;
    return h.name.equals(this.name);
  }

  /**
   * Overrides the hashCode for players.
   * @return the hashCode of the player's string, since that is what defines the player.
   */
  @Override
  public int hashCode() {
    return this.name.hashCode();
  }
}
