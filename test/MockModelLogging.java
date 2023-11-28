import cs3500.reversi.controller.ModelObserver;
import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.Player;

public class MockModelLogging implements ReversiModel {

  @Override
  public boolean validMove(LinearCoord coord) {
    return false;
  }

  @Override
  public Player getWinner() {
    return null;
  }

  @Override
  public boolean gameOver() {
    return false;
  }

  @Override
  public Player playerAt(LinearCoord coord) {
    return null;
  }

  @Override
  public int getRightCol() {
    return 0;
  }

  @Override
  public int getLeftCol() {
    return 0;
  }

  @Override
  public int getTopRow() {
    return 0;
  }

  @Override
  public int getBottomRow() {
    return 0;
  }

  @Override
  public ReversiModel getModel() {
    return null;
  }

  @Override
  public int getPlayerScore(Player player) {
    return 0;
  }

  @Override
  public Player nextToPlay() {
    return null;
  }

  @Override
  public void addObserver(ModelObserver obs) {

  }

  @Override
  public void startGame() {

  }

  @Override
  public void placePiece(LinearCoord coord) {

  }

  @Override
  public void pass() {

  }
}
