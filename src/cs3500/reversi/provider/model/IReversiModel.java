package cs3500.reversi.provider.model;

import reversi.IPlayer;
import reversi.controller.IReversiFeatures;

/**
 * Interface to represent the model of a game of Reversi.
 * Extends the Readonly interface to allow mutation of the model.
 */
public interface IReversiModel extends IReadonlyReversiModel {

  /**
   * Start a new game of Reversi, providing a board size and two IPlayers.
   * @param player1 the first player in the reversi game (black)
   * @param player2 the second player in the reversi game (white)
   * @throws IllegalStateException if game has already been started
   * @throws IllegalArgumentException if one of the given players is null
   *                                  if the maxCoordinate is <= 1
   */
  void startGame(IPlayer player1, IPlayer player2);

  /**
   * The provided IPlayer moves one of their tiles to the provided coordinates.
   * @param p the player making the move
   * @param s the s coordinate for the hexagon being moved to
   * @param q the q coordinate for the hexagon being moved to
   * @param r the r coordinate for the hexagon being moved to
   * @throws IllegalStateException if game has not been started
   *                               if wrong player's turn
   *                               if invalid move
   * @throws IllegalArgumentException if given coordinates are not within the board
   */
  void movePlay(IPlayer p, int s, int q, int r);

  /**
   * The provided IPlayer chooses to pass on their turn.
   * @param p the player passing
   * @throws IllegalStateException if game has not been started
   *                               if wrong player's turn
   */
  void movePass(IPlayer p);

  /**
   * Does the player, whose turn it currently is, have any legal moves?.
   * @return boolean whether there is a possible move
   * @throws IllegalStateException if game has not been started
   */
  boolean validMoveExistsForCurrentPlayer();

  /**
   * Allows a controller to subscribe itself as a features listener.
   * @param features the IReversiFeatures (controller) wanting to subscribe
   */
  void addFeatureListener(IReversiFeatures features);
}
