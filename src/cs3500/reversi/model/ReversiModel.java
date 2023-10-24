package cs3500.reversi.model;

import java.util.List;

import cs3500.reversi.Player;

/**
 * Represents a interface which describes the functionality of Reversi Models. Utilizes cube
 * coordinates.*/
public interface ReversiModel {

  /**
   * Starts the game with the given number of players. Game can only be started once.
   * @param players The players in the game.
   * @throws IllegalStateException The game has already started.
   * @throws IllegalArgumentException The number of players is invalid.
   */
  void startGame(List<Player> players);

  /**
   * Places a piece at the given coordinate.
   * @param coordinate The coordinate to place the piece.
   * @throws IllegalArgumentException The coordinate is not valid.
   * @throws IllegalStateException The move is invalid or the game has not started.
   */
  void placePiece(CubeCord coordinate);

  /**
   * Passes the turn of the current player.
   * @throws IllegalStateException The game has not started.
   */
  void pass();

  /**
   * Returns an integer representing the player who won. <code>null</code> if no one won.
   * @throws IllegalStateException If the has not started.
   */
  void getWinner();

  /**
   * Returns the player at the given CubeCord. <code>null</code> if there is no player.
   * @param coordinate The coordinate which is being queried.
   * @throws IllegalStateException The game has not started.
   * @throws IllegalArgumentException The coordinates are invalid.
   * */
  int playerAt(CubeCord coordinate);

}
