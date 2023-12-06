package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.List;

import javax.sound.sampled.Line;

/**
 * Represents a row of Hexes in a Hex grid with length being the number of hexes in between
 * the two end points of the row. Necessary to easily check along a row which deciding which pieces
 * to flip.
 */
class Row {

  //Length represents the number of tiles between the two end hexes
  public int length;
  public Direction direction;
  public LinearCoord start;
  private final boolean square;

  /**
   * Creates a new Row given the following parameters.
   *
   * @param length    The length of the row. It is the number of coordinates not including the
   *                  endpoints.
   * @param direction The direction of this row.
   * @param start     The starting coordinate in the row. The first endpoint.
   */
  Row(int length, Direction direction, LinearCoord start, boolean square) {
    this.length = length;
    this.direction = direction;
    this.start = start;
    this.square = square;
  }

  /**
   * Returns the next Coordinate in the row.
   */
  public LinearCoord next() {
    if (square) {
      SquareDirection dir = (SquareDirection) this.direction;
      return new BasicPoint(start.row() + (this.length + 1) * dir.deltaR,
              start.column() + (this.length + 1) * dir.deltaC);
    }
    else {
      HexDirection dir = (HexDirection) this.direction;
      CubeCoord start = (CubeCoord) this.start;
      return new CubeCoord(start.q + (this.length + 1) * dir.deltaQ,
              start.r + (this.length + 1) * dir.deltaR,
              start.s + (this.length + 1) * dir.deltaS);
    }
  }

  /**
   * Returns all coordinates in this row excluding the end points.
   */
  public List<LinearCoord> getCoordsInRow() {
    List<LinearCoord> out = new ArrayList<>();
    for (int length = 0; length < this.length; length++) {
      out.add(new Row(length, this.direction, this.start, this.square).next());
    }
    return out;
  }


}