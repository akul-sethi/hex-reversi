package cs3500.reversi.model;

import java.util.HashMap;
import java.util.List;

import cs3500.reversi.Player;

/**
 * A mock implementation of reversi. Used to tests for mutability.
 */
public class MockReversi extends AReversiModel {
  /**
   * Constructs abstract reversi model. In concrete implementations new board shapes with starting
   * locations and lists of players can easily be passed in to create new game modes.
   *
   * @param hexs    A map of hex tiles to put in the board with <code>null</code> or a Player as
   *                the values corresponding to if the tile is empty or if there is a player in the
   *                starting position.
   * @param players The list of players that will be in the game.
   */
  protected MockReversi(HashMap<CubeCoord, Player> hexs, List<Player> players) {
    super(hexs, players);
  }
}
