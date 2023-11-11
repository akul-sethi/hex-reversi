package cs3500.reversi.view;

import javax.swing.*;

import cs3500.reversi.model.ReadOnlyReversiModel;

public class BasicBoardView extends JPanel implements BoardView {

  private final ReadOnlyReversiModel model;
  public BasicBoardView(ReadOnlyReversiModel model) {
    this.model = model;
  }

}
