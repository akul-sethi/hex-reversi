package cs3500.reversi.model;

/**
 * Represents a Row in a hex board which goes down and to the left.
 */
final class DownLeft extends Row {
  /**
   * Creates a new Row given the following parameters.
   *
   * @param length The length of the row.
   * @param start  The start of the row.
   */
  DownLeft(int length, CubeCoord start) {
    super(length, -1, 1, 0, start);
  }


}
