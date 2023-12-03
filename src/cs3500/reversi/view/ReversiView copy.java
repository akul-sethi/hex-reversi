package cs3500.reversi.view;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import reversi.controller.IReversiFeatures;
import reversi.model.Hexagon;
import reversi.model.HexagonState;
import reversi.model.IReadonlyReversiModel;

/**
 * Represents the view of a ReversiGame, extending JFrame and implementing IReversiView.
 */
public class ReversiView extends JFrame implements IReversiView {

  private final ReversiPanel panel;

  /**
   * Constructor for ReversiView class.
   * @param readonly read only reversi model being viewed.
   */
  public ReversiView(IReadonlyReversiModel readonly) {
    super();

    this.setPreferredSize(new Dimension(500,500));
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    this.panel = new ReversiPanel(readonly);
    this.add(panel);
    this.pack();
  }

  @Override
  public void display(boolean show) {
    this.setVisible(show);
  }

  @Override
  public void setHotKey(KeyStroke key, String featureName) {
    this.panel.getInputMap().put(key, featureName);
  }

  @Override
  public void addFeatureListener(IReversiFeatures features) {
    this.panel.addFeaturesListener(features);
  }

  @Override
  public void repaint() {
    this.panel.repaint();
  }

  @Override
  public Hexagon getSelectedHex() {
    return this.panel.getSelectedHex();
  }

  @Override
  public void deselect() {
    this.panel.deselectHex();
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error);
  }

  @Override
  public void setTitle(HexagonState hexState) {
    String title = "Player ";
    if (hexState.equals(HexagonState.BLACK)) {
      title += "1";
    }
    if (hexState.equals(HexagonState.WHITE)) {
      title += "2";
    }
    super.setTitle(title);
  }
}
