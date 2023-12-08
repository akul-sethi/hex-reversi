package cs3500.reversi.view;

import java.awt.geom.Path2D;

final class Square extends MyShape {

  /**
   * Creates a Square with given center and side length.
   */
  Square(double centerX, double centerY, double sideLength) {
    super(centerX, centerY, sideLength, 4);
  }


  @Override
  public boolean equals(Object o) {
    if (!(o instanceof Square)) {
      return false;
    }

    Square s = (Square) o;

    return s.sideLength == this.sideLength &&
            this.centerY == s.centerY && this.centerX == s.centerX;
  }

  @Override
  public int hashCode() {
    return (int) (this.sideLength * 11 + this.centerX * 13 + this.centerX * 19);
  }

}
