package cs3500.reversi.player;

import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.strategy.CompleteReversiStrategyFromFallible;
import cs3500.reversi.strategy.FallibleReversiStrategy;
import cs3500.reversi.strategy.InfallibleReversiStrategy;

/**
 * A concrete implementation of a Player which uses a strategy rather than human input.
 */
public class MachinePlayer extends AbstractPlayer {

  private final InfallibleReversiStrategy strategy;

  /**
   * Creates a MachinePlayer with the given name which uses the given strategy to select its moves.
   */
  public MachinePlayer(Name name, FallibleReversiStrategy strategy) {
    super(name);

    this.strategy = new CompleteReversiStrategyFromFallible(strategy);
  }

  @Override
  public void startTurn(ReadOnlyReversiModel model) {
    /**try {
      Thread.sleep(100);
    } catch (InterruptedException e) {
      // do nothing
    }**/
    this.observer.ifPresent((obs -> {
      try {
        obs.moveHere(this.strategy.chooseMove(model.getModel(), this));
      } catch (IllegalStateException e) {
        obs.pass();
      }
    }));
  }

}
