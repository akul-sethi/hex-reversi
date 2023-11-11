package cs3500.reversi.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;

import javax.sound.midi.Synthesizer;
import javax.swing.*;

import cs3500.reversi.Player;
import cs3500.reversi.model.ReadOnlyReversiModel;

public class BasicBoardView extends JPanel implements BoardView {
  private final double SIDE_LENGTH = 10;

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
      double height = 2 * SIDE_LENGTH;
      double width = Math.sqrt(3) * SIDE_LENGTH;

      double startX = width / 2;
      double startY = height/ 2;
      for(int row = this.model.getTopRow(); row <= this.model.getBottomRow(); row++) {
        for(int column = this.model.getLeftCol(); column <= this.model.getRightCol(); column++) {
            try {

              Player p = this.model.playerAt(row, column);
              double logCol = column - this.model.getLeftCol() + (row&1) * 0.5;

              double logRow = row - this.model.getTopRow();
              Hexagon hex = new Hexagon(width * logCol + startX, height * 0.75 *
                      logRow + startY, SIDE_LENGTH);
              g2.setColor(Color.BLACK);
              g2.draw(hex);
              if(p == null) {
                g2.setColor(Color.GRAY);
              } else if(p.toString().equals("X")) {
                g2.setColor(Color.BLACK);
              } else {
                g2.setColor(Color.WHITE);
              }
              g2.fill(hex);
            } catch (IllegalArgumentException e) {
              //Paint nothing
            }
        }
      }
      g2.setTransform(oldTransform);
  }

  public void previewMove(int row, int column) {

  }

  private ArrayList<ArrayList<Hexagon>> getHexs() {
    ArrayList<ArrayList<Hexagon>> output = new ArrayList<>();
    for(int row = this.model.getTopRow(); row <= this.model.getBottomRow(); row++) {
      for(int column = this.model.getLeftCol(); column <= this.model.getRightCol(); column++) {
        try {

          Player p = this.model.playerAt(row, column);
          double logCol = column - this.model.getLeftCol() + (row&1) * 0.5;

          double logRow = row - this.model.getTopRow();
          Hexagon hex = new Hexagon(width * logCol + startX, height * 0.75 *
                  logRow + startY, SIDE_LENGTH);
          g2.setColor(Color.BLACK);
          g2.draw(hex);
          if(p == null) {
            g2.setColor(Color.GRAY);
          } else if(p.toString().equals("X")) {
            g2.setColor(Color.BLACK);
          } else {
            g2.setColor(Color.WHITE);
          }
          g2.fill(hex);
        } catch (IllegalArgumentException e) {
          output.get()
        }
      }
    }
  }

}
