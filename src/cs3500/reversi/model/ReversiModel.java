package cs3500.reversi.model;

import java.awt.geom.Rectangle2D;

import cs3500.reversi.Player;

/**
 * Represents a interface which describes the functionality of Reversi Models. (0, 0) is in the top
 * left.
 * Maintains the invariant where row and column of every piece are non-negative and contains one
 * piece where row = 0 and one were column = 0
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
   * Returns a Player representing the player who won. <code>null</code> if no one won.
   * @throws IllegalStateException If the has not started.
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
   * Get height of board.*/
  int getHeight();

  /**
   * Returns true if the coordinate is valid.*/
//  boolean validCoord(int row, int column);


}
