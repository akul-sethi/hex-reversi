package cs3500.reversi.view;

import java.awt.geom.Path2D;

abstract class MyShape extends Path2D.Double {
  final double sideLength;
  final double centerY;
  final double centerX;

  MyShape(double centerX, double centerY, double sideLength, int numVertices) {
    super();
    this.sideLength = sideLength;
    this.centerX = centerX;
    this.centerY = centerY;
    boolean firstPoint = true;
    for (int vertex = 0; vertex < numVertices; vertex++) {

      double angle_deg = 360.0 / numVertices * vertex;
      if (numVertices == 6) {
        angle_deg -= 30;
      }
      if (numVertices == 4) {
        angle_deg -= 45;
      }
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
}
