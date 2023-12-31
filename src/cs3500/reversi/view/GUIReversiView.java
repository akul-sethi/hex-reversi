package cs3500.reversi.view;

import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.KeyStroke;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import java.awt.Font;

import cs3500.reversi.controller.InputObserver;
import cs3500.reversi.model.ReadOnlyReversiModel;

/**
 * A concrete implementation of a view which has a GUI and a board which supports previewing.
 */
public final class GUIReversiView extends JFrame implements ReversiView {
  private BasicBoardView board;
  private Optional<String> name;

  /**
   * Creates a GUIReversiView using the given ReadOnlyReversiModel m.
   *
   * @throws NullPointerException If the given model is null
   */
  public GUIReversiView(ReadOnlyReversiModel m) {
    super("Reversi");
    Objects.requireNonNull(m);

    setSize(500, 300);
    setLocation(200, 200);
    setResizable(false);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());
    BasicBoardView basic = new BasicBoardView(m);
    this.board = basic;
    add(basic, BorderLayout.CENTER);


    this.setHotKey(KeyStroke.getKeyStroke("typed p"), "pass");
    this.setHotKey(KeyStroke.getKeyStroke("typed m"), "moveHere");

    pack();
  }


  /**
   * Creates a view with a given name indicating who the view represents.
   */
  public GUIReversiView(ReadOnlyReversiModel model, String name) {
    this(model);
    Font font = new Font("SansSerif", Font.BOLD, 20);
    JLabel label = new JLabel(name, SwingConstants.CENTER);
    label.setFont(font);
    this.add(label, BorderLayout.NORTH);
    pack();
  }

  @Override
  public void render() throws IOException {
    this.board.refresh();
  }

  public MouseListener[] getBoardMouseListeners() {
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
