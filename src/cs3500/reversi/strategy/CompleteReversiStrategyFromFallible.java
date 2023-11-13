package cs3500.reversi.strategy;

import java.util.ArrayList;
import java.util.Optional;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.Player;

public class CompleteReversiStrategyFromFallible implements InfallibleReversiStrategy {
  FallibleReversiStrategy strategyToTry;
  public CompleteReversiStrategyFromFallible(FallibleReversiStrategy strategyToTry) {
    this.strategyToTry = strategyToTry;
  }
  @Override
  public LinearCoord chooseMove(ReadOnlyReversiModel model, Player forWhom) {
    Optional<ArrayList<LinearCoord>> maybeAns = this.strategyToTry.chooseMove(model, forWhom,
            new ArrayList<>());
    if (maybeAns.isPresent()) {
      return maybeAns.get().get(0);
    }
    throw new IllegalStateException("This strategy can't choose a move!");
  }
}
