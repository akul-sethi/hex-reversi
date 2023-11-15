package cs3500.reversi.player;

import cs3500.reversi.strategy.FallibleReversiStrategy;

/**
 * To represent a player that can take in any FallibleStrategy.
 */
public class StrategyPlayer extends AbstractPlayer {

  /**
   * The constructor.
   *
   * @param name     The name to give the player
   * @param strategy The strategy to give the player
   */
  public StrategyPlayer(String name, FallibleReversiStrategy strategy) {
    super(name, strategy);
  }
}
