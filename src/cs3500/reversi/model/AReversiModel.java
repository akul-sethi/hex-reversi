package cs3500.reversi.model;

import java.util.Arrays;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import cs3500.reversi.Player;

abstract class AReversiModel implements ReversiModel {
  /**
   * this.tiles.size() >= 1 is a class invariant.
   */
  protected final HashMap<CubeCoord, Player> tiles;

  /**
   * Length of players > 1 is a class invariant.
   */
  protected final Queue<Player> players;
  private int passCount = 0;

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


  @Override
  public void pass() {
    this.passCount += 1;
    this.players.add(this.players.remove());
  }

  @Override
  public void placePiece(int row, int column) throws IllegalArgumentException,
          IllegalStateException {
    this.passCount = 0;
    List<Row> rows = getRadiatingRows(row, column);
    for (Row r : rows) {
      if (r.length > 0 && validCoord(r.next()) &&
              this.tiles.get(r.next()).equals(this.players.peek())) {
        for (CubeCoord c : r.getCoordsInRow()) {
          this.tiles.put(c, this.players.peek());
        }
        this.tiles.put(new CubeCoord(row, column), this.players.peek());
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

  private List<Row> getRadiatingRows(int row, int column) throws IllegalStateException,
          IllegalArgumentException {
    CubeCoord move = new CubeCoord(row, column);

    if (validCoord(move)) {
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
  public Player getWinner() throws IllegalStateException {
    if (passCount != this.players.size()) {
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

  private boolean validCoord(CubeCoord coordinate) {
    return this.tiles.containsKey(coordinate);
  }


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
