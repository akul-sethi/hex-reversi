package cs3500.reversi.view;

import java.io.IOException;

import cs3500.reversi.Player;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.model.ReversiModel;

/**
 * To represent a Reversi game in textual view. Renders the board assuming "odd-r" hex board as
 * described in the README.txt.
 */
public final class TextReversiView implements ReversiView {
  //model to store
  private final ReadOnlyReversiModel model;

  //appendable to append rendering to
  private final Appendable appendable;

  /**
   * The constructor.
   *
   * @param model      a Reversi Model passed in.
   * @param appendable an appendable to render output to.
   */
  public TextReversiView(ReversiModel model, Appendable appendable) {
    assert model != null;
    assert appendable != null;
    this.model = model;
    this.appendable = appendable;
  }

  /**
   * "Renders" the current game state of the model. Appends it to the appendable.
   *
   * @throws IOException if appendable is non-appendable.
   */
  @Override
  public void render() throws IOException {
    this.appendable.append(this.toString());
  }

  @Override
  public void addFeatures(Features features) {

  }

  @Override
  public void setVisible(boolean b) {

  }

  /**
   * Returns a string representation of the current game state.
   *
   * @return A string view of the board.
   */
  @Override
  public String toString() {
    int top = this.model.getTopRow();
    int bottom = this.model.getBottomRow();
    String rendering = "";
    for (int row = bottom; row < top + 1; row += 1) {
      boolean oddRow = row % 2 != 0;
      if (oddRow) {
        rendering += " ";
      }
      rendering += renderRow(row) + "\n";
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
        } else {
          rowString += chipInPos + " ";
        }
      } catch (IllegalArgumentException e) {
        rowString += "  ";
      }
    }

    return rowString;

  }
}
