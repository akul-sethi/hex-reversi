package cs3500.reversi.player;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.strategy.HumanStrategy;

/**
 * To represent a Human player, has a string len 1 name.
 */
public final class HumanPlayer extends AbstractPlayer {
  /**
   * Constructor for a Human Player.
   * @param name the name (str length 1) of the player, to represent their tiles in game.
   */
  public HumanPlayer(String name) {
    super(name, new HumanStrategy());
  }
}
