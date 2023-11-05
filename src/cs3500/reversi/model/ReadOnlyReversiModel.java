package cs3500.reversi.model;

import cs3500.reversi.Player;

/**
 * Represents an interface for making observations on ReversiModels. Masks all behavior of the model
 * other than observations making model mutation impossible. */
public interface ReadOnlyReversiModel {
  /**
   * Returns true if the given move is valid. Necessary so that the view and controller can preview
   * if the queried move is possible without actually moving. Also allows the view and controller to
   * indicate when the current player MUST pass as they have no possible moves.
   */
  boolean validMove(int row, int column);


  /**
   * Returns a Player representing the player who won. <code>null</code> if it is a draw. Necessary
   * to check when the game is over and who has won.
   *
   * @throws IllegalStateException If the game is not over yet.
   */
  Player getWinner();

  /**
   * Returns the player at the given CubeCord. <code>null</code> if there is no player. This is in
   * the model to make drawing it possible.
   *
   * @param row    The row which is being queried.
   * @param column The column which is being queried.
   * @throws IllegalArgumentException The coordinates are invalid.
   */
  Player playerAt(int row, int column);


  /**
   * Finds the rightmost column. Necessary for drawing the model regardless of shape.
   *
   * @return the index of the rightmost column of this model
   */
  public int getRightCol();

  /**
   * Finds the leftmost column. Necessary for drawing the model regardless of shape.
   *
   * @return the index of the leftmost column of this model
   */
  public int getLeftCol();

  /**
   * Finds the topmost row. Necessary for drawing the model regardless of shape.
   *
   * @return the index of the topmost row of this model
   */
  public int getTopRow();

  /**
   * Finds the bottom-most row. Necessary for drawing the model regardless of shape.
   *
   * @return the index of the bottom-most row of this model
   */
  public int getBottomRow();

}
