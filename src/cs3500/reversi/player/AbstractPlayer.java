package cs3500.reversi.player;

import java.util.Objects;
import java.util.Optional;

import cs3500.reversi.controller.InputObserver;

public abstract  class AbstractPlayer implements Player {
  protected Optional<InputObserver> observer;
  protected final Name name;

  /**
   * Creates an abstract Player object with given name.
   * @throws NullPointerException If the given name is <code>null</code>*/
  public AbstractPlayer(Name name) {
    this.observer = Optional.empty();
    Objects.requireNonNull(name);
    this.name = name;
  }

  @Override
   public void addObserver(InputObserver observer) {
    Objects.requireNonNull(observer);
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
    if (!(o instanceof AbstractPlayer)) {
      return false;
    }
    AbstractPlayer a = (AbstractPlayer) o;
    return a.name.equals(this.name);
  }

  @Override
  public int hashCode() {
    return this.name.name().hashCode();
  }
}
