package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import cs3500.reversi.player.HumanPlayer;
import cs3500.reversi.player.Name;
import cs3500.reversi.player.Player;

public class BasicSquareReversi extends AReversiModel {
  /**
   * Creates a BasicReversi with the given arguments.
   *
   * @param sideLength The side length of the board.
   * @param one        The first player to move. Can be AI or Human.
   * @param two        The second player to move. Can be AI or Human.
   */
  BasicSquareReversi(int sideLength, Player one, Player two) {
    super(makeBoard(sideLength),
            Arrays.asList(one, two), false);
  }

  BasicSquareReversi(HashMap<LinearCoord, Player> map, List<Player> players) {
    super(map, players, false);
  }


  /**
   * Creates the board and throws and error if the sideLength is less than 2.
   */
  private static HashMap<LinearCoord, Player> makeBoard(int sideLength)
          throws IllegalArgumentException {
    if (sideLength < 2) {
      throw new IllegalArgumentException("Side length too small");
    }
    HashMap<LinearCoord, Player> tiles = new HashMap<>();
    int qStart = 1;
    for (int r = -(sideLength - 1); r <= 0; r++) {
      qStart -= 1;
      for (int q = qStart; q < sideLength; q++) {
        tiles.put(new CubeCoord(q, r, -q - r), null);
      }
    }
    for (int r = 1; r <= sideLength - 1; r++) {
      for (int q = qStart; q < sideLength - r; q++) {
        tiles.put(new CubeCoord(q, r, -q - r), null);
      }
    }
    placeStarting(tiles);
    return tiles;
  }

  /**
   * Places the starting tiles on a board.
   *
   * @param tiles The board on which to place the tiles.
   */
  private static void placeStarting(HashMap<LinearCoord, Player> tiles) {
    tiles.put(new BasicPoint(0, 1), new HumanPlayer(Name.X));
    tiles.put(new BasicPoint(-1, 1), new HumanPlayer(Name.X));
    tiles.put(new BasicPoint(0, 0), new HumanPlayer(Name.X));
    tiles.put(new BasicPoint(-1, 0), new HumanPlayer(Name.X));
  }

  @Override
  public ReversiModel getModel() {
    List<Player> players = new ArrayList<>(this.players);
    HashMap<LinearCoord, Player> map = new HashMap<>(this.tiles);
    return new BasicHexReversi(map, players);
  }
}
