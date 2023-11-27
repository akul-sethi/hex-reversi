package cs3500.reversi.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

import cs3500.reversi.player.MachinePlayer;
import cs3500.reversi.player.Name;
import cs3500.reversi.strategy.AvoidNextToCornersStrategy;
import cs3500.reversi.strategy.CaptureCornersStrategy;
import cs3500.reversi.strategy.CaptureMaxStrategy;
import cs3500.reversi.view.ReversiView;
import cs3500.reversi.view.TextReversiView;

/**
 * Tests for mutability etc of the mock reversi class.
 */
public class MockTests {
  @Test
  public void captureInputCorners() {
    StringBuilder output = new StringBuilder();
    ReadOnlyReversiModel mockModel = new CaptureInputReversi(output,
            Arrays.asList(new MachinePlayer(Name.X, new CaptureCornersStrategy()),
                    new MachinePlayer(Name.X, new CaptureCornersStrategy())));
    ReversiModel testModel = mockModel.getModel();
    try {
      testModel.nextToPlay().startTurn(testModel);
    } catch (Exception e) {
      //inshallah do nothing
    }
    Assert.assertTrue(output.toString().contains("validMove: row = -5, col = -3"));
    Assert.assertTrue(output.toString().contains("validMove: row = -5, col = 2"));
    Assert.assertTrue(output.toString().contains("validMove: row = 0, col = -5"));
    Assert.assertTrue(output.toString().contains("validMove: row = 0, col = 5"));
    Assert.assertTrue(output.toString().contains("validMove: row = 5, col = -3"));
    Assert.assertTrue(output.toString().contains("validMove: row = 5, col = 2"));
  }

  @Test
  public void captureInputAvoidNextToCorners() {
    StringBuilder output = new StringBuilder();
    ReadOnlyReversiModel mockModel = new CaptureInputReversi(output,
            Arrays.asList(new MachinePlayer(Name.X, new AvoidNextToCornersStrategy()),
                    new MachinePlayer(Name.X, new AvoidNextToCornersStrategy())));
    ReversiModel testModel = mockModel.getModel();
    try {
      testModel.nextToPlay().startTurn(testModel);
    } catch (Exception e) {
      //inshallah do nothing
    }
    Assert.assertTrue(output.toString().contains("validMove: row = -5, col = -2"));
    Assert.assertTrue(output.toString().contains("validMove: row = -4, col = -3"));
    Assert.assertTrue(output.toString().contains("validMove: row = -4, col = -4"));
    Assert.assertTrue(output.toString().contains("validMove: row = -5, col = 1"));
    Assert.assertTrue(output.toString().contains("validMove: row = -4, col = 2"));
    Assert.assertTrue(output.toString().contains("validMove: row = -4, col = 3"));
    Assert.assertTrue(output.toString().contains("validMove: row = 0, col = -4"));
    Assert.assertTrue(output.toString().contains("validMove: row = -1, col = -5"));
    Assert.assertTrue(output.toString().contains("validMove: row = 1, col = -5"));
    Assert.assertTrue(output.toString().contains("validMove: row = 0, col = 4"));
    Assert.assertTrue(output.toString().contains("validMove: row = -1, col = 5"));
    Assert.assertTrue(output.toString().contains("validMove: row = 1, col = 5"));
    Assert.assertTrue(output.toString().contains("validMove: row = 5, col = -2"));
    Assert.assertTrue(output.toString().contains("validMove: row = 4, col = -3"));
    Assert.assertTrue(output.toString().contains("validMove: row = 4, col = -4"));
    Assert.assertTrue(output.toString().contains("validMove: row = 5, col = 1"));
    Assert.assertTrue(output.toString().contains("validMove: row = 4, col = 2"));
    Assert.assertTrue(output.toString().contains("validMove: row = 4, col = 3"));
  }

  @Test
  public void maxCaptureTranscript() {
    StringBuilder output = new StringBuilder();
    ReadOnlyReversiModel mockModel = new CaptureInputReversi(output,
            Arrays.asList(new MachinePlayer(Name.X, new CaptureMaxStrategy()),
                    new MachinePlayer(Name.X, new CaptureMaxStrategy())));
    ReversiModel testModel = mockModel.getModel();
    try {
      testModel.nextToPlay().startTurn(testModel);
    }
    catch (Exception e) {
      //inshallah do nothing
    }
    Assert.assertEquals(output.toString(), "getModel\n" +
            "nextToPlay\n" +
            "getModel\n" +
            "getPlayerScore: player\n" +
            "getBottomRow\n" +
            "getRightCol\n" +
            "getLeftCol\n" +
            "getTopRow\n" +
            "validMove: row = -5, col = -5\n" +
            "validMove: row = -5, col = -4\n" +
            "validMove: row = -5, col = -3\n" +
            "validMove: row = -5, col = -2\n" +
            "validMove: row = -5, col = -1\n" +
            "validMove: row = -5, col = 0\n" +
            "validMove: row = -5, col = 1\n" +
            "validMove: row = -5, col = 2\n" +
            "validMove: row = -5, col = 3\n" +
            "validMove: row = -5, col = 4\n" +
            "validMove: row = -5, col = 5\n" +
            "validMove: row = -4, col = -5\n" +
            "validMove: row = -4, col = -4\n" +
            "validMove: row = -4, col = -3\n" +
            "validMove: row = -4, col = -2\n" +
            "validMove: row = -4, col = -1\n" +
            "validMove: row = -4, col = 0\n" +
            "validMove: row = -4, col = 1\n" +
            "validMove: row = -4, col = 2\n" +
            "validMove: row = -4, col = 3\n" +
            "validMove: row = -4, col = 4\n" +
            "validMove: row = -4, col = 5\n" +
            "validMove: row = -3, col = -5\n" +
            "validMove: row = -3, col = -4\n" +
            "validMove: row = -3, col = -3\n" +
            "validMove: row = -3, col = -2\n" +
            "validMove: row = -3, col = -1\n" +
            "validMove: row = -3, col = 0\n" +
            "validMove: row = -3, col = 1\n" +
            "validMove: row = -3, col = 2\n" +
            "validMove: row = -3, col = 3\n" +
            "validMove: row = -3, col = 4\n" +
            "validMove: row = -3, col = 5\n" +
            "validMove: row = -2, col = -5\n" +
            "validMove: row = -2, col = -4\n" +
            "validMove: row = -2, col = -3\n" +
            "validMove: row = -2, col = -2\n" +
            "validMove: row = -2, col = -1\n" +
            "validMove: row = -2, col = 0\n" +
            "validMove: row = -2, col = 1\n" +
            "validMove: row = -2, col = 2\n" +
            "validMove: row = -2, col = 3\n" +
            "validMove: row = -2, col = 4\n" +
            "validMove: row = -2, col = 5\n" +
            "validMove: row = -1, col = -5\n" +
            "validMove: row = -1, col = -4\n" +
            "validMove: row = -1, col = -3\n" +
            "validMove: row = -1, col = -2\n" +
            "validMove: row = -1, col = -1\n" +
            "validMove: row = -1, col = 0\n" +
            "validMove: row = -1, col = 1\n" +
            "validMove: row = -1, col = 2\n" +
            "validMove: row = -1, col = 3\n" +
            "validMove: row = -1, col = 4\n" +
            "validMove: row = -1, col = 5\n" +
            "validMove: row = 0, col = -5\n" +
            "validMove: row = 0, col = -4\n" +
            "validMove: row = 0, col = -3\n" +
            "validMove: row = 0, col = -2\n" +
            "validMove: row = 0, col = -1\n" +
            "validMove: row = 0, col = 0\n" +
            "validMove: row = 0, col = 1\n" +
            "validMove: row = 0, col = 2\n" +
            "validMove: row = 0, col = 3\n" +
            "validMove: row = 0, col = 4\n" +
            "validMove: row = 0, col = 5\n" +
            "validMove: row = 1, col = -5\n" +
            "validMove: row = 1, col = -4\n" +
            "validMove: row = 1, col = -3\n" +
            "validMove: row = 1, col = -2\n" +
            "validMove: row = 1, col = -1\n" +
            "validMove: row = 1, col = 0\n" +
            "validMove: row = 1, col = 1\n" +
            "validMove: row = 1, col = 2\n" +
            "validMove: row = 1, col = 3\n" +
            "validMove: row = 1, col = 4\n" +
            "validMove: row = 1, col = 5\n" +
            "validMove: row = 2, col = -5\n" +
            "validMove: row = 2, col = -4\n" +
            "validMove: row = 2, col = -3\n" +
            "validMove: row = 2, col = -2\n" +
            "validMove: row = 2, col = -1\n" +
            "validMove: row = 2, col = 0\n" +
            "validMove: row = 2, col = 1\n" +
            "validMove: row = 2, col = 2\n" +
            "validMove: row = 2, col = 3\n" +
            "validMove: row = 2, col = 4\n" +
            "validMove: row = 2, col = 5\n" +
            "validMove: row = 3, col = -5\n" +
            "validMove: row = 3, col = -4\n" +
            "validMove: row = 3, col = -3\n" +
            "validMove: row = 3, col = -2\n" +
            "validMove: row = 3, col = -1\n" +
            "validMove: row = 3, col = 0\n" +
            "validMove: row = 3, col = 1\n" +
            "validMove: row = 3, col = 2\n" +
            "validMove: row = 3, col = 3\n" +
            "validMove: row = 3, col = 4\n" +
            "validMove: row = 3, col = 5\n" +
            "validMove: row = 4, col = -5\n" +
            "validMove: row = 4, col = -4\n" +
            "validMove: row = 4, col = -3\n" +
            "validMove: row = 4, col = -2\n" +
            "validMove: row = 4, col = -1\n" +
            "validMove: row = 4, col = 0\n" +
            "validMove: row = 4, col = 1\n" +
            "validMove: row = 4, col = 2\n" +
            "validMove: row = 4, col = 3\n" +
            "validMove: row = 4, col = 4\n" +
            "validMove: row = 4, col = 5\n" +
            "validMove: row = 5, col = -5\n" +
            "validMove: row = 5, col = -4\n" +
            "validMove: row = 5, col = -3\n" +
            "validMove: row = 5, col = -2\n" +
            "validMove: row = 5, col = -1\n" +
            "validMove: row = 5, col = 0\n" +
            "validMove: row = 5, col = 1\n" +
            "validMove: row = 5, col = 2\n" +
            "validMove: row = 5, col = 3\n" +
            "validMove: row = 5, col = 4\n" +
            "validMove: row = 5, col = 5\n" +
            "getModel\n" +
            "placePiece: row = -2, col = 0\n" +
            "getPlayerScore: player\n" +
            "getModel\n" +
            "placePiece: row = -1, col = -2\n" +
            "getPlayerScore: player\n" +
            "getModel\n" +
            "placePiece: row = -1, col = 1\n" +
            "getPlayerScore: player\n" +
            "getModel\n" +
            "placePiece: row = 1, col = -2\n" +
            "getPlayerScore: player\n" +
            "getModel\n" +
            "placePiece: row = 1, col = 1\n" +
            "getPlayerScore: player\n" +
            "getModel\n" +
            "placePiece: row = 2, col = 0\n" +
            "getPlayerScore: player\n");
  }

  @Test
  public void MockStringViewTest() {
    StringBuilder output = new StringBuilder();
    ReadOnlyReversiModel mockModel = new CaptureInputReversi(output,
            Arrays.asList(new MachinePlayer(Name.X, new CaptureMaxStrategy()),
                    new MachinePlayer(Name.X, new CaptureMaxStrategy())));
    ReversiModel testModel = mockModel.getModel();
    Appendable log = new StringBuilder();
    ReversiView textView = new TextReversiView(mockModel.getModel(), log);
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
            "     - - - - - -       \n", textView.toString());
    mockModel.nextToPlay().startTurn(mockModel);
    Assert.assertEquals("getModel\n" +
            "getModel\n" +
            "getTopRow\n" +
            "getBottomRow\n" +
            "getRightCol\n" +
            "getLeftCol\n" +
            "playerAt: row = -5, col = -5\n" +
            "playerAt: row = -5, col = -4\n" +
            "playerAt: row = -5, col = -3\n" +
            "playerAt: row = -5, col = -2\n" +
            "playerAt: row = -5, col = -1\n" +
            "playerAt: row = -5, col = 0\n" +
            "playerAt: row = -5, col = 1\n" +
            "playerAt: row = -5, col = 2\n" +
            "playerAt: row = -5, col = 3\n" +
            "playerAt: row = -5, col = 4\n" +
            "playerAt: row = -5, col = 5\n" +
            "getRightCol\n" +
            "getLeftCol\n" +
            "playerAt: row = -4, col = -5\n" +
            "playerAt: row = -4, col = -4\n" +
            "playerAt: row = -4, col = -3\n" +
            "playerAt: row = -4, col = -2\n" +
            "playerAt: row = -4, col = -1\n" +
            "playerAt: row = -4, col = 0\n" +
            "playerAt: row = -4, col = 1\n" +
            "playerAt: row = -4, col = 2\n" +
            "playerAt: row = -4, col = 3\n" +
            "playerAt: row = -4, col = 4\n" +
            "playerAt: row = -4, col = 5\n" +
            "getRightCol\n" +
            "getLeftCol\n" +
            "playerAt: row = -3, col = -5\n" +
            "playerAt: row = -3, col = -4\n" +
            "playerAt: row = -3, col = -3\n" +
            "playerAt: row = -3, col = -2\n" +
            "playerAt: row = -3, col = -1\n" +
            "playerAt: row = -3, col = 0\n" +
            "playerAt: row = -3, col = 1\n" +
            "playerAt: row = -3, col = 2\n" +
            "playerAt: row = -3, col = 3\n" +
            "playerAt: row = -3, col = 4\n" +
            "playerAt: row = -3, col = 5\n" +
            "getRightCol\n" +
            "getLeftCol\n" +
            "playerAt: row = -2, col = -5\n" +
            "playerAt: row = -2, col = -4\n" +
            "playerAt: row = -2, col = -3\n" +
            "playerAt: row = -2, col = -2\n" +
            "playerAt: row = -2, col = -1\n" +
            "playerAt: row = -2, col = 0\n" +
            "playerAt: row = -2, col = 1\n" +
            "playerAt: row = -2, col = 2\n" +
            "playerAt: row = -2, col = 3\n" +
            "playerAt: row = -2, col = 4\n" +
            "playerAt: row = -2, col = 5\n" +
            "getRightCol\n" +
            "getLeftCol\n" +
            "playerAt: row = -1, col = -5\n" +
            "playerAt: row = -1, col = -4\n" +
            "playerAt: row = -1, col = -3\n" +
            "playerAt: row = -1, col = -2\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 1\n" +
            "playerAt: row = -1, col = 2\n" +
            "playerAt: row = -1, col = 3\n" +
            "playerAt: row = -1, col = 4\n" +
            "playerAt: row = -1, col = 5\n" +
            "getRightCol\n" +
            "getLeftCol\n" +
            "playerAt: row = 0, col = -5\n" +
            "playerAt: row = 0, col = -4\n" +
            "playerAt: row = 0, col = -3\n" +
            "playerAt: row = 0, col = -2\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = 0\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 2\n" +
            "playerAt: row = 0, col = 3\n" +
            "playerAt: row = 0, col = 4\n" +
            "playerAt: row = 0, col = 5\n" +
            "getRightCol\n" +
            "getLeftCol\n" +
            "playerAt: row = 1, col = -5\n" +
            "playerAt: row = 1, col = -4\n" +
            "playerAt: row = 1, col = -3\n" +
            "playerAt: row = 1, col = -2\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 1\n" +
            "playerAt: row = 1, col = 2\n" +
            "playerAt: row = 1, col = 3\n" +
            "playerAt: row = 1, col = 4\n" +
            "playerAt: row = 1, col = 5\n" +
            "getRightCol\n" +
            "getLeftCol\n" +
            "playerAt: row = 2, col = -5\n" +
            "playerAt: row = 2, col = -4\n" +
            "playerAt: row = 2, col = -3\n" +
            "playerAt: row = 2, col = -2\n" +
            "playerAt: row = 2, col = -1\n" +
            "playerAt: row = 2, col = 0\n" +
            "playerAt: row = 2, col = 1\n" +
            "playerAt: row = 2, col = 2\n" +
            "playerAt: row = 2, col = 3\n" +
            "playerAt: row = 2, col = 4\n" +
            "playerAt: row = 2, col = 5\n" +
            "getRightCol\n" +
            "getLeftCol\n" +
            "playerAt: row = 3, col = -5\n" +
            "playerAt: row = 3, col = -4\n" +
            "playerAt: row = 3, col = -3\n" +
            "playerAt: row = 3, col = -2\n" +
            "playerAt: row = 3, col = -1\n" +
            "playerAt: row = 3, col = 0\n" +
            "playerAt: row = 3, col = 1\n" +
            "playerAt: row = 3, col = 2\n" +
            "playerAt: row = 3, col = 3\n" +
            "playerAt: row = 3, col = 4\n" +
            "playerAt: row = 3, col = 5\n" +
            "getRightCol\n" +
            "getLeftCol\n" +
            "playerAt: row = 4, col = -5\n" +
            "playerAt: row = 4, col = -4\n" +
            "playerAt: row = 4, col = -3\n" +
            "playerAt: row = 4, col = -2\n" +
            "playerAt: row = 4, col = -1\n" +
            "playerAt: row = 4, col = 0\n" +
            "playerAt: row = 4, col = 1\n" +
            "playerAt: row = 4, col = 2\n" +
            "playerAt: row = 4, col = 3\n" +
            "playerAt: row = 4, col = 4\n" +
            "playerAt: row = 4, col = 5\n" +
            "getRightCol\n" +
            "getLeftCol\n" +
            "playerAt: row = 5, col = -5\n" +
            "playerAt: row = 5, col = -4\n" +
            "playerAt: row = 5, col = -3\n" +
            "playerAt: row = 5, col = -2\n" +
            "playerAt: row = 5, col = -1\n" +
            "playerAt: row = 5, col = 0\n" +
            "playerAt: row = 5, col = 1\n" +
            "playerAt: row = 5, col = 2\n" +
            "playerAt: row = 5, col = 3\n" +
            "playerAt: row = 5, col = 4\n" +
            "playerAt: row = 5, col = 5\n" +
            "nextToPlay\n" +
            "getModel\n" +
            "getPlayerScore: player\n" +
            "getBottomRow\n" +
            "getRightCol\n" +
            "getLeftCol\n" +
            "getTopRow\n" +
            "validMove: row = -5, col = -5\n" +
            "validMove: row = -5, col = -4\n" +
            "validMove: row = -5, col = -3\n" +
            "validMove: row = -5, col = -2\n" +
            "validMove: row = -5, col = -1\n" +
            "validMove: row = -5, col = 0\n" +
            "validMove: row = -5, col = 1\n" +
            "validMove: row = -5, col = 2\n" +
            "validMove: row = -5, col = 3\n" +
            "validMove: row = -5, col = 4\n" +
            "validMove: row = -5, col = 5\n" +
            "validMove: row = -4, col = -5\n" +
            "validMove: row = -4, col = -4\n" +
            "validMove: row = -4, col = -3\n" +
            "validMove: row = -4, col = -2\n" +
            "validMove: row = -4, col = -1\n" +
            "validMove: row = -4, col = 0\n" +
            "validMove: row = -4, col = 1\n" +
            "validMove: row = -4, col = 2\n" +
            "validMove: row = -4, col = 3\n" +
            "validMove: row = -4, col = 4\n" +
            "validMove: row = -4, col = 5\n" +
            "validMove: row = -3, col = -5\n" +
            "validMove: row = -3, col = -4\n" +
            "validMove: row = -3, col = -3\n" +
            "validMove: row = -3, col = -2\n" +
            "validMove: row = -3, col = -1\n" +
            "validMove: row = -3, col = 0\n" +
            "validMove: row = -3, col = 1\n" +
            "validMove: row = -3, col = 2\n" +
            "validMove: row = -3, col = 3\n" +
            "validMove: row = -3, col = 4\n" +
            "validMove: row = -3, col = 5\n" +
            "validMove: row = -2, col = -5\n" +
            "validMove: row = -2, col = -4\n" +
            "validMove: row = -2, col = -3\n" +
            "validMove: row = -2, col = -2\n" +
            "validMove: row = -2, col = -1\n" +
            "validMove: row = -2, col = 0\n" +
            "validMove: row = -2, col = 1\n" +
            "validMove: row = -2, col = 2\n" +
            "validMove: row = -2, col = 3\n" +
            "validMove: row = -2, col = 4\n" +
            "validMove: row = -2, col = 5\n" +
            "validMove: row = -1, col = -5\n" +
            "validMove: row = -1, col = -4\n" +
            "validMove: row = -1, col = -3\n" +
            "validMove: row = -1, col = -2\n" +
            "validMove: row = -1, col = -1\n" +
            "validMove: row = -1, col = 0\n" +
            "validMove: row = -1, col = 1\n" +
            "validMove: row = -1, col = 2\n" +
            "validMove: row = -1, col = 3\n" +
            "validMove: row = -1, col = 4\n" +
            "validMove: row = -1, col = 5\n" +
            "validMove: row = 0, col = -5\n" +
            "validMove: row = 0, col = -4\n" +
            "validMove: row = 0, col = -3\n" +
            "validMove: row = 0, col = -2\n" +
            "validMove: row = 0, col = -1\n" +
            "validMove: row = 0, col = 0\n" +
            "validMove: row = 0, col = 1\n" +
            "validMove: row = 0, col = 2\n" +
            "validMove: row = 0, col = 3\n" +
            "validMove: row = 0, col = 4\n" +
            "validMove: row = 0, col = 5\n" +
            "validMove: row = 1, col = -5\n" +
            "validMove: row = 1, col = -4\n" +
            "validMove: row = 1, col = -3\n" +
            "validMove: row = 1, col = -2\n" +
            "validMove: row = 1, col = -1\n" +
            "validMove: row = 1, col = 0\n" +
            "validMove: row = 1, col = 1\n" +
            "validMove: row = 1, col = 2\n" +
            "validMove: row = 1, col = 3\n" +
            "validMove: row = 1, col = 4\n" +
            "validMove: row = 1, col = 5\n" +
            "validMove: row = 2, col = -5\n" +
            "validMove: row = 2, col = -4\n" +
            "validMove: row = 2, col = -3\n" +
            "validMove: row = 2, col = -2\n" +
            "validMove: row = 2, col = -1\n" +
            "validMove: row = 2, col = 0\n" +
            "validMove: row = 2, col = 1\n" +
            "validMove: row = 2, col = 2\n" +
            "validMove: row = 2, col = 3\n" +
            "validMove: row = 2, col = 4\n" +
            "validMove: row = 2, col = 5\n" +
            "validMove: row = 3, col = -5\n" +
            "validMove: row = 3, col = -4\n" +
            "validMove: row = 3, col = -3\n" +
            "validMove: row = 3, col = -2\n" +
            "validMove: row = 3, col = -1\n" +
            "validMove: row = 3, col = 0\n" +
            "validMove: row = 3, col = 1\n" +
            "validMove: row = 3, col = 2\n" +
            "validMove: row = 3, col = 3\n" +
            "validMove: row = 3, col = 4\n" +
            "validMove: row = 3, col = 5\n" +
            "validMove: row = 4, col = -5\n" +
            "validMove: row = 4, col = -4\n" +
            "validMove: row = 4, col = -3\n" +
            "validMove: row = 4, col = -2\n" +
            "validMove: row = 4, col = -1\n" +
            "validMove: row = 4, col = 0\n" +
            "validMove: row = 4, col = 1\n" +
            "validMove: row = 4, col = 2\n" +
            "validMove: row = 4, col = 3\n" +
            "validMove: row = 4, col = 4\n" +
            "validMove: row = 4, col = 5\n" +
            "validMove: row = 5, col = -5\n" +
            "validMove: row = 5, col = -4\n" +
            "validMove: row = 5, col = -3\n" +
            "validMove: row = 5, col = -2\n" +
            "validMove: row = 5, col = -1\n" +
            "validMove: row = 5, col = 0\n" +
            "validMove: row = 5, col = 1\n" +
            "validMove: row = 5, col = 2\n" +
            "validMove: row = 5, col = 3\n" +
            "validMove: row = 5, col = 4\n" +
            "validMove: row = 5, col = 5\n" +
            "getModel\n" +
            "placePiece: row = -2, col = 0\n" +
            "getPlayerScore: player\n" +
            "getModel\n" +
            "placePiece: row = -1, col = -2\n" +
            "getPlayerScore: player\n" +
            "getModel\n" +
            "placePiece: row = -1, col = 1\n" +
            "getPlayerScore: player\n" +
            "getModel\n" +
            "placePiece: row = 1, col = -2\n" +
            "getPlayerScore: player\n" +
            "getModel\n" +
            "placePiece: row = 1, col = 1\n" +
            "getPlayerScore: player\n" +
            "getModel\n" +
            "placePiece: row = 2, col = 0\n" +
            "getPlayerScore: player\n", output.toString());
  }

  @Test
  public void maxCaptureFalseLegalityTranscript() {
    StringBuilder output = new StringBuilder();
    ReadOnlyReversiModel mockModel = new FalseLegalityReversi(output,
            Arrays.asList(new MachinePlayer(Name.X, new CaptureMaxStrategy()),
                    new MachinePlayer(Name.X, new CaptureMaxStrategy())));
    ReversiModel testModel = mockModel.getModel();
    try {
      testModel.nextToPlay().startTurn(testModel);
    }
    catch (Exception e) {
      //inshallah do nothing
    }
    Assert.assertEquals(output.toString(),"getModel\n" +
            "nextToPlay\n" +
            "getModel\n" +
            "getPlayerScore: player\n" +
            "getBottomRow\n" +
            "getRightCol\n" +
            "getLeftCol\n" +
            "getTopRow\n" +
            "validMove: row = -5, col = -5\n" +
            "validMove: row = -5, col = -4\n" +
            "validMove: row = -5, col = -3\n" +
            "validMove: row = -5, col = -2\n" +
            "validMove: row = -5, col = -1\n" +
            "validMove: row = -5, col = 0\n" +
            "validMove: row = -5, col = 1\n" +
            "validMove: row = -5, col = 2\n" +
            "validMove: row = -5, col = 3\n" +
            "validMove: row = -5, col = 4\n" +
            "validMove: row = -5, col = 5\n" +
            "validMove: row = -4, col = -5\n" +
            "validMove: row = -4, col = -4\n" +
            "validMove: row = -4, col = -3\n" +
            "validMove: row = -4, col = -2\n" +
            "validMove: row = -4, col = -1\n" +
            "validMove: row = -4, col = 0\n" +
            "validMove: row = -4, col = 1\n" +
            "validMove: row = -4, col = 2\n" +
            "validMove: row = -4, col = 3\n" +
            "validMove: row = -4, col = 4\n" +
            "validMove: row = -4, col = 5\n" +
            "validMove: row = -3, col = -5\n" +
            "validMove: row = -3, col = -4\n" +
            "validMove: row = -3, col = -3\n" +
            "validMove: row = -3, col = -2\n" +
            "validMove: row = -3, col = -1\n" +
            "validMove: row = -3, col = 0\n" +
            "validMove: row = -3, col = 1\n" +
            "validMove: row = -3, col = 2\n" +
            "validMove: row = -3, col = 3\n" +
            "validMove: row = -3, col = 4\n" +
            "validMove: row = -3, col = 5\n" +
            "validMove: row = -2, col = -5\n" +
            "validMove: row = -2, col = -4\n" +
            "validMove: row = -2, col = -3\n" +
            "validMove: row = -2, col = -2\n" +
            "validMove: row = -2, col = -1\n" +
            "validMove: row = -2, col = 0\n" +
            "validMove: row = -2, col = 1\n" +
            "validMove: row = -2, col = 2\n" +
            "validMove: row = -2, col = 3\n" +
            "validMove: row = -2, col = 4\n" +
            "validMove: row = -2, col = 5\n" +
            "validMove: row = -1, col = -5\n" +
            "validMove: row = -1, col = -4\n" +
            "validMove: row = -1, col = -3\n" +
            "validMove: row = -1, col = -2\n" +
            "validMove: row = -1, col = -1\n" +
            "validMove: row = -1, col = 0\n" +
            "validMove: row = -1, col = 1\n" +
            "validMove: row = -1, col = 2\n" +
            "validMove: row = -1, col = 3\n" +
            "validMove: row = -1, col = 4\n" +
            "validMove: row = -1, col = 5\n" +
            "validMove: row = 0, col = -5\n" +
            "validMove: row = 0, col = -4\n" +
            "validMove: row = 0, col = -3\n" +
            "validMove: row = 0, col = -2\n" +
            "validMove: row = 0, col = -1\n" +
            "validMove: row = 0, col = 0\n" +
            "validMove: row = 0, col = 1\n" +
            "validMove: row = 0, col = 2\n" +
            "validMove: row = 0, col = 3\n" +
            "validMove: row = 0, col = 4\n" +
            "validMove: row = 0, col = 5\n" +
            "validMove: row = 1, col = -5\n" +
            "validMove: row = 1, col = -4\n" +
            "validMove: row = 1, col = -3\n" +
            "validMove: row = 1, col = -2\n" +
            "validMove: row = 1, col = -1\n" +
            "validMove: row = 1, col = 0\n" +
            "validMove: row = 1, col = 1\n" +
            "validMove: row = 1, col = 2\n" +
            "validMove: row = 1, col = 3\n" +
            "validMove: row = 1, col = 4\n" +
            "validMove: row = 1, col = 5\n" +
            "validMove: row = 2, col = -5\n" +
            "validMove: row = 2, col = -4\n" +
            "validMove: row = 2, col = -3\n" +
            "validMove: row = 2, col = -2\n" +
            "validMove: row = 2, col = -1\n" +
            "validMove: row = 2, col = 0\n" +
            "validMove: row = 2, col = 1\n" +
            "validMove: row = 2, col = 2\n" +
            "validMove: row = 2, col = 3\n" +
            "validMove: row = 2, col = 4\n" +
            "validMove: row = 2, col = 5\n" +
            "validMove: row = 3, col = -5\n" +
            "validMove: row = 3, col = -4\n" +
            "validMove: row = 3, col = -3\n" +
            "validMove: row = 3, col = -2\n" +
            "validMove: row = 3, col = -1\n" +
            "validMove: row = 3, col = 0\n" +
            "validMove: row = 3, col = 1\n" +
            "validMove: row = 3, col = 2\n" +
            "validMove: row = 3, col = 3\n" +
            "validMove: row = 3, col = 4\n" +
            "validMove: row = 3, col = 5\n" +
            "validMove: row = 4, col = -5\n" +
            "validMove: row = 4, col = -4\n" +
            "validMove: row = 4, col = -3\n" +
            "validMove: row = 4, col = -2\n" +
            "validMove: row = 4, col = -1\n" +
            "validMove: row = 4, col = 0\n" +
            "validMove: row = 4, col = 1\n" +
            "validMove: row = 4, col = 2\n" +
            "validMove: row = 4, col = 3\n" +
            "validMove: row = 4, col = 4\n" +
            "validMove: row = 4, col = 5\n" +
            "validMove: row = 5, col = -5\n" +
            "validMove: row = 5, col = -4\n" +
            "validMove: row = 5, col = -3\n" +
            "validMove: row = 5, col = -2\n" +
            "validMove: row = 5, col = -1\n" +
            "validMove: row = 5, col = 0\n" +
            "validMove: row = 5, col = 1\n" +
            "validMove: row = 5, col = 2\n" +
            "validMove: row = 5, col = 3\n" +
            "validMove: row = 5, col = 4\n" +
            "validMove: row = 5, col = 5\n" +
            "getModel\n" +
            "placePiece: row = 2, col = 2\n");
  }
}
