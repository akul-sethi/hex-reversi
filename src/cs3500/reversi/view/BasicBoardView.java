package cs3500.reversi.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;

import javax.swing.*;

import cs3500.reversi.model.ReadOnlyReversiModel;

public class BasicBoardView extends JPanel implements BoardView {

  private final ReadOnlyReversiModel model;
  public BasicBoardView(ReadOnlyReversiModel model) {
    this.model = model;
    this.setVisible(true);
  }

  public void addFeatures(Features features) {
    this.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
         features.previewMove(10, 10);
      }
    });
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(600, 600);
  }

  @Override
  public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      AffineTransform oldTransform =  g2.getTransform();
      g2.translate(getWidth() / 2, getHeight() / 2);
      Hexagon hex = new Hexagon(50);
      g2.setColor(Color.RED);
      g2.fill(hex);
//      for(int row = this.model.getTopRow(); row < this.model.getBottomRow(); row++) {
//        for(int column = this.model.getLeftCol(); column < this.model.getRightCol(); column++) {
//
//        }
//      }
      g2.setTransform(oldTransform);
  }

  public void previewMove(int row, int column) {

  }

}
