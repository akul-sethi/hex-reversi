package cs3500.reversi;


import cs3500.reversi.controller.ReversiController;
import cs3500.reversi.model.GameType;
import cs3500.reversi.model.ReversiCreator;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.CaptureMaxPlayer;
import cs3500.reversi.player.HumanPlayer;
import cs3500.reversi.player.MachinePlayer;
import cs3500.reversi.player.Player;
import cs3500.reversi.player.SuperStrategyPlayer;
import cs3500.reversi.strategy.CaptureMaxStrategy;
import cs3500.reversi.view.GUIReversiView;
import cs3500.reversi.view.ReversiView;

/**
 * Entry point to a game of Reversi.
 */
public final class Reversi {

  /**

   * Actual entry point method.
   * @param args The system args to create the game from.
   */
  public static void main(String[] args) {
    Player p1 = new MachinePlayer("X", new CaptureMaxStrategy());
    Player p2 = new HumanPlayer("O");
    ReversiModel model = ReversiCreator.create(GameType.BASIC, 6, p1, p2);
    ReversiView view1 = new GUIReversiView(model);
    ReversiView view2 = new GUIReversiView(model);
    ReversiController controller1 = new ReversiController(p1, view1, model);
    ReversiController controller2 = new ReversiController(p2, view2, model);
    model.startGame();
  }

}

