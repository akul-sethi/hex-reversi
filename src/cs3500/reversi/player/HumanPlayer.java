package cs3500.reversi.player;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.strategy.HumanStrategy;
import cs3500.reversi.view.InputObserver;

/**

 * To represent a human player which uses a view for input rather than a strategy.
 */
public class HumanPlayer extends AkulAbstractPlayer {

  /**
   * The constructor, initializes name and strategy to those provided.
   *
   * @param name The name to give this player
   */
  public HumanPlayer(Name name) {
      super(name);
  }

  @Override
  public void startTurn(ReadOnlyReversiModel model) {
    //DO NOTHING
  }

}
