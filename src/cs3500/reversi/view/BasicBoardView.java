package cs3500.reversi.view;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Optional;

import javax.swing.*;

import cs3500.reversi.player.Player;
import cs3500.reversi.model.ReadOnlyReversiModel;

public class BasicBoardView extends JPanel implements BoardView {
  private final double SIDE_LENGTH = 30;
  private Optional<Hexagon> selected;

  private final ReadOnlyReversiModel model;
  public BasicBoardView(ReadOnlyReversiModel model) {
    this.model = model;
    this.setVisible(true);
    this.selected = Optional.empty();
  }

  public void addFeatures(Features features) {
    this.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        attempyPreview(e, features);
      }

    });
  }

  private void attempyPreview(MouseEvent e, Features features) {
    ArrayList<ArrayList<Hexagon>> hexs = getHexs();
    for (int row = 0; row < hexs.size(); row++) {
      for (int column = 0; column < hexs.get(row).size(); column++) {
        Hexagon selected = hexs.get(row).get(column);
        if (selected != null && selected.contains(e.getX(), e.getY())) {
          features.previewMove(row, column);
        }
      }
    }
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

      ArrayList<ArrayList<Hexagon>> hexs = getHexs();

      for(int row = 0; row < hexs.size(); row++) {
        for(int column = 0; column < hexs.get(row).size(); column++) {
              int gameRow = row + this.model.getTopRow();
              int gameCol = column + this.model.getLeftCol();

              if(hexs.get(row).get(column) == null) {
                  continue;
              }

              g2.setColor(Color.BLACK);
              g2.draw(hexs.get(row).get(column));

              Player p = this.model.playerAt(gameRow, gameCol);

              if(p == null) {
                g2.setColor(Color.GRAY);
              } else if(p.toString().equals("X")) {
                g2.setColor(Color.BLACK);
              } else {
                g2.setColor(Color.WHITE);
              }
              g2.fill(hexs.get(row).get(column));
        }
      }

      selected.ifPresent((hexagon) -> {
          g2.setColor(Color.BLUE);
          g2.fill(hexagon);
      });


      g2.setTransform(oldTransform);
  }

  public void previewMove(int row, int column) {
      Hexagon clicked =  getHexs().get(row).get(column);
      if(clicked == null) {

      } else if(this.selected.isPresent() && clicked.equals(this.selected.get())) {
        this.selected = Optional.empty();
      } else if(this.model.playerAt(row + model.getTopRow(), column + model.getLeftCol())
        == null) {
        this.selected = Optional.of(clicked);
      }

      repaint();
  }

  public void refresh() {
    repaint();
  }

  private ArrayList<ArrayList<Hexagon>> getHexs() {
    ArrayList<ArrayList<Hexagon>> output = new ArrayList<>();

    double height = 2 * SIDE_LENGTH;
    double width = Math.sqrt(3) * SIDE_LENGTH;

    double startX = width / 2;
    double startY = height/ 2;

    for(int row = this.model.getTopRow(); row <= this.model.getBottomRow(); row++) {
      output.add(new ArrayList<>());
      for(int column = this.model.getLeftCol(); column <= this.model.getRightCol(); column++) {
        ArrayList<Hexagon> rowList = output.get(output.size() - 1);
        try {
          this.model.playerAt(row, column);
          double logCol = column - this.model.getLeftCol() + (row&1) * 0.5;
          double logRow = row - this.model.getTopRow();

          Hexagon hex = new Hexagon(width * logCol + startX, height * 0.75 *
                  logRow + startY, SIDE_LENGTH);
          rowList.add(hex);
        } catch (IllegalArgumentException e) {
          rowList.add(null);
        }
      }
    }
    return output;
  }

}
