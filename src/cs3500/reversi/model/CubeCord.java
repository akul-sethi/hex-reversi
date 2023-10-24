package cs3500.reversi.model;

import java.util.Objects;

/**
 * Represents a Cube coordinate in hexagonal boards.*/
public class CubeCord {
  //Fields are public as they must exist by definition of the class.
  public final int q;
  public final int r;
  public final int s;

  /**
   * Creates a Cube coordinate with the given values.
   */
  public CubeCord(int q, int r, int s) {
    this.q = q;
    this.r = r;
    this.s = s;
  }

  @Override
  public boolean equals(Object o) {
    if(!(o instanceof CubeCord)) return false;

    CubeCord c = (CubeCord) o;
    return c.q == this.q && c.s == this.s && c.r == this.r;
  }

  @Override
  public int hashCode() {
    return this.q * 7 + this.s * 11 + this.r * 13;
  }
}
