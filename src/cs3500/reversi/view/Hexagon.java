package cs3500.reversi.view;


import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.nio.file.Path;

public class Hexagon extends Path2D.Double {
  private final double width;

  public Hexagon(double width) {
    super();
    this.width = width;

    boolean firstPoint = true;
    for(int vertex = 0; vertex < 6; vertex++) {

      double angle_deg = 60 * vertex - 30;
      double angle_rad = Math.PI / 180 * angle_deg;
      double x = 100 + width * Math.cos(angle_rad);
      double y = 100 + width * Math.sin(angle_rad);

      if(firstPoint) {
        firstPoint = false;
        this.moveTo(x, y);
      } else {
        this.lineTo(x, y);
      }
      this.closePath();
    }

  }

}
