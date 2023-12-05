package cs3500.provider.model;

import java.util.HashMap;

import cs3500.provider.IPlayer;
import cs3500.provider.model.Hexagon;
import cs3500.provider.model.HexagonState;
import cs3500.provider.model.Hexagon;
import cs3500.provider.model.HexagonState;
import cs3500.provider.IPlayer;

/**
 * A Readonly interface for a ReversiModel that only allows non-mutable observations.
 */
public interface IReadonlyReversiModel {

  /**
   * Determines if this Reversi game is over.
   * Game is over if both players pass in a row or the board is full.
   * @return true if game is over
   * @throws IllegalStateException if game has not been started
   */
  boolean isGameOver();

  /**
   * Returns the winning player in this game, if the game is over.
   * @return winning IPlayer, the player with more tiles
   * @throws IllegalStateException if game has not been started
   *                               if game is not over
   *                               if there is a tie between black and white
   */
  IPlayer getWinner();

  /**
   * Returns the HexagonState for the Hexagon in the board at coordinates s, q, r.
   * @param s the s coordinate for the hexagon being checked
   * @param q the q coordinate for the hexagon being checked
   * @param r the r coordinate for the hexagon being checked
   * @return the HexagonState for the Hexagon in the board at the coordinates s, q, r
   * @throws IllegalArgumentException if given coordinates are not within the board
   * @throws IllegalStateException if game has not been started
   */
  cs3500.provider.model.HexagonState getHexagonStateAt(int s, int q, int r);

  /**
   * Returns the maxCoordinate for the board in the Reversi game.
   * @return the maxCoordinate for the board in the Reversi game.
   * @throws IllegalStateException if game has not been started
   */
  int getMaxCoordinate();

  /**
   * Returns the player whose turn it currently is.
   *
   * @return the IPlayer whose turn it is
   * @throws IllegalStateException if game has not been started
   */
  IPlayer getTurn();

  /**
   * Returns the current score of the given player.
   * @return the player's current score
   * @throws IllegalStateException if game has not been started
   * @throws IllegalArgumentException if player is not a player in this game
   */
  int getPlayerScore(IPlayer p);

  /**
   * Copies the board object in the ReversiModel using the copy constructor in ReversiBoard class.
   * @return a copy of the ReversiBoard object.
   * @throws IllegalStateException if game has not been started
   */
  ReversiBoard getCopyBoardObject();

  /**
   * Copies the board hashmap from the ReversiBoard class.
   * @return a copy of the ReversiBoard HashMap with key Hexagon and value HexagonState.
   * @throws IllegalStateException if game has not been started
   */
  HashMap<cs3500.provider.model.Hexagon, cs3500.provider.model.HexagonState> getCopyBoardHashMap();

  /**
   * Returns the Player associated with the given HexagonState.
   * @return the IPlayer
   * @throws IllegalStateException if game has not been started
   */
  IPlayer getPlayer(cs3500.provider.model.HexagonState state);

  /**
   * Returns the hypothetical number of pieces gained for a given move for a given state.
   * @param h the hexagon being moved to
   * @param s the hexagon state making the move
   * @return the number of pieces gained by this move.
   *         Will be 0 if not a valid move.
   * @throws IllegalStateException if game has not been started
   */
  int getNumPiecesGainedForMove(cs3500.provider.model.Hexagon h, cs3500.provider.model.HexagonState s);

  /**
   * Determines if the given hexagon is NEXT TO one of the six corners of the ReversiBoard.
   * @param h the hexagon being checked
   * @return true if the hexagon is next to a corner in the ReversiBoard.
   * @throws IllegalStateException if game has not been started
   */
  boolean isHexagonNextToCorner(cs3500.provider.model.Hexagon h);

  /**
   * Determines if the given hexagon is ONE OF the six corners of the ReversiBoard.
   * @param h the hexagon being checked
   * @return true if the hexagon is in a corner in the ReversiBoard.
   * @throws IllegalStateException if game has not been started
   */
  boolean isHexagonInCorner(cs3500.provider.model.Hexagon h);

  /**
   * Is this move, according to the given hexagon and hexagon state, a valid move?.
   * @throws IllegalStateException if game has not been started
   */
  boolean isValidMove(Hexagon h, HexagonState s);
}
