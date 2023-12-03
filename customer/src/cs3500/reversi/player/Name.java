package cs3500.reversi.player;

/**
 * Represents a name for a Player. Restricts the possibilities to just X and O as there are
 * currently only two Player objects in a game.
 */
public enum Name {
  X("X"), O("O");
  private final String name;

  Name(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return this.name;
  }

}
