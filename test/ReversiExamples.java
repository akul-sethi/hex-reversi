import org.junit.Assert;
import org.junit.Test;
import org.w3c.dom.Text;

import java.io.IOException;

import cs3500.reversi.HumanPlayer;
import cs3500.reversi.model.GameType;
import cs3500.reversi.model.ReversiCreator;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.view.ReversiView;
import cs3500.reversi.view.TextReversiView;

public class ReversiExamples {

  @Test
  public void basicGameTestDimensions() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);;
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    Assert.assertEquals(-5, basicModel.getLeftCol());
    Assert.assertEquals(5, basicModel.getRightCol());
    Assert.assertEquals(-6, basicModel.getBottomRow());
    Assert.assertEquals(5, basicModel.getTopRow());
    textRV.render();
    Assert.assertEquals("                      \n     - - - - - -       \n    - - - - - - -     \n   - - - - - - - -     \n  - - - - - - - - -   \n - - - - O X - - - -   \n- - - - X - O - - - - \n - - - - O X - - - -   \n  - - - - - - - - -   \n   - - - - - - - -     \n    - - - - - - -     \n     - - - - - -       \n", textRV.toString());
  }

  @Test
  public void basicGameAssertStartPlayerCoordinates() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);;
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    Assert.assertEquals(new HumanPlayer("X"), basicModel.playerAt(1, 0));
    Assert.assertEquals(new HumanPlayer("X"), basicModel.playerAt(-1, 0));
    Assert.assertEquals(new HumanPlayer("O"), basicModel.playerAt(0, 1));
    Assert.assertEquals(new HumanPlayer("X"), basicModel.playerAt(0, -1));
    Assert.assertEquals(new HumanPlayer("O"), basicModel.playerAt(1, -1));
    Assert.assertEquals(new HumanPlayer("O"), basicModel.playerAt(-1, -1));
    Assert.assertEquals(null, basicModel.playerAt(0, 0));
  }

  @Test
  public void basicGameTestSimpleMove() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);;
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.placePiece(1, 1);
    textRV.render();
    Assert.assertEquals("", textRV.toString());
  }
}
