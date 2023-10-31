package cs3500.reversi.model;

import java.awt.geom.Rectangle2D;

import cs3500.reversi.Player;

/**
 * Represents an interface which describes the functionality of Reversi Models.
 * (0, 0) is in the top left.
 * Maintains the invariant where row and column of every piece are non-negative and contains one
 * piece where row = 0 and one where column = 0
 */
public interface ReversiModel {

  /**
   * Starts the game. Game can only be started once.
   * @throws IllegalStateException The game has already started.
   * @throws IllegalArgumentException The number of players is invalid.
   */
  void startGame();

  /**
   * Places a piece at the given coordinate.
   * @param row The row to place the piece.
   * @param column The column to place the piece.
   * @throws IllegalArgumentException The coordinate is not valid.
   * @throws IllegalStateException The move is invalid or the game has not started.
   */
  void placePiece(int row, int column);

  /**
   * Passes the turn of the current player.
   * @throws IllegalStateException The game has not started.
   */
  void pass();

  /**
   * Returns a Player representing the player who won. <code>null</code> if it is a draw.
   * @throws IllegalStateException If the game has not started or no one won
   */
  Player getWinner();

  /**
   * Returns the player at the given CubeCord. <code>null</code> if there is no player.
   * @param row The row which is being queried.
   * @param column The column which is being queried.
   * @throws IllegalStateException The game has not started.
   * @throws IllegalArgumentException The coordinates are invalid.
   * */
  Player playerAt(int row, int column);

  /**
   * Get width of board.
   */
  int getWidth();


  /**
   * Get height of board.
   */
  int getHeight();

  /**
   * Finds the rightmost column.
   * @return the index of the rightmost column of this model. (0 index is the middle)
   */
  public int getRightCol();

  /**
   * Finds the leftmost column.
   * @return the index of the leftmost column of this model. (0 index is the middle)
   */
  public int getLeftCol();

  /**
   * Finds the topmost row.
   * @return the index of the topmost row of this model. (0 index is the middle)
   */
  public int getTopRow();

  /**
   * Finds the bottom-most row.
   * @return the index of the bottom-most row of this model. (0 index is the middle)
   */
  public int getBottomRow();

}
