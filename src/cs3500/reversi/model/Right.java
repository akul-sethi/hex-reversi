package cs3500.reversi.model;

class Right extends Row {
  /**
   * Creates a new Direction given the following parameters:
   *
   * @param length
   */
  protected Right(int length, CubeCoord start) {
    super(length, 1, 0, -1, start);
  }


}
