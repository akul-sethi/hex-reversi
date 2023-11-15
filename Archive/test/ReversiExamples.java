import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import cs3500.reversi.model.BasicPoint;
import cs3500.reversi.model.GameType;
import cs3500.reversi.model.ReversiCreator;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.HumanPlayer;
import cs3500.reversi.view.ReversiView;
import cs3500.reversi.view.TextReversiView;

/**
 * Contains basic example tests for Reversi.
 */
public class ReversiExamples {

  @Test
  public void basicGameTestDimensions() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    Assert.assertEquals(-5, basicModel.getLeftCol());
    Assert.assertEquals(5, basicModel.getRightCol());
    Assert.assertEquals(5, basicModel.getBottomRow());
    Assert.assertEquals(-5, basicModel.getTopRow());
    textRV.render();
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
            "     - - - - - -       \n", textRV.toString());
  }

  @Test
  public void basicGameAssertStartPlayerCoordinates() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    Assert.assertEquals(new HumanPlayer("X"), basicModel.playerAt(new BasicPoint(1, 0)));
    Assert.assertEquals(new HumanPlayer("X"), basicModel.playerAt(new BasicPoint(-1, 0)));
    Assert.assertEquals(new HumanPlayer("O"), basicModel.playerAt(new BasicPoint(0, 1)));
    Assert.assertEquals(new HumanPlayer("X"), basicModel.playerAt(new BasicPoint(0, -1)));
    Assert.assertEquals(new HumanPlayer("O"), basicModel.playerAt(new BasicPoint(1, -1)));
    Assert.assertEquals(new HumanPlayer("O"), basicModel.playerAt(new BasicPoint(-1, -1)));
    Assert.assertEquals(null, basicModel.playerAt(new BasicPoint(0, 0)));
  }

  @Test
  public void basicGameTestSimpleMove() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.placePiece(new BasicPoint(-1, 1));
    textRV.render();
    Assert.assertEquals("     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - - - - - -     \n" +
            "  - - - - - - - - -   \n" +
            " - - - - O X X - - -   \n" +
            "- - - - X - X - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n", textRV.toString());
  }

  @Test
  public void basicGameTestTwoMoves() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.placePiece(new BasicPoint(-1, 1));
    basicModel.placePiece(new BasicPoint(-1, 2));
    textRV.render();
    Assert.assertEquals("     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - - - - - -     \n" +
            "  - - - - - - - - -   \n" +
            " - - - - O O O O - -   \n" +
            "- - - - X - X - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n", textRV.toString());
  }
}