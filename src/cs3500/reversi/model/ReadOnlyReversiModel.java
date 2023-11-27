package cs3500.reversi.model;

import cs3500.reversi.player.Player;

/**
 * Represents an interface for making observations on ReversiModels. Masks all behavior of the model
 * other than observations making model mutation impossible.
 */
public interface ReadOnlyReversiModel {
  /**
   * Returns true if the given move is valid. Necessary so that the view and controller can preview
   * if the queried move is possible without actually moving. Also allows the view and controller to
   * indicate when the current player MUST pass as they have no possible moves.
   */
  boolean validMove(LinearCoord coord);


  /**
   * Returns a Player representing the player who won. <code>null</code> if it is a draw. Necessary
   * to check when the game is over and who has won.
   *
   * @throws IllegalStateException If the game is not over yet.
   */
  Player getWinner();

  /**
   * Returns true if the game is over.
   */
  boolean gameOver();

  /**
   * Returns the player at the given CubeCord. <code>null</code> if there is no player. This is in
   * the model to make drawing it possible.
   *
   * @param coord The coordinate which is being queried.
   * @throws IllegalArgumentException The coordinates are invalid.
   */
  Player playerAt(LinearCoord coord);


  /**
   * Finds the rightmost column. Necessary for drawing the model regardless of shape.
   *
   * @return the index of the rightmost column of this model
   */
  int getRightCol();

  /**
   * Finds the leftmost column. Necessary for drawing the model regardless of shape.
   *
   * @return the index of the leftmost column of this model
   */
  int getLeftCol();

  /**
   * Finds the topmost row. Necessary for drawing the model regardless of shape.
   *
   * @return the index of the topmost row of this model
   */
  int getTopRow();

  /**
   * Finds the bottom-most row. Necessary for drawing the model regardless of shape.
   *
   * @return the index of the bottom-most row of this model
   */
  int getBottomRow();

  /**
   * Makes a shallow copy of the model. This is for testing moves in the strategies.
   *
   * @return A copy of the model.
   */
  ReversiModel getModel();

  /**
   * Returns the score of the given player.
   */
  int getPlayerScore(Player player);

  /**
   * Returns the Player which will play next.
   */
  Player nextToPlay();
}
