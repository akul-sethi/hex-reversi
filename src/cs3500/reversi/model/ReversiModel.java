package cs3500.reversi.model;

import cs3500.reversi.Player;

/**
 * Represents an interface which describes the functionality of Reversi Models.
 * Row and columns work "odd-r" style as described in the README.txt overview section.
 * Moving right increases columns and moving down increases rows.
 *
 * Utilizing offset coordinates externally is done to make viewing the model simple as a view can
 * easily use this models methods to figure out the range to query, and then query every coordinate
 * as if traversing a grid.
 */
public interface ReversiModel {


  /**
   * Places a piece at the given coordinate. Necessary to allow players to move.
   * @param row The row to place the piece.
   * @param column The column to place the piece.
   * @throws IllegalArgumentException The coordinate is not valid.
   * @throws IllegalStateException The move is invalid or the game is over (all players passed in
   *     a row).
   */
  void placePiece(int row, int column);

  /**
   * Passes the turn of the current player. Players are allowed to pass even if they have other
   * valid moves. While players are required to pass if there are no valid moves, the model does
   * force them to; pass must be called. This is similar to Klondike where players are not forced to
   * draw even if that is there only move.
   * @throws IllegalStateException If the game is over (all players passed in
   *     a row).
   */
  void pass();

  /**
   * Returns true if the given move is valid. Necessary so that the view and controller can preview
   * if the queried move is possible without actually moving. Also allows the view and controller to
   * indicate when the current player MUST pass as they have no possible moves.
   */
  boolean validMove(int row, int column);



  /**
   * Returns a Player representing the player who won. <code>null</code> if it is a draw. Necessary
   * to check when the game is over and who has won.
   * @throws IllegalStateException If the game is not over yet.
   */
  Player getWinner();

  /**
   * Returns the player at the given CubeCord. <code>null</code> if there is no player. This is in
   * the model to make drawing it possible.
   * @param row The row which is being queried.
   * @param column The column which is being queried.
   * @throws IllegalArgumentException The coordinates are invalid.
   * */
  Player playerAt(int row, int column);


  /**
   * Finds the rightmost column. Necessary for drawing the model regardless of shape.
   * @return the index of the rightmost column of this model
   */
  public int getRightCol();

  /**
   * Finds the leftmost column. Necessary for drawing the model regardless of shape.
   * @return the index of the leftmost column of this model
   */
  public int getLeftCol();

  /**
   * Finds the topmost row. Necessary for drawing the model regardless of shape.
   * @return the index of the topmost row of this model
   */
  public int getTopRow();

  /**
   * Finds the bottom-most row. Necessary for drawing the model regardless of shape.
   * @return the index of the bottom-most row of this model
   */
  public int getBottomRow();

}
