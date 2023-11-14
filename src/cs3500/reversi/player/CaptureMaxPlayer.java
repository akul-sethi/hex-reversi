package cs3500.reversi.player;

import cs3500.reversi.strategy.CaptureMaxStrategy;

/**
 * To represent a player that always uses the captureMax strategy.
 */
public class CaptureMaxPlayer extends AbstractPlayer {

  /**
   * The constructor, doesn't need a strategy, since it's predetermined.
   * @param name the name to give the player
   */
  public CaptureMaxPlayer(String name) {
    super(name, new CaptureMaxStrategy());
  }
}
