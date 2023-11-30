package cs3500.reversi.strategy;

import java.util.ArrayList;
import java.util.Optional;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.Player;

/**
 * A class to convert any fallible reversi strategy into an infallible (complete) one.
 */
public class CompleteReversiStrategyFromFallible implements InfallibleReversiStrategy {

  //the strategy to attempt to get a move from
  private final FallibleReversiStrategy strategyToTry;

  /**
   * The constructor, sets the strategy.
   *
   * @param strategyToTry the strategy which this strategy will try to get a move from.
   */
  public CompleteReversiStrategyFromFallible(FallibleReversiStrategy strategyToTry) {
    this.strategyToTry = strategyToTry;
  }

  /**
   * Chooses a move, using the strategy.
   *
   * @param model   the model to look for a move in
   * @param forWhom the player to look for a move for
   * @return The move it finds using the strategy, or
   */
  @Override
  public LinearCoord chooseMove(ReadOnlyReversiModel model, Player forWhom)
          throws IllegalStateException {
    ReversiModel testModel = model.getModel();
    testModel.startGame();
    Optional<ArrayList<LinearCoord>> maybeAns = this.strategyToTry.chooseMove(testModel, forWhom,
            new ArrayList<>());
    if (maybeAns.isPresent()) {
      return maybeAns.get().get(0);
    }
    throw new IllegalStateException("The strategy given can't come up with a move!");
  }
}
