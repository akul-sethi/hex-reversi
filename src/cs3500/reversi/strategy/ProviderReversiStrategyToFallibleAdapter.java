package cs3500.reversi.strategy;

import java.util.ArrayList;
import java.util.Optional;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.Player;
import cs3500.provider.strategy.IReversiStrategy;

public final class ProviderReversiStrategyToFallibleAdapter implements FallibleReversiStrategy {
  private final IReversiStrategy adaptee;

  public ProviderReversiStrategyToFallibleAdapter(IReversiStrategy adaptee) {
    this.adaptee = adaptee;
  }

  @Override
  public Optional<ArrayList<LinearCoord>> chooseMove(ReadOnlyReversiModel model, Player forWhom, ArrayList<LinearCoord> legalMoves) {
    ReversiModel testModel = model.getModel();
    testModel.startGame();
    //if legal moves is empty, gets all the legal moves again.
    if (legalMoves.isEmpty()) {
      legalMoves = Utils.allLegalMoves(testModel);
    }
    //if still empty, no possible moves exist, so return empty.
    if (legalMoves.isEmpty()) {
      return Optional.empty();
    }
    return this.adaptee.chooseMoveWithPossibles(model, legalMoves)
  }
}
