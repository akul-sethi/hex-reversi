import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Text;

import java.io.IOException;

import cs3500.reversi.model.GameType;
import cs3500.reversi.model.ReversiCreator;
import cs3500.reversi.model.ReversiModel;
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
    ReversiView textView = new TextReversiView(basicModel, nullApp);
    Assert.assertEquals(1, 2 - 1);
  }

  @Test(expected = AssertionError.class)
  public void nullModelTextView() {
    Appendable basicApp = new StringBuilder();
    ReversiModel nullModel = null;
    ReversiView textView = new TextReversiView(nullModel, basicApp);
    Assert.assertEquals(1, 2 - 1);
  }

  @Test
  public void gameJustStartedTextView() throws IOException {
    Appendable emptyBuilder = new StringBuilder();
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    ReversiView textView = new TextReversiView(basicModel, emptyBuilder);
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
  public void gameJustStartedSmallTextView() throws IOException {
    Appendable emptyBuilder = new StringBuilder();
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 2);
    ReversiView textView = new TextReversiView(basicModel, emptyBuilder);
    textView.render();
    Assert.assertEquals(" O X   \n" +
            "X - O \n" +
            " O X   \n", emptyBuilder.toString());
  }

  @Test
  public void gameJustStartedOddTextView() throws IOException {
    Appendable emptyBuilder = new StringBuilder();
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 7);
    ReversiView textView = new TextReversiView(basicModel, emptyBuilder);
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
    ReversiView textView = new TextReversiView(basicModel, emptyBuilder);
    textView.render();
    Assert.assertEquals("      \n" +
            " O X   \n" +
            "X - O \n" +
            " O X   \n", emptyBuilder.toString());
  }
}
