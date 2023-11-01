package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a row of Hexes in  Hex grid with length being the number of hexes in between
 * the two end points of the row.
 */
class Row {
  //Length represents the number of tiles between the two end hexes
  protected int length;
  protected int deltaQ;
  protected int deltaS;
  protected int deltaR;
  protected CubeCoord start;

  /**
   * Creates a new Direction given the following parameters.
   *
   * @param length The length of the row. It is the number of coordinates not including the
   *               endpoints.
   * @param deltaQ The change in Q which describes this row.
   * @param deltaR The change in R which describes this row.
   * @param deltaS The change in S which describes this row.
   * @param start  The starting coordinate in the row. The first endpoint.
   */
  protected Row(int length, int deltaQ, int deltaR, int deltaS, CubeCoord start) {
    this.length = length;
    this.deltaQ = deltaQ;
    this.deltaR = deltaR;
    this.deltaS = deltaS;
    this.start = start;
  }

  /**
   * Returns the next Coordinate in the row.
   */
  public CubeCoord next() {
    return new CubeCoord(start.q + (this.length + 1) * this.deltaQ,
            start.r + (this.length + 1) * this.deltaR,
            start.s + (this.length + 1) * this.deltaS);
  }

  /**
   * Returns all coordinates in this row excluding the end points.
   */
  public List<CubeCoord> getCoordsInRow() {
    List<CubeCoord> out = new ArrayList<>();
    for (int length = 0; length < this.length; length++) {
      out.add(new Row(length, this.deltaQ, this.deltaR, this.deltaS, this.start).next());
    }
    return out;
  }

}