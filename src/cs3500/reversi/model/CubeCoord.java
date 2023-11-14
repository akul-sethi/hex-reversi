package cs3500.reversi.model;

/**
 * Represents a Cube coordinate in hexagonal boards. This makes it easier to see which coordinates
 * are in a row as compared to offset coordinates.
 */
final class CubeCoord implements LinearCoord {
  //Fields are public as they must exist by definition of the class.
  public final int q;
  public final int r;
  public final int s;

  /**
   * Creates a Cube coordinate with the given values.
   *
   * @param q The q value of this coordinate
   * @param r The r value of this coordinate
   * @param s The s value of this coordinate
   */
  CubeCoord(int q, int r, int s) throws IllegalArgumentException {
    if (q + r + s != 0) {
      throw new IllegalArgumentException("must sum to 0");
    }
    this.q = q;
    this.r = r;
    this.s = s;
  }

  /**
   * Creates a Cube coordinate from odd-r coordinates as described in the Overview section of the
   * README.txt.
   *
   * @param row    The row of this coordinate.
   * @param column The column of this coordinate.
   */
  CubeCoord(int row, int column) {
    this.q = column - (row - (row & 1)) / 2;
    this.r = row;
    this.s = -this.q - this.r;
  }

  @Override
  public boolean equals(Object o) {
    if (!(o instanceof CubeCoord)) {
      return false;
    }

    CubeCoord c = (CubeCoord) o;
    return c.q == this.q && c.s == this.s && c.r == this.r;
  }

  @Override
  public int hashCode() {
    return this.q * 7 + this.s * 11 + this.r * 13;
  }

  /**
   * Returns the odd-r row value of this coordinate.
   */
  public int row() {
    return this.r;
  }

  /**
   * Returns the odd-r column value of this coordinate.
   */
  public int column() {
    return this.q + (this.r - (this.r & 1)) / 2;
  }
}
