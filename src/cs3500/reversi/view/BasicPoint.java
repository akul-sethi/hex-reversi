package cs3500.reversi.view;

import cs3500.reversi.model.LinearCoord;

/**
 * A basic implementation of a LinearCoord.*/
final class BasicPoint implements LinearCoord {
  private final int row;
  private final int column;

  /**
   * Creates a BasicPoint with the given row and column coordinates.*/
  BasicPoint(int row, int column) {
    this.row = row;
    this.column = column;
  }

  @Override
  public int row() {
    return this.row;
  }

  @Override
  public int column() {
    return this.column;
  }
}
