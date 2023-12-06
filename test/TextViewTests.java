import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import cs3500.reversi.model.BasicHexReversi;
import cs3500.reversi.model.BasicSquareReversi;
import cs3500.reversi.model.GameType;
import cs3500.reversi.model.ReversiCreator;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.MachinePlayer;
import cs3500.reversi.player.Name;
import cs3500.reversi.strategy.CaptureMaxStrategy;
import cs3500.reversi.strategy.MiniMaxStrategy;
import cs3500.reversi.view.ReversiView;
import cs3500.reversi.view.TextReversiView;

/**
 * Represent tests for the textual view of reversi boards.
 */
public class TextViewTests {
  @Test(expected = AssertionError.class)
  public void nullAppendableTextView() {
    Appendable nullApp = null;
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 3);
    ReversiView textView = new TextReversiView(basicModel, nullApp, false);
    Assert.assertEquals(1, 2 - 1);
  }

  @Test(expected = AssertionError.class)
  public void nullModelTextView() {
    Appendable basicApp = new StringBuilder();
    ReversiModel nullModel = null;
    ReversiView textView = new TextReversiView(nullModel, basicApp, false);
    Assert.assertEquals(1, 2 - 1);
  }

  @Test
  public void gameJustStartedTextView() throws IOException {
    Appendable emptyBuilder = new StringBuilder();
    ReversiModel basicModel = new BasicHexReversi(6,
            new MachinePlayer(Name.X, new MiniMaxStrategy()),
            new MachinePlayer(Name.O, new CaptureMaxStrategy()));
    ReversiView textView = new TextReversiView(basicModel, emptyBuilder, false);
    textView.render();
    Assert.assertEquals("     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - - - - - -     \n" +
            "  - - - - - - - - -   \n" +
            " - - - - O X - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n", emptyBuilder.toString());
  }

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
  public void gameJustStartedSmallTextView() throws IOException {
    Appendable emptyBuilder = new StringBuilder();
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 2);
    ReversiView textView = new TextReversiView(basicModel, emptyBuilder, false);
    textView.render();
    Assert.assertEquals(" O X   \n" +
            "X - O \n" +
            " O X   \n", emptyBuilder.toString());
  }

  @Test
  public void gameJustStartedOddTextView() throws IOException {
    Appendable emptyBuilder = new StringBuilder();
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 7);
    ReversiView textView = new TextReversiView(basicModel, emptyBuilder, false);
    textView.render();
    Assert.assertEquals("      - - - - - - -       \n" +
            "     - - - - - - - -       \n" +
            "    - - - - - - - - -     \n" +
            "   - - - - - - - - - -     \n" +
            "  - - - - - - - - - - -   \n" +
            " - - - - - O X - - - - -   \n" +
            "- - - - - X - O - - - - - \n" +
            " - - - - - O X - - - - -   \n" +
            "  - - - - - - - - - - -   \n" +
            "   - - - - - - - - - -     \n" +
            "    - - - - - - - - -     \n" +
            "     - - - - - - - -       \n" +
            "      - - - - - - -       \n", emptyBuilder.toString());
  }

  @Test(expected = Exception.class)
  public void gameJustStartedTooSmallTextView() throws IOException {
    Appendable emptyBuilder = new StringBuilder();
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 1);
    ReversiView textView = new TextReversiView(basicModel, emptyBuilder, false);
    textView.render();
    Assert.assertEquals("      \n" +
            " O X   \n" +
            "X - O \n" +
            " O X   \n", emptyBuilder.toString());
  }
}
