package cs3500.provider.strategy;

import java.util.ArrayList;
import java.util.List;


import cs3500.provider.IPlayer;
import cs3500.provider.model.Hexagon;
import cs3500.provider.model.IReadonlyReversiModel;

/**
 * Class to represent the reversi strategy of avoiding moves that are next to corners.
 */
public class AvoidNextToCorners extends AbstractReversiStrategy {

  @Override
  public List<Hexagon> chooseMoveWithPossibles(IReadonlyReversiModel model, IPlayer forWhom,
                                               List<Hexagon> possibles) {

    List<Hexagon> answer = new ArrayList<>();
    for (Hexagon h: possibles) {
      if (!model.isHexagonNextToCorner(h)) {
        answer.add(h);
      }
    }
    return answer;
  }
}
