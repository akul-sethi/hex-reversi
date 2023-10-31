package cs3500.reversi.view;

import java.io.IOException;

import cs3500.reversi.Player;
import cs3500.reversi.model.ReversiModel;

public class TextReversiView implements ReversiView {
  private final ReversiModel model;
  private final Appendable appendable;

  public TextReversiView(ReversiModel model, Appendable appendable) {
    this.model = model;
    this.appendable = appendable;
  }

  @Override
  public void render() throws IOException {
    assert this.appendable != null;
    this.appendable.append(this.toString());
  }

  @Override
  public String toString() {
    String rendering = "";
    for (int row = 0; row < this.model.getHeight(); row += 1) {
      rendering += renderRow(row) + "\n";
    }
    return rendering;
  }

  private String renderRow(int row) {
    int rows = this.model.getHeight();
    int columns = this.model.getWidth();
    int middleRow = rows / 2 + 1;
    int distanceFromMiddle = Math.abs(middleRow - row);
    int numInRow = columns - distanceFromMiddle;
    String rowString = "";
    for (int blanks = 0; blanks < distanceFromMiddle; blanks += 1) {
      rowString += " ";
    }
    for (int col = 0; col < numInRow; col += 1) {
      Player chipInPos = this.model.playerAt(row, col);
      if (chipInPos == null) {
        rowString += "  ";
      }
      else {
        rowString += chipInPos + " ";
      }
    }
    return rowString;
  }
}
