package cs3500.reversi.model;

import java.util.HashMap;

import cs3500.provider.IPlayer;
import cs3500.provider.IReversiFeatures;
import cs3500.provider.model.Hexagon;
import cs3500.provider.model.HexagonState;
import cs3500.provider.model.IReversiBoard;
import cs3500.provider.model.IReadonlyReversiModel;
import cs3500.provider.model.IReversiModel;
import cs3500.reversi.controller.ModelObserver;
import cs3500.reversi.player.Player;
import cs3500.reversi.player.PlayerAdapter;

/**
 * A concrete implementation which can act like a IReadOnlyReversiModel (provider model).
 * Consumes a ReadOnlyReversiModel and uses it to implement all
 * methods. Necessary so that the Provider view has a model to display.
 */
public class ReadOnlyModelAdapter implements IReadonlyReversiModel {


  private final ReadOnlyReversiModel delegate;

  /**
   * Creates a ReadOnlyModelAdapter from a ReadOnlyReversiModel.
   */
  public ReadOnlyModelAdapter(ReadOnlyReversiModel adaptee) {
    this.delegate = adaptee;
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public IPlayer getWinner() {
    return new PlayerAdapter(this.delegate.getWinner());
  }

  @Override
  public HexagonState getHexagonStateAt(int s, int q, int r) {
    return AdapterUtils.playerToHexagonState(delegate.playerAt(new CubeCoord(q, r, s)));
  }

  @Override
  public int getMaxCoordinate() {
    return (delegate.getRightCol() - delegate.getLeftCol() - 1) / 2 + 1;
  }

  @Override
  public IPlayer getTurn() {
    return new PlayerAdapter(delegate.nextToPlay());
  }

  @Override
  public int getPlayerScore(IPlayer p) {
    return delegate.getPlayerScore(AdapterUtils.hexagonStateToPlayer(p.getHexagonState()));
  }

  /**
   * This does not need to be implemented as it is not used by either the provides strategies or
   * views.
   */
  @Override
  public ReversiBoard getCopyBoardObject() {
    throw new IllegalStateException("NOT IMPLEMENTED");
  }

  @Override
  public HashMap<Hexagon, HexagonState> getCopyBoardHashMap() {
    HashMap<Hexagon, HexagonState> output = new HashMap<>();
    for (int row = delegate.getTopRow(); row <= delegate.getBottomRow(); row++) {
      for (int column = delegate.getLeftCol(); column <= delegate.getRightCol(); column++) {
        CubeCoord cube = new CubeCoord(row, column);
        try {
          Player p = delegate.playerAt(cube);
          Hexagon hex = AdapterUtils.cubeCoordToHexagon(cube);
          output.put(hex, AdapterUtils.playerToHexagonState(p));
        } catch (IllegalArgumentException e) {
          //DO NOT ADD ANYTHING TO HASHMAP, THE COORD DOES NOT EXIST
        }
      }
    }
    return output;
  }

  @Override
  public IPlayer getPlayer(HexagonState state) {
    return new PlayerAdapter(AdapterUtils.hexagonStateToPlayer(state));
  }

  @Override
  public int getNumPiecesGainedForMove(Hexagon h, HexagonState s) {
    Player player = AdapterUtils.hexagonStateToPlayer(s);
    if (!delegate.nextToPlay().equals(player)) {
      return 0;
    }
    try {
      int scoreBefore = delegate.getPlayerScore(player);
      delegate.getModel().placePiece(AdapterUtils.hexagonToCubeCoord(h));
      int scoreAfter = delegate.getPlayerScore(player);
      return scoreBefore - scoreAfter;
    } catch (IllegalArgumentException | IllegalStateException e) {
      return 0;
    }
  }

  @Override
  public boolean isHexagonNextToCorner(Hexagon h) {
    CubeCoord cube = AdapterUtils.hexagonToCubeCoord(h);
    if (!coordExists(cube)) {
      return false;
    }
    Hexagon left = AdapterUtils.cubeCoordToHexagon(new Row(0, Direction.LEFT, cube).next());
    Hexagon upLeft = AdapterUtils.cubeCoordToHexagon(new Row(0, Direction.UP_LEFT, cube).next());
    Hexagon upRight = AdapterUtils.cubeCoordToHexagon(new Row(0, Direction.UP_RIGHT, cube).next());
    Hexagon right = AdapterUtils.cubeCoordToHexagon(new Row(0, Direction.RIGHT, cube).next());
    Hexagon downRight = AdapterUtils.cubeCoordToHexagon(new Row(0, Direction.DOWN_RIGHT, cube).next());
    Hexagon downLeft = AdapterUtils.cubeCoordToHexagon(new Row(0, Direction.DOWN_LEFT, cube).next());


    return this.isHexagonInCorner(left) || this.isHexagonInCorner(upLeft) ||
            this.isHexagonInCorner(upRight) || this.isHexagonInCorner(right) ||
            this.isHexagonInCorner(downRight) || this.isHexagonInCorner(downLeft);
  }


  @Override
  public boolean isHexagonInCorner(Hexagon h) {
    CubeCoord cube = AdapterUtils.hexagonToCubeCoord(h);

    if (coordExists(cube)) return false;
    int horizontalNeighbors = numNeighborsInDirection(cube, Direction.LEFT) +
            numNeighborsInDirection(cube, Direction.RIGHT);
    int positiveDiagNeighbors = numNeighborsInDirection(cube, Direction.UP_RIGHT) +
            numNeighborsInDirection(cube, Direction.DOWN_LEFT);
    int negativeDiagNeighbors = numNeighborsInDirection(cube, Direction.UP_LEFT) +
            numNeighborsInDirection(cube, Direction.DOWN_RIGHT);
    return horizontalNeighbors < 2 && negativeDiagNeighbors < 2 && positiveDiagNeighbors < 2;
  }

  //Returns if the given coordinate exists in the model
  private boolean coordExists(CubeCoord cube) {
    try {
      delegate.playerAt(cube);
    } catch (IllegalArgumentException e) {
      return true;
    }
    return false;
  }

  //Returns the number of neighbors in the given direction
  private int numNeighborsInDirection(CubeCoord cube, Direction direction) {
    try {
      Row row = new Row(0, direction, cube);
      delegate.playerAt(new BasicPoint(row.next().row(), row.next().column()));
      return 1;
    } catch (IllegalArgumentException e) {
      return 0;
    }
  }

  @Override
  public boolean isValidMove(Hexagon h, HexagonState s) {
    if (!delegate.nextToPlay().equals(AdapterUtils.hexagonStateToPlayer(s))) {
      return false;
    }
    return delegate.validMove(AdapterUtils.hexagonToCubeCoord(h));
  }
}