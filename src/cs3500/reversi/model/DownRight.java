package cs3500.reversi.model;

/**
 * Represents a Row in a hex board which goes down and to the right.*/
class DownRight extends Row {
  /**
   * Creates a new Row given the following parameters:
   * @param length The length of the row.
   * @param start The start of the row.
   */
  protected DownRight(int length, CubeCoord start) {
    super(length, 0, 1, -1, start);
  }


}