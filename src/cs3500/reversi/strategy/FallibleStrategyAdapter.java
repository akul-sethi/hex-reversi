package cs3500.reversi.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import cs3500.provider.model.Hexagon;
import cs3500.provider.strategy.IReversiStrategy;
import cs3500.reversi.model.AdapterUtils;
import cs3500.reversi.model.CubeCoord;
import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ModelAdapter;
import cs3500.reversi.model.ReadOnlyModelAdapter;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.player.Player;
import cs3500.reversi.player.PlayerAdapter;

public class FallibleStrategyAdapter implements FallibleReversiStrategy{

  private final IReversiStrategy delegate;
  FallibleStrategyAdapter(IReversiStrategy adaptee) {
    this.delegate = adaptee;
  }

  @Override
  public Optional<ArrayList<LinearCoord>> chooseMove(ReadOnlyReversiModel model, Player forWhom, ArrayList<LinearCoord> legalMoves) {
    if (legalMoves.isEmpty()) {
      legalMoves = Utils.allLegalMoves(model.getModel());
    }
    if (legalMoves.isEmpty()) {
      return Optional.empty();
    }
    List<Hexagon> possibles = new ArrayList<>();
    for(LinearCoord c : legalMoves) {
      possibles.add(AdapterUtils.cubeCoordToHexagon(new CubeCoord(c.row(), c.column())));
    }
    List<Hexagon> deemedMoves = delegate.chooseMoveWithPossibles(new ReadOnlyModelAdapter(model.getModel()),
            new PlayerAdapter(forWhom), possibles);
    ArrayList<LinearCoord> bestMoves = new ArrayList<>();
    for (Hexagon hex : deemedMoves) {
      bestMoves.add(AdapterUtils.hexagonToLinearCoord(hex));
    }
    if (bestMoves.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(bestMoves);
  }
}
