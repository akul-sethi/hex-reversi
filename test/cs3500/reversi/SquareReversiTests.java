package cs3500.reversi;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import cs3500.reversi.model.BasicPoint;
import cs3500.reversi.model.BasicSquareReversi;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.MachinePlayer;
import cs3500.reversi.player.Name;
import cs3500.reversi.strategy.CaptureMaxStrategy;
import cs3500.reversi.strategy.MiniMaxStrategy;
import cs3500.reversi.view.ReversiView;
import cs3500.reversi.view.TextReversiView;

public class SquareReversiTests {
  @Test
  public void gameJustStartedSquareTextView() throws IOException {
    Appendable emptyBuilder = new StringBuilder();
    ReversiModel basicModel = new BasicSquareReversi(6,
            new MachinePlayer(Name.X, new MiniMaxStrategy()),
            new MachinePlayer(Name.O, new CaptureMaxStrategy()));
    ReversiView textView = new TextReversiView(basicModel, emptyBuilder, true);
    basicModel.startGame();
    textView.render();
    Assert.assertEquals("- - - - - - \n" +
            "- - - - - - \n" +
            "- - X O - - \n" +
            "- - O X - - \n" +
            "- - - - - - \n" +
            "- - - - - - \n", emptyBuilder.toString());
  }

  @Test
  public void aFewMovesSquareTextView() throws IOException {
    Appendable emptyBuilder = new StringBuilder();
    ReversiModel basicModel = new BasicSquareReversi(6,
            new MachinePlayer(Name.X, new MiniMaxStrategy()),
            new MachinePlayer(Name.O, new CaptureMaxStrategy()));
    ReversiView textView = new TextReversiView(basicModel, emptyBuilder, true);
    basicModel.startGame();
    textView.render();
    int movesDone = 0;
    for (int moves = 0; moves < 100; moves += 1) {
      for (int col = 0; col < 6; col += 1) {
        for (int row = 0; row < 6; row += 1) {
          try {
            basicModel.placePiece(new BasicPoint(row, col));
            textView.render();
            movesDone += 1;
            break;
          }
          catch (IllegalStateException e) {
          }
        }
        if (movesDone >= moves + 1) {
          break;
        }
      }
    }
    Assert.assertEquals("- - - - - - \n" +
            "- - - - - - \n" +
            "- - X O - - \n" +
            "- - O X - - \n" +
            "- - - - - - \n" +
            "- - - - - - \n", emptyBuilder.toString());
  }
}
