package cs3500.reversi.model;

/**
 * Represents a Cube coordinate in hexagonal boards.*/
class CubeCoord {
  //Fields are public as they must exist by definition of the class.
  public final int q;
  public final int r;
  public final int s;

  /**
   * Creates a Cube coordinate with the given values.
   */
  CubeCoord(int q, int r, int s) throws IllegalArgumentException {
    if(q + r + s != 0) {
      throw new IllegalArgumentException("must sum to 0");
    }
    this.q = q;
    this.r = r;
    this.s = s;
  }

  CubeCoord(int row, int column) {
    this.q = column - (row  - (row&1)) / 2;
    this.r = row;
    this.s = -this.q - this.r;
  }

  @Override
  public boolean equals(Object o) {
    if(!(o instanceof CubeCoord)) return false;

    CubeCoord c = (CubeCoord) o;
    return c.q == this.q && c.s == this.s && c.r == this.r;
  }

  @Override
  public int hashCode() {
    return this.q * 7 + this.s * 11 + this.r * 13;
  }

  public int row() {
    return this.r + (this.q - (this.q&1));
  }

  public int column() {
    return this.q;
  }

}
