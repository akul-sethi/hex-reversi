package cs3500.reversi.player;

import cs3500.reversi.strategy.FallibleReversiStrategy;

public class StrategyPlayer extends AbstractPlayer {
  public StrategyPlayer(String name, FallibleReversiStrategy strategy) {
    super(name, strategy);
  }
}
