package cs3500.reversi.view;


import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.nio.file.Path;

public class Hexagon extends Path2D.Double {
  private final double width;
  private final double centerY;
  private final double centerX;

  public Hexagon(double centerX, double centerY, double width) {
    super();
    this.width = width;
    this.centerX = centerX;
    this.centerY = centerY;

    boolean firstPoint = true;
    for(int vertex = 0; vertex < 6; vertex++) {

      double angle_deg = 60 * vertex - 30;
      double angle_rad = Math.PI / 180 * angle_deg;
      double x = centerX + width * Math.cos(angle_rad);
      double y = centerY + width * Math.sin(angle_rad);

      if(firstPoint) {
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
    if(!(o instanceof Hexagon)) {
      return false;
    }

    Hexagon h = (Hexagon) o;


    return h.width == this.width && this.centerY == h.centerY && this.centerX == h.centerX;
  }



}
