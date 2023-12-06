package cs3500.reversi.model;

enum SquareDirection implements Direction {
  UP_RIGHT(-1, 1), RIGHT(0, 1),
  DOWN_RIGHT(1, 1), DOWN_LEFT(1, -1),
  LEFT(0, -1), UP_LEFT(-1, -1),
  UP(-1, 0), DOWN(1, 0);

  public final int deltaR;
  public final int deltaC;

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
}
