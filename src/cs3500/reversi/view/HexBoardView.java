package cs3500.reversi.view;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import cs3500.reversi.model.BasicPoint;
import cs3500.reversi.player.Player;
import cs3500.reversi.model.ReadOnlyReversiModel;

/**
 * A concrete implementation of BoardView which shows the board as it is shown in the assignment.
 */
class HexBoardView extends ABoardView {
  private static final int SIDE_LENGTH = 30;
  private static final double HEX_HEIGHT = 2 * SIDE_LENGTH;
  private static final double VERT_GAP = HEX_HEIGHT * 0.75;
  private static final double HEX_WIDTH = Math.sqrt(3) * SIDE_LENGTH;

  /**
   * Creates a BasicBoardView backed by the given model.
   */
  HexBoardView(ReadOnlyReversiModel model) {
    super(model, SIDE_LENGTH, HEX_HEIGHT, VERT_GAP, HEX_WIDTH);
  }

  /*
   * Sets the transform of the board based on the logical coordinates of the model.
   */
  @Override
  protected void setTransform() {
    this.at = new AffineTransform();

    this.at.translate(-HEX_WIDTH * (this.model.getLeftCol() - 0.5),
            -VERT_GAP * (this.model.getTopRow()) + 0.5 * HEX_HEIGHT);
  }

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

}
