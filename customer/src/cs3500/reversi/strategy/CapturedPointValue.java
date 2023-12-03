package cs3500.reversi.strategy;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.Player;

/**
 * To represent point value determined by whether the tile is captured by player.
 */
public class CapturedPointValue implements PointValue {
  @Override
  public int getPointValue(LinearCoord coord, ReversiModel model, Player forWhom) {
    if (model.playerAt(coord) == null) {
      return 0;
    }
    return model.playerAt(coord).equals(forWhom) ? 1 : 0;
  }
}
