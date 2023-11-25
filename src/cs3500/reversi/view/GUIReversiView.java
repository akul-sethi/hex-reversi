package cs3500.reversi.view;

import java.awt.BorderLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.KeyStroke;

import cs3500.reversi.model.ReadOnlyReversiModel;

/**
 * A concrete implementation of a view which has a GUI and a board which supports previewing.
 */
public final class GUIReversiView extends JFrame implements ReversiView {
  private final BasicBoardView board;

  /**
   * Creates a GUIReversiView using the given ReadOnlyReversiModel m.
   */
  public GUIReversiView(ReadOnlyReversiModel m) {
    super("Reversi");

    setSize(500, 300);
    setLocation(200, 200);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    BasicBoardView basic = new BasicBoardView(m);
    this.board = basic;
    add(basic, BorderLayout.CENTER);

    System.out.println((KeyStroke.getKeyStroke("typed p")));
    System.out.println((KeyStroke.getKeyStroke("typed m")));

    this.setHotKey(KeyStroke.getKeyStroke("typed p"), "pass");
    this.setHotKey(KeyStroke.getKeyStroke("typed m"), "moveHere");

    pack();
    setVisible(true);
  }

  @Override
  public void render() throws IOException {
    this.board.refresh();
  }

  @Override
  public void addObserver(InputObserver features) {
    this.board.addObserver(features);
  }

  @Override
  public void setVisible(boolean b) {
    super.setVisible(b);
  }


  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }

  @Override
  public void setHotKey(KeyStroke keyStroke, String featureName) {
    this.board.getInputMap().put(keyStroke, featureName);
  }


}
