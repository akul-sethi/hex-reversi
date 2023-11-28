package cs3500.reversi.view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Objects;

import javax.swing.*;

import cs3500.reversi.model.ReadOnlyReversiModel;

/**
 * A concrete implementation of a view which has a GUI and a board which supports previewing.
 */
public final class GUIReversiView extends JFrame implements ReversiView {
  private BasicBoardView board;

  /**
   * Creates a GUIReversiView using the given ReadOnlyReversiModel m.
   * @throws NullPointerException If the given model is null
   */
  public GUIReversiView(ReadOnlyReversiModel m) {
    super("Reversi");
    Objects.requireNonNull(m);

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
  }

  /**
   * Special constructor for setting up the board with a specific view.
   * @param model The model that this view represents
   * @param view  The baord to use.
   * @throws NullPointerException If the view is <code>null</code>*/
  GUIReversiView(ReadOnlyReversiModel model, BasicBoardView view) {
    this(model);
    Objects.requireNonNull(view);
    this.remove(this.board);
    this.board = view;
    this.add(this.board, BorderLayout.CENTER);
  }

  @Override
  public void render() throws IOException {
    this.board.refresh();
  }

  public  MouseListener[] getBoardMouseListeners() {
    return this.board.getMouseListeners();
  }

  @Override
  public void addObserver(InputObserver features) {
    Objects.requireNonNull(features);
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

  void performAction(String actionName, ActionEvent event) {
    this.board.getActionMap().get(actionName).actionPerformed(event);
  }

  @Override
  public void setHotKey(KeyStroke keyStroke, String featureName) {
    this.board.getInputMap().put(keyStroke, featureName);
  }

  @Override
  public void alertMessage(String message) {
    JOptionPane.showMessageDialog(this, message, "Alert",
            JOptionPane.ERROR_MESSAGE);
  }


}
