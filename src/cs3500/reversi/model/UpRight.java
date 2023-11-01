package cs3500.reversi.model;

/**
 * Represents a Row in a hex grid which goes up and to the right. Necessary for checking if this
 * type of row should be flipped.
 */
final class UpRight extends Row {
  /**
   * Creates a new Row given the following parameters.
   *
   * @param length The length of the row.
   * @param start  The start of the row.
   */
  UpRight(int length, CubeCoord start) {
    super(length, 1, -1, 0, start);
  }


}
