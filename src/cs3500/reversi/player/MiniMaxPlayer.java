package cs3500.reversi.player;

import cs3500.reversi.strategy.FallibleReversiStrategy;
import cs3500.reversi.strategy.MiniMaxStrategy;

public class MiniMaxPlayer extends AbstractPlayer {
  public MiniMaxPlayer(String name) {
    super(name, new MiniMaxStrategy());
  }
}
