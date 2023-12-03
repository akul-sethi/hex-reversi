package cs3500.reversi.provider;

import java.util.List;

import reversi.model.Hexagon;
import reversi.model.HexagonState;

/**
 * Interface for Player, might represent a Human or AI player.
 */
public interface IPlayer {

  /**
   * Method to determine the next move for this player.
   * This method does not determine if it is the player's turn or not
   * (this is handled in the controller/model).
   * @return a list of hexagons representing the player's next move
   *         an empty list of hexagons means there are no valid moves to be played
   *         (player must pass)
   *         or the list contains one hexagon representing the hexagon the player should move to.
   */
  List<Hexagon> play();

  /**
   * Method to get the HexagonState field for this player.
   * @return the HexagonState for the player.
   */
  HexagonState getHexagonState();

  /**
   * Method to set the HexagonState field for this player.
   * @param state - HexagonState to set
   */
  void setHexagonState(HexagonState state);

}
