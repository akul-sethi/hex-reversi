package cs3500.reversi.model;

/**
 * Represents a Row in a hex board which goes to the Left.
 */
final class Left extends Row {
  /**
   * Creates a new Row given the following parameters.
   *
   * @param length The length of the row.
   * @param start  The start of the row.
   */
  Left(int length, CubeCoord start) {
    super(length, -1, 0, 1, start);
  }


}