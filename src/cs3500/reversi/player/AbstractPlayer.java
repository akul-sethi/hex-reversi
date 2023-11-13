package cs3500.reversi.player;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.strategy.CompleteReversiStrategyFromFallible;
import cs3500.reversi.strategy.FallibleReversiStrategy;
import cs3500.reversi.strategy.InfallibleReversiStrategy;

public abstract class AbstractPlayer implements Player {
  private final String name;
  InfallibleReversiStrategy strategy;
  AbstractPlayer(String name, FallibleReversiStrategy strategy) {
    assert strategy != null;
    assert name != null;
    assert name.length() == 1;
    this.strategy = new CompleteReversiStrategyFromFallible(strategy);
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
    if (!(o instanceof AbstractPlayer)) {
      return false;
    }
    AbstractPlayer a = (AbstractPlayer) o;
    return a.name.equals(this.name);
  }

  @Override
  public LinearCoord getMove(ReadOnlyReversiModel readOnlyModel) {
    return this.strategy.chooseMove(readOnlyModel, this);
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
