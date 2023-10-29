package cs3500.reversi.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import cs3500.reversi.Player;

abstract class AReversiModel implements ReversiModel {


  //Map of CubeCoord to Player. All spaces on the board are represented as CubeCoords.
  //The tile they contain is represented with a Player. If the space is empty, the tile is null.
  protected final Map<CubeCoord, Player> tiles;

  //True if game is started, False otherwise.
  protected boolean gameStarted;


  //The Queue of players. The first player in the queue is next to move.
  protected final Queue<Player> players;
  //The current count of consecutive passes. Incremented on any pass, set to zero on any move.
  private int passCount = 0;

  //The max allowed number of passes. If this number is PASSED, then the game is over.
  private final int maxPasses;

  /**
   * The constructor for an abstract reversi model.
   * Some design choices:
   * The constructor is passed in a Map of CubeCoord to Player. We originally had it designed
   * as getting passed in a list of CubeCåoords instead, but due to not knowing where the starting
   * tiles would then be, we decided to have it get passed a Map.
   * The constructor is also passed a List of players instead of Queue. This is because we
   * thought it would be better to have the constructor do more and be easier to use.
   * In some versions, the board is started as empty, and the players are given an amount of
   * start moves to place tiles without capturing, creating the board layout. This will be easy
   * to implement if needed in the future, with multiple constructors.
   *
   * @param hexMap  A Map of CubeCoords to Players.
   * @param players A List of Players, converted to a queue and stored (by order).
   */
  protected AReversiModel(Map<CubeCoord, Player> hexMap, List<Player> players) {
    this.gameStarted = false;
    this.players = new LinkedList<>();
    this.players.addAll(players);
    this.maxPasses = this.players.size() - 1;
    this.tiles = new HashMap<>();
    this.tiles.putAll(hexMap);
  }


  @Override
   public void pass() {
     this.players.add(this.players.remove());
   }

  @Override
  public void placePiece(int row, int column) {
    requireValidMove(row, column);
  }

  private void requireValidMove(int row, int column) throws IllegalStateException,
          IllegalArgumentException {
    CubeCoord move = new CubeCoord(row, column);
    if(!this.tiles.containsKey(move)) { throw new IllegalArgumentException("Invalid coordinate");}
    if(this.tiles.get(move) != null) {throw new IllegalStateException("Someone is already here");}



    this.pass();
  }

  @Override
  public int getWidth() {
    int max = 0;
    for(CubeCoord c : this.tiles.keySet()) {
      if (c.row() > max) {
        max = c.column();
      }
    }
    return max;
  }

  @Override
  public int getHeight() {
     int max = 0;
     for(CubeCoord c : this.tiles.keySet()) {
       if (c.row() > max) {
         max = c.row();
       }
     }
    return max;
  }




}
