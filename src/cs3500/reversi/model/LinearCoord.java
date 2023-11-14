package cs3500.reversi.model;

/**
 * Represents a coordinate with the ability to be interpreted as having a row and a column.
 */
public interface LinearCoord {

  /**
   * Returns the row value of this coordinate.*/
  public int row();
  /**
   * Returns the column value of this coordinate. */
  public int column();


}
