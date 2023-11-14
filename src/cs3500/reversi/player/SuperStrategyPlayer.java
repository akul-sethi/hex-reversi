package cs3500.reversi.player;

import cs3500.reversi.strategy.AvoidNextToCornersStrategy;
import cs3500.reversi.strategy.CaptureCornersStrategy;
import cs3500.reversi.strategy.CaptureMaxStrategy;
import cs3500.reversi.strategy.FallibleReversiStrategy;
import cs3500.reversi.strategy.MiniMaxStrategy;
import cs3500.reversi.strategy.TryTwo;

public class SuperStrategyPlayer extends AbstractPlayer {
  static final FallibleReversiStrategy SuperStrategy =
          new TryTwo(new CaptureCornersStrategy(),
                  new TryTwo(new AvoidNextToCornersStrategy(),
                  new TryTwo(new MiniMaxStrategy(),
                  new CaptureMaxStrategy())));
  public SuperStrategyPlayer(String name) {
    super(name, SuperStrategy);
  }
}
