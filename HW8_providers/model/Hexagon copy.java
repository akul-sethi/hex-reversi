package reversi.model;

import java.util.Objects;

/**
 * Class to represent a Hexagon.
 * A Hexagon is represented by its 3 coordinates, s, q, and r, to define its position
 * within a hexagonal board.
 */
public class Hexagon {

  // s, q, and r represent the coordinate system for a hexagonal board.
  // +s --> -s is the top right corner to the bottom left corner in the hexagonal board
  // +q --> -q is the top left corner to the bottom right corner in the hexagonal board
  // +r --> -r is the bottom to the top in the hexagonal board
  // Examples:
  // - the middle hexagon has coordinates s:0, q:0, r:0
  // - the top leftmost hexagon has coordinates s:0, q:+MaxQ, r:-MaxR
  // - the top rightmost hexagon has coordinates s:+MaxS, q:0, r:-MaxR
  private final int s;
  private final int q;
  private final int r;

  /**
   * Instantiate a Hexagon with its three coordinate fields.
   */
  public Hexagon(int s, int q, int r) {
    this.s = s;
    this.q = q;
    this.r = r;
  }

  /**
   * Copy constructor to instantiate a new Hexagon when given an already existing Hexagon.
   */
  public Hexagon(Hexagon hex) {
    this.s = hex.getS();
    this.q = hex.getQ();
    this.r = hex.getR();
  }

  /**
   * Gets the s coordinate value for this hexagon.
   *
   * @return the s value for this hexagon.
   */
  public int getS() {
    return this.s;
  }

  /**
   * Gets the q coordinate value for this hexagon.
   *
   * @return the q value for this hexagon.
   */
  public int getQ() {
    return this.q;
  }

  /**
   * Gets the r coordinate value for this hexagon.
   *
   * @return the r value for this hexagon.
   */
  public int getR() {
    return this.r;
  }

  // Two hexagons are equal if the s, q, and r values of each are the same,
  // which allows us to compare hexagons within the hexagonal board.
  @Override
  public boolean equals(Object object) {
    if (object == this) {
      return true;
    }

    if (!(object instanceof Hexagon)) {
      return false;
    }

    Hexagon other = (Hexagon) object;
    return (this.s == other.getS() && this.q == other.getQ() && this.r == other.getR());
  }

  @Override
  public int hashCode() {
    return Objects.hash(s, q, r);
  }

  @Override
  public String toString() {
    return s + " " + q + " " + r;
  }
}
