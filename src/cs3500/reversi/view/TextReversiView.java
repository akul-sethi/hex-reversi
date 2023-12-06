package cs3500.reversi.view;

import java.awt.*;
import java.io.IOException;

import javax.swing.KeyStroke;

import cs3500.reversi.controller.InputObserver;
import cs3500.reversi.model.BasicPoint;

import cs3500.reversi.player.Player;
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

  private boolean square;

  /**
   * The constructor.
   *
   * @param model      a Reversi Model passed in.
   * @param appendable an appendable to render output to.
   */
  public TextReversiView(ReversiModel model, Appendable appendable, Boolean square) {
    assert model != null;
    assert appendable != null;
    this.model = model;
    this.appendable = appendable;
    this.square = square;
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
  public void addObserver(InputObserver features) {
    //placeholder
  }

  @Override
  public void setVisible(boolean b) {
    //placeholder
  }


  @Override
  public void resetFocus() {
    //placeholder
  }

  @Override
  public void setHotKey(KeyStroke keyStroke, String featureName) {
    //placeholder
  }

  @Override
  public void alertMessage(String message) {
    //placeholder
  }

  /**
   * Returns a string representation of the current game state.
   *
   * @return A string view of the board.
   */
  @Override
  public String toString() {
    String rendering;
    if (this.square) {
      int top = this.model.getRightCol();
      rendering = "";
      for (int row = 0; row < top + 1; row += 1) {
        rendering += renderRow(row) + "\n";
      }
      return rendering + "\n";
    } else {
      int top = this.model.getTopRow();
      int bottom = this.model.getBottomRow();
      rendering = "";
      for (int row = top; row < bottom + 1; row += 1) {
        boolean oddRow = row % 2 != 0;
        if (oddRow) {
          rendering += " ";
        }
        rendering += renderRow(row) + "\n";
      }
    }
    return rendering;
  }

  //Renders a single row of the board.
  private String renderRow(int row) {
    String rowString;
    if (this.square) {
      int right = this.model.getRightCol();
      rowString = "";
      for (int col = 0; col < right + 1; col += 1) {
        try {
          Player chipInPos = this.model.playerAt(new BasicPoint(row, col));
          if (chipInPos == null) {
            rowString += "- ";
          } else {
            rowString += chipInPos + " ";
          }
        } catch (IllegalArgumentException e) {
          rowString += "  ";
        }
      }
    } else {
      int right = this.model.getRightCol();
      int left = this.model.getLeftCol();
      rowString = "";
      for (int col = left; col < right + 1; col += 1) {
        try {
          Player chipInPos = this.model.playerAt(new BasicPoint(row, col));
          if (chipInPos == null) {
            rowString += "- ";
          } else {
            rowString += chipInPos + " ";
          }
        } catch (IllegalArgumentException e) {
          rowString += "  ";
        }
      }
    }
    return rowString;
  }
}