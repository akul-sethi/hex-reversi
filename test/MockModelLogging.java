import cs3500.reversi.controller.ModelObserver;
import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.Player;
import cs3500.reversi.player.PlayerAdapter;

/**
 * A mock implementation of a ReversiModel which logs all inputs made to it. Necessary to test
 * if the controller is making the correct requests to the ReversiModel object it uses.*/
public class MockModelLogging implements ReversiModel {

  public String log;

  public MockModelLogging() {
    this.log = "";
  }

  @Override
  public boolean validMove(LinearCoord coord) {
    this.log += "validMove(" + coord.row() + ", " + coord.column() + ")\n";
    return false;
  }

  @Override
  public PlayerAdapter getWinner() {
    this.log += "getWinner()\n";
    return null;
  }

  @Override
  public boolean gameOver() {
    this.log += "gameOver()\n";
    return false;
  }

  @Override
  public Player playerAt(LinearCoord coord) {
    this.log += "playerAt(" + coord.row() + ", " + coord.column() + ")\n";
    return null;
  }

  @Override
  public int getRightCol() {
    this.log += "getRightCol()\n";
    return 0;
  }

  @Override
  public int getLeftCol() {
    this.log += "getLeftCol()\n";
    return 0;
  }

  @Override
  public int getTopRow() {
    this.log += "getTopRow()\n";
    return 0;
  }

  @Override
  public int getBottomRow() {
    this.log += "getBottomRow()\n";
    return 0;
  }

  @Override
  public ReversiModel getModel() {
    this.log += "getModel()\n";
    return null;
  }

  @Override
  public int getPlayerScore(Player player) {
    this.log += "getPlayerScore(" + player + ")\n";
    return 0;
  }

  @Override
  public Player nextToPlay() {
    this.log += "nextToPlay()\n";
    return null;
  }

  @Override
  public void addObserver(ModelObserver obs) {
    this.log += "addObserver()\n";
  }

  @Override
  public void startGame() {
    this.log += "startGame()\n";
  }

  @Override
  public void placePiece(LinearCoord coord) {
    this.log += "placePiece(" + coord.row() + ", " + coord.column() + ")\n";
  }

  @Override
  public void pass() {
    this.log += "pass()\n";
  }
}
