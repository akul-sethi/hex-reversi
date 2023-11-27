package cs3500.reversi.player;

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
