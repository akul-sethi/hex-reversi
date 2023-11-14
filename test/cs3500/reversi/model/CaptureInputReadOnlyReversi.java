package cs3500.reversi.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import cs3500.reversi.player.CaptureMaxPlayer;
import cs3500.reversi.player.Player;

/**
 * A mock implementation of reversi. Used to tests for mutability.
 */
public class CaptureInputReadOnlyReversi implements ReadOnlyReversiModel {
  final StringBuilder log;
  final Queue<Player> players;
  protected CaptureInputReadOnlyReversi(StringBuilder log, List<Player> players) {
    this.log = log;
    this.players = new LinkedList<Player>(players);
  }

  @Override
  public boolean validMove(LinearCoord move) {
    log.append(String.format("validMove: row = %d, col = %d\n", move.row(), move.column()));
    return false;
  }

  @Override
  public Player getWinner() {
    log.append("getWinner\n");
    return new CaptureMaxPlayer("Z");
  }

  @Override
  public Player playerAt(LinearCoord move) {
    log.append(String.format("playerAt: row = %d, col = %d\n", move.row(), move.column()));
    return new CaptureMaxPlayer("Z");
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
    log.append("getModel");
    return new CaptureInputReversi(this.log, new ArrayList<Player>(this.players));
  }

  @Override
  public int getPlayerScore(Player player) {
    log.append("getPlayerScore: " + "player\n");
    return 0;
  }

  @Override
  public Player nextToPlay() {
    log.append("nextToPlay\n");
    return this.players.peek();
  }
}
