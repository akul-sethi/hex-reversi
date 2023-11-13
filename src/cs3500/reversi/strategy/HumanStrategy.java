package cs3500.reversi.strategy;

import java.util.Optional;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.player.Player;

public class HumanStrategy implements FallibleReversiStrategy {
  @Override
  public Optional<LinearCoord> chooseMove(ReadOnlyReversiModel model, Player forWhom) {
    return Optional.empty();
  }
}
