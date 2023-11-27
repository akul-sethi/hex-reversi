package cs3500.reversi.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import cs3500.reversi.view.InputObserver;

abstract  class AkulAbstractPlayer implements Player {
  protected Optional<InputObserver> observer;
  protected final Name name;

  AkulAbstractPlayer(Name name) {
    assert name != null;
    this.observer = Optional.empty();
    this.name = name;
  }

  @Override
   public void addObserver(InputObserver observer) {
    this.observer = Optional.of(observer);
  }

  /**
   * Overrides the toString method.
   *
   * @return The string len 1 name of this player.
   */
  @Override
  public String toString() {
    return this.name.toString();
  }

  /**
   * Overrides the equality for players.
   * Players are equal if they have the same String name.
   *
   * @param o other object to compare to.
   * @return True if equal objects
   */
  @Override
  public boolean equals(Object o) {
    if (!(o instanceof AkulAbstractPlayer)) {
      return false;
    }
    AkulAbstractPlayer a = (AkulAbstractPlayer) o;
    return this.toString().equals(o.toString());
  }

  @Override
  public int hashCode() {
    return this.name.name().hashCode();
  }
}
