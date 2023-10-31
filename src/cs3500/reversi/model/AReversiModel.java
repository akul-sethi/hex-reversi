package cs3500.reversi.model;

import java.util.Arrays;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import cs3500.reversi.Player;

/**
 * An abstract representation of a reversi board.
 * The coordinate system is stored internally as cube coordinates, but input is taken in linear
 * coordinates. The middle tile is indexed at (0, 0).
 * To the left and down is negative. To the right and up is positive.
 */
abstract class AReversiModel implements ReversiModel {
  //The map for tiles to players
  protected final HashMap<CubeCoord, Player> tiles;
  protected boolean gameStarted;

  /**
   * Length of players > 1 is invariant.
   */
  protected final Queue<Player> players;
  private int passCount = 0;

  /**
   * Constructor abstract reversi model.
   * @param hexs A map of hex tiles to put in the board.
   * @param players The list of players that will be in the game.
   */
  protected AReversiModel(HashMap<CubeCoord, Player> hexs, List<Player> players) {
    this.tiles = new HashMap<>();
    this.tiles.putAll(hexs);
    this.gameStarted = false;
    this.players = new LinkedList<>();
    this.players.addAll(players);
  }


  @Override
  public void startGame() {
    if (this.gameStarted) {
      throw new IllegalStateException("Game already started");
    }
    this.gameStarted = true;
  }

  /**
   * The option to pass your turn. Puts the next player up.
   */
  @Override
  public void pass() {
    this.passCount += 1;
    this.players.add(this.players.remove());
  }

  /**
   * The option to place a piece on the board.
   * @param row The row to place the piece.
   * @param column The column to place the piece.
   */
  @Override
  public void placePiece(int row, int column) {
    this.passCount = 0;
    List<Row> rows = getRows(row, column);
    for (Row r : rows) {
      if (r.next() != null && this.tiles.containsKey(r.next()) &&
              this.tiles.get(r.next()).equals(this.players.peek())) {
        for (CubeCoord c : r.getCoordsInRow()) {
          this.tiles.put(c, this.players.peek());
        }
      }
    }
    this.pass();
  }

  @Override
  public Player playerAt(int row, int column) throws IllegalArgumentException {
    return playerAt(new CubeCoord(row, column));
  }


  private Player playerAt(CubeCoord coordinate) throws IllegalArgumentException {
    if (!this.tiles.containsKey(coordinate)) {
      throw new IllegalArgumentException("Coordinates do not exist");
    }
    return this.tiles.get(coordinate);
  }

  private List<Row> getRows(int row, int column) throws IllegalStateException,
          IllegalArgumentException {
    CubeCoord move = new CubeCoord(row, column);

    if (!this.tiles.containsKey(move)) {
      throw new IllegalArgumentException("Invalid coordinate");
    }
    if (this.tiles.get(move) != null) {
      throw new IllegalStateException("Someone is already here");
    }

    List<Row> directions = Arrays.asList(new UpRight(0, move),
            new Right(0, move), new DownRight(0, move), new DownLeft(0, move),
            new Left(0, move),
            new UpLeft(0, move));
    for (Row r : directions) {
      while (validCoord(r.next()) &&
              playerAt(r.next()) != this.players.peek() &&
              playerAt(r.next()) != null) {
        r.length += 1;
      }
    }

    return directions;
  }

  @Override
  public Player getWinner() {
    if (passCount != this.players.size()) {
      return null;
    }
    int max = 0;
    Player best = this.players.peek();
    for (Player p : this.players) {
      int numTilesForPlayer = (int) this.tiles.values().stream().filter(x -> x.equals(p)).count();
      if (numTilesForPlayer > max) {
        max = numTilesForPlayer;
        best = p;
      }
    }
    return best;
  }

  private boolean validCoord(CubeCoord coordinate) {
    return this.tiles.containsKey(coordinate);
  }

  @Override
  public int getWidth() {
    int max = 0;
    for (CubeCoord c : this.tiles.keySet()) {
      if (c.column() > max) {
        max = c.column();
      }
    }
    return max;
  }

  @Override
  public int getHeight() {
    int max = 0;
    for (CubeCoord c : this.tiles.keySet()) {
      if (c.row() > max) {
        max = c.row();
      }
    }
    return max;
  }

  @Override
  public int getBottomRow() {
    int min = 999;
    for (CubeCoord c : this.tiles.keySet()) {
      if (c.row() < min) {
        min = c.row();
      }
    }
    return min;
  }

  @Override
  public int getTopRow() {
    int max = 0;
    for (CubeCoord c : this.tiles.keySet()) {
      if (c.row() > max) {
        max = c.row();
      }
    }
    return max;
  }

  @Override
  public int getLeftCol() {
    int min = 999;
    for (CubeCoord c : this.tiles.keySet()) {
      if (c.column() < min) {
        min = c.column();
      }
    }
    return min;
  }

  @Override
  public int getRightCol() {
    int max = 0;
    for (CubeCoord c : this.tiles.keySet()) {
      if (c.column() > max) {
        max = c.column();
      }
    }
    return max;
  }
}
