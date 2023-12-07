import cs3500.reversi.controller.ModelObserver;
import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.HumanPlayer;
import cs3500.reversi.player.Name;
import cs3500.reversi.player.Player;
import cs3500.reversi.player.PlayerAdapter;

/**
 * A mock implementation of a ReversiModel which throws an error any time someone tries to pass or
 * move. Also says Player X won with a score of 5 for Player X and a score of 1 for player O.
 * Necessary to test if the controller responds correctly to the model throwing an error and when
 * the game is over.
 */
public class FakeDataThrowingMockModel implements ReversiModel {

  @Override
  public boolean validMove(LinearCoord coord) {
    return false;
  }

  @Override
  public Player getWinner() {
    return new HumanPlayer(Name.X);
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
    if (player.equals(new HumanPlayer(Name.X))) {
      return 5;
    } else {
      return 1;
    }
  }

  @Override
  public Player nextToPlay() {
    return null;
  }

  @Override
  public void addObserver(ModelObserver obs) {
      //IT IS NOT NECESSARY FOR THIS METHOD TO DO ANYTHING IN THIS MOCK
  }

  @Override
  public void startGame() {
      //IT IS NOT NECESSARY FOR THIS METHOD TO DO ANYTHING IN THIS MOCK
  }

  @Override
  public void placePiece(LinearCoord coord) {
    throw new IllegalStateException("I ALWAYS DO THIS");
  }

  @Override
  public void pass() {
    //IT IS NOT NECESSARY FOR THIS METHOD TO DO ANYTHING IN THIS MOCK
  }
}
