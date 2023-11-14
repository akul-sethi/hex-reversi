package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import cs3500.reversi.player.Player;

/**
 * An abstract representation of a reversi board allowing for games to be played with different
 * board shapes, players, and starting pieces. All boards which are a subset of "odd-r" grids are
 * allowed. Contains common functionality which would be in all reversi games.
 * The coordinate system is stored internally as cube coordinates, but input is taken in offset
 * coordinates. "odd-r" and coordinate systems are explained in the README.txt. Moving right
 * increases columns count and moving down increases rows.
 * This is done as Cube Coordinates make it easy to check which cubes are in a row, but for making
 * queries to the model when viewing, it is simpler to traverse the board as a grid.
 */
abstract class AReversiModel implements ReversiModel {
  /**
   * this.tiles.size() >= 1 is a class invariant.
   * The map for tiles to players. This makes the design scalable as it allows for any board shape.
   * Existence of coordinate indicates the tile exists in the board and the Player indicates who is
   * there. <code>null</code> indicates the tile is empty.
   */
  protected final HashMap<CubeCoord, Player> tiles;

  /**
   * Length of players > 1 is a class invariant.
   * Stores the players who are playing as well as their
   * order. The first person in the queue is the person whose turn it is. This allows for any number
   * of players to play the game but creates the above invariant.
   */
  protected final Queue<Player> players;
  private int passCount = 0;
  private boolean gameOver;

  /**
   * Constructs abstract reversi model. In concrete implementations new board shapes with starting
   * locations and lists of players can easily be passed in to create new game modes.
   *
   * @param hexs    A map of hex tiles to put in the board with <code>null</code> or a Player as
   *                the values corresponding to if the tile is empty or if there is a player in the
   *                starting position.
   * @param players The list of players that will be in the game.
   */
  protected AReversiModel(HashMap<CubeCoord, Player> hexs, List<Player> players) {
    if (players.size() <= 1) {
      throw new IllegalArgumentException("Length of players must be greater than 1");
    }
    if (hexs.isEmpty()) {
      throw new IllegalArgumentException("Size of hexes must be greater than 0");
    }
    this.tiles = new HashMap<>();
    this.tiles.putAll(hexs);
    this.players = new LinkedList<>();
    this.players.addAll(players);
  }

  @Override
  public void pass() {
    requireGameNotOver();
    this.passCount += 1;
    changeTurn();
  }

  //Throws an error if the game is over
  private void requireGameNotOver() {
    if (gameOver) {
      throw new IllegalStateException("Game over");
    }
  }


  //Changes the turn to the next players turn
  private void changeTurn() {
    this.players.add(this.players.remove());
    if (passCount == this.players.size()) {
      this.gameOver = true;
    }
  }

  @Override
  public void placePiece(LinearCoord coord) throws IllegalArgumentException,
          IllegalStateException {
    int row = coord.row();
    int column = coord.column();
    this.passCount = 0;
    requireGameNotOver();
    List<Row> rows = getRadiatingRows(row, column);
    boolean noGoodRows = true;
    for (Row r : rows) {
      if (r.length > 0 && validCoord(r.next()) && this.tiles.get(r.next()) != null &&
              this.tiles.get(r.next()).equals(this.players.peek())) {
        for (CubeCoord c : r.getCoordsInRow()) {
          this.tiles.put(c, this.players.peek());
        }
        this.tiles.put(new CubeCoord(row, column), this.players.peek());
        noGoodRows = false;
      }
    }
    if (noGoodRows) {
      throw new IllegalStateException("Move is not valid");
    }
    changeTurn();
  }


  @Override
  public Player playerAt(LinearCoord coord) throws IllegalArgumentException {
    return playerAt(new CubeCoord(coord.row(), coord.column()));
  }

  /**
   * Returns the player at the specified coordinate.
   *
   * @param coordinate The cube coordinate to find the player at.
   * @return The player at the coordinate, null if nobody is there.
   * @throws IllegalArgumentException If coordinate doesn't exist.
   */
  private Player playerAt(CubeCoord coordinate) throws IllegalArgumentException {
    if (!this.tiles.containsKey(coordinate)) {
      throw new IllegalArgumentException("Coordinates do not exist");
    }
    return this.tiles.get(coordinate);
  }

  /**
   * Returns a list of rows in all six directions from given coordinate which extends until
   * a "wall" is met, an empty cell is met, or a cell containing the current player is met.
   *
   * @param row    The row to look at lines from.
   * @param column The column to look at lines from.
   * @return A list of rows in all six directions from given coordinate.
   * @throws IllegalStateException    If somebody already put a tile at given coordinate.
   * @throws IllegalArgumentException If coordinate is invalid.
   */
  private List<Row> getRadiatingRows(int row, int column) throws IllegalStateException,
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
  public boolean validMove(LinearCoord coord) {
    try {
      List<Row> rows = getRadiatingRows(coord.row(), coord.column());
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


  @Override
  public Player getWinner() throws IllegalStateException {
    if (!gameOver) {
      throw new IllegalStateException("There is no winner");
    }
    ArrayList<Player> winners = new ArrayList<>();
    int max = 0;
    for (Player p : this.players) {
      int numTilesForPlayer = this.getPlayerScore(p);
      if (numTilesForPlayer > max) {
        winners.clear();
        max = numTilesForPlayer;
        winners.add(p);
      } else if (numTilesForPlayer == max) {
        winners.add(p);
      }
    }
    if (winners.size() > 1) {
      return null;
    }
    return winners.get(0);
  }

  @Override
  public int getPlayerScore(Player player) throws IllegalStateException {
    int numTilesForPlayer = 0;
    for (Player val : this.tiles.values()) {
      if (player.equals(val)) {
        numTilesForPlayer += 1;
      }
    }
    return numTilesForPlayer;
  }

  //returns true if given coordinate exists in tiles.
  private boolean validCoord(CubeCoord coordinate) {
    return this.tiles.containsKey(coordinate);
  }

  @Override
  public int getTopRow() {
    int min = this.tiles.keySet().stream().findAny().get().row();
    for (CubeCoord c : this.tiles.keySet()) {
      if (c.row() < min) {
        min = c.row();
      }
    }
    return min;
  }


  @Override
  public int getBottomRow() {
    int max = this.tiles.keySet().stream().findAny().get().row();
    for (CubeCoord c : this.tiles.keySet()) {
      if (c.row() > max) {
        max = c.row();
      }
    }
    return max;
  }


  @Override
  public int getLeftCol() {
    int min = this.tiles.keySet().stream().findAny().get().column();
    for (CubeCoord c : this.tiles.keySet()) {
      if (c.column() < min) {
        min = c.column();
      }
    }
    return min;
  }

  @Override
  public int getRightCol() {
    int max = this.tiles.keySet().stream().findAny().get().column();
    for (CubeCoord c : this.tiles.keySet()) {
      if (c.column() > max) {
        max = c.column();
      }
    }
    return max;
  }

  @Override
  public Player nextToPlay() {
    return this.players.peek();
  }
}
