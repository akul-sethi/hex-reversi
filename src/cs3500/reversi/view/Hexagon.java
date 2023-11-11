package cs3500.reversi.view;


import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.nio.file.Path;

public class Hexagon extends Path2D.Double {
  private final int row, column;
  public Hexagon(int row, int column) {
    this.row = row;
    this.column = column;
  }



}
