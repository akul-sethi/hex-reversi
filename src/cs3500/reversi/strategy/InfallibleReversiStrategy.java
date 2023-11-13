package cs3500.reversi.strategy;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.player.Player;

public interface InfallibleReversiStrategy {
  LinearCoord chooseMove(ReadOnlyReversiModel model, Player forWhom);
}
