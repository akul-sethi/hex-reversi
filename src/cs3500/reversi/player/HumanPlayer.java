package cs3500.reversi.player;

import cs3500.reversi.strategy.HumanStrategy;

/**
 * A concrete implementation of a Human Player which selecteds
 * its own moves.*/
public final class HumanPlayer extends AbstractPlayer {

  /**
   * The constructor, initializes name and strategy to those provided.
   *
   * @param name The name to give this player
   */
  public HumanPlayer(String name) {
    super(name, new HumanStrategy());
  }
}
