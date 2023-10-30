package cs3500.reversi.model;

class DownRight extends Row {
  /**
   * Creates a new Direction given the following parameters:
   *
   * @param length
   */
  protected DownRight(int length, CubeCoord start) {
    super(length, 0, 1, -1, start);
  }


}