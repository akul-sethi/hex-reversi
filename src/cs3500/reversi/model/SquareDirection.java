package cs3500.reversi.model;

enum SquareDirection implements Direction {
  UP_RIGHT(-1, 1), RIGHT(0, 1),
  DOWN_RIGHT(1, 1), DOWN_LEFT(1, -1),
  LEFT(0, -1), UP_LEFT(-1, -1),
  UP(-1, 0), DOWN(1, 0);

  private final int deltaR;
  private final int deltaC;

  /**
   * Creates a Direction object using the given deltas.
   *
   * @param deltaR The change in row.
   * @param deltaC The change in col.
   */
  SquareDirection(int deltaR, int deltaC) {
    this.deltaR = deltaR;
    this.deltaC = deltaC;
  }

  /**
   * The number of dimensions the direction is represented in. How many axes it has.
   *
   * @return The number of axes needed to represent this direction.
   */
  @Override
  public int numDimensions() {
    return 2;
  }

  /**
   * @return
   */
  @Override
  public int[] changeByAxes() {
    return new int[]{this.deltaR, this.deltaC};
  }
}
