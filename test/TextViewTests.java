import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.view.ReversiView;
import cs3500.reversi.view.TextReversiView;

public class TextViewTests {

  private void setEnvironment() {

  }

  @Test
  public void gameJustStartedTextView() throws IOException {
    Appendable emptyBuilder = new StringBuilder();
    ReversiModel basicModel = new BasicReversi(6);
    ReversiView textView = new TextReversiView(basicModel, emptyBuilder);
    textView.render();
    Assert.assertEquals("                      \n" +
            "     - - - - - -       \n" +
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
    ReversiModel basicModel = new BasicReversi(2);
    ReversiView textView = new TextReversiView(basicModel, emptyBuilder);
    textView.render();
    Assert.assertEquals("      \n" +
            " O X   \n" +
            "X - O \n" +
            " O X   \n", emptyBuilder.toString());
  }

  @Test
  public void gameJustStartedOddTextView() throws IOException {
    Appendable emptyBuilder = new StringBuilder();
    ReversiModel basicModel = new BasicReversi(7);
    ReversiView textView = new TextReversiView(basicModel, emptyBuilder);
    textView.render();
    Assert.assertEquals("                           \n" +
            "      - - - - - - -       \n" +
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

  @Test (expected = Exception.class)
  public void gameJustStartedTooSmallTextView() throws IOException {
    Appendable emptyBuilder = new StringBuilder();
    ReversiModel basicModel = new BasicReversi(1);
    ReversiView textView = new TextReversiView(basicModel, emptyBuilder);
    textView.render();
    Assert.assertEquals("      \n" +
            " O X   \n" +
            "X - O \n" +
            " O X   \n", emptyBuilder.toString());
  }
}
