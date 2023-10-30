package cs3500.reversi;

public class HumanPlayer implements Player {
  private String name;
  public HumanPlayer(String name) {
    this.name = name;
  }
  public String toString() {
    return this.name;
  }

  public boolean equals(Object o) {
    if(!(o instanceof HumanPlayer)) {
      return false;
    }
    HumanPlayer h = (HumanPlayer) o;
    return h.name.equals(this.name);
  }
}
