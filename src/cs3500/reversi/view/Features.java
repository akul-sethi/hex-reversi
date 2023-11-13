package cs3500.reversi.view;

import cs3500.reversi.model.LinearCoord;

/**
 * Represents Features that a view should have. */
public interface Features {

  void previewMove(int row, int column);
  void moveHere(LinearCoord coord);
  void move();


}
