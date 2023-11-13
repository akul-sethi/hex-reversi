import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import cs3500.reversi.model.GameType;
import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReversiCreator;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.view.ReversiView;
import cs3500.reversi.view.TextReversiView;

public class StrategyTests {

  @Test
  public void gameJustStartedCaptureMaxTest() throws IOException {
    Appendable emptyBuilder = new StringBuilder();
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    ReversiView textView = new TextReversiView(basicModel, emptyBuilder);
    LinearCoord bestMove = basicModel.nextToPlay().getMove(basicModel);
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
}
