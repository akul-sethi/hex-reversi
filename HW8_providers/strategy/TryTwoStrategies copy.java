package reversi.strategy;

import java.util.List;

import reversi.IPlayer;
import reversi.model.Hexagon;
import reversi.model.IReadonlyReversiModel;

/**
 * Class that represents the reversi strategy of using two strategies. This class allows reversi
 * strategies to be chained together.
 */
public class TryTwoStrategies extends AbstractReversiStrategy {

  // represents the first IReversiStrategy to be used in the chooseMoveWithPossibles method
  IReversiStrategy strategy1;

  // represents the second IReversiStrategy to be used in the chooseMoveWithPossibles method
  IReversiStrategy strategy2;

  /**
   * Constructor for the TryTwoStrategies reversi strategy class.
   * @param strategy1 first IReversiStrategy to try
   * @param strategy2 second IReversiStrategy to try after strategy1
   * @throws IllegalArgumentException is either strategy is null
   */
  public TryTwoStrategies(IReversiStrategy strategy1, IReversiStrategy strategy2) {
    if (strategy1 == null || strategy2 == null) {
      throw new IllegalArgumentException("list of strategies cannot be null");
    }
    this.strategy1 = strategy1;
    this.strategy2 = strategy2;
  }

  @Override
  public List<Hexagon> chooseMoveWithPossibles(IReadonlyReversiModel model, IPlayer forWhom,
                                               List<Hexagon> possibles) {
    // use strategy 1 on possibles
    List<Hexagon> answer = this.strategy1.chooseMoveWithPossibles(model, forWhom, possibles);

    // if no valid moves with strategy 1 only use strategy 2 on possibles
    if (answer.isEmpty()) {
      answer = strategy2.chooseMoveWithPossibles(model, forWhom, possibles);
    }
    // if valid moves with strategy 1, apply strategy 2 to this output
    else {
      List<Hexagon> answer2 = strategy2.chooseMoveWithPossibles(model, forWhom, answer);
      if (answer2.isEmpty()) {
        return answer;
      } else {
        return answer2;
      }
    }

    // if no valid moves with either strategy, will return empty list of hexagons
    return answer;
  }
}
