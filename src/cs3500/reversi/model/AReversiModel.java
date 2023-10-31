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
  /**
   * this.tiles.size() >= 1 is a class invariant.
   * The map for tiles to players
   */
  protected final HashMap<CubeCoord, Player> tiles;

  /**
   * Length of players > 1 is a class invariant.
   */
  protected final Queue<Player> players;
  private int passCount = 0;
  private boolean gameOver;

  /**
   * Constructor abstract reversi model.
   * @param hexs A map of hex tiles to put in the board.
   * @param players The list of players that will be in the game.
   */
  protected AReversiModel(HashMap<CubeCoord, Player> hexs, List<Player> players) {
    if(players.size() <= 1) {
      throw new IllegalArgumentException("Length of players must be greater than 1");
    }
    if(hexs.isEmpty()) {
      throw new IllegalArgumentException("Size of hexes must be greater than 0");
    }
    this.tiles = new HashMap<>();
    this.tiles.putAll(hexs);
    this.players = new LinkedList<>();
    this.players.addAll(players);
  }

  /**
   * The option to pass your turn. Puts the next player up.
   */
  @Override
  public void pass() {
    requireGameNotOver();
    this.passCount += 1;
    changeTurn();
  }

  private void requireGameNotOver() {
    if(gameOver) {
      throw new IllegalStateException("Game over");
    }
  }

  private void changeTurn() {
    this.players.add(this.players.remove());
    if(passCount == this.players.size()) {
      this.gameOver = true;
    }
  }

  /**
   * The option to place a piece on the board.
   * @param row The row to place the piece.
   * @param column The column to place the piece.
   */
  @Override
  public void placePiece(int row, int column) throws IllegalArgumentException,
          IllegalStateException {
    this.passCount = 0;
    requireGameNotOver();
    List<Row> rows = getRadiatingRows(row, column);
    for (Row r : rows) {
      if (r.length > 0 && validCoord(r.next()) &&
              this.tiles.get(r.next()) == this.players.peek()) {
        for (CubeCoord c : r.getCoordsInRow()) {
          this.tiles.put(c, this.players.peek());
        }
        this.tiles.put(new CubeCoord(row, column), this.players.peek());
      }
    }
    changeTurn();
  }

  /**
   * Returns the player at given coordinate, null if nobody there, error if it doesn't exist.
   * @param row The row which is being queried.
   * @param column The column which is being queried.
   * @return The player at given coordinate, null if nobody is there.
   * @throws IllegalArgumentException if the coordinate doesn't exist.
   */
  @Override
  public Player playerAt(int row, int column) throws IllegalArgumentException {
    return playerAt(new CubeCoord(row, column));
  }

  /**
   * Returns the player at the specified coordinate.
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
   * Returns a list of rows in all six directions from given coordinate.
   * @param row The row to look at lines from.
   * @param column The column to look at lines from.
   * @return A list of rows in all six directions from given coordinate.
   * @throws IllegalStateException If somebody already put a tile at given coordinate.
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

  /**
   * Gets the winner of the game, determined by tile count on the board.
   * @return The player that has won the game.
   * @throws IllegalStateException If the game hasn't been won, each player hasn't skipped.
   */
  @Override
  public Player getWinner() throws IllegalStateException {
    if (!gameOver) {
      throw new IllegalStateException("There is no winner");
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

  //returns true if given coordinate exists in tiles.
  private boolean validCoord(CubeCoord coordinate) {
    return this.tiles.containsKey(coordinate);
  }

  /**
   * Finds the bottom most row.
   * @return The index of the lowest row (middle row is indexed 0)
   */
  @Override
  public int getBottomRow() {
    int min = this.tiles.keySet().stream().findAny().get().row();
    for (CubeCoord c : this.tiles.keySet()) {
      if (c.row() < min) {
        min = c.row();
      }
    }
    return min;
  }

  /**
   * Finds the top most row.
   * @return The index of the highest row (middle row is indexed 0)
   */
  @Override
  public int getTopRow() {
    int max = this.tiles.keySet().stream().findAny().get().row();
    for (CubeCoord c : this.tiles.keySet()) {
      if (c.row() > max) {
        max = c.row();
      }
    }
    return max;
  }

  /**
   * Finds the left most row.
   * @return The index of the leftest row (middle row is indexed 0)
   */
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

  /**
   * Finds the right most row.
   * @return The index of the rightest row (middle row is indexed 0)
   */
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
  public boolean validMoveExists() {
    boolean validMove = false;
    try {
      for(int row = getTopRow(); row < getBottomRow(); row++) {
        for(int col = getLeftCol(); col < getRightCol(); col++) {
          List<Row> rows = getRadiatingRows(row, col);
          for(Row r : rows) {
            if(r.length != 0 && validCoord(r.next()) &&
                    this.tiles.get(r.next()).equals(this.players.peek())) {
              return true;
            }
          }
        }
      }
    } catch(IllegalArgumentException | IllegalStateException e) {
      validMove = false;
    }
    return validMove;
  }
}
