package cs3500.provider.view;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.swing.JPanel;
import javax.swing.AbstractAction;
import javax.swing.event.MouseInputAdapter;

import reversi.controller.IReversiFeatures;
import reversi.model.Hexagon;
import reversi.model.HexagonState;
import reversi.model.IReadonlyReversiModel;

/**
 * Represents a panel of a ReversiGame, extending JPanel.
 */
public class ReversiPanel extends JPanel {

  private final IReadonlyReversiModel model;
  private HashMap<Map.Entry<Hexagon, HexagonState>, HexagonPath> hexagonPathMap;
  private final int maxRow;
  private Hexagon selectedHex;
  private final List<IReversiFeatures> featuresListeners;

  /**
   * Takes in an IReadonlyReversiModel to display observations about the model.
   * @param model - IReadonlyReversiModel
   */
  public ReversiPanel(IReadonlyReversiModel model) {
    this.model = Objects.requireNonNull(model);
    // how many hexagons on the longest/center row
    this.maxRow = (this.model.getMaxCoordinate() * 2) + 1;
    this.featuresListeners = new ArrayList<>();
    // mouse listener handles user selections of hexagons
    MouseEventsListener mouseListener = new MouseEventsListener();
    this.addMouseListener(mouseListener);

    // adds new actions according to the features' hot keys - move and pass
    this.getActionMap().put("movePlay", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (IReversiFeatures f : featuresListeners) {
          f.movePlay();
        }
      }
    });
    this.getActionMap().put("movePass", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        for (IReversiFeatures f : featuresListeners) {
          f.movePass();
        }
      }
    });
  }

  /**
   * Get the current selected Hexagon.
   * @return the selectedHex
   * @throw IllegalStateException if no hexagon is selected.
   */
  public Hexagon getSelectedHex() {
    if (this.selectedHex == null) {
      throw new IllegalStateException("No hexagon selected.");
    }
    return new Hexagon(this.selectedHex);
  }

  public void deselectHex() {
    this.selectedHex = null;
  }

  // Draw the outline of the board by drawing each hexagon's path
  private void setHexagonPaths() {
    this.hexagonPathMap = new HashMap<>();
    for (Map.Entry<Hexagon, HexagonState> e : this.model.getCopyBoardHashMap().entrySet()) {
      HexagonPath hexPath = new HexagonPath();
      hexPath.createHexPath(this.getHexCenterX(e.getKey()), this.getHexCenterY(e.getKey()));
      this.hexagonPathMap.put(e, hexPath);
    }
  }

  /**
   * Add a given IReversiFeatures (controller) to this panel.
   * @param features the IReversiFeature being added.
   */
  public void addFeaturesListener(IReversiFeatures features) {
    this.featuresListeners.add(Objects.requireNonNull(features));
  }

  @Override
  public Dimension getPreferredSize() {
    // set the new preferred size to 100 by 100
    return new Dimension(100, 100);
  }

  // Preferred logical size allows conversion from hex coordinates to real coordinates
  private Dimension getPreferredLogicalSize() {
    // set the new preferred LOGICAL size to double the maxRow (longest row of hexagons)
    return new Dimension(maxRow * 2, maxRow * 2);
  }

  // Transform LOGICAL coordinates to PHYSICAL
  private AffineTransform transformLogicalToPhysical() {
    AffineTransform ret = new AffineTransform();
    Dimension preferred = getPreferredLogicalSize();
    ret.translate(getWidth() / 2., getHeight() / 2.);
    ret.scale(getWidth() / preferred.getWidth(), getHeight() / preferred.getHeight());
    ret.scale(1, -1);
    return ret;
  }

  // Transform PHYSICAL coordinates to LOGICAL
  private AffineTransform transformPhysicalToLogical() {
    AffineTransform ret = new AffineTransform();
    Dimension preferred = getPreferredLogicalSize();
    ret.scale(1, -1);
    ret.scale(preferred.getWidth() / getWidth(), preferred.getHeight() / getHeight());
    ret.translate(-getWidth() / 2., -getHeight() / 2.);
    return ret;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    this.setHexagonPaths();
    this.setBackground(Color.darkGray);
    Graphics2D g2d = (Graphics2D) g.create();
    g2d.transform(transformLogicalToPhysical()); // makes origin the MIDDLE OF PANEL
    g2d.setStroke(new BasicStroke(.1F)); // thickness of paths

    // Draw the board
    for (Map.Entry<Map.Entry<Hexagon, HexagonState>, HexagonPath> pathEntry
            : this.hexagonPathMap.entrySet()) {
      // draw the hexagon
      g2d.setColor(Color.gray);
      g2d.draw(pathEntry.getValue());
      g2d.setColor(Color.lightGray);
      g2d.fill(pathEntry.getValue());
      // draw the token contained in the hexagon, if there is one
      this.drawToken(g2d, pathEntry.getKey());
    }

    // Paint the selected hex, if there is one
    if (this.selectedHex != null) {
      //System.out.println("selected hex: " + selectedHex); // print what the user highlights
      g2d.setColor(Color.GREEN);
      // create a new, fully green path on top of the hexagon being selected
      HexagonPath highlight = new HexagonPath();
      highlight.createHexPath(this.getHexCenterX(this.selectedHex),
              this.getHexCenterY(this.selectedHex));
      g2d.fill(highlight);
    }
  }

  /**
   * HexagonPath extends Path2D.Double to create a new path that draws a hexagon.
   */
  private class HexagonPath extends Path2D.Double {
    private void createHexPath(double xCenter, double yCenter) {
      double h = ReversiPanel.this.getPreferredLogicalSize().height
              / (Math.ceil(ReversiPanel.this.maxRow * 1.5) / 2);
      double w = (double) ReversiPanel.this.getPreferredLogicalSize().width
              / ReversiPanel.this.maxRow;
      double b = h / 2;
      double x = xCenter;
      double y = yCenter;
      moveTo(x, y + h / 2); //at the top pt of the hexagon
      lineTo(x + w / 2, y + b / 2);
      lineTo(x + w / 2, y - b / 2);
      lineTo(x, y - h / 2);
      lineTo(x - w / 2, y - b / 2);
      lineTo(x - w / 2, y + b / 2);
      lineTo(x, y + h / 2);
      closePath();
    }
  }

  /**
   * Helper method to draw a token on the board given the state of the hexagon.
   * @param g2d Graphics2D
   * @param hexStateEntry the hexagon state being used to draw the token
   */
  private void drawToken(Graphics2D g2d, Map.Entry<Hexagon, HexagonState> hexStateEntry) {
    double circleWidth = ((double)this.getPreferredLogicalSize().width
            / (double)this.maxRow) / 1.5; // circle is proportional to size of hexagons
    Shape circle = new Ellipse2D.Double(
            this.getHexCenterX(hexStateEntry.getKey()) - (circleWidth / 2),
            this.getHexCenterY(hexStateEntry.getKey()) - (circleWidth / 2),
            circleWidth, circleWidth);

    // Set the correct color for this token then fill it -- only if the state is black or white
    if (hexStateEntry.getValue().equals(HexagonState.BLACK)) {
      g2d.setColor(Color.black);
      g2d.fill(circle);
    }
    else if (hexStateEntry.getValue().equals(HexagonState.WHITE)) {
      g2d.setColor(Color.white);
      g2d.fill(circle);
    }
    g2d.setColor(Color.white); // reset color
  }

  // helper method to determine X coordinate center of a hexagon using their S,Q,R coordinates
  private double getHexCenterX(Hexagon h) {
    return h.getS() - h.getQ();
  }

  // helper method to determine Y coordinate center of a hexagon using their S,Q,R coordinates
  private double getHexCenterY(Hexagon h) {
    return - h.getR() * (this.maxRow * 3 / Math.ceil(ReversiPanel.this.maxRow * 1.5));
  }

  // mouse clicks for highlighting are entirely handled by the view
  // (not sent to the controller)

  /**
   * MouseEventsListener extends MouseInputAdapter to handle user selecting of hexagons.
   * (All mouse clicks are entirely handled by the view, while keyboard clicks are
   *  passed/handled in the controller.)
   */
  private class MouseEventsListener extends MouseInputAdapter {

    @Override
    public void mouseClicked(MouseEvent event) {
      Point physicalP = event.getPoint();
      Point2D logicalP = transformPhysicalToLogical().transform(physicalP, null);

      boolean matchFound = false;
      for (Map.Entry<Map.Entry<Hexagon, HexagonState>, HexagonPath> entry
              : ReversiPanel.this.hexagonPathMap.entrySet()) {

        // match has been found -- click is within the board
        if (entry.getValue().contains(logicalP)) {
          Hexagon newHex = entry.getKey().getKey();
          // selection equals the current selection -- deselect
          // OR selection equals a non-empty hexagon -- cannot select hex w/token
          if (newHex.equals(ReversiPanel.this.selectedHex)
                  || !entry.getKey().getValue().equals(HexagonState.EMPTY)) {
            ReversiPanel.this.selectedHex = null;
          }
          // selection does not equal the current selection AND it is an empty hexagon
          else {
            ReversiPanel.this.selectedHex = newHex; // -- update selected
            System.out.println(newHex);
          }
          // break if a match is found -- don't keep searching board
          matchFound = true;
          break;
        }
      }
      // match is not found = click is not within the board -- deselect
      if (!matchFound) {
        ReversiPanel.this.selectedHex = null;
      }

      ReversiPanel.this.repaint();
    }
  }

}
