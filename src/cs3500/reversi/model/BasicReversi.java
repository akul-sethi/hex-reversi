package cs3500.reversi.model;

import java.io.BufferedReader;
import java.util.Arrays;
import java.util.HashMap;

import cs3500.reversi.HumanPlayer;
import cs3500.reversi.Player;

public class BasicReversi extends AReversiModel{
  public BasicReversi(int sideLength) {
    super(makeBoard(sideLength), Arrays.asList(new HumanPlayer("X"), new HumanPlayer("O")));
  }

  /**
   * Creates the board and throws and error if the sideLength is less than 2.*/
  private static HashMap<CubeCoord, Player> makeBoard(int sideLength)
    throws IllegalArgumentException {
    if(sideLength < 2) {
      throw new IllegalArgumentException("Side length too small");
    }
    HashMap<CubeCoord, Player> tiles = new HashMap<CubeCoord, Player>();
    int qStart = 0;
    for(int r = -(sideLength - 1); r <= 0; r++) {
      for(int q = qStart; q < sideLength; q++) {
        tiles.put(new CubeCoord(q, r, -q-r), null);
      }
      qStart -= 1;
    }
    qStart = 1;
    for(int r = 1; r < sideLength; r++) {
      for(int q = qStart; q > -sideLength; q--) {
        tiles.put(new CubeCoord(q, r, -q-r), null);
      }
      qStart += 1;
    }
    placeStarting(tiles);
    return tiles;
  }

  private static void placeStarting(HashMap<CubeCoord, Player> tiles) {
    tiles.put(new CubeCoord(0, 1, -1), new HumanPlayer("X"));
    tiles.put(new CubeCoord(1, -1, 0), new HumanPlayer("O"));
    tiles.put(new CubeCoord(1, 0, -1), new HumanPlayer("X"));
    tiles.put(new CubeCoord(0, -1, 1), new HumanPlayer("O"));
    tiles.put(new CubeCoord(-1, 1, 0), new HumanPlayer("X"));
    tiles.put(new CubeCoord(-1, 0, 1), new HumanPlayer("O"));
  }
}
