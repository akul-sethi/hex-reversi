package cs3500.reversi.model;

import cs3500.reversi.model.LinearCoord;

/**
 * A basic implementation of a LinearCoord.
 */
public final class BasicPoint implements LinearCoord {
  private final int row;
  private final int column;

  /**
   * Creates a BasicPoint with the given row and column coordinates.
   */
  public BasicPoint(int row, int column) {
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

  /**
   * Overrides the toString method, output is row and column in point form.
   *
   * @return The row and column in a coordinate form.
   */
  @Override
  public String toString() {
    return "(" + this.row() + ", " + this.column() + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof LinearCoord)) {
      return false;
    }

    LinearCoord c = (LinearCoord) o;
    return c.row() == this.row() && c.column() == this.column();
  }

  @Override
  public int hashCode() {
    return this.row() * 7 + this.column * 11;
  }
}
