package cs3500.reversi.view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.NoninvertibleTransformException;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Optional;

import javax.swing.*;

import cs3500.reversi.controller.InputObserver;
import cs3500.reversi.model.BasicPoint;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.player.Player;

abstract class ABoardView extends JPanel implements BoardView {
  final int SIDE_LENGTH;
  final double SHAPE_HEIGHT;
  final double VERT_GAP;
  final double SHAPE_WIDTH;
  Optional<ABoardView.Tile> selected;
  AffineTransform at;
  InputObserver features;

  final ReadOnlyReversiModel model;

  /**
   * Creates a BasicBoardView backed by the given model.
   */
  ABoardView(ReadOnlyReversiModel model, int SIDE_LENGTH, double SHAPE_HEIGHT, double VERT_GAP,
             double SHAPE_WIDTH) {
    this.SIDE_LENGTH = SIDE_LENGTH;
    this.SHAPE_HEIGHT = SHAPE_HEIGHT;
    this.SHAPE_WIDTH = SHAPE_WIDTH;
    this.VERT_GAP = VERT_GAP;
    this.model = model;
    this.setVisible(true);
    this.selected = Optional.empty();

    this.addListeners();
    this.getActionMap().put("moveHere", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        attemptMove();
      }
    });

    this.getActionMap().put("pass", new AbstractAction() {
      @Override
      public void actionPerformed(ActionEvent e) {
        features.pass();
      }
    });
    setTransform();
    this.resetFocus();
  }


  /*
   * Sends the features object a request to move to the coordinates of the selected tile.*/
  void attemptMove() {
    this.selected.ifPresent((tile) -> {
      this.features.moveHere(new BasicPoint(tile.row, tile.column));
    });
  }

  /*
   * Sets the transform of the board based on the logical coordinates of the model.
   */
  void setTransform() {
    this.at = new AffineTransform();

    this.at.translate(-SHAPE_WIDTH * (this.model.getLeftCol() - 0.5),
            -VERT_GAP * (this.model.getTopRow()) + 0.5 * SHAPE_HEIGHT);
  }

  @Override
  public void addObserver(InputObserver features) {
    this.features = features;
  }

  /*
   * Adds listeners to the board which do not need to send their events to be sent to a Features
   * object. Currently, it implements previewing as this is not something which every Reversi game
   * needs. */
  void addListeners() {
    this.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        resetFocus();
        attemptPreview(e);
      }
    });
  }


  /*
   * Attempts to preview a move using a MouseEvent. (Screen Coordinates). */
  void attemptPreview(MouseEvent e) {
    ArrayList<ArrayList<ABoardView.Tile>> shapes = getTiles();
    for (int row = 0; row < shapes.size(); row++) {
      for (int column = 0; column < shapes.get(row).size(); column++) {
        ABoardView.Tile t = shapes.get(row).get(column);
        if (t == null || t.player != null) {
          continue;
        }
        try {
          Point2D transformedPoint = new Point();
          this.at.inverseTransform(e.getPoint(), transformedPoint);
          if (t.shape.contains(transformedPoint)) {
            this.previewMove(row, column);

          }
        } catch (NoninvertibleTransformException exc) {
          // Cannot happen due to our transform
        }
      }
    }
  }

  @Override
  public Dimension getPreferredSize() {
    Dimension logicalSize = this.getPreferredLogicalSize();
    return new Dimension((int) (logicalSize.width * SHAPE_WIDTH),
            (int) (logicalSize.height * VERT_GAP + SHAPE_HEIGHT / 4));
  }


  /*
   * Returns the logical dimensions of the board.*/
  private Dimension getPreferredLogicalSize() {
    return new Dimension(this.model.getRightCol() - this.model.getLeftCol() + 1,
            this.model.getBottomRow() - this.model.getTopRow() + 1);
  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    AffineTransform oldTransform = g2.getTransform();

    g2.transform(this.at);

    ArrayList<ArrayList<ABoardView.Tile>> shapes = getTiles();

    for (int row = 0; row < shapes.size(); row++) {
      for (int column = 0; column < shapes.get(row).size(); column++) {
        ABoardView.Tile t = shapes.get(row).get(column);
        if (t == null) {
          continue;
        }
        g2.setColor(Color.BLACK);
        g2.draw(t.shape);

        Player p = t.player;

        if (p == null) {
          g2.setColor(Color.GRAY);
        } else if (p.toString().equals("X")) {
          g2.setColor(Color.BLACK);
        } else {
          g2.setColor(Color.WHITE);
        }
        g2.fill(t.shape);
      }
    }

    selected.ifPresent((tile) -> {
      g2.setColor(Color.BLUE);
      g2.fill(tile.shape);
    });

    g2.setTransform(oldTransform);
  }

  /*
   Correctly sets the currently previewed move based on the logical coordinates provided.
   (Indexing starts at 0).*/
  void previewMove(int row, int column) {
    ABoardView.Tile t = getTiles().get(row).get(column);
    if (t != null) {
      if (this.selected.isPresent() && t.shape.equals(this.selected.get().shape)) {
        this.selected = Optional.empty();
      } else if (t.player == null) {
        this.selected = Optional.of(t);
      }
    }
    refresh();
  }

  public void refresh() {
    repaint();
  }

  /*
   * Returns A 2-dimensional list of Tiles which is represents the current state of the model.
   * Indexing is shifted to be all non-negative.*/
  ArrayList<ArrayList<ABoardView.Tile>> getTiles() {
    setTransform();
    ArrayList<ArrayList<ABoardView.Tile>> output = new ArrayList<>();

    for (int row = this.model.getTopRow(); row <= this.model.getBottomRow(); row++) {
      output.add(new ArrayList<>());
      for (int column = this.model.getLeftCol(); column <= this.model.getRightCol(); column++) {
        ArrayList<ABoardView.Tile> rowList = output.get(output.size() - 1);
        try {
          double shiftedColumn = column + (row & 1) * 0.5;
          Player p = this.model.playerAt(new BasicPoint(row, column));
          Hexagon hex = new Hexagon(SHAPE_WIDTH * shiftedColumn, VERT_GAP *
                  row, SIDE_LENGTH);
          rowList.add(new ABoardView.Tile(hex, p, row, column));
        } catch (IllegalArgumentException e) {
          rowList.add(null);
        }
      }
    }
    return output;
  }

  @Override
  public void resetFocus() {
    this.setFocusable(true);
    this.requestFocus();
  }


  /**
   * Represents a Tile on the board. Contains its bounding Hexagon and the Player which is within it
   * Fields are public as they are essential to the definition of the class.
   */
  final class Tile {
    //Field is public as by definition the tile must have a Hexagon.
    public final MyShape shape;
    //Field is public as by definition the tile must have a Player.
    public final Player player;
    public final int row;
    public final int column;

    /**
     * Creates a Tile with the given Hexagon and Player.
     */
    Tile(MyShape shape, Player player, int row, int column) {
      this.shape = shape;
      this.player = player;
      this.row = row;
      this.column = column;
    }

  }
}
