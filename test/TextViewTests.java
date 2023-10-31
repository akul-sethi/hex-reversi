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
}
