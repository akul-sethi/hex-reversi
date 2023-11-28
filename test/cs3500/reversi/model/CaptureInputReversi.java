package cs3500.reversi.model;

import java.util.List;

import cs3500.reversi.controller.ModelObserver;
import cs3500.reversi.player.Player;

/**
 * A mock implementation of Reversi, captures the input, and appends to a log.
 */
public class CaptureInputReversi extends CaptureInputReadOnlyReversi implements ReversiModel {
  private final StringBuilder log;

  protected CaptureInputReversi(StringBuilder log, List<Player> players) {
    super(log, players);
    this.log = log;
  }

  @Override
  public void addObserver(ModelObserver obs) {
    log.append("addObserver\n");
    this.observers.add(obs);
  }

  @Override
  public void startGame() {
    log.append("startGame\n");
    this.gameStarted = true;
    this.notifyObservers();
  }

  private void notifyObservers() {
    for(ModelObserver obs: this.observers) {
      obs.giveControlTo(this.players.peek());
    }
  }

  @Override
  public void placePiece(LinearCoord move) {
    log.append(String.format("placePiece: row = %d, col = %d\n", move.row(), move.column()));
    int row = move.row();
    int column = move.column();
    List<Row> rows = getRadiatingRows(row, column);
    boolean noGoodRows = true;
    for (Row r : rows) {
      if (r.length > 0 && validCoord(r.next())
              && this.tiles.get(r.next()).equals(this.players.peek())) {
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
  }

  @Override
  public void pass() {
    Player hold = this.players.remove();
    this.players.add(hold);
    log.append("pass\n");
  }
}
