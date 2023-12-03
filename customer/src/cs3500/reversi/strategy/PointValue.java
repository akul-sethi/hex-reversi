package cs3500.reversi.strategy;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.Player;

/**
 * To assign a value to each point on a reversi board, to be used with the miniMax strategy.
 */
public interface PointValue {
  /**
   * Gets the point value for a designated point.
   *
   * @param coord the coordinate to inspect
   * @param model the model with the board to inspect
   * @return the calculated point value for the point.
   */
  int getPointValue(LinearCoord coord, ReversiModel model, Player forWhom);
}
