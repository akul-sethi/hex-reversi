import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.player.AbstractPlayer;
import cs3500.reversi.player.Name;
import cs3500.reversi.controller.InputObserver;

/**
 * A mock implementation of a Player which logs all requests to it. */
public class MockPlayerLogging extends AbstractPlayer {

  public String log;

  /**
   * Creates a MockPlayerLogging with an empty log.*/
  MockPlayerLogging(Name name) {
    super(name);
    this.log = "";
  }
  @Override
  public void startTurn(ReadOnlyReversiModel model) {
      this.log += "startTurn()\n";
  }

  @Override
  public void addObserver(InputObserver observer) {
    this.log += "addObserver()\n";
  }


}
