package cs3500.reversi.view;

import java.awt.*;
import java.awt.geom.Path2D;

abstract class MyShape extends Path2D.Double implements BoardShape {
  final protected double sideLength;
  final protected double centerY;
  final protected double centerX;


  MyShape(double centerX, double centerY, double sideLength, int numVertices) {
    super();
    this.sideLength = sideLength;
    this.centerX = centerX;
    this.centerY = centerY;
    boolean firstPoint = true;
    for (int vertex = 0; vertex < numVertices; vertex++) {

      double angle_deg = 360.0 / numVertices * vertex;
      angle_deg -= 180.0 / numVertices;
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
  public void draw(Graphics2D g2) {
    g2.fill(this);
    g2.setColor(Color.BLACK);
    g2.draw(this);
  }
}

