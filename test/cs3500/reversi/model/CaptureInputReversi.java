package cs3500.reversi.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import cs3500.reversi.player.CaptureMaxPlayer;
import cs3500.reversi.player.Player;

public class CaptureInputReversi implements ReversiModel {
  final StringBuilder log;
  final Queue<Player> players;
  protected CaptureInputReversi(StringBuilder log, List<Player> players) {
    this.log = log;
    this.players = new LinkedList<Player>(players);
  }

  @Override
  public void placePiece(LinearCoord move) {
    log.append(String.format("placePiece: row = %d, col = %d\n", move.row(), move.column()));
  }

  @Override
  public void pass() {
    Player hold = this.players.remove();
    this.players.add(hold);
    log.append("pass\n");
  }

  @Override
  public boolean validMove(LinearCoord move) {
    log.append(String.format("validMove: row = %d, col = %d\n", move.row(), move.column()));
    return false;
  }

  @Override
  public Player getWinner() {
    log.append("getWinner\n");
    return this.players.peek();
  }

  @Override
  public Player playerAt(LinearCoord move) {
    log.append(String.format("playerAt: row = %d, col = %d\n", move.row(), move.column()));
    return this.players.peek();
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
    return this;
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
