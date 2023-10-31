package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a row of Hexes in  Hex grid with length being the number of hexes in between
 * the two end points of the row.*/
class Row {
  //Length represents the number of tiles between the two end hexes
  protected int length;
  protected int deltaQ;
  protected int deltaS;
  protected int deltaR;
  protected CubeCoord start;

  /**
   * Creates a new Direction given the following parameters:*/
  protected Row(int length, int deltaQ, int deltaR, int deltaS, CubeCoord start) {
    this.length = length;
    this.deltaQ = deltaQ;
    this.deltaR = deltaR;
    this.deltaS = deltaS;
    this.start = start;
  }

  public CubeCoord next() {
    return new CubeCoord(start.q + (this.length + 1) * this.deltaQ,
            start.r + (this.length + 1) * this.deltaR,
            start.s  + (this.length + 1) * this.deltaS);
  }

  /**
   * Returns all coordinates in this row excluding the end points*/
  public List<CubeCoord> getCoordsInRow() {
    List<CubeCoord> out = new ArrayList<>();
    for(int length = 0; length < this.length; length++) {
      out.add(new Row(length, this.deltaQ, this.deltaR, this.deltaS, this.start).next());
    }
    return out;
  }

//  public CubeCoord last() {
//    return new CubeCoord();
//  }

}