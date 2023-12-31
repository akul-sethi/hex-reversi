import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import cs3500.reversi.controller.ControllerWithLog;
import cs3500.reversi.model.BasicPoint;
import cs3500.reversi.model.GameType;
import cs3500.reversi.model.ReversiCreator;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.HumanPlayer;
import cs3500.reversi.player.Name;
import cs3500.reversi.view.ReversiView;
import cs3500.reversi.view.TextReversiView;

/**
 * To tests edge cases for reversi games.
 */
public class InDepthReversiTests {
  @Test(expected = Exception.class)
  public void basicGameTestInvalidMoveVeryWrong() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.placePiece(new BasicPoint(4, 4));
    textRV.render();
    Assert.assertEquals(-1, 1);
  }

  @Test(expected = Exception.class)
  public void basicGameTestInvalidMoveOutOfBounds() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 2);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.placePiece(new BasicPoint(2, 0));
    textRV.render();
    Assert.assertEquals(-1, 1);
  }

  @Test(expected = IllegalStateException.class)
  public void basicGameTestInvalidMoveNoneToFlip() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 2);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.placePiece(new BasicPoint(0, 0));
    textRV.render();
    Assert.assertEquals(-1, 1);
  }

  @Test(expected = IllegalStateException.class)
  public void basicGameTestTwoMovesGameNotOver() throws IOException {
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
    basicModel.getWinner();
  }

  @Test
  public void basicGameTestTwoPassesGameOver() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    basicModel.startGame();
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.placePiece(new BasicPoint(-1, 1));
    basicModel.placePiece(new BasicPoint(-1, 2));
    basicModel.pass();
    basicModel.pass();
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
    Assert.assertEquals(new HumanPlayer(Name.O), basicModel.getWinner());
  }

  @Test
  public void basicGameTestFromStartTwoPassesGameOverIsNull() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    basicModel.startGame();
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.pass();
    basicModel.pass();
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
  public void passCountGetsSetBackToZeroOnMove() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    basicModel.startGame();
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.pass();
    basicModel.placePiece(new BasicPoint(-1, 1));
    basicModel.pass();
    basicModel.pass();
    textRV.render();
    Assert.assertEquals("     - - - - - -       \n" +
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
    Assert.assertEquals(new HumanPlayer(Name.O), basicModel.getWinner());
  }

  @Test(expected = IllegalStateException.class)
  public void cantMoveAfterGameOver() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.pass();
    basicModel.placePiece(new BasicPoint(-1, 1));
    basicModel.pass();
    basicModel.pass();
    basicModel.placePiece(new BasicPoint(-2, 1));
    textRV.render();
    Assert.assertEquals("     - - - - - -       \n" +
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
    Assert.assertEquals(new HumanPlayer(Name.O), basicModel.getWinner());
  }

  @Test
  public void placeChangesAllDirections() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    basicModel.startGame();
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.placePiece(new BasicPoint(-1, 1));
    basicModel.placePiece(new BasicPoint(-1, 2));
    basicModel.pass();
    basicModel.placePiece(new BasicPoint(1, 1));
    textRV.render();
    Assert.assertEquals("     - - - - - -       \n" +
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
    basicModel.startGame();
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.placePiece(new BasicPoint(-1, 1));
    basicModel.placePiece(new BasicPoint(-1, 2));
    basicModel.placePiece(new BasicPoint(-2, 0));
    basicModel.placePiece(new BasicPoint(2, 0));
    basicModel.pass();
    basicModel.placePiece(new BasicPoint(-1, -2));
    textRV.render();
    Assert.assertEquals("     - - - - - -       \n" +
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
    basicModel.placePiece(new BasicPoint(-1, 1));
    basicModel.placePiece(new BasicPoint(-1, 2));
    basicModel.placePiece(new BasicPoint(-2, 0));
    basicModel.placePiece(new BasicPoint(2, 0));
    basicModel.pass();
    basicModel.placePiece(new BasicPoint(-1, -2));
    basicModel.placePiece(new BasicPoint(0, 0));
    textRV.render();
    Assert.assertEquals("     - - - - - -       \n" +
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
    basicModel.startGame();
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.placePiece(new BasicPoint(-1, 1));
    basicModel.placePiece(new BasicPoint(-1, 2));
    basicModel.placePiece(new BasicPoint(-2, 0));
    basicModel.placePiece(new BasicPoint(2, 0));
    basicModel.pass();
    basicModel.placePiece(new BasicPoint(-1, -2));
    basicModel.pass();
    basicModel.placePiece(new BasicPoint(-3, -1));
    textRV.render();
    Assert.assertEquals("     - - - - - -       \n" +
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
    basicModel.startGame();
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.placePiece(new BasicPoint(-1, 1));
    basicModel.placePiece(new BasicPoint(-1, 2));
    basicModel.placePiece(new BasicPoint(-2, 0));
    basicModel.placePiece(new BasicPoint(2, 0));
    basicModel.pass();
    basicModel.placePiece(new BasicPoint(-1, -2));
    basicModel.pass();
    basicModel.placePiece(new BasicPoint(-3, -1));
    textRV.render();
    Assert.assertEquals("     - - - - - -       \n" +
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
    Assert.assertEquals(basicModel.playerAt(new BasicPoint(1, 0)), new HumanPlayer(Name.O));
    Assert.assertEquals(basicModel.playerAt(new BasicPoint(3, 0)), null);
    Assert.assertEquals(basicModel.playerAt(new BasicPoint(-3, -1)), new HumanPlayer(Name.O));
  }

  @Test(expected = IllegalArgumentException.class)
  public void playerAtOutOfBounds() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    basicModel.startGame();
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.placePiece(new BasicPoint(-1, 1));
    basicModel.placePiece(new BasicPoint(-1, 2));
    basicModel.placePiece(new BasicPoint(-2, 0));
    basicModel.placePiece(new BasicPoint(2, 0));
    basicModel.pass();
    basicModel.placePiece(new BasicPoint(-1, -2));
    basicModel.pass();
    basicModel.placePiece(new BasicPoint(-3, -1));
    textRV.render();
    Assert.assertEquals("     - - - - - -       \n" +
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
    Assert.assertEquals(basicModel.playerAt(new BasicPoint(1, 0)), new HumanPlayer(Name.O));
    Assert.assertEquals(basicModel.playerAt(new BasicPoint(9, 0)), null);
  }

  @Test
  public void onlyGivesYouTilesOnYourMove() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);
    basicModel.startGame();
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.placePiece(new BasicPoint(1, -2));
    basicModel.placePiece(new BasicPoint(-1, 1));
    basicModel.pass();
    basicModel.placePiece(new BasicPoint(2, 0));
    basicModel.placePiece(new BasicPoint(1, 1));
    textRV.render();
    Assert.assertEquals("     - - - - - -       \n" +
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
    basicModel.startGame();
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.placePiece(new BasicPoint(1, -2));
    basicModel.placePiece(new BasicPoint(-1, 1));
    basicModel.pass();
    basicModel.placePiece(new BasicPoint(2, 0));
    basicModel.placePiece(new BasicPoint(1, 1));
    basicModel.pass();
    basicModel.placePiece(new BasicPoint(-2, 2));

    textRV.render();
    Assert.assertEquals("     - - - - - -       \n" +
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
    basicModel.startGame();
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    textRV.render();
    basicModel.pass();
    basicModel.placePiece(new BasicPoint(-1, -2));
    basicModel.pass();
    basicModel.placePiece(new BasicPoint(-2, 0));
    basicModel.placePiece(new BasicPoint(1, -2));
    Assert.assertEquals("  - O -   \n" +
            " O O O -   \n" +
            "- O - O - \n" +
            " X X X -   \n" +
            "  - - -   \n", textRV.toString());
  }

  @Test(expected = IllegalStateException.class)
  public void invalidMoveWithGaps() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 4);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    textRV.render();
    basicModel.pass();
    basicModel.placePiece(new BasicPoint(0, -2));
    Assert.assertEquals("   - - - -     \n" +
            "  - - - - -   \n" +
            " - - O X - -   \n" +
            "- - X - O - - \n" +
            " - - O X - -   \n" +
            "  - - - - -   \n" +
            "   - - - -     \n", textRV.toString());
  }

  @Test
  public void validMoveNone() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 2);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    textRV.render();
    Assert.assertFalse(basicModel.validMove(new BasicPoint(0, 0)));
  }

  @Test
  public void validMoveSimple() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 3);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    textRV.render();
    Assert.assertEquals("  - - -   \n" +
            " - O X -   \n" +
            "- X - O - \n" +
            " - O X -   \n" +
            "  - - -   \n", textRV.toString());
    Assert.assertTrue(basicModel.validMove(new BasicPoint(1, 1)));
  }

  @Test
  public void validMoveNoneButEmptySpaces() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 3);
    basicModel.startGame();
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.pass();
    basicModel.placePiece(new BasicPoint(-2, 0));
    basicModel.pass();
    basicModel.placePiece(new BasicPoint(1, -2));
    basicModel.pass();
    basicModel.placePiece(new BasicPoint(1, 1));
    textRV.render();
    Assert.assertEquals("  - O -   \n" +
            " - O O -   \n" +
            "- O - O - \n" +
            " O O O O   \n" +
            "  - - -   \n", textRV.toString());
    Assert.assertFalse(basicModel.validMove(new BasicPoint(2, 0)));
  }

  @Test
  public void invalidMoveOutOfBoundaries() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 3);
    basicModel.startGame();
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.pass();
    basicModel.placePiece(new BasicPoint(-2, 0));
    basicModel.pass();
    basicModel.placePiece(new BasicPoint(1, -2));
    basicModel.pass();
    basicModel.placePiece(new BasicPoint(1, 1));
    textRV.render();
    Assert.assertEquals("  - O -   \n" +
            " - O O -   \n" +
            "- O - O - \n" +
            " O O O O   \n" +
            "  - - -   \n", textRV.toString());
    Assert.assertFalse(basicModel.validMove(new BasicPoint(8, 5)));
  }

  @Test
  public void invalidMoveDoesntMakeSense() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 3);
    basicModel.startGame();
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder());
    basicModel.pass();
    basicModel.placePiece(new BasicPoint(-2, 0));
    basicModel.pass();
    basicModel.placePiece(new BasicPoint(1, -2));
    basicModel.pass();
    basicModel.placePiece(new BasicPoint(1, 1));
    textRV.render();
    Assert.assertEquals("  - O -   \n" +
            " - O O -   \n" +
            "- O - O - \n" +
            " O O O O   \n" +
            "  - - -   \n", textRV.toString());
    Assert.assertFalse(basicModel.validMove(new BasicPoint(2, 0)));
  }

  @Test
  public void fullGameTest() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 3);
    basicModel.startGame();
    Appendable output = new StringBuilder();
    ReversiView textRV = new TextReversiView(basicModel, output);
    textRV.render();
    basicModel.placePiece(new BasicPoint(1, 1));
    textRV.render();
    basicModel.placePiece(new BasicPoint(1, -2));
    textRV.render();
    basicModel.placePiece(new BasicPoint(-1, -2));
    textRV.render();
    basicModel.placePiece(new BasicPoint(-2, 0));
    textRV.render();
    basicModel.placePiece(new BasicPoint(2, 0));
    textRV.render();
    basicModel.placePiece(new BasicPoint(-1, 1));
    textRV.render();
    basicModel.pass();
    textRV.render();
    basicModel.pass();
    textRV.render();
    Assert.assertEquals("  - - -   \n" +
            " - O X -   \n" +
            "- X - O - \n" +
            " - O X -   \n" +
            "  - - -   \n" +
            "  - - -   \n" +
            " - O X -   \n" +
            "- X - X - \n" +
            " - O X X   \n" +
            "  - - -   \n" +
            "  - - -   \n" +
            " - O X -   \n" +
            "- O - X - \n" +
            " O O X X   \n" +
            "  - - -   \n" +
            "  - - -   \n" +
            " X X X -   \n" +
            "- O - X - \n" +
            " O O X X   \n" +
            "  - - -   \n" +
            "  - O -   \n" +
            " X O X -   \n" +
            "- O - X - \n" +
            " O O X X   \n" +
            "  - - -   \n" +
            "  - O -   \n" +
            " X O X -   \n" +
            "- X - X - \n" +
            " O X X X   \n" +
            "  - X -   \n" +
            "  - O -   \n" +
            " X O O O   \n" +
            "- X - X - \n" +
            " O X X X   \n" +
            "  - X -   \n" +
            "  - O -   \n" +
            " X O O O   \n" +
            "- X - X - \n" +
            " O X X X   \n" +
            "  - X -   \n" +
            "  - O -   \n" +
            " X O O O   \n" +
            "- X - X - \n" +
            " O X X X   \n" +
            "  - X -   \n", output.toString());
    Assert.assertEquals(basicModel.getWinner(), new HumanPlayer(Name.X));
  }

  @Test(expected = IllegalArgumentException.class)
  public void creatorTestTooLittleArgs() {
    ReversiCreator.create(GameType.BASIC);
    Assert.assertEquals(1, 2 - 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void creatorTestTooManyArgs() {
    ReversiCreator.create(GameType.BASIC, 5, 5);
    Assert.assertEquals(1, 2 - 1);
  }

  @Test
  public void giveControlToObserverTest() {
    ReversiModel model = ReversiCreator.create(GameType.BASIC, 6);
    ControllerWithLog controller = new ControllerWithLog();
    model.addObserver(controller);
    Assert.assertEquals(controller.log, "");
    model.startGame();
    Assert.assertEquals(controller.log, "giveControlTo(X)\n");
    model.pass();
    Assert.assertEquals(controller.log, "giveControlTo(X)\ngiveControlTo(O)\n");
    model.placePiece(new BasicPoint(-1, 1));
    Assert.assertEquals(controller.log, "giveControlTo(X)\ngiveControlTo(O)\ngiveControlTo(X)\n");
  }

  @Test
  public void gameOverNotifiedTest() {
    ReversiModel model = ReversiCreator.create(GameType.BASIC, 6);
    ControllerWithLog controller = new ControllerWithLog();
    model.addObserver(controller);
    Assert.assertEquals(controller.log, "");
    model.startGame();
    Assert.assertEquals(controller.log, "giveControlTo(X)\n");
    model.pass();
    model.pass();
    Assert.assertEquals(controller.log, "giveControlTo(X)\ngiveControlTo(O)\ngiveControlTo" +
            "(X)\ngameOver()\n");
  }

  @Test
  public void nullObserver() {
    ReversiModel model = ReversiCreator.create(GameType.BASIC, 6);
    Assert.assertThrows(NullPointerException.class, () -> {
      model.addObserver(null);
    });
  }
}
