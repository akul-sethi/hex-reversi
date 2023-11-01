package cs3500.reversi.model;

/**
 * Represents a Row in a hex grid which goes up and to the Left.
 */
final class UpLeft extends Row {
  /**
   * Creates a new Row given the following parameters.
   *
   * @param length The length of the row.
   * @param start  The start of the row.
   */
  UpLeft(int length, CubeCoord start) {
    super(length, 0, -1, 1, start);
  }


}