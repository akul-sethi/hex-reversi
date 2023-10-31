package cs3500.reversi.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    int[] rowExtremes = this.model.getRowExtremes();
    int trueHeight = rowExtremes[1] - rowExtremes[0];
    String rendering = "";
    boolean oddRow = (trueHeight - 1) % 4 == 0;
    for (int row = rowExtremes[0]; row < rowExtremes[1] + 1; row += 1) {
      if (oddRow) {
        rendering += " ";
      }
      rendering += renderRow(row) + "\n";
      oddRow = !oddRow;
    }
    return rendering;
  }

  private String renderRow(int row) {
    int[] colExtremes = this.model.getColExtremes();
    String rowString = "";
    for (int col = colExtremes[0]; col < colExtremes[1] + 1; col += 1) {
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
//  private String renderRow(int row) {
//    List<Player> allInRow = new ArrayList<>();
//    for (Player player : this.model.)
//  }
}
