package cs3500.reversi.model;

class DownLeft extends Row {
  /**
   * Creates a new Direction given the following parameters:
   *
   * @param length
   */
  protected DownLeft(int length, CubeCoord start) {
    super(length, -1, 1, 0, start);
  }


}
