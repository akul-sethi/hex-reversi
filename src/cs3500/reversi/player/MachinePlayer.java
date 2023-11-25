package cs3500.reversi.player;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.strategy.CompleteReversiStrategyFromFallible;
import cs3500.reversi.strategy.FallibleReversiStrategy;
import cs3500.reversi.strategy.InfallibleReversiStrategy;

public class MachinePlayer extends AkulAbstractPlayer {

  private final InfallibleReversiStrategy strategy;

  public MachinePlayer(String name, FallibleReversiStrategy strategy) {
    super(name);

    this.strategy = new CompleteReversiStrategyFromFallible(strategy);
  }
  @Override
  public LinearCoord getMove(ReadOnlyReversiModel readOnlyModel) {
     return this.strategy.chooseMove(readOnlyModel, this);
  }

  @Override
  public boolean usesStrategy() {
    return true;
  }
}
