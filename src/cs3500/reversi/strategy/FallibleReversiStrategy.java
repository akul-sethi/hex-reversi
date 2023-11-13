package cs3500.reversi.strategy;

import java.util.ArrayList;
import java.util.Optional;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.player.Player;

public interface FallibleReversiStrategy {
  Optional<LinearCoord> chooseMove(ReadOnlyReversiModel model, Player forWhom);
}
