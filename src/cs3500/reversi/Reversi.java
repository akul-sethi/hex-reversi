package cs3500.reversi;

import cs3500.reversi.controller.ReversiController;
import cs3500.reversi.model.GameType;
import cs3500.reversi.model.ReversiCreator;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.view.GUIReversiView;
import cs3500.reversi.view.ReversiView;


public final class Reversi {
    public static void main(String[] args) {
      ReversiModel model = ReversiCreator.create(GameType.BASIC, 6);
      ReversiView view = new GUIReversiView(model);
      ReversiController controller = new ReversiController();
      controller.setView(view);
      view.setVisible(true);
    }

}
