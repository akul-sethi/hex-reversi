package cs3500.reversi.view;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Optional;

import javax.swing.*;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.player.Player;
import cs3500.reversi.model.ReadOnlyReversiModel;

public class BasicBoardView extends JPanel implements BoardView {
  private final int SIDE_LENGTH = 30;
  private final double HEX_HEIGHT = 2 * SIDE_LENGTH;
  private final double VERT_GAP = HEX_HEIGHT * 0.75;
  private final double HEX_WIDTH = Math.sqrt(3) * SIDE_LENGTH;
  private Optional<Hexagon> selected;
  private  AffineTransform at;

  private final ReadOnlyReversiModel model;
  public BasicBoardView(ReadOnlyReversiModel model) {
    this.model = model;
    this.setVisible(true);
    this.selected = Optional.empty();
    this.setFocusable(true);
    this.requestFocus();
    setTransform();
  }

  private void setTransform() {
    this.at = new AffineTransform();

    this.at.translate(-HEX_WIDTH*(this.model.getLeftCol() - 0.5),
            -VERT_GAP * (this.model.getTopRow()) + 0.5*HEX_HEIGHT);
  }

  public void addFeatures(Features features) {
    this.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        attempyPreview(e, features);
      }

    });



    this.addKeyListener(new KeyAdapter() {
      @Override
      public void keyTyped(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_P) {
          System.out.println("adsfadsf");
          features.move();
        }
      }

      @Override
      public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_P) {
          System.out.println("adsfadsf");
          features.move();
        }
      }
    });
  }

  private void attempyPreview(MouseEvent e, Features features) {
    ArrayList<ArrayList<Tile>> hexs = getTiles();
    for (int row = 0; row < hexs.size(); row++) {
      for (int column = 0; column < hexs.get(row).size(); column++) {
        Tile t = hexs.get(row).get(column);
         if(t == null || t.player != null) {
           continue;
         }
         try {
           Point2D transformedPoint = new Point();
           this.at.inverseTransform(e.getPoint(), transformedPoint);
           if (t.hex.contains(transformedPoint)) {
             features.previewMove(row, column);
           }
         } catch (NoninvertibleTransformException exc) {
           //This will not happen inshallah
         }
      }
    }
  }

  @Override
  public Dimension getPreferredSize() {
    Dimension logicalSize = this.getPreferredLogicalSize();
    return new Dimension((int)(logicalSize.width * HEX_WIDTH),
            (int)(logicalSize.height * VERT_GAP + HEX_HEIGHT / 4));
  }


  private Dimension getPreferredLogicalSize() {
    return new Dimension(this.model.getRightCol() - this.model.getLeftCol() + 1,
            this.model.getBottomRow() - this.model.getTopRow() + 1);
  }

  @Override
  public void paintComponent(Graphics g) {
      super.paintComponent(g);
      Graphics2D g2 = (Graphics2D)g;
      AffineTransform oldTransform =  g2.getTransform();

      g2.transform(this.at);

      ArrayList<ArrayList<Tile>> hexs = getTiles();

      for(int row = 0; row < hexs.size(); row++) {
        for(int column = 0; column < hexs.get(row).size(); column++) {
              Tile t = hexs.get(row).get(column);
          if(t == null) {
                  continue;
              }

              g2.setColor(Color.BLACK);
              g2.draw(t.hex);

              Player p = t.player;

              if(p == null) {
                g2.setColor(Color.GRAY);
              } else if(p.toString().equals("X")) {
                g2.setColor(Color.BLACK);
              } else {
                g2.setColor(Color.WHITE);
              }
              g2.fill(t.hex);
        }
      }

      selected.ifPresent((hexagon) -> {
          g2.setColor(Color.BLUE);
          g2.fill(hexagon);
      });

      g2.setTransform(oldTransform);
  }

  public void previewMove(int row, int column) {
      Tile t =  getTiles().get(row).get(column);
      if(t == null) {
      } else if(this.selected.isPresent() && t.hex.equals(this.selected.get())) {
        this.selected = Optional.empty();
      } else if(t.player == null) {
        this.selected = Optional.of(t.hex);
      }
      repaint();
  }

  public void refresh() {
    repaint();
  }

  private ArrayList<ArrayList<Tile>> getTiles() {
    setTransform();
    ArrayList<ArrayList<Tile>> output = new ArrayList<>();

    for(int row = this.model.getTopRow(); row <= this.model.getBottomRow(); row++) {
      output.add(new ArrayList<>());
      for(int column = this.model.getLeftCol(); column <= this.model.getRightCol(); column++) {
        ArrayList<Tile> rowList = output.get(output.size() - 1);
        try {
          double shiftedColumn = column  + (row&1) * 0.5;
          Player p = this.model.playerAt(row, column);
          Hexagon hex = new Hexagon(HEX_WIDTH * shiftedColumn, VERT_GAP *
                  row, SIDE_LENGTH);
          rowList.add(new Tile(hex, p));
        } catch (IllegalArgumentException e) {
          rowList.add(null);
        }
      }
    }
    return output;
  }

}
