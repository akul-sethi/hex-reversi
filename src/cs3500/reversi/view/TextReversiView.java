package cs3500.reversi.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cs3500.reversi.Player;
import cs3500.reversi.model.ReversiModel;

/**
 * To represent a Reversi game in textual view.
 */
public final class TextReversiView implements ReversiView {
  //model to store
  private final ReversiModel model;

  //appendable to append rendering to
  private final Appendable appendable;

  /**
   * The constructor.
   * @param model a Reversi Model passed in.
   * @param appendable an appendable to render output to.
   */
  public TextReversiView(ReversiModel model, Appendable appendable) {
    this.model = model;
    this.appendable = appendable;
  }

  /**
   * "Renders" the current game state of the model. Appends it to the appendable.
   * @throws IOException
   */
  @Override
  public void render() throws IOException {
    assert this.appendable != null;
    this.appendable.append(this.toString());
  }

  /**
   * Returns a string representation of the current game state.
   * @return A string view of the board.
   */
  @Override
  public String toString() {
    int top = this.model.getTopRow();
    int bottom = this.model.getBottomRow();
    int trueHeight = top - bottom;
    String rendering = "";
    boolean oddRow = (trueHeight - 1) % 4 == 0;
    for (int row = bottom; row < top + 1; row += 1) {
      if (oddRow) {
        rendering += " ";
      }
      rendering += renderRow(row) + "\n";
      oddRow = !oddRow;
    }
    return rendering;
  }

  //Renders a single row of the board.
  private String renderRow(int row) {
    int right = this.model.getRightCol();
    int left = this.model.getLeftCol();
    String rowString = "";
    for (int col = left; col < right + 1; col += 1) {
      try {
        Player chipInPos = this.model.playerAt(row, col);
        if (chipInPos == null) {
          rowString += "- ";
        }
        else {
          rowString += chipInPos + " ";
        }
      }
      catch (IllegalArgumentException e) {
        rowString += "  ";
      }
    }
    return rowString;
  }
}
