import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import cs3500.reversi.HumanPlayer;
import cs3500.reversi.model.GameType;
import cs3500.reversi.model.ReversiCreator;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.view.ReversiView;
import cs3500.reversi.view.TextReversiView;

/**
 * Represents tests for sample reversi boards.
 */
public class ReversiExamples {

  @Test
  public void basicGameTestDimensions() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    Assert.assertEquals(-5, basicModel.getLeftCol());
    Assert.assertEquals(5, basicModel.getRightCol());
    Assert.assertEquals(-6, basicModel.getBottomRow());
    Assert.assertEquals(5, basicModel.getTopRow());
    textRV.render();
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
            "     - - - - - -       \n", textRV.toString());
  }

  @Test
  public void basicGameAssertStartPlayerCoordinates() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
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
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.placePiece(-1, 1);
    textRV.render();
    Assert.assertEquals("                      \n" +
            "     - - - - - -       \n" +
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
    basicModel.placePiece(-1, 1);
    basicModel.placePiece(-1, 2);
    textRV.render();
    Assert.assertEquals("                      \n" +
            "     - - - - - -       \n" +
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

  @Test(expected = Exception.class)
  public void basicGameTestInvalidMoveVeryWrong() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.placePiece(4, 4);
    textRV.render();
    Assert.assertEquals(-1, 1);
  }

  @Test(expected = Exception.class)
  public void basicGameTestInvalidMoveOutOfBounds() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 2);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.placePiece(2, 0);
    textRV.render();
    Assert.assertEquals(-1, 1);
  }

  @Test(expected = IllegalStateException.class)
  public void basicGameTestInvalidMoveNoneToFlip() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 2);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.placePiece(0, 0);
    textRV.render();
    Assert.assertEquals(-1, 1);
  }

  @Test(expected = IllegalStateException.class)
  public void basicGameTestTwoMovesGameNotOver() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.placePiece(-1, 1);
    basicModel.placePiece(-1, 2);
    textRV.render();
    Assert.assertEquals("                      \n" +
            "     - - - - - -       \n" +
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
    basicModel.getWinner();
  }

  @Test
  public void basicGameTestTwoPassesGameOver() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.placePiece(-1, 1);
    basicModel.placePiece(-1, 2);
    basicModel.pass();
    basicModel.pass();
    textRV.render();
    Assert.assertEquals("                      \n" +
            "     - - - - - -       \n" +
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
    Assert.assertEquals(new HumanPlayer("O"), basicModel.getWinner());
  }

  @Test
  public void basicGameTestFromStartTwoPassesGameOverIsNull() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.pass();
    basicModel.pass();
    textRV.render();
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
            "     - - - - - -       \n", textRV.toString());
    Assert.assertNull(basicModel.getWinner());
  }

  @Test(expected = IllegalStateException.class)
  public void basicGamePassThreeTimes() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.pass();
    basicModel.pass();
    basicModel.pass();
    textRV.render();
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
            "     - - - - - -       \n", textRV.toString());
  }

  @Test
  public void passCountGetsSetBackToZeroOnMove() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.pass();
    basicModel.placePiece(-1, 1);
    basicModel.pass();
    basicModel.pass();
    textRV.render();
    Assert.assertEquals("                      \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - - - - - -     \n" +
            "  - - - - - - - - -   \n" +
            " - - - - O O O - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n", textRV.toString());
    Assert.assertEquals(new HumanPlayer("O"), basicModel.getWinner());
  }

  @Test(expected = IllegalStateException.class)
  public void cantMoveAfterGameOver() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.pass();
    basicModel.placePiece(-1, 1);
    basicModel.pass();
    basicModel.pass();
    basicModel.placePiece(-2, 1);
    textRV.render();
    Assert.assertEquals("                      \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - - - - - -     \n" +
            "  - - - - - - - - -   \n" +
            " - - - - O O O - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n", textRV.toString());
    Assert.assertEquals(new HumanPlayer("O"), basicModel.getWinner());
  }

  @Test
  public void placeChangesAllDirections() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.placePiece(-1, 1);
    basicModel.placePiece(-1, 2);
    basicModel.pass();
    basicModel.placePiece(1, 1);
    textRV.render();
    Assert.assertEquals("                      \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - - - - - -     \n" +
            "  - - - - - - - - -   \n" +
            " - - - - O O O O - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O O O - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n", textRV.toString());
  }

  @Test
  public void placeChangesDownRightRightUpRight() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.placePiece(-1, 1);
    basicModel.placePiece(-1, 2);
    basicModel.placePiece(-2, 0);
    basicModel.placePiece(2, 0);
    basicModel.pass();
    basicModel.placePiece(-1, -2);
    textRV.render();
    Assert.assertEquals("                      \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - - - - - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - O O O O O - -   \n" +
            "- - - - O - O - - - - \n" +
            " - - - - O O - - - -   \n" +
            "  - - - - O - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n", textRV.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void invalidMoveDiagonals() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.placePiece(-1, 1);
    basicModel.placePiece(-1, 2);
    basicModel.placePiece(-2, 0);
    basicModel.placePiece(2, 0);
    basicModel.pass();
    basicModel.placePiece(-1, -2);
    basicModel.placePiece(0, 0);
    textRV.render();
    Assert.assertEquals("                      \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - - - - - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - O O O O O - -   \n" +
            "- - - - O - O - - - - \n" +
            " - - - - O O - - - -   \n" +
            "  - - - - O - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n", textRV.toString());
  }

  @Test
  public void noMovesLeft() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.placePiece(-1, 1);
    basicModel.placePiece(-1, 2);
    basicModel.placePiece(-2, 0);
    basicModel.placePiece(2, 0);
    basicModel.pass();
    basicModel.placePiece(-1, -2);
    basicModel.pass();
    basicModel.placePiece(-3, -1);
    textRV.render();
    Assert.assertEquals("                      \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - O - - - -     \n" +
            "  - - - - O - - - -   \n" +
            " - - - O O O O O - -   \n" +
            "- - - - O - O - - - - \n" +
            " - - - - O O - - - -   \n" +
            "  - - - - O - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n", textRV.toString());
  }

  @Test
  public void playerAtValid() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.placePiece(-1, 1);
    basicModel.placePiece(-1, 2);
    basicModel.placePiece(-2, 0);
    basicModel.placePiece(2, 0);
    basicModel.pass();
    basicModel.placePiece(-1, -2);
    basicModel.pass();
    basicModel.placePiece(-3, -1);
    textRV.render();
    Assert.assertEquals("                      \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - O - - - -     \n" +
            "  - - - - O - - - -   \n" +
            " - - - O O O O O - -   \n" +
            "- - - - O - O - - - - \n" +
            " - - - - O O - - - -   \n" +
            "  - - - - O - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n", textRV.toString());
    Assert.assertEquals(basicModel.playerAt(1,0), new HumanPlayer("O"));
    Assert.assertEquals(basicModel.playerAt(3,0), null);
    Assert.assertEquals(basicModel.playerAt(-3,-1), new HumanPlayer("O"));
  }

  @Test (expected = IllegalArgumentException.class)
  public void playerAtOutOfBounds() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.placePiece(-1, 1);
    basicModel.placePiece(-1, 2);
    basicModel.placePiece(-2, 0);
    basicModel.placePiece(2, 0);
    basicModel.pass();
    basicModel.placePiece(-1, -2);
    basicModel.pass();
    basicModel.placePiece(-3, -1);
    textRV.render();
    Assert.assertEquals("                      \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - O - - - -     \n" +
            "  - - - - O - - - -   \n" +
            " - - - O O O O O - -   \n" +
            "- - - - O - O - - - - \n" +
            " - - - - O O - - - -   \n" +
            "  - - - - O - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n", textRV.toString());
    Assert.assertEquals(basicModel.playerAt(1,0), new HumanPlayer("O"));
    Assert.assertEquals(basicModel.playerAt(9,0), null);
  }

  @Test
  public void onlyGivesYouTilesOnYourMove() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.placePiece(1, -2);
    basicModel.placePiece(-1, 1);
    basicModel.pass();
    basicModel.placePiece(2, 0);
    basicModel.placePiece(1, 1);
    textRV.render();
    Assert.assertEquals("                      \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - - - - - -     \n" +
            "  - - - - - - - - -   \n" +
            " - - - - O O O - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - X X X X - - -   \n" +
            "  - - - - O - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n", textRV.toString());
  }

  @Test
  public void onlyConvertsUpToYourNextTile() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.placePiece(1, -2);
    basicModel.placePiece(-1, 1);
    basicModel.pass();
    basicModel.placePiece(2, 0);
    basicModel.placePiece(1, 1);
    basicModel.pass();
    basicModel.placePiece(-2, 2);

    textRV.render();
    Assert.assertEquals("                      \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - - - - - -     \n" +
            "  - - - - - - X - -   \n" +
            " - - - - O O X - - -   \n" +
            "- - - - X - X - - - - \n" +
            " - - - X X X X - - -   \n" +
            "  - - - - O - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n", textRV.toString());
  }

  @Test
  public void doesntChangeWithWallOnOtherSide() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 3);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    textRV.render();
    basicModel.pass();
    basicModel.placePiece(-1, -2);
    basicModel.pass();
    basicModel.placePiece(-2, 0);
    basicModel.placePiece(1, -2);
    Assert.assertEquals("           \n" +
            "  - O -   \n" +
            " O O O -   \n" +
            "- O - O - \n" +
            " X X X -   \n" +
            "  - - -   \n", textRV.toString());
  }

  @Test (expected = IllegalStateException.class)
  public void invalidMoveWithGaps() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 4);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    textRV.render();
    basicModel.pass();
    basicModel.placePiece(0, -2);
    Assert.assertEquals("              \n" +
            "   - - - -     \n" +
            "  - - - - -   \n" +
            " - - O X - -   \n" +
            "- - X - O - - \n" +
            " - - O X - -   \n" +
            "  - - - - -   \n" +
            "   - - - -     \n", textRV.toString());
  }

  @Test
  public void validMovesNone() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 2);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    textRV.render();
    Assert.assertFalse(basicModel.validMoveExists());
  }

  @Test
  public void validMovesSome() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 3);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    textRV.render();
    Assert.assertEquals("           \n" +
            "  - - -   \n" +
            " - O X -   \n" +
            "- X - O - \n" +
            " - O X -   \n" +
            "  - - -   \n", textRV.toString());
    Assert.assertTrue(basicModel.validMoveExists());
  }

  @Test
  public void validMovesNoneButEmptySpaces() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 3);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.pass();
    basicModel.placePiece(-2, 0);
    basicModel.pass();
    basicModel.placePiece(1, -2);
    basicModel.pass();
    basicModel.placePiece(1, 1);
    textRV.render();
    Assert.assertEquals("           \n" +
            "  - O -   \n" +
            " - O O -   \n" +
            "- O - O - \n" +
            " O O O O   \n" +
            "  - - -   \n", textRV.toString());
    Assert.assertFalse(basicModel.validMoveExists());
  }
}

//length board 2, valid moves