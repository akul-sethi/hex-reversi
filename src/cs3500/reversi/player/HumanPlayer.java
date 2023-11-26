package cs3500.reversi.player;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.strategy.HumanStrategy;
import cs3500.reversi.view.InputObserver;

/**

 * To represent a human player, without a real strategy.
 */
public class HumanPlayer extends AkulAbstractPlayer {

  /**
   * The constructor, initializes name and strategy to those provided.
   *
   * @param name The name to give this player
   */
  public HumanPlayer(String name) {
      super(name);
  }

  @Override
  public LinearCoord getMove(ReadOnlyReversiModel readOnlyModel) {
    throw new IllegalStateException("This is a human player");
  }

  @Override
  public void startTurn(ReadOnlyReversiModel model) {
    //DO NOTHING
  }

  @Override
  public boolean usesStrategy() {
    return false;
  }
}
