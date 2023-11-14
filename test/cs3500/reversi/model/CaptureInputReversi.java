package cs3500.reversi.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import cs3500.reversi.player.CaptureMaxPlayer;
import cs3500.reversi.player.Player;

public class CaptureInputReversi extends CaptureInputReadOnlyReversi implements ReversiModel {
  private final StringBuilder log;
  protected CaptureInputReversi(StringBuilder log, List<Player> players) {
    super(log, players);
    this.log = log;
  }

  @Override
  public void placePiece(LinearCoord move) {
    log.append(String.format("placePiece: row = %d, col = %d\n", move.row(), move.column()));
    int row = move.row();
    int column = move.column();
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
  }

  @Override
  public void pass() {
    Player hold = this.players.remove();
    this.players.add(hold);
    log.append("pass\n");
  }
}
