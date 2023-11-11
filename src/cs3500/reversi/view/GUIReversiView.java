package cs3500.reversi.view;

import java.io.IOException;

import javax.swing.*;

import cs3500.reversi.model.ReadOnlyReversiModel;

public class GUIReversiView extends JFrame implements ReversiView{
  private final ReadOnlyReversiModel model;
  public GUIReversiView(ReadOnlyReversiModel m) {
    super("Reversi");
    this.model = m;

    setSize(500, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    add(new BasicBoardView(model));

    pack();
    setVisible(true);
  }

  @Override
  public void render() throws IOException {

  }

  @Override
  public void addFeatures(Features features) {

  }

  @Override
  public void setVisible(boolean b) {
    super.setVisible(b);
  }
}
