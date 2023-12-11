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

public class InDepthSquareReversiTests {

  /**
   * To tests edge cases for square reversi games.
   */
  @Test(expected = Exception.class)
  public void basicSquareGameTestInvalidMoveVeryWrong() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, "square", 6);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder(), false);
    basicModel.placePiece(new BasicPoint(4, 4));
    textRV.render();
    Assert.assertEquals(-1, 1);
  }

  @Test(expected = Exception.class)
  public void basicSquareGameTestInvalidMoveOutOfBounds() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, "square", 2);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder(), false);
    basicModel.placePiece(new BasicPoint(2, 0));
    textRV.render();
    Assert.assertEquals(-1, 1);
  }

  @Test(expected = IllegalStateException.class)
  public void basicSquareGameTestInvalidMoveNoneToFlip() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, "square", 2);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder(), false);
    basicModel.placePiece(new BasicPoint(0, 0));
    textRV.render();
    Assert.assertEquals(-1, 1);
  }

  @Test(expected = IllegalStateException.class)
  public void basicSquareGameTestTwoMovesGameNotOver() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, "square", 6);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder(), false);
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
  public void basicSquareGameTestTwoPassesGameOver() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, "square", 6);
    basicModel.startGame();
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder(), true);
    basicModel.pass();
    basicModel.pass();
    textRV.render();
    Assert.assertEquals("- - - - - - \n" +
            "- - - - - - \n" +
            "- - X O - - \n" +
            "- - O X - - \n" +
            "- - - - - - \n" +
            "- - - - - - \n" +
            "\n", textRV.toString());
    Assert.assertEquals(null, basicModel.getWinner());
  }

  @Test
  public void basicSquareGameTestFromStartTwoPassesGameOverIsNull() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, "square", 6);
    basicModel.startGame();
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder(), true);
    basicModel.pass();
    basicModel.pass();
    textRV.render();
    Assert.assertEquals("- - - - - - \n" +
            "- - - - - - \n" +
            "- - X O - - \n" +
            "- - O X - - \n" +
            "- - - - - - \n" +
            "- - - - - - \n" +
            "\n", textRV.toString());
    Assert.assertNull(basicModel.getWinner());
  }

  @Test(expected = IllegalStateException.class)
  public void basicSquareGamePassThreeTimes() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, "square", 6);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder(), false);
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
  public void passCountSquareGetsSetBackToZeroOnMove() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, "square", 4);
    basicModel.startGame();
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder(), true);
    basicModel.pass();
    basicModel.placePiece(new BasicPoint(1, 0));
    basicModel.pass();
    basicModel.pass();
    textRV.render();
    Assert.assertEquals("- - - - \n" +
            "O O O - \n" +
            "- O X - \n" +
            "- - - - \n" +
            "\n", textRV.toString());
    Assert.assertEquals(new HumanPlayer(Name.O), basicModel.getWinner());
  }

  @Test(expected = IllegalStateException.class)
  public void cantMoveSquareAfterGameOver() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, "square", 6);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder(), false);
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

  @Test(expected = IllegalStateException.class)
  public void invalidMoveSquareDiagonals() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, "square", 6);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder(), false);
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

  @Test (expected = Exception.class)
  public void noSquareMovesLeft() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, "square", 2);
    basicModel.startGame();
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder(), false);
    basicModel.placePiece(new BasicPoint(-1, 1));

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

  @Test(expected = IllegalArgumentException.class)
  public void playerSquareAtOutOfBounds() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, "square", 6);
    basicModel.startGame();
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder(), false);
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

  @Test(expected = IllegalStateException.class)
  public void invalidSquareMoveWithGaps() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, "square", 4);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder(), false);
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
  public void validSquareMoveNone() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, "square", 2);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder(), false);
    textRV.render();
    Assert.assertFalse(basicModel.validMove(new BasicPoint(0, 0)));
  }

  @Test
  public void validSquareMoveSimple() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, "square", 4);
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder(), true);
    textRV.render();
    Assert.assertEquals("- - - - \n" +
            "- X O - \n" +
            "- O X - \n" +
            "- - - - \n" +
            "\n", textRV.toString());
    Assert.assertTrue(basicModel.validMove(new BasicPoint(0, 2)));
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidSquareMoveOutOfBoundaries() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, "square", 4);
    basicModel.startGame();
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder(), false);
    basicModel.pass();
    basicModel.placePiece(new BasicPoint(-2, 0));
    textRV.render();
    Assert.assertEquals("  - O -   \n" +
            " - O O -   \n" +
            "- O - O - \n" +
            " O O O O   \n" +
            "  - - -   \n", textRV.toString());
    Assert.assertFalse(basicModel.validMove(new BasicPoint(8, 5)));
  }

  @Test (expected = IllegalArgumentException.class)
  public void invalidSideLenVal() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, "square", 3);
    basicModel.startGame();
    ReversiView textRV = new TextReversiView(basicModel, new StringBuilder(), false);
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
  public void fullSquareGameTest() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, "square", 4);
    basicModel.startGame();
    Appendable output = new StringBuilder();
    ReversiView textRV = new TextReversiView(basicModel, output, true);
    textRV.render();
    basicModel.placePiece(new BasicPoint(2, 0));
    textRV.render();
    basicModel.placePiece(new BasicPoint(3, 0));
    textRV.render();
    basicModel.placePiece(new BasicPoint(1, 3));
    textRV.render();
    basicModel.placePiece(new BasicPoint(0, 3));
    textRV.render();
    basicModel.placePiece(new BasicPoint(0, 2));
    textRV.render();
    basicModel.placePiece(new BasicPoint(0, 1));
    textRV.render();
    basicModel.placePiece(new BasicPoint(0, 0));
    textRV.render();
    basicModel.placePiece(new BasicPoint(1, 0));
    textRV.render();
    basicModel.placePiece(new BasicPoint(3, 1));
    textRV.render();
    basicModel.placePiece(new BasicPoint(3, 2));
    textRV.render();
    basicModel.placePiece(new BasicPoint(3, 3));
    textRV.render();
    basicModel.placePiece(new BasicPoint(2, 3));
    basicModel.pass();
    textRV.render();
    basicModel.pass();
    Assert.assertEquals("- - - - \n" +
            "- X O - \n" +
            "- O X - \n" +
            "- - - - \n" +
            "\n" +
            "- - - - \n" +
            "- X O - \n" +
            "X X X - \n" +
            "- - - - \n" +
            "\n" +
            "- - - - \n" +
            "- X O - \n" +
            "X O X - \n" +
            "O - - - \n" +
            "\n" +
            "- - - - \n" +
            "- X X X \n" +
            "X O X - \n" +
            "O - - - \n" +
            "\n" +
            "- - - O \n" +
            "- X O X \n" +
            "X O X - \n" +
            "O - - - \n" +
            "\n" +
            "- - X O \n" +
            "- X X X \n" +
            "X O X - \n" +
            "O - - - \n" +
            "\n" +
            "- O O O \n" +
            "- O X X \n" +
            "X O X - \n" +
            "O - - - \n" +
            "\n" +
            "X O O O \n" +
            "- X X X \n" +
            "X O X - \n" +
            "O - - - \n" +
            "\n" +
            "X O O O \n" +
            "O X X X \n" +
            "O O X - \n" +
            "O - - - \n" +
            "\n" +
            "X O O O \n" +
            "O X X X \n" +
            "O X X - \n" +
            "O X - - \n" +
            "\n" +
            "X O O O \n" +
            "O X O X \n" +
            "O O O - \n" +
            "O O O - \n" +
            "\n" +
            "X O O O \n" +
            "O X O X \n" +
            "O O X - \n" +
            "O O O X \n" +
            "\n" +
            "X O O O \n" +
            "O X O O \n" +
            "O O O O \n" +
            "O O O X \n" +
            "\n", output.toString());
    Assert.assertEquals(basicModel.getWinner(), new HumanPlayer(Name.O));
  }

  @Test(expected = IllegalArgumentException.class)
  public void creatorSquareTestTooLittleArgs() {
    ReversiCreator.create(GameType.BASIC);
    Assert.assertEquals(1, 2 - 1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void creatorSquareTestTooManyArgs() {
    ReversiCreator.create(GameType.BASIC, 5, 5);
    Assert.assertEquals(1, 2 - 1);
  }

  @Test
  public void giveSquareControlToObserverTest() {
    ReversiModel model = ReversiCreator.create(GameType.BASIC, "square", 6);
    ControllerWithLog controller = new ControllerWithLog();
    model.addObserver(controller);
    Assert.assertEquals(controller.log, "");
    model.startGame();
    Assert.assertEquals(controller.log, "giveControlTo(X)\n");
    model.pass();
    Assert.assertEquals(controller.log, "giveControlTo(X)\ngiveControlTo(O)\n");
    model.pass();
    Assert.assertEquals(controller.log,
            "giveControlTo(X)\n" +
                    "giveControlTo(O)\n" +
                    "giveControlTo(X)\n" +
                    "gameOver()\n");
  }

  @Test
  public void gameSquareOverNotifiedTest() {
    ReversiModel model = ReversiCreator.create(GameType.BASIC, "square", 6);
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
  public void nullSquareObserver() {
    ReversiModel model = ReversiCreator.create(GameType.BASIC, "square", 6);
    Assert.assertThrows(NullPointerException.class, () -> {
      model.addObserver(null);
    });
  }
}
