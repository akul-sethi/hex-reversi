package cs3500.reversi.model;

import java.awt.geom.Rectangle2D;

import cs3500.reversi.Player;

/**
 * Represents an interface which describes the functionality of Reversi Models.
 * Row and columns work "odd-r" style as described in the README.txt overview section.
 */
public interface ReversiModel {


  /**
   * Places a piece at the given coordinate.
   * @param row The row to place the piece.
   * @param column The column to place the piece.
   * @throws IllegalArgumentException The coordinate is not valid.
   * @throws IllegalStateException The move is invalid or the game is over (all players passed in
   * a row).
   */
  void placePiece(int row, int column);

  /**
   * Passes the turn of the current player. Players are allowed to pass even if they have other
   * valid moves. While players are required to pass if there are no valid moves, the model does
   * force them to; pass must be called. This is similar to Klondike where players are not forced to
   * draw even if that is there only move.
   * @throws IllegalStateException If the game is over (all players passed in
   *  a row).
   */
  void pass();
  /**
   * Returns true if a valid move for the current player exists.
   */
  boolean validMoveExists();

  /**
   * Returns a Player representing the player who won. <code>null</code> if it is a draw.
   * @throws IllegalStateException If the game is not over yet.
   */
  Player getWinner();

  /**
   * Returns the player at the given CubeCord. <code>null</code> if there is no player.
   * @param row The row which is being queried.
   * @param column The column which is being queried.
   * @throws IllegalArgumentException The coordinates are invalid.
   * */
  Player playerAt(int row, int column);


  /**
   * Finds the rightmost column.
   * @return the index of the rightmost column of this model
   */
  public int getRightCol();

  /**
   * Finds the leftmost column.
   * @return the index of the leftmost column of this model
   */
  public int getLeftCol();

  /**
   * Finds the topmost row.
   * @return the index of the topmost row of this model
   */
  public int getTopRow();

  /**
   * Finds the bottom-most row.
   * @return the index of the bottom-most row of this model
   */
  public int getBottomRow();

}
