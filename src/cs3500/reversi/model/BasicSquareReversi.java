package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import cs3500.reversi.player.HumanPlayer;
import cs3500.reversi.player.Name;
import cs3500.reversi.player.Player;

/**
 * A concrete basic square implementation of a ReversiModel.
 */
public class BasicSquareReversi extends AReversiModel {
  /**
   * Creates a BasicSquareReversi with the given arguments.
   *
   * @param sideLength The side length of the board.
   * @param one        The first player to move. Can be AI or Human.
   * @param two        The second player to move. Can be AI or Human.
   */
  public BasicSquareReversi(int sideLength, Player one, Player two) {
    super(makeBoard(sideLength),
            Arrays.asList(one, two), List.of(SquareDirection.values()));
  }

  public BasicSquareReversi(HashMap<LinearCoord, Player> map, List<Player> players) {
    super(map, players, List.of(SquareDirection.values()));
  }


  /**
   * Creates the board and throws and error if the sideLength is less than 2.
   */
  private static HashMap<LinearCoord, Player> makeBoard(int sideLength)
          throws IllegalArgumentException {
    if (sideLength < 2) {
      throw new IllegalArgumentException("Side length too small");
    }
    if (sideLength % 2 != 0) {
      throw new IllegalArgumentException("Side length must be even!");
    }
    HashMap<LinearCoord, Player> tiles = new HashMap<>();
    for (int r = 0; r < sideLength; r += 1) {
      for (int c = 0; c < sideLength; c += 1) {
        tiles.put(new CubeCoord(r, c), null);
      }
    }
    placeStarting(tiles, sideLength);
    return tiles;
  }

  /**
   * Places the starting tiles on a board.
   *
   * @param tiles The board on which to place the tiles.
   */
  private static void placeStarting(HashMap<LinearCoord, Player> tiles, int sideLength) {
    int m = sideLength / 2;
    tiles.put(new CubeCoord(m - 1, m), new HumanPlayer(Name.O));
    tiles.put(new CubeCoord(m - 1, m - 1), new HumanPlayer(Name.X));
    tiles.put(new CubeCoord(m, m - 1), new HumanPlayer(Name.O));
    tiles.put(new CubeCoord(m, m), new HumanPlayer(Name.X));
  }

  @Override
  public ReversiModel getModel() {
    List<Player> players = new ArrayList<>(this.players);
    HashMap<LinearCoord, Player> map = new HashMap<>(this.tiles);
    return new BasicSquareReversi(map, players);
  }


}
