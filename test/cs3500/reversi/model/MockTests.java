package cs3500.reversi.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import cs3500.reversi.player.CaptureMaxPlayer;
import cs3500.reversi.player.HumanPlayer;
import cs3500.reversi.player.Player;
import cs3500.reversi.player.StrategyPlayer;
import cs3500.reversi.strategy.AvoidNextToCornersStrategy;
import cs3500.reversi.strategy.CaptureCornersStrategy;
import cs3500.reversi.view.ReversiView;
import cs3500.reversi.view.TextReversiView;

/**
 * Tests for mutability etc of the mock reversi class.
 */
public class MockTests {
  @Test
  public void captureInputCorners() {
    StringBuilder output = new StringBuilder();
    ReadOnlyReversiModel mockModel = new CaptureInputReversi(output, Arrays.asList(new StrategyPlayer("X", new CaptureCornersStrategy()), new StrategyPlayer("X", new CaptureCornersStrategy())));
    ReversiModel testModel = mockModel.getModel();
    try {
      testModel.nextToPlay().getMove(testModel);
    }
    catch (Exception e) {
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
    ReadOnlyReversiModel mockModel = new CaptureInputReversi(output, Arrays.asList(new StrategyPlayer("X", new AvoidNextToCornersStrategy()), new StrategyPlayer("X", new AvoidNextToCornersStrategy())));
    ReversiModel testModel = mockModel.getModel();
    try {
      testModel.nextToPlay().getMove(testModel);
    }
    catch (Exception e) {
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
}
