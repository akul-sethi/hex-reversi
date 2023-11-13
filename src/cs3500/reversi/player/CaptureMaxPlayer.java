package cs3500.reversi.player;

import cs3500.reversi.strategy.CaptureMaxStrategy;

public class CaptureMaxPlayer extends AbstractPlayer {
  public CaptureMaxPlayer(String name) {
    super(name, new CaptureMaxStrategy());
  }
}
