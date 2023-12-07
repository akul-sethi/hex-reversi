package cs3500.reversi.strategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import cs3500.provider.model.Hexagon;
import cs3500.provider.strategy.IReversiStrategy;
import cs3500.reversi.model.AdapterUtils;
import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReadOnlyModelAdapter;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.player.Player;
import cs3500.reversi.player.PlayerAdapter;

/**
 * Adapts a provider strategy into one of ours, a FallibleReversiStrategy, so that our
 * implementation of players can use them.
 */
public class FallibleStrategyAdapter implements FallibleReversiStrategy {

  private final IReversiStrategy delegate;

  /**
   * Creates a FallibleStrategyAdapter given an IReversiStrategy.
   */
  public FallibleStrategyAdapter(IReversiStrategy adaptee) {
    this.delegate = adaptee;
  }

  @Override
  public Optional<ArrayList<LinearCoord>> chooseMove(ReadOnlyReversiModel model, Player forWhom, ArrayList<LinearCoord> legalMoves) {
    ArrayList<LinearCoord> output = new ArrayList<>();
    List<Hexagon> delegateOutput = delegate.chooseMove(new ReadOnlyModelAdapter(model),
            new PlayerAdapter(forWhom));
    for(Hexagon h : delegateOutput) {
      output.add(AdapterUtils.hexagonToLinearCoord(h));

    }
    return Optional.of(output);
  }
}
