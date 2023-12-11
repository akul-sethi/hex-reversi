package cs3500.reversi.view;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import cs3500.reversi.model.BasicPoint;
import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.player.Player;

public class SquareBoardView extends ABoardView {
  private static final int SIDE_LENGTH = 45;
  private static final double SQUARE_HEIGHT = SIDE_LENGTH * 2;
  private static final double VERT_GAP = SIDE_LENGTH * Math.sqrt(2);
  private static final double SQUARE_WIDTH = SIDE_LENGTH * Math.sqrt(2);

  /**
   * Creates a BasicBoardView backed by the given model.
   */
  SquareBoardView(ReadOnlyReversiModel model) {
    super(model, SIDE_LENGTH, SQUARE_HEIGHT, VERT_GAP, SQUARE_WIDTH);
  }

  ArrayList<ArrayList<Tile>> getTiles() {
    setTransform();
    ArrayList<ArrayList<ABoardView.Tile>> output = new ArrayList<>();

    for (int row = this.model.getTopRow(); row <= this.model.getBottomRow(); row++) {
      output.add(new ArrayList<>());
      for (int column = this.model.getLeftCol(); column <= this.model.getRightCol(); column++) {
        ArrayList<ABoardView.Tile> rowList = output.get(output.size() - 1);
        try {
          Player p = this.model.playerAt(new BasicPoint(row, column));
          Square square = new Square(SQUARE_WIDTH * column, VERT_GAP *
                  row, SIDE_LENGTH);
          rowList.add(new ABoardView.Tile(square, p, row, column));
        } catch (IllegalArgumentException e) {
          rowList.add(null);
        }
      }
    }
    return output;
  }


}
