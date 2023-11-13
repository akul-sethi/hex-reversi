package cs3500.reversi;

import cs3500.reversi.controller.ReversiController;
import cs3500.reversi.model.GameType;
import cs3500.reversi.model.ReversiCreator;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.CaptureMaxPlayer;
import cs3500.reversi.player.Player;
import cs3500.reversi.player.SuperStrategyPlayer;
import cs3500.reversi.view.GUIReversiView;
import cs3500.reversi.view.ReversiView;


public final class Reversi {
    public static void main(String[] args) {
      Player p1 = new SuperStrategyPlayer("X");
      Player p2 = new CaptureMaxPlayer("O");
      ReversiModel model = ReversiCreator.create(GameType.BASIC, 6, p1, p2);
      ReversiView view = new GUIReversiView(model);
      ReversiController controller = new ReversiController(model);
      controller.setView(view);
      view.setVisible(true);
    }

}
