package cs3500.reversi.strategy;

import java.util.ArrayList;
import java.util.Optional;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.Player;

public class TryTwo implements FallibleReversiStrategy {
  FallibleReversiStrategy first, second;

  public TryTwo(FallibleReversiStrategy first, FallibleReversiStrategy second) {
    this.first = first;
    this.second = second;
  }
  @Override
  public Optional<ArrayList<LinearCoord>> chooseMove(ReadOnlyReversiModel model, Player forWhom,
                                                     ArrayList<LinearCoord> legalMoves) {
    ReversiModel testModel = model.getModel();
    if (legalMoves.isEmpty()) {
      legalMoves = Utils.allLegalMoves(testModel);
    }
    Optional<ArrayList<LinearCoord>> ans = this.first.chooseMove(model, forWhom, legalMoves);
    if (ans.isPresent()) {
      if (ans.get().size() > 1) {
        return this.second.chooseMove(model, forWhom, ans.get());
      }
      return ans;
    }
    return this.second.chooseMove(model, forWhom, new ArrayList<LinearCoord>());
  }
}
