package cs3500.reversi.player;

import java.util.ArrayList;
import java.util.List;

import cs3500.reversi.view.InputObserver;

abstract  class AkulAbstractPlayer implements Player {
  protected final List<InputObserver> observers;
  protected final String name;

  AkulAbstractPlayer(String name) {
    this.observers = new ArrayList<>();
    this.name = name;
  }

  @Override
   public void addObserver(InputObserver observer) {
    this.observers.add(observer);
  }

  /**
   * Overrides the toString method.
   *
   * @return The string len 1 name of this player.
   */
  @Override
  public String toString() {
    return this.name;
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
    return a.name.equals(this.name);
  }

}
