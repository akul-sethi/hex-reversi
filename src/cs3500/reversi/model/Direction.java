package cs3500.reversi.model;

public interface Direction {
  /**
   * The number of dimensions the direction is represented in. How many axes it has.
   * @return The number of axes needed to represent this direction.
   */
  int numDimensions();

  /**
   * The delta of each axis for this direction.
   * @return A list containing the delta of each axis for this direction.
   */
  int[] changeByAxes();
}
