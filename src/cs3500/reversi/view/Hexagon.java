package cs3500.reversi.view;


import java.awt.geom.Path2D;

/**
 * Represents a Hexagon in a game of Reversi. Stores the location and information which describes
 * the space that it fills. This can be used for drawing and determining if a point is within its
 * bounds.
 */
final class Hexagon extends Path2D.Double {
  private final double sideLength;
  private final double centerY;
  private final double centerX;

  /**
   * Creates a Hexagon with given center and side length.
   */
  Hexagon(double centerX, double centerY, double sideLength) {
    super();
    this.sideLength = sideLength;
    this.centerX = centerX;
    this.centerY = centerY;

    boolean firstPoint = true;
    for (int vertex = 0; vertex < 6; vertex++) {

      double angle_deg = 60 * vertex - 30;
      double angle_rad = Math.PI / 180 * angle_deg;
      double x = centerX + this.sideLength * Math.cos(angle_rad);
      double y = centerY + this.sideLength * Math.sin(angle_rad);

      if (firstPoint) {
        firstPoint = false;
        this.moveTo(x, y);
      } else {
        this.lineTo(x, y);
      }

    }
    this.closePath();
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
