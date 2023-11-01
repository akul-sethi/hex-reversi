package cs3500.reversi.model;

/**
 * Represents a Row in a hex grid which goes to the right.
 */
final class Right extends Row {
  /**
   * Creates a new Row given the following parameters.
   *
   * @param length The length of the row.
   * @param start  The start of the row.
   */
  Right(int length, CubeCoord start) {
    super(length, 1, 0, -1, start);
  }


}
