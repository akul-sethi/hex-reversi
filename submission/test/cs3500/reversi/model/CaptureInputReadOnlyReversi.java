package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import cs3500.reversi.controller.ModelObserver;
import cs3500.reversi.player.HumanPlayer;
import cs3500.reversi.player.Name;
import cs3500.reversi.player.Player;

/**
 * A mock implementation of reversi. Used to tests for mutability.
 */
public class CaptureInputReadOnlyReversi implements ReadOnlyReversiModel {
  private final StringBuilder log;
  final Queue<Player> players;
  final HashMap<CubeCoord, Player> tiles;
  boolean gameStarted = true;
  List<ModelObserver> observers;

  protected CaptureInputReadOnlyReversi(StringBuilder log, List<Player> players) {
    this.tiles = new HashMap<>();
    this.tiles.putAll(makeBoard(6));
    this.log = log;
    this.players = new LinkedList<>(players);
    this.observers = new ArrayList<>();
  }

  private static HashMap<CubeCoord, Player> makeBoard(int sideLength)
          throws IllegalArgumentException {
    if (sideLength < 2) {
      throw new IllegalArgumentException("Side length too small");
    }
    HashMap<CubeCoord, Player> tiles = new HashMap<>();
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

  private static void placeStarting(HashMap<CubeCoord, Player> tiles) {
    tiles.put(new CubeCoord(0, 1, -1), new HumanPlayer(Name.X));
    tiles.put(new CubeCoord(1, -1, 0), new HumanPlayer(Name.X));
    tiles.put(new CubeCoord(1, 0, -1), new HumanPlayer(Name.O));
    tiles.put(new CubeCoord(0, -1, 1), new HumanPlayer(Name.O));
    tiles.put(new CubeCoord(-1, 1, 0), new HumanPlayer(Name.O));
    tiles.put(new CubeCoord(-1, 0, 1), new HumanPlayer(Name.X));
  }

  @Override
  public boolean validMove(LinearCoord move) {
    log.append(String.format("validMove: row = %d, col = %d\n", move.row(), move.column()));
    try {
      List<Row> rows = getRadiatingRows(move.row(), move.column());
      for (Row r : rows) {
        if (r.length > 0 && validCoord(r.next()) && this.tiles.get(r.next()) != null &&
                this.tiles.get(r.next()).equals(this.players.peek())) {
          return true;
        }
      }
    } catch (Exception e) {
      return false;
    }
    return false;
  }

  boolean validCoord(CubeCoord coordinate) {
    return this.tiles.containsKey(coordinate);
  }

  List<Row> getRadiatingRows(int row, int column) throws IllegalStateException,
          IllegalArgumentException {
    CubeCoord move = new CubeCoord(row, column);

    if (!validCoord(move)) {
      throw new IllegalArgumentException("Invalid coordinate");
    }
    if (this.tiles.get(move) != null) {
      throw new IllegalStateException("Someone is already here");
    }

    List<Row> directions = Arrays.asList(
            new Row(0, Direction.UP_RIGHT, move),
            new Row(0, Direction.RIGHT, move),
            new Row(0, Direction.DOWN_RIGHT, move),
            new Row(0, Direction.DOWN_LEFT, move),
            new Row(0, Direction.LEFT, move),
            new Row(0, Direction.UP_LEFT, move));
    for (Row r : directions) {
      while (validCoord(r.next()) &&
              playerAt(r.next()) != null &&
              !playerAt(r.next()).equals(this.players.peek())) {
        r.length += 1;
      }
    }

    return directions;
  }

  @Override
  public Player getWinner() {
    log.append("getWinner\n");
    return new HumanPlayer(Name.X);
  }

  @Override
  public boolean gameOver() {
    log.append("gameOver\n");
    return false;
  }

  @Override
  public Player playerAt(LinearCoord move) {
    log.append(String.format("playerAt: row = %d, col = %d\n", move.row(), move.column()));
    return playerAt(new CubeCoord(move.row(), move.column()));
  }

  private Player playerAt(CubeCoord coordinate) throws IllegalArgumentException {
    if (!this.tiles.containsKey(coordinate)) {
      throw new IllegalArgumentException("Coordinates do not exist");
    }
    return this.tiles.get(coordinate);
  }

  @Override
  public int getRightCol() {
    log.append("getRightCol\n");
    return 5;
  }

  @Override
  public int getLeftCol() {
    log.append("getLeftCol\n");
    return -5;
  }

  @Override
  public int getTopRow() {
    log.append("getTopRow\n");
    return -5;
  }

  @Override
  public int getBottomRow() {
    log.append("getBottomRow\n");
    return 5;
  }

  @Override
  public ReversiModel getModel() {
    log.append("getModel\n");
    return new CaptureInputReversi(this.log, new ArrayList<>(this.players));
  }

  @Override
  public int getPlayerScore(Player player) {
    log.append("getPlayerScore: " + "player\n");
    int numTilesForPlayer = 0;
    for (Player val : this.tiles.values()) {
      if (player.equals(val)) {
        numTilesForPlayer += 1;
      }
    }
    return numTilesForPlayer;
  }

  @Override
  public Player nextToPlay() {
    log.append("nextToPlay\n");
    return this.players.peek();
  }
}
