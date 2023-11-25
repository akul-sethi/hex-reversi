package cs3500.reversi.player;

import cs3500.reversi.strategy.MiniMaxStrategy;
import cs3500.reversi.view.InputObserver;

/**
 * A player that always uses the miniMax strategy.
 */
public class MiniMaxPlayer extends AbstractPlayer {

  /**
   * The constructor, only takes in name because strategy is predetermined.
   *
   * @param name the name to give the player
   */
  public MiniMaxPlayer(String name) {
    super(name, new MiniMaxStrategy());
  }

  @Override
  public void addObserver(InputObserver observer) {

  }

  @Override
  public boolean usesStrategy() {
    return false;
  }
}
