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
    List<Hexagon> possibles = new ArrayList<>();
    for(LinearCoord c : legalMoves) {
      possibles.add(AdapterUtils.cubeCoordToHexagon(new CubeCoord(c.row(), c.column())));
    }
    return Optional.of(delegate.chooseMoveWithPossibles(new ReadOnlyModelAdapter(model),
            new PlayerAdapter(forWhom), possibles));
  }
}
