package cs3500.reversi.player;

import cs3500.reversi.strategy.AvoidNextToCornersStrategy;
import cs3500.reversi.strategy.CaptureCornersStrategy;
import cs3500.reversi.strategy.CaptureMaxStrategy;
import cs3500.reversi.strategy.FallibleReversiStrategy;
import cs3500.reversi.strategy.MiniMaxStrategy;
import cs3500.reversi.strategy.TryTwo;

/**
 * An example player that always use a "Super" strategy, which we made, that is a combination
 * of 4 strategies. First, it captures the corners if possible, then avoids the tiles next to
 * corners. Then it uses the minimax strategy, and then tiebreaks with capture max
 */
public class SuperStrategyPlayer extends AbstractPlayer {
  //the strategy this player type will always use. These strategies could be combined in any order,
  //and would easily work. The code is completely modular.
  private static final FallibleReversiStrategy SuperStrategy =
          new TryTwo(new CaptureCornersStrategy(),
                  new TryTwo(new AvoidNextToCornersStrategy(),
                  new TryTwo(new MiniMaxStrategy(),
                  new CaptureMaxStrategy())));

  /**
   * The constructor, only takes a name, since strategy is predetermined.
   * @param name The name for the player a one length string
   */
  public SuperStrategyPlayer(String name) {
    super(name, SuperStrategy);
  }
}
