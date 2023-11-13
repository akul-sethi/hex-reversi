package cs3500.reversi.view;

import java.awt.*;
import java.io.IOException;

import javax.swing.*;

import cs3500.reversi.model.ReadOnlyReversiModel;

public class GUIReversiView extends JFrame implements ReversiView{
  private final ReadOnlyReversiModel model;
  private final BasicBoardView board;
  public GUIReversiView(ReadOnlyReversiModel m) {
    super("Reversi");
    this.model = m;

    setSize(500, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    this.board = new BasicBoardView(model);
    add(this.board, BorderLayout.CENTER);
//    this.resetFocus();

    pack();
    setVisible(true);
  }

  @Override
  public void render() throws IOException {

  }

  @Override
  public void addFeatures(Features features) {
      this.board.addFeatures(features);
  }

  @Override
  public void setVisible(boolean b) {
    super.setVisible(b);
  }

  @Override
  public void previewMove(int row, int column) {
    this.board.previewMove(row, column);
  }

  @Override
  public void refresh() {
    this.board.refresh();
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }


}
