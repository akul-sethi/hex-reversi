package cs3500.reversi.view;


import java.awt.geom.Path2D;

/**
 * Represents a Hexagon in a game of Reversi. Stores the location and information which describes
 * the space that it fills. This can be used for drawing and determining if a point is within its
 * bounds.
 */
final class Hexagon extends MyShape {


  /**
   * Creates a Hexagon with given center and side length.
   */
  Hexagon(double centerX, double centerY, double sideLength) {
    super(centerX, centerY, sideLength, 6);
  }


  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Hexagon)) {
      return false;
    }

    Hexagon h = (Hexagon) o;

    return h.sideLength == this.sideLength &&
            this.centerY == h.centerY && this.centerX == h.centerX;
  }

  @Override
  public int hashCode() {
    return (int) (this.sideLength * 11 + this.centerX * 13 + this.centerX * 19);
  }

}
