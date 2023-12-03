package cs3500.reversi.strategy;

import java.util.ArrayList;
import java.util.List;

import reversi.IPlayer;
import reversi.model.Hexagon;
import reversi.model.IReadonlyReversiModel;

/**
 * Class to represent the reversi strategy to capture as many pieces on a player's turn as possible.
 */
public class AsManyPiecesAsPossible extends AbstractReversiStrategy {

  @Override
  public List<Hexagon> chooseMoveWithPossibles(IReadonlyReversiModel model, IPlayer forWhom,
                                               List<Hexagon> possibles) {

    int maxPieces = 0;
    List<Hexagon> ans = new ArrayList<>();
    for (Hexagon h : possibles) {
      int numGained = model.getNumPiecesGainedForMove(h, forWhom.getHexagonState());
      if (numGained == maxPieces) {
        ans.add(h);
      } else if (numGained > maxPieces) {
        maxPieces = numGained;
        ans = new ArrayList<>();
        ans.add(h);
      }
    }
    return ans;
  }
}
