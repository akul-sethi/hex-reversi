package cs3500.reversi.model;


import cs3500.reversi.controller.ModelObserver;

/**
 * Represents an interface which describes the functionality of Reversi Models.
 * Row and columns work "odd-r" style as described in the README.txt overview section.
 * Moving right increases columns and moving down increases rows.
 * Utilizing offset coordinates externally is done to make viewing the model simple as a view can
 * easily use this models methods to figure out the range to query, and then query every coordinate
 * as if traversing a grid.
 */
public interface ReversiModel extends ReadOnlyReversiModel {

  /**
   * Add an observer to the model.
   */
  void addObserver(ModelObserver obs);

  /**
   * Starts the game and notifies observers whose move it is.
   */
  void startGame();


  /**
   * Places a piece at the given coordinate. Necessary to allow players to move.
   *
   * @param coord The coord where the piece is being placed
   * @throws IllegalArgumentException The coordinate is not valid.
   * @throws IllegalStateException    The move is invalid or the game is over (all players passed in
   *                                  a row).
   */
  void placePiece(LinearCoord coord);

  /**
   * Passes the turn of the current player. Players are allowed to pass even if they have other
   * valid moves. While players are required to pass if there are no valid moves, the model does
   * force them to; pass must be called. This is similar to Klondike where players are not forced to
   * draw even if that is there only move.
   *
   * @throws IllegalStateException If the game is over (all players passed in
   *                               a row).
   */
  void pass();


}
