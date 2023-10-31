package cs3500.reversi.model;

import java.util.Arrays;
import java.util.HashMap;

import cs3500.reversi.HumanPlayer;
import cs3500.reversi.Player;

final class BasicReversi extends AReversiModel{
   BasicReversi(int sideLength, Player one, Player two) {
    super(makeBoard(sideLength),
            Arrays.asList(one, two));
  }

  /**
   * Creates the board and throws and error if the sideLength is less than 2.*/
  private static HashMap<CubeCoord, Player> makeBoard(int sideLength)
    throws IllegalArgumentException {
    if(sideLength < 2) {
      throw new IllegalArgumentException("Side length too small");
    }
    HashMap<CubeCoord, Player> tiles = new HashMap<>();
    int qStart = 1;
    for(int r = -(sideLength - 1); r <= 0; r++) {
      qStart -= 1;
      for(int q = qStart; q < sideLength; q++) {
        tiles.put(new CubeCoord(q, r, -q-r), null);
      }
    }
    for(int r = 1; r <= sideLength - 1; r++) {
      for(int q = qStart; q < sideLength - r; q++) {
        tiles.put(new CubeCoord(q, r, -q-r), null);
      }
    }
    placeStarting(tiles);
    return tiles;
  }

  private static void placeStarting(HashMap<CubeCoord, Player> tiles) {
    tiles.put(new CubeCoord(0, 1, -1), new HumanPlayer("X"));
    tiles.put(new CubeCoord(1, -1, 0), new HumanPlayer("X"));
    tiles.put(new CubeCoord(1, 0, -1), new HumanPlayer("O"));
    tiles.put(new CubeCoord(0, -1, 1), new HumanPlayer("O"));
    tiles.put(new CubeCoord(-1, 1, 0), new HumanPlayer("O"));
    tiles.put(new CubeCoord(-1, 0, 1), new HumanPlayer("X"));
  }
}
