package cs3500.reversi.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import cs3500.reversi.player.MachinePlayer;
import cs3500.reversi.player.Name;
import cs3500.reversi.strategy.AvoidNextToCornersStrategy;
import cs3500.reversi.strategy.CaptureCornersStrategy;
import cs3500.reversi.strategy.CaptureMaxStrategy;
import cs3500.reversi.strategy.CompleteReversiStrategyFromFallible;
import cs3500.reversi.view.ReversiView;
import cs3500.reversi.view.TextReversiView;

/**
 * Tests if the Strategies make correct requests to the model.
 */
public class StrategyMockTests {
  @Test
  public void captureInputCorners() {
    StringBuilder output = new StringBuilder();
    ReadOnlyReversiModel mockModel = new CaptureInputReadOnlyReversi(output,
            Arrays.asList(new MachinePlayer(Name.X, new CaptureCornersStrategy()),
                    new MachinePlayer(Name.O, new CaptureCornersStrategy())),
            List.of(HexDirection.values()));
    CompleteReversiStrategyFromFallible captureCorners =
            new CompleteReversiStrategyFromFallible(new CaptureCornersStrategy());
    LinearCoord bestMove = captureCorners.chooseMove(mockModel,
            new MachinePlayer(Name.X, new CaptureCornersStrategy()));
    Assert.assertEquals(new BasicPoint(-2, 0), bestMove);
    Assert.assertEquals("getModel\n" +
            "startGame\n" +
            "getModel\n" +
            "startGame\n" +
            "startGame\n" +
            "getBottomRow\n" +
            "getRightCol\n" +
            "getLeftCol\n" +
            "getTopRow\n" +
            "validMove: row = -5, col = -5\n" +
            "validMove: row = -5, col = -4\n" +
            "validMove: row = -5, col = -3\n" +
            "playerAt: row = -5, col = -2\n" +
            "playerAt: row = -4, col = -2\n" +
            "playerAt: row = -4, col = -3\n" +
            "validMove: row = -5, col = -2\n" +
            "playerAt: row = -5, col = -1\n" +
            "playerAt: row = -4, col = -1\n" +
            "playerAt: row = -4, col = -2\n" +
            "playerAt: row = -5, col = -3\n" +
            "validMove: row = -5, col = -1\n" +
            "playerAt: row = -5, col = 0\n" +
            "playerAt: row = -4, col = 0\n" +
            "playerAt: row = -4, col = -1\n" +
            "playerAt: row = -5, col = -2\n" +
            "validMove: row = -5, col = 0\n" +
            "playerAt: row = -5, col = 1\n" +
            "playerAt: row = -4, col = 1\n" +
            "playerAt: row = -4, col = 0\n" +
            "playerAt: row = -5, col = -1\n" +
            "validMove: row = -5, col = 1\n" +
            "playerAt: row = -5, col = 2\n" +
            "playerAt: row = -4, col = 2\n" +
            "playerAt: row = -4, col = 1\n" +
            "playerAt: row = -5, col = 0\n" +
            "validMove: row = -5, col = 2\n" +
            "playerAt: row = -4, col = 3\n" +
            "playerAt: row = -4, col = 2\n" +
            "playerAt: row = -5, col = 1\n" +
            "validMove: row = -5, col = 3\n" +
            "validMove: row = -5, col = 4\n" +
            "validMove: row = -5, col = 5\n" +
            "validMove: row = -4, col = -5\n" +
            "validMove: row = -4, col = -4\n" +
            "validMove: row = -4, col = -3\n" +
            "playerAt: row = -5, col = -3\n" +
            "playerAt: row = -4, col = -2\n" +
            "playerAt: row = -3, col = -3\n" +
            "playerAt: row = -3, col = -4\n" +
            "validMove: row = -4, col = -2\n" +
            "playerAt: row = -5, col = -2\n" +
            "playerAt: row = -4, col = -1\n" +
            "playerAt: row = -3, col = -2\n" +
            "playerAt: row = -3, col = -3\n" +
            "playerAt: row = -4, col = -3\n" +
            "playerAt: row = -5, col = -3\n" +
            "validMove: row = -4, col = -1\n" +
            "playerAt: row = -5, col = -1\n" +
            "playerAt: row = -4, col = 0\n" +
            "playerAt: row = -3, col = -1\n" +
            "playerAt: row = -3, col = -2\n" +
            "playerAt: row = -4, col = -2\n" +
            "playerAt: row = -5, col = -2\n" +
            "validMove: row = -4, col = 0\n" +
            "playerAt: row = -5, col = 0\n" +
            "playerAt: row = -4, col = 1\n" +
            "playerAt: row = -3, col = 0\n" +
            "playerAt: row = -3, col = -1\n" +
            "playerAt: row = -4, col = -1\n" +
            "playerAt: row = -5, col = -1\n" +
            "validMove: row = -4, col = 1\n" +
            "playerAt: row = -5, col = 1\n" +
            "playerAt: row = -4, col = 2\n" +
            "playerAt: row = -3, col = 1\n" +
            "playerAt: row = -3, col = 0\n" +
            "playerAt: row = -4, col = 0\n" +
            "playerAt: row = -5, col = 0\n" +
            "validMove: row = -4, col = 2\n" +
            "playerAt: row = -5, col = 2\n" +
            "playerAt: row = -4, col = 3\n" +
            "playerAt: row = -3, col = 2\n" +
            "playerAt: row = -3, col = 1\n" +
            "playerAt: row = -4, col = 1\n" +
            "playerAt: row = -5, col = 1\n" +
            "validMove: row = -4, col = 3\n" +
            "playerAt: row = -3, col = 3\n" +
            "playerAt: row = -3, col = 2\n" +
            "playerAt: row = -4, col = 2\n" +
            "playerAt: row = -5, col = 2\n" +
            "validMove: row = -4, col = 4\n" +
            "validMove: row = -4, col = 5\n" +
            "validMove: row = -3, col = -5\n" +
            "validMove: row = -3, col = -4\n" +
            "playerAt: row = -4, col = -3\n" +
            "playerAt: row = -3, col = -3\n" +
            "playerAt: row = -2, col = -3\n" +
            "playerAt: row = -2, col = -4\n" +
            "validMove: row = -3, col = -3\n" +
            "playerAt: row = -4, col = -2\n" +
            "playerAt: row = -3, col = -2\n" +
            "playerAt: row = -2, col = -2\n" +
            "playerAt: row = -2, col = -3\n" +
            "playerAt: row = -3, col = -4\n" +
            "playerAt: row = -4, col = -3\n" +
            "validMove: row = -3, col = -2\n" +
            "playerAt: row = -4, col = -1\n" +
            "playerAt: row = -3, col = -1\n" +
            "playerAt: row = -2, col = -1\n" +
            "playerAt: row = -2, col = -2\n" +
            "playerAt: row = -3, col = -3\n" +
            "playerAt: row = -4, col = -2\n" +
            "validMove: row = -3, col = -1\n" +
            "playerAt: row = -4, col = 0\n" +
            "playerAt: row = -3, col = 0\n" +
            "playerAt: row = -2, col = 0\n" +
            "playerAt: row = -2, col = -1\n" +
            "playerAt: row = -3, col = -2\n" +
            "playerAt: row = -4, col = -1\n" +
            "validMove: row = -3, col = 0\n" +
            "playerAt: row = -4, col = 1\n" +
            "playerAt: row = -3, col = 1\n" +
            "playerAt: row = -2, col = 1\n" +
            "playerAt: row = -2, col = 0\n" +
            "playerAt: row = -3, col = -1\n" +
            "playerAt: row = -4, col = 0\n" +
            "validMove: row = -3, col = 1\n" +
            "playerAt: row = -4, col = 2\n" +
            "playerAt: row = -3, col = 2\n" +
            "playerAt: row = -2, col = 2\n" +
            "playerAt: row = -2, col = 1\n" +
            "playerAt: row = -3, col = 0\n" +
            "playerAt: row = -4, col = 1\n" +
            "validMove: row = -3, col = 2\n" +
            "playerAt: row = -4, col = 3\n" +
            "playerAt: row = -3, col = 3\n" +
            "playerAt: row = -2, col = 3\n" +
            "playerAt: row = -2, col = 2\n" +
            "playerAt: row = -3, col = 1\n" +
            "playerAt: row = -4, col = 2\n" +
            "validMove: row = -3, col = 3\n" +
            "playerAt: row = -2, col = 4\n" +
            "playerAt: row = -2, col = 3\n" +
            "playerAt: row = -3, col = 2\n" +
            "playerAt: row = -4, col = 3\n" +
            "validMove: row = -3, col = 4\n" +
            "validMove: row = -3, col = 5\n" +
            "validMove: row = -2, col = -5\n" +
            "validMove: row = -2, col = -4\n" +
            "playerAt: row = -3, col = -4\n" +
            "playerAt: row = -2, col = -3\n" +
            "playerAt: row = -1, col = -4\n" +
            "playerAt: row = -1, col = -5\n" +
            "validMove: row = -2, col = -3\n" +
            "playerAt: row = -3, col = -3\n" +
            "playerAt: row = -2, col = -2\n" +
            "playerAt: row = -1, col = -3\n" +
            "playerAt: row = -1, col = -4\n" +
            "playerAt: row = -2, col = -4\n" +
            "playerAt: row = -3, col = -4\n" +
            "validMove: row = -2, col = -2\n" +
            "playerAt: row = -3, col = -2\n" +
            "playerAt: row = -2, col = -1\n" +
            "playerAt: row = -1, col = -2\n" +
            "playerAt: row = -1, col = -3\n" +
            "playerAt: row = -2, col = -3\n" +
            "playerAt: row = -3, col = -3\n" +
            "validMove: row = -2, col = -1\n" +
            "playerAt: row = -3, col = -1\n" +
            "playerAt: row = -2, col = 0\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = 0, col = 0\n" +
            "playerAt: row = -1, col = -2\n" +
            "playerAt: row = -2, col = -2\n" +
            "playerAt: row = -3, col = -2\n" +
            "validMove: row = -2, col = 0\n" +
            "playerAt: row = -3, col = 0\n" +
            "playerAt: row = -2, col = 1\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = -2, col = -1\n" +
            "playerAt: row = -3, col = -1\n" +
            "validMove: row = -2, col = 1\n" +
            "playerAt: row = -3, col = 1\n" +
            "playerAt: row = -2, col = 2\n" +
            "playerAt: row = -1, col = 1\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -2, col = 0\n" +
            "playerAt: row = -3, col = 0\n" +
            "validMove: row = -2, col = 2\n" +
            "playerAt: row = -3, col = 2\n" +
            "playerAt: row = -2, col = 3\n" +
            "playerAt: row = -1, col = 2\n" +
            "playerAt: row = -1, col = 1\n" +
            "playerAt: row = -2, col = 1\n" +
            "playerAt: row = -3, col = 1\n" +
            "validMove: row = -2, col = 3\n" +
            "playerAt: row = -3, col = 3\n" +
            "playerAt: row = -2, col = 4\n" +
            "playerAt: row = -1, col = 3\n" +
            "playerAt: row = -1, col = 2\n" +
            "playerAt: row = -2, col = 2\n" +
            "playerAt: row = -3, col = 2\n" +
            "validMove: row = -2, col = 4\n" +
            "playerAt: row = -1, col = 4\n" +
            "playerAt: row = -1, col = 3\n" +
            "playerAt: row = -2, col = 3\n" +
            "playerAt: row = -3, col = 3\n" +
            "validMove: row = -2, col = 5\n" +
            "validMove: row = -1, col = -5\n" +
            "playerAt: row = -2, col = -4\n" +
            "playerAt: row = -1, col = -4\n" +
            "playerAt: row = 0, col = -4\n" +
            "playerAt: row = 0, col = -5\n" +
            "validMove: row = -1, col = -4\n" +
            "playerAt: row = -2, col = -3\n" +
            "playerAt: row = -1, col = -3\n" +
            "playerAt: row = 0, col = -3\n" +
            "playerAt: row = 0, col = -4\n" +
            "playerAt: row = -1, col = -5\n" +
            "playerAt: row = -2, col = -4\n" +
            "validMove: row = -1, col = -3\n" +
            "playerAt: row = -2, col = -2\n" +
            "playerAt: row = -1, col = -2\n" +
            "playerAt: row = 0, col = -2\n" +
            "playerAt: row = 0, col = -3\n" +
            "playerAt: row = -1, col = -4\n" +
            "playerAt: row = -2, col = -3\n" +
            "validMove: row = -1, col = -2\n" +
            "playerAt: row = -2, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -2\n" +
            "playerAt: row = -1, col = -3\n" +
            "playerAt: row = -2, col = -2\n" +
            "validMove: row = -1, col = -1\n" +
            "validMove: row = -1, col = 0\n" +
            "validMove: row = -1, col = 1\n" +
            "playerAt: row = -2, col = 2\n" +
            "playerAt: row = -1, col = 2\n" +
            "playerAt: row = 0, col = 2\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -2, col = 1\n" +
            "validMove: row = -1, col = 2\n" +
            "playerAt: row = -2, col = 3\n" +
            "playerAt: row = -1, col = 3\n" +
            "playerAt: row = 0, col = 3\n" +
            "playerAt: row = 0, col = 2\n" +
            "playerAt: row = -1, col = 1\n" +
            "playerAt: row = -2, col = 2\n" +
            "validMove: row = -1, col = 3\n" +
            "playerAt: row = -2, col = 4\n" +
            "playerAt: row = -1, col = 4\n" +
            "playerAt: row = 0, col = 4\n" +
            "playerAt: row = 0, col = 3\n" +
            "playerAt: row = -1, col = 2\n" +
            "playerAt: row = -2, col = 3\n" +
            "validMove: row = -1, col = 4\n" +
            "playerAt: row = 0, col = 5\n" +
            "playerAt: row = 0, col = 4\n" +
            "playerAt: row = -1, col = 3\n" +
            "playerAt: row = -2, col = 4\n" +
            "validMove: row = -1, col = 5\n" +
            "validMove: row = 0, col = -5\n" +
            "playerAt: row = -1, col = -5\n" +
            "playerAt: row = 0, col = -4\n" +
            "playerAt: row = 1, col = -5\n" +
            "validMove: row = 0, col = -4\n" +
            "playerAt: row = -1, col = -4\n" +
            "playerAt: row = 0, col = -3\n" +
            "playerAt: row = 1, col = -4\n" +
            "playerAt: row = 1, col = -5\n" +
            "playerAt: row = 0, col = -5\n" +
            "playerAt: row = -1, col = -5\n" +
            "validMove: row = 0, col = -3\n" +
            "playerAt: row = -1, col = -3\n" +
            "playerAt: row = 0, col = -2\n" +
            "playerAt: row = 1, col = -3\n" +
            "playerAt: row = 1, col = -4\n" +
            "playerAt: row = 0, col = -4\n" +
            "playerAt: row = -1, col = -4\n" +
            "validMove: row = 0, col = -2\n" +
            "playerAt: row = -1, col = -2\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 1, col = -2\n" +
            "playerAt: row = 1, col = -3\n" +
            "playerAt: row = 0, col = -3\n" +
            "playerAt: row = -1, col = -3\n" +
            "validMove: row = 0, col = -1\n" +
            "validMove: row = 0, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 2\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 2, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -2, col = -1\n" +
            "validMove: row = 0, col = 1\n" +
            "validMove: row = 0, col = 2\n" +
            "playerAt: row = -1, col = 2\n" +
            "playerAt: row = 0, col = 3\n" +
            "playerAt: row = 1, col = 2\n" +
            "playerAt: row = 1, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 0\n" +
            "playerAt: row = -1, col = 1\n" +
            "validMove: row = 0, col = 3\n" +
            "playerAt: row = -1, col = 3\n" +
            "playerAt: row = 0, col = 4\n" +
            "playerAt: row = 1, col = 3\n" +
            "playerAt: row = 1, col = 2\n" +
            "playerAt: row = 0, col = 2\n" +
            "playerAt: row = -1, col = 2\n" +
            "validMove: row = 0, col = 4\n" +
            "playerAt: row = -1, col = 4\n" +
            "playerAt: row = 0, col = 5\n" +
            "playerAt: row = 1, col = 4\n" +
            "playerAt: row = 1, col = 3\n" +
            "playerAt: row = 0, col = 3\n" +
            "playerAt: row = -1, col = 3\n" +
            "validMove: row = 0, col = 5\n" +
            "playerAt: row = 1, col = 4\n" +
            "playerAt: row = 0, col = 4\n" +
            "playerAt: row = -1, col = 4\n" +
            "validMove: row = 1, col = -5\n" +
            "playerAt: row = 0, col = -4\n" +
            "playerAt: row = 1, col = -4\n" +
            "playerAt: row = 2, col = -4\n" +
            "playerAt: row = 0, col = -5\n" +
            "validMove: row = 1, col = -4\n" +
            "playerAt: row = 0, col = -3\n" +
            "playerAt: row = 1, col = -3\n" +
            "playerAt: row = 2, col = -3\n" +
            "playerAt: row = 2, col = -4\n" +
            "playerAt: row = 1, col = -5\n" +
            "playerAt: row = 0, col = -4\n" +
            "validMove: row = 1, col = -3\n" +
            "playerAt: row = 0, col = -2\n" +
            "playerAt: row = 1, col = -2\n" +
            "playerAt: row = 2, col = -2\n" +
            "playerAt: row = 2, col = -3\n" +
            "playerAt: row = 1, col = -4\n" +
            "playerAt: row = 0, col = -3\n" +
            "validMove: row = 1, col = -2\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 2, col = -1\n" +
            "playerAt: row = 2, col = -2\n" +
            "playerAt: row = 1, col = -3\n" +
            "playerAt: row = 0, col = -2\n" +
            "validMove: row = 1, col = -1\n" +
            "validMove: row = 1, col = 0\n" +
            "validMove: row = 1, col = 1\n" +
            "playerAt: row = 0, col = 2\n" +
            "playerAt: row = 1, col = 2\n" +
            "playerAt: row = 2, col = 2\n" +
            "playerAt: row = 2, col = 1\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "validMove: row = 1, col = 2\n" +
            "playerAt: row = 0, col = 3\n" +
            "playerAt: row = 1, col = 3\n" +
            "playerAt: row = 2, col = 3\n" +
            "playerAt: row = 2, col = 2\n" +
            "playerAt: row = 1, col = 1\n" +
            "playerAt: row = 0, col = 2\n" +
            "validMove: row = 1, col = 3\n" +
            "playerAt: row = 0, col = 4\n" +
            "playerAt: row = 1, col = 4\n" +
            "playerAt: row = 2, col = 4\n" +
            "playerAt: row = 2, col = 3\n" +
            "playerAt: row = 1, col = 2\n" +
            "playerAt: row = 0, col = 3\n" +
            "validMove: row = 1, col = 4\n" +
            "playerAt: row = 0, col = 5\n" +
            "playerAt: row = 2, col = 4\n" +
            "playerAt: row = 1, col = 3\n" +
            "playerAt: row = 0, col = 4\n" +
            "validMove: row = 1, col = 5\n" +
            "validMove: row = 2, col = -5\n" +
            "validMove: row = 2, col = -4\n" +
            "playerAt: row = 1, col = -4\n" +
            "playerAt: row = 2, col = -3\n" +
            "playerAt: row = 3, col = -4\n" +
            "playerAt: row = 1, col = -5\n" +
            "validMove: row = 2, col = -3\n" +
            "playerAt: row = 1, col = -3\n" +
            "playerAt: row = 2, col = -2\n" +
            "playerAt: row = 3, col = -3\n" +
            "playerAt: row = 3, col = -4\n" +
            "playerAt: row = 2, col = -4\n" +
            "playerAt: row = 1, col = -4\n" +
            "validMove: row = 2, col = -2\n" +
            "playerAt: row = 1, col = -2\n" +
            "playerAt: row = 2, col = -1\n" +
            "playerAt: row = 3, col = -2\n" +
            "playerAt: row = 3, col = -3\n" +
            "playerAt: row = 2, col = -3\n" +
            "playerAt: row = 1, col = -3\n" +
            "validMove: row = 2, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 0, col = 0\n" +
            "playerAt: row = 2, col = 0\n" +
            "playerAt: row = 3, col = -1\n" +
            "playerAt: row = 3, col = -2\n" +
            "playerAt: row = 2, col = -2\n" +
            "playerAt: row = 1, col = -2\n" +
            "validMove: row = 2, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 2, col = 1\n" +
            "playerAt: row = 3, col = 0\n" +
            "playerAt: row = 3, col = -1\n" +
            "playerAt: row = 2, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "validMove: row = 2, col = 1\n" +
            "playerAt: row = 1, col = 1\n" +
            "playerAt: row = 2, col = 2\n" +
            "playerAt: row = 3, col = 1\n" +
            "playerAt: row = 3, col = 0\n" +
            "playerAt: row = 2, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "validMove: row = 2, col = 2\n" +
            "playerAt: row = 1, col = 2\n" +
            "playerAt: row = 2, col = 3\n" +
            "playerAt: row = 3, col = 2\n" +
            "playerAt: row = 3, col = 1\n" +
            "playerAt: row = 2, col = 1\n" +
            "playerAt: row = 1, col = 1\n" +
            "validMove: row = 2, col = 3\n" +
            "playerAt: row = 1, col = 3\n" +
            "playerAt: row = 2, col = 4\n" +
            "playerAt: row = 3, col = 3\n" +
            "playerAt: row = 3, col = 2\n" +
            "playerAt: row = 2, col = 2\n" +
            "playerAt: row = 1, col = 2\n" +
            "validMove: row = 2, col = 4\n" +
            "playerAt: row = 1, col = 4\n" +
            "playerAt: row = 3, col = 3\n" +
            "playerAt: row = 2, col = 3\n" +
            "playerAt: row = 1, col = 3\n" +
            "validMove: row = 2, col = 5\n" +
            "validMove: row = 3, col = -5\n" +
            "validMove: row = 3, col = -4\n" +
            "playerAt: row = 2, col = -3\n" +
            "playerAt: row = 3, col = -3\n" +
            "playerAt: row = 4, col = -3\n" +
            "playerAt: row = 2, col = -4\n" +
            "validMove: row = 3, col = -3\n" +
            "playerAt: row = 2, col = -2\n" +
            "playerAt: row = 3, col = -2\n" +
            "playerAt: row = 4, col = -2\n" +
            "playerAt: row = 4, col = -3\n" +
            "playerAt: row = 3, col = -4\n" +
            "playerAt: row = 2, col = -3\n" +
            "validMove: row = 3, col = -2\n" +
            "playerAt: row = 2, col = -1\n" +
            "playerAt: row = 3, col = -1\n" +
            "playerAt: row = 4, col = -1\n" +
            "playerAt: row = 4, col = -2\n" +
            "playerAt: row = 3, col = -3\n" +
            "playerAt: row = 2, col = -2\n" +
            "validMove: row = 3, col = -1\n" +
            "playerAt: row = 2, col = 0\n" +
            "playerAt: row = 3, col = 0\n" +
            "playerAt: row = 4, col = 0\n" +
            "playerAt: row = 4, col = -1\n" +
            "playerAt: row = 3, col = -2\n" +
            "playerAt: row = 2, col = -1\n" +
            "validMove: row = 3, col = 0\n" +
            "playerAt: row = 2, col = 1\n" +
            "playerAt: row = 3, col = 1\n" +
            "playerAt: row = 4, col = 1\n" +
            "playerAt: row = 4, col = 0\n" +
            "playerAt: row = 3, col = -1\n" +
            "playerAt: row = 2, col = 0\n" +
            "validMove: row = 3, col = 1\n" +
            "playerAt: row = 2, col = 2\n" +
            "playerAt: row = 3, col = 2\n" +
            "playerAt: row = 4, col = 2\n" +
            "playerAt: row = 4, col = 1\n" +
            "playerAt: row = 3, col = 0\n" +
            "playerAt: row = 2, col = 1\n" +
            "validMove: row = 3, col = 2\n" +
            "playerAt: row = 2, col = 3\n" +
            "playerAt: row = 3, col = 3\n" +
            "playerAt: row = 4, col = 3\n" +
            "playerAt: row = 4, col = 2\n" +
            "playerAt: row = 3, col = 1\n" +
            "playerAt: row = 2, col = 2\n" +
            "validMove: row = 3, col = 3\n" +
            "playerAt: row = 2, col = 4\n" +
            "playerAt: row = 4, col = 3\n" +
            "playerAt: row = 3, col = 2\n" +
            "playerAt: row = 2, col = 3\n" +
            "validMove: row = 3, col = 4\n" +
            "validMove: row = 3, col = 5\n" +
            "validMove: row = 4, col = -5\n" +
            "validMove: row = 4, col = -4\n" +
            "validMove: row = 4, col = -3\n" +
            "playerAt: row = 3, col = -3\n" +
            "playerAt: row = 4, col = -2\n" +
            "playerAt: row = 5, col = -3\n" +
            "playerAt: row = 3, col = -4\n" +
            "validMove: row = 4, col = -2\n" +
            "playerAt: row = 3, col = -2\n" +
            "playerAt: row = 4, col = -1\n" +
            "playerAt: row = 5, col = -2\n" +
            "playerAt: row = 5, col = -3\n" +
            "playerAt: row = 4, col = -3\n" +
            "playerAt: row = 3, col = -3\n" +
            "validMove: row = 4, col = -1\n" +
            "playerAt: row = 3, col = -1\n" +
            "playerAt: row = 4, col = 0\n" +
            "playerAt: row = 5, col = -1\n" +
            "playerAt: row = 5, col = -2\n" +
            "playerAt: row = 4, col = -2\n" +
            "playerAt: row = 3, col = -2\n" +
            "validMove: row = 4, col = 0\n" +
            "playerAt: row = 3, col = 0\n" +
            "playerAt: row = 4, col = 1\n" +
            "playerAt: row = 5, col = 0\n" +
            "playerAt: row = 5, col = -1\n" +
            "playerAt: row = 4, col = -1\n" +
            "playerAt: row = 3, col = -1\n" +
            "validMove: row = 4, col = 1\n" +
            "playerAt: row = 3, col = 1\n" +
            "playerAt: row = 4, col = 2\n" +
            "playerAt: row = 5, col = 1\n" +
            "playerAt: row = 5, col = 0\n" +
            "playerAt: row = 4, col = 0\n" +
            "playerAt: row = 3, col = 0\n" +
            "validMove: row = 4, col = 2\n" +
            "playerAt: row = 3, col = 2\n" +
            "playerAt: row = 4, col = 3\n" +
            "playerAt: row = 5, col = 2\n" +
            "playerAt: row = 5, col = 1\n" +
            "playerAt: row = 4, col = 1\n" +
            "playerAt: row = 3, col = 1\n" +
            "validMove: row = 4, col = 3\n" +
            "playerAt: row = 3, col = 3\n" +
            "playerAt: row = 5, col = 2\n" +
            "playerAt: row = 4, col = 2\n" +
            "playerAt: row = 3, col = 2\n" +
            "validMove: row = 4, col = 4\n" +
            "validMove: row = 4, col = 5\n" +
            "validMove: row = 5, col = -5\n" +
            "validMove: row = 5, col = -4\n" +
            "validMove: row = 5, col = -3\n" +
            "playerAt: row = 4, col = -2\n" +
            "playerAt: row = 5, col = -2\n" +
            "playerAt: row = 4, col = -3\n" +
            "validMove: row = 5, col = -2\n" +
            "playerAt: row = 4, col = -1\n" +
            "playerAt: row = 5, col = -1\n" +
            "playerAt: row = 5, col = -3\n" +
            "playerAt: row = 4, col = -2\n" +
            "validMove: row = 5, col = -1\n" +
            "playerAt: row = 4, col = 0\n" +
            "playerAt: row = 5, col = 0\n" +
            "playerAt: row = 5, col = -2\n" +
            "playerAt: row = 4, col = -1\n" +
            "validMove: row = 5, col = 0\n" +
            "playerAt: row = 4, col = 1\n" +
            "playerAt: row = 5, col = 1\n" +
            "playerAt: row = 5, col = -1\n" +
            "playerAt: row = 4, col = 0\n" +
            "validMove: row = 5, col = 1\n" +
            "playerAt: row = 4, col = 2\n" +
            "playerAt: row = 5, col = 2\n" +
            "playerAt: row = 5, col = 0\n" +
            "playerAt: row = 4, col = 1\n" +
            "validMove: row = 5, col = 2\n" +
            "playerAt: row = 4, col = 3\n" +
            "playerAt: row = 5, col = 1\n" +
            "playerAt: row = 4, col = 2\n" +
            "validMove: row = 5, col = 3\n" +
            "validMove: row = 5, col = 4\n" +
            "validMove: row = 5, col = 5\n" +
            "startGame\n" +
            "startGame\n" +
            "getTopRow\n" +
            "getBottomRow\n" +
            "getLeftCol\n" +
            "getRightCol\n" +
            "playerAt: row = -5, col = -5\n" +
            "getRightCol\n" +
            "playerAt: row = -5, col = -4\n" +
            "getRightCol\n" +
            "playerAt: row = -5, col = -3\n" +
            "getRightCol\n" +
            "playerAt: row = -5, col = -2\n" +
            "getRightCol\n" +
            "playerAt: row = -5, col = -1\n" +
            "getRightCol\n" +
            "playerAt: row = -5, col = 0\n" +
            "getRightCol\n" +
            "playerAt: row = -5, col = 1\n" +
            "getRightCol\n" +
            "playerAt: row = -5, col = 2\n" +
            "getRightCol\n" +
            "playerAt: row = -5, col = 3\n" +
            "getRightCol\n" +
            "playerAt: row = -5, col = 4\n" +
            "getRightCol\n" +
            "playerAt: row = -5, col = 5\n" +
            "getRightCol\n" +
            "getBottomRow\n" +
            "getLeftCol\n" +
            "getRightCol\n" +
            "playerAt: row = -4, col = -5\n" +
            "getRightCol\n" +
            "playerAt: row = -4, col = -4\n" +
            "getRightCol\n" +
            "playerAt: row = -4, col = -3\n" +
            "getRightCol\n" +
            "playerAt: row = -4, col = -2\n" +
            "getRightCol\n" +
            "playerAt: row = -4, col = -1\n" +
            "getRightCol\n" +
            "playerAt: row = -4, col = 0\n" +
            "getRightCol\n" +
            "playerAt: row = -4, col = 1\n" +
            "getRightCol\n" +
            "playerAt: row = -4, col = 2\n" +
            "getRightCol\n" +
            "playerAt: row = -4, col = 3\n" +
            "getRightCol\n" +
            "playerAt: row = -4, col = 4\n" +
            "getRightCol\n" +
            "playerAt: row = -4, col = 5\n" +
            "getRightCol\n" +
            "getBottomRow\n" +
            "getLeftCol\n" +
            "getRightCol\n" +
            "playerAt: row = -3, col = -5\n" +
            "getRightCol\n" +
            "playerAt: row = -3, col = -4\n" +
            "getRightCol\n" +
            "playerAt: row = -3, col = -3\n" +
            "getRightCol\n" +
            "playerAt: row = -3, col = -2\n" +
            "getRightCol\n" +
            "playerAt: row = -3, col = -1\n" +
            "getRightCol\n" +
            "playerAt: row = -3, col = 0\n" +
            "getRightCol\n" +
            "playerAt: row = -3, col = 1\n" +
            "getRightCol\n" +
            "playerAt: row = -3, col = 2\n" +
            "getRightCol\n" +
            "playerAt: row = -3, col = 3\n" +
            "getRightCol\n" +
            "playerAt: row = -3, col = 4\n" +
            "getRightCol\n" +
            "playerAt: row = -3, col = 5\n" +
            "getRightCol\n" +
            "getBottomRow\n" +
            "getLeftCol\n" +
            "getRightCol\n" +
            "playerAt: row = -2, col = -5\n" +
            "getRightCol\n" +
            "playerAt: row = -2, col = -4\n" +
            "getRightCol\n" +
            "playerAt: row = -2, col = -3\n" +
            "getRightCol\n" +
            "playerAt: row = -2, col = -2\n" +
            "getRightCol\n" +
            "playerAt: row = -2, col = -1\n" +
            "getRightCol\n" +
            "playerAt: row = -2, col = 0\n" +
            "getRightCol\n" +
            "playerAt: row = -2, col = 1\n" +
            "getRightCol\n" +
            "playerAt: row = -2, col = 2\n" +
            "getRightCol\n" +
            "playerAt: row = -2, col = 3\n" +
            "getRightCol\n" +
            "playerAt: row = -2, col = 4\n" +
            "getRightCol\n" +
            "playerAt: row = -2, col = 5\n" +
            "getRightCol\n" +
            "getBottomRow\n" +
            "getLeftCol\n" +
            "getRightCol\n" +
            "playerAt: row = -1, col = -5\n" +
            "getRightCol\n" +
            "playerAt: row = -1, col = -4\n" +
            "getRightCol\n" +
            "playerAt: row = -1, col = -3\n" +
            "getRightCol\n" +
            "playerAt: row = -1, col = -2\n" +
            "getRightCol\n" +
            "playerAt: row = -1, col = -1\n" +
            "getRightCol\n" +
            "playerAt: row = -1, col = 0\n" +
            "getRightCol\n" +
            "playerAt: row = -1, col = 1\n" +
            "getRightCol\n" +
            "playerAt: row = -1, col = 2\n" +
            "getRightCol\n" +
            "playerAt: row = -1, col = 3\n" +
            "getRightCol\n" +
            "playerAt: row = -1, col = 4\n" +
            "getRightCol\n" +
            "playerAt: row = -1, col = 5\n" +
            "getRightCol\n" +
            "getBottomRow\n" +
            "getLeftCol\n" +
            "getRightCol\n" +
            "playerAt: row = 0, col = -5\n" +
            "getRightCol\n" +
            "playerAt: row = 0, col = -4\n" +
            "getRightCol\n" +
            "playerAt: row = 0, col = -3\n" +
            "getRightCol\n" +
            "playerAt: row = 0, col = -2\n" +
            "getRightCol\n" +
            "playerAt: row = 0, col = -1\n" +
            "getRightCol\n" +
            "playerAt: row = 0, col = 0\n" +
            "getRightCol\n" +
            "playerAt: row = 0, col = 1\n" +
            "getRightCol\n" +
            "playerAt: row = 0, col = 2\n" +
            "getRightCol\n" +
            "playerAt: row = 0, col = 3\n" +
            "getRightCol\n" +
            "playerAt: row = 0, col = 4\n" +
            "getRightCol\n" +
            "playerAt: row = 0, col = 5\n" +
            "getRightCol\n" +
            "getBottomRow\n" +
            "getLeftCol\n" +
            "getRightCol\n" +
            "playerAt: row = 1, col = -5\n" +
            "getRightCol\n" +
            "playerAt: row = 1, col = -4\n" +
            "getRightCol\n" +
            "playerAt: row = 1, col = -3\n" +
            "getRightCol\n" +
            "playerAt: row = 1, col = -2\n" +
            "getRightCol\n" +
            "playerAt: row = 1, col = -1\n" +
            "getRightCol\n" +
            "playerAt: row = 1, col = 0\n" +
            "getRightCol\n" +
            "playerAt: row = 1, col = 1\n" +
            "getRightCol\n" +
            "playerAt: row = 1, col = 2\n" +
            "getRightCol\n" +
            "playerAt: row = 1, col = 3\n" +
            "getRightCol\n" +
            "playerAt: row = 1, col = 4\n" +
            "getRightCol\n" +
            "playerAt: row = 1, col = 5\n" +
            "getRightCol\n" +
            "getBottomRow\n" +
            "getLeftCol\n" +
            "getRightCol\n" +
            "playerAt: row = 2, col = -5\n" +
            "getRightCol\n" +
            "playerAt: row = 2, col = -4\n" +
            "getRightCol\n" +
            "playerAt: row = 2, col = -3\n" +
            "getRightCol\n" +
            "playerAt: row = 2, col = -2\n" +
            "getRightCol\n" +
            "playerAt: row = 2, col = -1\n" +
            "getRightCol\n" +
            "playerAt: row = 2, col = 0\n" +
            "getRightCol\n" +
            "playerAt: row = 2, col = 1\n" +
            "getRightCol\n" +
            "playerAt: row = 2, col = 2\n" +
            "getRightCol\n" +
            "playerAt: row = 2, col = 3\n" +
            "getRightCol\n" +
            "playerAt: row = 2, col = 4\n" +
            "getRightCol\n" +
            "playerAt: row = 2, col = 5\n" +
            "getRightCol\n" +
            "getBottomRow\n" +
            "getLeftCol\n" +
            "getRightCol\n" +
            "playerAt: row = 3, col = -5\n" +
            "getRightCol\n" +
            "playerAt: row = 3, col = -4\n" +
            "getRightCol\n" +
            "playerAt: row = 3, col = -3\n" +
            "getRightCol\n" +
            "playerAt: row = 3, col = -2\n" +
            "getRightCol\n" +
            "playerAt: row = 3, col = -1\n" +
            "getRightCol\n" +
            "playerAt: row = 3, col = 0\n" +
            "getRightCol\n" +
            "playerAt: row = 3, col = 1\n" +
            "getRightCol\n" +
            "playerAt: row = 3, col = 2\n" +
            "getRightCol\n" +
            "playerAt: row = 3, col = 3\n" +
            "getRightCol\n" +
            "playerAt: row = 3, col = 4\n" +
            "getRightCol\n" +
            "playerAt: row = 3, col = 5\n" +
            "getRightCol\n" +
            "getBottomRow\n" +
            "getLeftCol\n" +
            "getRightCol\n" +
            "playerAt: row = 4, col = -5\n" +
            "getRightCol\n" +
            "playerAt: row = 4, col = -4\n" +
            "getRightCol\n" +
            "playerAt: row = 4, col = -3\n" +
            "getRightCol\n" +
            "playerAt: row = 4, col = -2\n" +
            "getRightCol\n" +
            "playerAt: row = 4, col = -1\n" +
            "getRightCol\n" +
            "playerAt: row = 4, col = 0\n" +
            "getRightCol\n" +
            "playerAt: row = 4, col = 1\n" +
            "getRightCol\n" +
            "playerAt: row = 4, col = 2\n" +
            "getRightCol\n" +
            "playerAt: row = 4, col = 3\n" +
            "getRightCol\n" +
            "playerAt: row = 4, col = 4\n" +
            "getRightCol\n" +
            "playerAt: row = 4, col = 5\n" +
            "getRightCol\n" +
            "getBottomRow\n" +
            "getLeftCol\n" +
            "getRightCol\n" +
            "playerAt: row = 5, col = -5\n" +
            "getRightCol\n" +
            "playerAt: row = 5, col = -4\n" +
            "getRightCol\n" +
            "playerAt: row = 5, col = -3\n" +
            "getRightCol\n" +
            "playerAt: row = 5, col = -2\n" +
            "getRightCol\n" +
            "playerAt: row = 5, col = -1\n" +
            "getRightCol\n" +
            "playerAt: row = 5, col = 0\n" +
            "getRightCol\n" +
            "playerAt: row = 5, col = 1\n" +
            "getRightCol\n" +
            "playerAt: row = 5, col = 2\n" +
            "getRightCol\n" +
            "playerAt: row = 5, col = 3\n" +
            "getRightCol\n" +
            "playerAt: row = 5, col = 4\n" +
            "getRightCol\n" +
            "playerAt: row = 5, col = 5\n" +
            "getRightCol\n" +
            "getBottomRow\n", output.toString());
    Assert.assertTrue(output.toString().contains("validMove: row = -5, col = -3"));
    Assert.assertTrue(output.toString().contains("validMove: row = -5, col = 2"));
    Assert.assertTrue(output.toString().contains("validMove: row = 0, col = -5"));
    Assert.assertTrue(output.toString().contains("validMove: row = 0, col = 5"));
    Assert.assertTrue(output.toString().contains("validMove: row = 5, col = -3"));
    Assert.assertTrue(output.toString().contains("validMove: row = 5, col = 2"));
  }

  @Test
  public void captureSquareInputAvoidNextToCorners() {
    StringBuilder output = new StringBuilder();
    ReadOnlyReversiModel mockModel = new CaptureInputReversi(output,
            Arrays.asList(new MachinePlayer(Name.X, new AvoidNextToCornersStrategy()),
                    new MachinePlayer(Name.O, new AvoidNextToCornersStrategy())),
            List.of(SquareDirection.values()));
    CompleteReversiStrategyFromFallible avoidNextToCorners =
            new CompleteReversiStrategyFromFallible(new AvoidNextToCornersStrategy());
    LinearCoord bestMove = avoidNextToCorners.chooseMove(mockModel,
            new MachinePlayer(Name.X, new AvoidNextToCornersStrategy()));
    Assert.assertEquals(new BasicPoint(-2, -1), bestMove);
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
                    new MachinePlayer(Name.O, new CaptureMaxStrategy())),
            List.of(HexDirection.values()));
    CompleteReversiStrategyFromFallible captureMax =
            new CompleteReversiStrategyFromFallible(new CaptureMaxStrategy());
    LinearCoord bestMove = captureMax.chooseMove(mockModel,
            new MachinePlayer(Name.X, new CaptureMaxStrategy()));
    Assert.assertEquals(new BasicPoint(-2, 0), bestMove);
    Assert.assertEquals("getModel\n" +
            "startGame\n" +
            "getModel\n" +
            "startGame\n" +
            "getPlayerScore: player\n" +
            "startGame\n" +
            "getBottomRow\n" +
            "getRightCol\n" +
            "getLeftCol\n" +
            "getTopRow\n" +
            "validMove: row = -5, col = -5\n" +
            "validMove: row = -5, col = -4\n" +
            "validMove: row = -5, col = -3\n" +
            "playerAt: row = -5, col = -2\n" +
            "playerAt: row = -4, col = -2\n" +
            "playerAt: row = -4, col = -3\n" +
            "validMove: row = -5, col = -2\n" +
            "playerAt: row = -5, col = -1\n" +
            "playerAt: row = -4, col = -1\n" +
            "playerAt: row = -4, col = -2\n" +
            "playerAt: row = -5, col = -3\n" +
            "validMove: row = -5, col = -1\n" +
            "playerAt: row = -5, col = 0\n" +
            "playerAt: row = -4, col = 0\n" +
            "playerAt: row = -4, col = -1\n" +
            "playerAt: row = -5, col = -2\n" +
            "validMove: row = -5, col = 0\n" +
            "playerAt: row = -5, col = 1\n" +
            "playerAt: row = -4, col = 1\n" +
            "playerAt: row = -4, col = 0\n" +
            "playerAt: row = -5, col = -1\n" +
            "validMove: row = -5, col = 1\n" +
            "playerAt: row = -5, col = 2\n" +
            "playerAt: row = -4, col = 2\n" +
            "playerAt: row = -4, col = 1\n" +
            "playerAt: row = -5, col = 0\n" +
            "validMove: row = -5, col = 2\n" +
            "playerAt: row = -4, col = 3\n" +
            "playerAt: row = -4, col = 2\n" +
            "playerAt: row = -5, col = 1\n" +
            "validMove: row = -5, col = 3\n" +
            "validMove: row = -5, col = 4\n" +
            "validMove: row = -5, col = 5\n" +
            "validMove: row = -4, col = -5\n" +
            "validMove: row = -4, col = -4\n" +
            "validMove: row = -4, col = -3\n" +
            "playerAt: row = -5, col = -3\n" +
            "playerAt: row = -4, col = -2\n" +
            "playerAt: row = -3, col = -3\n" +
            "playerAt: row = -3, col = -4\n" +
            "validMove: row = -4, col = -2\n" +
            "playerAt: row = -5, col = -2\n" +
            "playerAt: row = -4, col = -1\n" +
            "playerAt: row = -3, col = -2\n" +
            "playerAt: row = -3, col = -3\n" +
            "playerAt: row = -4, col = -3\n" +
            "playerAt: row = -5, col = -3\n" +
            "validMove: row = -4, col = -1\n" +
            "playerAt: row = -5, col = -1\n" +
            "playerAt: row = -4, col = 0\n" +
            "playerAt: row = -3, col = -1\n" +
            "playerAt: row = -3, col = -2\n" +
            "playerAt: row = -4, col = -2\n" +
            "playerAt: row = -5, col = -2\n" +
            "validMove: row = -4, col = 0\n" +
            "playerAt: row = -5, col = 0\n" +
            "playerAt: row = -4, col = 1\n" +
            "playerAt: row = -3, col = 0\n" +
            "playerAt: row = -3, col = -1\n" +
            "playerAt: row = -4, col = -1\n" +
            "playerAt: row = -5, col = -1\n" +
            "validMove: row = -4, col = 1\n" +
            "playerAt: row = -5, col = 1\n" +
            "playerAt: row = -4, col = 2\n" +
            "playerAt: row = -3, col = 1\n" +
            "playerAt: row = -3, col = 0\n" +
            "playerAt: row = -4, col = 0\n" +
            "playerAt: row = -5, col = 0\n" +
            "validMove: row = -4, col = 2\n" +
            "playerAt: row = -5, col = 2\n" +
            "playerAt: row = -4, col = 3\n" +
            "playerAt: row = -3, col = 2\n" +
            "playerAt: row = -3, col = 1\n" +
            "playerAt: row = -4, col = 1\n" +
            "playerAt: row = -5, col = 1\n" +
            "validMove: row = -4, col = 3\n" +
            "playerAt: row = -3, col = 3\n" +
            "playerAt: row = -3, col = 2\n" +
            "playerAt: row = -4, col = 2\n" +
            "playerAt: row = -5, col = 2\n" +
            "validMove: row = -4, col = 4\n" +
            "validMove: row = -4, col = 5\n" +
            "validMove: row = -3, col = -5\n" +
            "validMove: row = -3, col = -4\n" +
            "playerAt: row = -4, col = -3\n" +
            "playerAt: row = -3, col = -3\n" +
            "playerAt: row = -2, col = -3\n" +
            "playerAt: row = -2, col = -4\n" +
            "validMove: row = -3, col = -3\n" +
            "playerAt: row = -4, col = -2\n" +
            "playerAt: row = -3, col = -2\n" +
            "playerAt: row = -2, col = -2\n" +
            "playerAt: row = -2, col = -3\n" +
            "playerAt: row = -3, col = -4\n" +
            "playerAt: row = -4, col = -3\n" +
            "validMove: row = -3, col = -2\n" +
            "playerAt: row = -4, col = -1\n" +
            "playerAt: row = -3, col = -1\n" +
            "playerAt: row = -2, col = -1\n" +
            "playerAt: row = -2, col = -2\n" +
            "playerAt: row = -3, col = -3\n" +
            "playerAt: row = -4, col = -2\n" +
            "validMove: row = -3, col = -1\n" +
            "playerAt: row = -4, col = 0\n" +
            "playerAt: row = -3, col = 0\n" +
            "playerAt: row = -2, col = 0\n" +
            "playerAt: row = -2, col = -1\n" +
            "playerAt: row = -3, col = -2\n" +
            "playerAt: row = -4, col = -1\n" +
            "validMove: row = -3, col = 0\n" +
            "playerAt: row = -4, col = 1\n" +
            "playerAt: row = -3, col = 1\n" +
            "playerAt: row = -2, col = 1\n" +
            "playerAt: row = -2, col = 0\n" +
            "playerAt: row = -3, col = -1\n" +
            "playerAt: row = -4, col = 0\n" +
            "validMove: row = -3, col = 1\n" +
            "playerAt: row = -4, col = 2\n" +
            "playerAt: row = -3, col = 2\n" +
            "playerAt: row = -2, col = 2\n" +
            "playerAt: row = -2, col = 1\n" +
            "playerAt: row = -3, col = 0\n" +
            "playerAt: row = -4, col = 1\n" +
            "validMove: row = -3, col = 2\n" +
            "playerAt: row = -4, col = 3\n" +
            "playerAt: row = -3, col = 3\n" +
            "playerAt: row = -2, col = 3\n" +
            "playerAt: row = -2, col = 2\n" +
            "playerAt: row = -3, col = 1\n" +
            "playerAt: row = -4, col = 2\n" +
            "validMove: row = -3, col = 3\n" +
            "playerAt: row = -2, col = 4\n" +
            "playerAt: row = -2, col = 3\n" +
            "playerAt: row = -3, col = 2\n" +
            "playerAt: row = -4, col = 3\n" +
            "validMove: row = -3, col = 4\n" +
            "validMove: row = -3, col = 5\n" +
            "validMove: row = -2, col = -5\n" +
            "validMove: row = -2, col = -4\n" +
            "playerAt: row = -3, col = -4\n" +
            "playerAt: row = -2, col = -3\n" +
            "playerAt: row = -1, col = -4\n" +
            "playerAt: row = -1, col = -5\n" +
            "validMove: row = -2, col = -3\n" +
            "playerAt: row = -3, col = -3\n" +
            "playerAt: row = -2, col = -2\n" +
            "playerAt: row = -1, col = -3\n" +
            "playerAt: row = -1, col = -4\n" +
            "playerAt: row = -2, col = -4\n" +
            "playerAt: row = -3, col = -4\n" +
            "validMove: row = -2, col = -2\n" +
            "playerAt: row = -3, col = -2\n" +
            "playerAt: row = -2, col = -1\n" +
            "playerAt: row = -1, col = -2\n" +
            "playerAt: row = -1, col = -3\n" +
            "playerAt: row = -2, col = -3\n" +
            "playerAt: row = -3, col = -3\n" +
            "validMove: row = -2, col = -1\n" +
            "playerAt: row = -3, col = -1\n" +
            "playerAt: row = -2, col = 0\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = 0, col = 0\n" +
            "playerAt: row = -1, col = -2\n" +
            "playerAt: row = -2, col = -2\n" +
            "playerAt: row = -3, col = -2\n" +
            "validMove: row = -2, col = 0\n" +
            "playerAt: row = -3, col = 0\n" +
            "playerAt: row = -2, col = 1\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = -2, col = -1\n" +
            "playerAt: row = -3, col = -1\n" +
            "validMove: row = -2, col = 1\n" +
            "playerAt: row = -3, col = 1\n" +
            "playerAt: row = -2, col = 2\n" +
            "playerAt: row = -1, col = 1\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -2, col = 0\n" +
            "playerAt: row = -3, col = 0\n" +
            "validMove: row = -2, col = 2\n" +
            "playerAt: row = -3, col = 2\n" +
            "playerAt: row = -2, col = 3\n" +
            "playerAt: row = -1, col = 2\n" +
            "playerAt: row = -1, col = 1\n" +
            "playerAt: row = -2, col = 1\n" +
            "playerAt: row = -3, col = 1\n" +
            "validMove: row = -2, col = 3\n" +
            "playerAt: row = -3, col = 3\n" +
            "playerAt: row = -2, col = 4\n" +
            "playerAt: row = -1, col = 3\n" +
            "playerAt: row = -1, col = 2\n" +
            "playerAt: row = -2, col = 2\n" +
            "playerAt: row = -3, col = 2\n" +
            "validMove: row = -2, col = 4\n" +
            "playerAt: row = -1, col = 4\n" +
            "playerAt: row = -1, col = 3\n" +
            "playerAt: row = -2, col = 3\n" +
            "playerAt: row = -3, col = 3\n" +
            "validMove: row = -2, col = 5\n" +
            "validMove: row = -1, col = -5\n" +
            "playerAt: row = -2, col = -4\n" +
            "playerAt: row = -1, col = -4\n" +
            "playerAt: row = 0, col = -4\n" +
            "playerAt: row = 0, col = -5\n" +
            "validMove: row = -1, col = -4\n" +
            "playerAt: row = -2, col = -3\n" +
            "playerAt: row = -1, col = -3\n" +
            "playerAt: row = 0, col = -3\n" +
            "playerAt: row = 0, col = -4\n" +
            "playerAt: row = -1, col = -5\n" +
            "playerAt: row = -2, col = -4\n" +
            "validMove: row = -1, col = -3\n" +
            "playerAt: row = -2, col = -2\n" +
            "playerAt: row = -1, col = -2\n" +
            "playerAt: row = 0, col = -2\n" +
            "playerAt: row = 0, col = -3\n" +
            "playerAt: row = -1, col = -4\n" +
            "playerAt: row = -2, col = -3\n" +
            "validMove: row = -1, col = -2\n" +
            "playerAt: row = -2, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -2\n" +
            "playerAt: row = -1, col = -3\n" +
            "playerAt: row = -2, col = -2\n" +
            "validMove: row = -1, col = -1\n" +
            "validMove: row = -1, col = 0\n" +
            "validMove: row = -1, col = 1\n" +
            "playerAt: row = -2, col = 2\n" +
            "playerAt: row = -1, col = 2\n" +
            "playerAt: row = 0, col = 2\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -2, col = 1\n" +
            "validMove: row = -1, col = 2\n" +
            "playerAt: row = -2, col = 3\n" +
            "playerAt: row = -1, col = 3\n" +
            "playerAt: row = 0, col = 3\n" +
            "playerAt: row = 0, col = 2\n" +
            "playerAt: row = -1, col = 1\n" +
            "playerAt: row = -2, col = 2\n" +
            "validMove: row = -1, col = 3\n" +
            "playerAt: row = -2, col = 4\n" +
            "playerAt: row = -1, col = 4\n" +
            "playerAt: row = 0, col = 4\n" +
            "playerAt: row = 0, col = 3\n" +
            "playerAt: row = -1, col = 2\n" +
            "playerAt: row = -2, col = 3\n" +
            "validMove: row = -1, col = 4\n" +
            "playerAt: row = 0, col = 5\n" +
            "playerAt: row = 0, col = 4\n" +
            "playerAt: row = -1, col = 3\n" +
            "playerAt: row = -2, col = 4\n" +
            "validMove: row = -1, col = 5\n" +
            "validMove: row = 0, col = -5\n" +
            "playerAt: row = -1, col = -5\n" +
            "playerAt: row = 0, col = -4\n" +
            "playerAt: row = 1, col = -5\n" +
            "validMove: row = 0, col = -4\n" +
            "playerAt: row = -1, col = -4\n" +
            "playerAt: row = 0, col = -3\n" +
            "playerAt: row = 1, col = -4\n" +
            "playerAt: row = 1, col = -5\n" +
            "playerAt: row = 0, col = -5\n" +
            "playerAt: row = -1, col = -5\n" +
            "validMove: row = 0, col = -3\n" +
            "playerAt: row = -1, col = -3\n" +
            "playerAt: row = 0, col = -2\n" +
            "playerAt: row = 1, col = -3\n" +
            "playerAt: row = 1, col = -4\n" +
            "playerAt: row = 0, col = -4\n" +
            "playerAt: row = -1, col = -4\n" +
            "validMove: row = 0, col = -2\n" +
            "playerAt: row = -1, col = -2\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 1, col = -2\n" +
            "playerAt: row = 1, col = -3\n" +
            "playerAt: row = 0, col = -3\n" +
            "playerAt: row = -1, col = -3\n" +
            "validMove: row = 0, col = -1\n" +
            "validMove: row = 0, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 2\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 2, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -2, col = -1\n" +
            "validMove: row = 0, col = 1\n" +
            "validMove: row = 0, col = 2\n" +
            "playerAt: row = -1, col = 2\n" +
            "playerAt: row = 0, col = 3\n" +
            "playerAt: row = 1, col = 2\n" +
            "playerAt: row = 1, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 0\n" +
            "playerAt: row = -1, col = 1\n" +
            "validMove: row = 0, col = 3\n" +
            "playerAt: row = -1, col = 3\n" +
            "playerAt: row = 0, col = 4\n" +
            "playerAt: row = 1, col = 3\n" +
            "playerAt: row = 1, col = 2\n" +
            "playerAt: row = 0, col = 2\n" +
            "playerAt: row = -1, col = 2\n" +
            "validMove: row = 0, col = 4\n" +
            "playerAt: row = -1, col = 4\n" +
            "playerAt: row = 0, col = 5\n" +
            "playerAt: row = 1, col = 4\n" +
            "playerAt: row = 1, col = 3\n" +
            "playerAt: row = 0, col = 3\n" +
            "playerAt: row = -1, col = 3\n" +
            "validMove: row = 0, col = 5\n" +
            "playerAt: row = 1, col = 4\n" +
            "playerAt: row = 0, col = 4\n" +
            "playerAt: row = -1, col = 4\n" +
            "validMove: row = 1, col = -5\n" +
            "playerAt: row = 0, col = -4\n" +
            "playerAt: row = 1, col = -4\n" +
            "playerAt: row = 2, col = -4\n" +
            "playerAt: row = 0, col = -5\n" +
            "validMove: row = 1, col = -4\n" +
            "playerAt: row = 0, col = -3\n" +
            "playerAt: row = 1, col = -3\n" +
            "playerAt: row = 2, col = -3\n" +
            "playerAt: row = 2, col = -4\n" +
            "playerAt: row = 1, col = -5\n" +
            "playerAt: row = 0, col = -4\n" +
            "validMove: row = 1, col = -3\n" +
            "playerAt: row = 0, col = -2\n" +
            "playerAt: row = 1, col = -2\n" +
            "playerAt: row = 2, col = -2\n" +
            "playerAt: row = 2, col = -3\n" +
            "playerAt: row = 1, col = -4\n" +
            "playerAt: row = 0, col = -3\n" +
            "validMove: row = 1, col = -2\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 2, col = -1\n" +
            "playerAt: row = 2, col = -2\n" +
            "playerAt: row = 1, col = -3\n" +
            "playerAt: row = 0, col = -2\n" +
            "validMove: row = 1, col = -1\n" +
            "validMove: row = 1, col = 0\n" +
            "validMove: row = 1, col = 1\n" +
            "playerAt: row = 0, col = 2\n" +
            "playerAt: row = 1, col = 2\n" +
            "playerAt: row = 2, col = 2\n" +
            "playerAt: row = 2, col = 1\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "validMove: row = 1, col = 2\n" +
            "playerAt: row = 0, col = 3\n" +
            "playerAt: row = 1, col = 3\n" +
            "playerAt: row = 2, col = 3\n" +
            "playerAt: row = 2, col = 2\n" +
            "playerAt: row = 1, col = 1\n" +
            "playerAt: row = 0, col = 2\n" +
            "validMove: row = 1, col = 3\n" +
            "playerAt: row = 0, col = 4\n" +
            "playerAt: row = 1, col = 4\n" +
            "playerAt: row = 2, col = 4\n" +
            "playerAt: row = 2, col = 3\n" +
            "playerAt: row = 1, col = 2\n" +
            "playerAt: row = 0, col = 3\n" +
            "validMove: row = 1, col = 4\n" +
            "playerAt: row = 0, col = 5\n" +
            "playerAt: row = 2, col = 4\n" +
            "playerAt: row = 1, col = 3\n" +
            "playerAt: row = 0, col = 4\n" +
            "validMove: row = 1, col = 5\n" +
            "validMove: row = 2, col = -5\n" +
            "validMove: row = 2, col = -4\n" +
            "playerAt: row = 1, col = -4\n" +
            "playerAt: row = 2, col = -3\n" +
            "playerAt: row = 3, col = -4\n" +
            "playerAt: row = 1, col = -5\n" +
            "validMove: row = 2, col = -3\n" +
            "playerAt: row = 1, col = -3\n" +
            "playerAt: row = 2, col = -2\n" +
            "playerAt: row = 3, col = -3\n" +
            "playerAt: row = 3, col = -4\n" +
            "playerAt: row = 2, col = -4\n" +
            "playerAt: row = 1, col = -4\n" +
            "validMove: row = 2, col = -2\n" +
            "playerAt: row = 1, col = -2\n" +
            "playerAt: row = 2, col = -1\n" +
            "playerAt: row = 3, col = -2\n" +
            "playerAt: row = 3, col = -3\n" +
            "playerAt: row = 2, col = -3\n" +
            "playerAt: row = 1, col = -3\n" +
            "validMove: row = 2, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 0, col = 0\n" +
            "playerAt: row = 2, col = 0\n" +
            "playerAt: row = 3, col = -1\n" +
            "playerAt: row = 3, col = -2\n" +
            "playerAt: row = 2, col = -2\n" +
            "playerAt: row = 1, col = -2\n" +
            "validMove: row = 2, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 2, col = 1\n" +
            "playerAt: row = 3, col = 0\n" +
            "playerAt: row = 3, col = -1\n" +
            "playerAt: row = 2, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "validMove: row = 2, col = 1\n" +
            "playerAt: row = 1, col = 1\n" +
            "playerAt: row = 2, col = 2\n" +
            "playerAt: row = 3, col = 1\n" +
            "playerAt: row = 3, col = 0\n" +
            "playerAt: row = 2, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "validMove: row = 2, col = 2\n" +
            "playerAt: row = 1, col = 2\n" +
            "playerAt: row = 2, col = 3\n" +
            "playerAt: row = 3, col = 2\n" +
            "playerAt: row = 3, col = 1\n" +
            "playerAt: row = 2, col = 1\n" +
            "playerAt: row = 1, col = 1\n" +
            "validMove: row = 2, col = 3\n" +
            "playerAt: row = 1, col = 3\n" +
            "playerAt: row = 2, col = 4\n" +
            "playerAt: row = 3, col = 3\n" +
            "playerAt: row = 3, col = 2\n" +
            "playerAt: row = 2, col = 2\n" +
            "playerAt: row = 1, col = 2\n" +
            "validMove: row = 2, col = 4\n" +
            "playerAt: row = 1, col = 4\n" +
            "playerAt: row = 3, col = 3\n" +
            "playerAt: row = 2, col = 3\n" +
            "playerAt: row = 1, col = 3\n" +
            "validMove: row = 2, col = 5\n" +
            "validMove: row = 3, col = -5\n" +
            "validMove: row = 3, col = -4\n" +
            "playerAt: row = 2, col = -3\n" +
            "playerAt: row = 3, col = -3\n" +
            "playerAt: row = 4, col = -3\n" +
            "playerAt: row = 2, col = -4\n" +
            "validMove: row = 3, col = -3\n" +
            "playerAt: row = 2, col = -2\n" +
            "playerAt: row = 3, col = -2\n" +
            "playerAt: row = 4, col = -2\n" +
            "playerAt: row = 4, col = -3\n" +
            "playerAt: row = 3, col = -4\n" +
            "playerAt: row = 2, col = -3\n" +
            "validMove: row = 3, col = -2\n" +
            "playerAt: row = 2, col = -1\n" +
            "playerAt: row = 3, col = -1\n" +
            "playerAt: row = 4, col = -1\n" +
            "playerAt: row = 4, col = -2\n" +
            "playerAt: row = 3, col = -3\n" +
            "playerAt: row = 2, col = -2\n" +
            "validMove: row = 3, col = -1\n" +
            "playerAt: row = 2, col = 0\n" +
            "playerAt: row = 3, col = 0\n" +
            "playerAt: row = 4, col = 0\n" +
            "playerAt: row = 4, col = -1\n" +
            "playerAt: row = 3, col = -2\n" +
            "playerAt: row = 2, col = -1\n" +
            "validMove: row = 3, col = 0\n" +
            "playerAt: row = 2, col = 1\n" +
            "playerAt: row = 3, col = 1\n" +
            "playerAt: row = 4, col = 1\n" +
            "playerAt: row = 4, col = 0\n" +
            "playerAt: row = 3, col = -1\n" +
            "playerAt: row = 2, col = 0\n" +
            "validMove: row = 3, col = 1\n" +
            "playerAt: row = 2, col = 2\n" +
            "playerAt: row = 3, col = 2\n" +
            "playerAt: row = 4, col = 2\n" +
            "playerAt: row = 4, col = 1\n" +
            "playerAt: row = 3, col = 0\n" +
            "playerAt: row = 2, col = 1\n" +
            "validMove: row = 3, col = 2\n" +
            "playerAt: row = 2, col = 3\n" +
            "playerAt: row = 3, col = 3\n" +
            "playerAt: row = 4, col = 3\n" +
            "playerAt: row = 4, col = 2\n" +
            "playerAt: row = 3, col = 1\n" +
            "playerAt: row = 2, col = 2\n" +
            "validMove: row = 3, col = 3\n" +
            "playerAt: row = 2, col = 4\n" +
            "playerAt: row = 4, col = 3\n" +
            "playerAt: row = 3, col = 2\n" +
            "playerAt: row = 2, col = 3\n" +
            "validMove: row = 3, col = 4\n" +
            "validMove: row = 3, col = 5\n" +
            "validMove: row = 4, col = -5\n" +
            "validMove: row = 4, col = -4\n" +
            "validMove: row = 4, col = -3\n" +
            "playerAt: row = 3, col = -3\n" +
            "playerAt: row = 4, col = -2\n" +
            "playerAt: row = 5, col = -3\n" +
            "playerAt: row = 3, col = -4\n" +
            "validMove: row = 4, col = -2\n" +
            "playerAt: row = 3, col = -2\n" +
            "playerAt: row = 4, col = -1\n" +
            "playerAt: row = 5, col = -2\n" +
            "playerAt: row = 5, col = -3\n" +
            "playerAt: row = 4, col = -3\n" +
            "playerAt: row = 3, col = -3\n" +
            "validMove: row = 4, col = -1\n" +
            "playerAt: row = 3, col = -1\n" +
            "playerAt: row = 4, col = 0\n" +
            "playerAt: row = 5, col = -1\n" +
            "playerAt: row = 5, col = -2\n" +
            "playerAt: row = 4, col = -2\n" +
            "playerAt: row = 3, col = -2\n" +
            "validMove: row = 4, col = 0\n" +
            "playerAt: row = 3, col = 0\n" +
            "playerAt: row = 4, col = 1\n" +
            "playerAt: row = 5, col = 0\n" +
            "playerAt: row = 5, col = -1\n" +
            "playerAt: row = 4, col = -1\n" +
            "playerAt: row = 3, col = -1\n" +
            "validMove: row = 4, col = 1\n" +
            "playerAt: row = 3, col = 1\n" +
            "playerAt: row = 4, col = 2\n" +
            "playerAt: row = 5, col = 1\n" +
            "playerAt: row = 5, col = 0\n" +
            "playerAt: row = 4, col = 0\n" +
            "playerAt: row = 3, col = 0\n" +
            "validMove: row = 4, col = 2\n" +
            "playerAt: row = 3, col = 2\n" +
            "playerAt: row = 4, col = 3\n" +
            "playerAt: row = 5, col = 2\n" +
            "playerAt: row = 5, col = 1\n" +
            "playerAt: row = 4, col = 1\n" +
            "playerAt: row = 3, col = 1\n" +
            "validMove: row = 4, col = 3\n" +
            "playerAt: row = 3, col = 3\n" +
            "playerAt: row = 5, col = 2\n" +
            "playerAt: row = 4, col = 2\n" +
            "playerAt: row = 3, col = 2\n" +
            "validMove: row = 4, col = 4\n" +
            "validMove: row = 4, col = 5\n" +
            "validMove: row = 5, col = -5\n" +
            "validMove: row = 5, col = -4\n" +
            "validMove: row = 5, col = -3\n" +
            "playerAt: row = 4, col = -2\n" +
            "playerAt: row = 5, col = -2\n" +
            "playerAt: row = 4, col = -3\n" +
            "validMove: row = 5, col = -2\n" +
            "playerAt: row = 4, col = -1\n" +
            "playerAt: row = 5, col = -1\n" +
            "playerAt: row = 5, col = -3\n" +
            "playerAt: row = 4, col = -2\n" +
            "validMove: row = 5, col = -1\n" +
            "playerAt: row = 4, col = 0\n" +
            "playerAt: row = 5, col = 0\n" +
            "playerAt: row = 5, col = -2\n" +
            "playerAt: row = 4, col = -1\n" +
            "validMove: row = 5, col = 0\n" +
            "playerAt: row = 4, col = 1\n" +
            "playerAt: row = 5, col = 1\n" +
            "playerAt: row = 5, col = -1\n" +
            "playerAt: row = 4, col = 0\n" +
            "validMove: row = 5, col = 1\n" +
            "playerAt: row = 4, col = 2\n" +
            "playerAt: row = 5, col = 2\n" +
            "playerAt: row = 5, col = 0\n" +
            "playerAt: row = 4, col = 1\n" +
            "validMove: row = 5, col = 2\n" +
            "playerAt: row = 4, col = 3\n" +
            "playerAt: row = 5, col = 1\n" +
            "playerAt: row = 4, col = 2\n" +
            "validMove: row = 5, col = 3\n" +
            "validMove: row = 5, col = 4\n" +
            "validMove: row = 5, col = 5\n" +
            "getModel\n" +
            "startGame\n" +
            "placePiece: row = -2, col = 0\n" +
            "playerAt: row = -3, col = 0\n" +
            "playerAt: row = -2, col = 1\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = -2, col = -1\n" +
            "playerAt: row = -3, col = -1\n" +
            "getPlayerScore: player\n" +
            "getModel\n" +
            "startGame\n" +
            "placePiece: row = -1, col = -2\n" +
            "playerAt: row = -2, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -2\n" +
            "playerAt: row = -1, col = -3\n" +
            "playerAt: row = -2, col = -2\n" +
            "getPlayerScore: player\n" +
            "getModel\n" +
            "startGame\n" +
            "placePiece: row = -1, col = 1\n" +
            "playerAt: row = -2, col = 2\n" +
            "playerAt: row = -1, col = 2\n" +
            "playerAt: row = 0, col = 2\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -2, col = 1\n" +
            "getPlayerScore: player\n" +
            "getModel\n" +
            "startGame\n" +
            "placePiece: row = 1, col = -2\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 2, col = -1\n" +
            "playerAt: row = 2, col = -2\n" +
            "playerAt: row = 1, col = -3\n" +
            "playerAt: row = 0, col = -2\n" +
            "getPlayerScore: player\n" +
            "getModel\n" +
            "startGame\n" +
            "placePiece: row = 1, col = 1\n" +
            "playerAt: row = 0, col = 2\n" +
            "playerAt: row = 1, col = 2\n" +
            "playerAt: row = 2, col = 2\n" +
            "playerAt: row = 2, col = 1\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "getPlayerScore: player\n" +
            "getModel\n" +
            "startGame\n" +
            "placePiece: row = 2, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 2, col = 1\n" +
            "playerAt: row = 3, col = 0\n" +
            "playerAt: row = 3, col = -1\n" +
            "playerAt: row = 2, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "getPlayerScore: player\n", output.toString());
  }

  @Test
  public void maxCaptureSquareFalseLegalityTranscript() {
    StringBuilder output = new StringBuilder();
    ReadOnlyReversiModel mockModel = new FalseLegalityReversi(output,
            Arrays.asList(new MachinePlayer(Name.X, new CaptureMaxStrategy()),
                    new MachinePlayer(Name.O, new CaptureMaxStrategy())),
            List.of(SquareDirection.values()));
    CompleteReversiStrategyFromFallible captureMax =
            new CompleteReversiStrategyFromFallible(new CaptureMaxStrategy());
    LinearCoord bestMove = captureMax.chooseMove(mockModel,
            new MachinePlayer(Name.X, new CaptureMaxStrategy()));
    Assert.assertEquals(new BasicPoint(2, 2), bestMove);
    Assert.assertEquals("getModel\n" +
            "startGame\n" +
            "getModel\n" +
            "startGame\n" +
            "getPlayerScore: player\n" +
            "startGame\n" +
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
            "startGame\n" +
            "placePiece: row = 2, col = 2\n" +
            "playerAt: row = 1, col = 3\n" +
            "playerAt: row = 2, col = 3\n" +
            "playerAt: row = 3, col = 3\n" +
            "playerAt: row = 3, col = 1\n" +
            "playerAt: row = 2, col = 1\n" +
            "playerAt: row = 1, col = 1\n" +
            "playerAt: row = 1, col = 2\n" +
            "playerAt: row = 3, col = 2\n" +
            "getPlayerScore: player\n", output.toString());
  }

  @Test
  public void maxSquareCaptureFalseLegalityTranscript() {
    StringBuilder output = new StringBuilder();
    ReadOnlyReversiModel mockModel = new FalseLegalityReversi(output,
            Arrays.asList(new MachinePlayer(Name.X, new CaptureMaxStrategy()),
                    new MachinePlayer(Name.O, new CaptureMaxStrategy())),
            List.of(SquareDirection.values()));
    CompleteReversiStrategyFromFallible captureMax =
            new CompleteReversiStrategyFromFallible(new CaptureMaxStrategy());
    LinearCoord bestMove = captureMax.chooseMove(mockModel,
            new MachinePlayer(Name.X, new CaptureMaxStrategy()));
    Assert.assertEquals(new BasicPoint(2, 2), bestMove);
    Assert.assertEquals("getModel\n" +
            "startGame\n" +
            "getModel\n" +
            "startGame\n" +
            "getPlayerScore: player\n" +
            "startGame\n" +
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
            "startGame\n" +
            "placePiece: row = 2, col = 2\n" +
            "playerAt: row = 1, col = 3\n" +
            "playerAt: row = 2, col = 3\n" +
            "playerAt: row = 3, col = 3\n" +
            "playerAt: row = 3, col = 1\n" +
            "playerAt: row = 2, col = 1\n" +
            "playerAt: row = 1, col = 1\n" +
            "playerAt: row = 1, col = 2\n" +
            "playerAt: row = 3, col = 2\n" +
            "getPlayerScore: player\n", output.toString());
  }

  @Test
  public void captureSquareInputCorners() {
    StringBuilder output = new StringBuilder();
    ReadOnlyReversiModel mockModel = new CaptureInputReadOnlyReversi(output,
            Arrays.asList(new MachinePlayer(Name.X, new CaptureCornersStrategy()),
                    new MachinePlayer(Name.O, new CaptureCornersStrategy())),
            List.of(SquareDirection.values()));
    CompleteReversiStrategyFromFallible captureCorners =
            new CompleteReversiStrategyFromFallible(new CaptureCornersStrategy());
    LinearCoord bestMove = captureCorners.chooseMove(mockModel,
            new MachinePlayer(Name.X, new CaptureCornersStrategy()));
    Assert.assertEquals(new BasicPoint(-2, -1), bestMove);
    Assert.assertEquals("getModel\n" +
            "startGame\n" +
            "getModel\n" +
            "startGame\n" +
            "startGame\n" +
            "getBottomRow\n" +
            "getRightCol\n" +
            "getLeftCol\n" +
            "getTopRow\n" +
            "validMove: row = -5, col = -5\n" +
            "validMove: row = -5, col = -4\n" +
            "validMove: row = -5, col = -3\n" +
            "playerAt: row = -5, col = -2\n" +
            "playerAt: row = -4, col = -2\n" +
            "playerAt: row = -4, col = -3\n" +
            "validMove: row = -5, col = -2\n" +
            "playerAt: row = -5, col = -1\n" +
            "playerAt: row = -4, col = -1\n" +
            "playerAt: row = -4, col = -3\n" +
            "playerAt: row = -5, col = -3\n" +
            "playerAt: row = -4, col = -2\n" +
            "validMove: row = -5, col = -1\n" +
            "playerAt: row = -5, col = 0\n" +
            "playerAt: row = -4, col = 0\n" +
            "playerAt: row = -4, col = -2\n" +
            "playerAt: row = -5, col = -2\n" +
            "playerAt: row = -4, col = -1\n" +
            "validMove: row = -5, col = 0\n" +
            "playerAt: row = -5, col = 1\n" +
            "playerAt: row = -4, col = 1\n" +
            "playerAt: row = -4, col = -1\n" +
            "playerAt: row = -5, col = -1\n" +
            "playerAt: row = -4, col = 0\n" +
            "validMove: row = -5, col = 1\n" +
            "playerAt: row = -5, col = 2\n" +
            "playerAt: row = -4, col = 2\n" +
            "playerAt: row = -4, col = 0\n" +
            "playerAt: row = -5, col = 0\n" +
            "playerAt: row = -4, col = 1\n" +
            "validMove: row = -5, col = 2\n" +
            "playerAt: row = -4, col = 3\n" +
            "playerAt: row = -4, col = 1\n" +
            "playerAt: row = -5, col = 1\n" +
            "playerAt: row = -4, col = 2\n" +
            "validMove: row = -5, col = 3\n" +
            "validMove: row = -5, col = 4\n" +
            "validMove: row = -5, col = 5\n" +
            "validMove: row = -4, col = -5\n" +
            "validMove: row = -4, col = -4\n" +
            "validMove: row = -4, col = -3\n" +
            "playerAt: row = -5, col = -2\n" +
            "playerAt: row = -4, col = -2\n" +
            "playerAt: row = -3, col = -2\n" +
            "playerAt: row = -3, col = -4\n" +
            "playerAt: row = -5, col = -3\n" +
            "playerAt: row = -3, col = -3\n" +
            "validMove: row = -4, col = -2\n" +
            "playerAt: row = -5, col = -1\n" +
            "playerAt: row = -4, col = -1\n" +
            "playerAt: row = -3, col = -1\n" +
            "playerAt: row = -3, col = -3\n" +
            "playerAt: row = -4, col = -3\n" +
            "playerAt: row = -5, col = -3\n" +
            "playerAt: row = -5, col = -2\n" +
            "playerAt: row = -3, col = -2\n" +
            "validMove: row = -4, col = -1\n" +
            "playerAt: row = -5, col = 0\n" +
            "playerAt: row = -4, col = 0\n" +
            "playerAt: row = -3, col = 0\n" +
            "playerAt: row = -3, col = -2\n" +
            "playerAt: row = -4, col = -2\n" +
            "playerAt: row = -5, col = -2\n" +
            "playerAt: row = -5, col = -1\n" +
            "playerAt: row = -3, col = -1\n" +
            "validMove: row = -4, col = 0\n" +
            "playerAt: row = -5, col = 1\n" +
            "playerAt: row = -4, col = 1\n" +
            "playerAt: row = -3, col = 1\n" +
            "playerAt: row = -3, col = -1\n" +
            "playerAt: row = -4, col = -1\n" +
            "playerAt: row = -5, col = -1\n" +
            "playerAt: row = -5, col = 0\n" +
            "playerAt: row = -3, col = 0\n" +
            "validMove: row = -4, col = 1\n" +
            "playerAt: row = -5, col = 2\n" +
            "playerAt: row = -4, col = 2\n" +
            "playerAt: row = -3, col = 2\n" +
            "playerAt: row = -3, col = 0\n" +
            "playerAt: row = -4, col = 0\n" +
            "playerAt: row = -5, col = 0\n" +
            "playerAt: row = -5, col = 1\n" +
            "playerAt: row = -3, col = 1\n" +
            "validMove: row = -4, col = 2\n" +
            "playerAt: row = -4, col = 3\n" +
            "playerAt: row = -3, col = 3\n" +
            "playerAt: row = -3, col = 1\n" +
            "playerAt: row = -4, col = 1\n" +
            "playerAt: row = -5, col = 1\n" +
            "playerAt: row = -5, col = 2\n" +
            "playerAt: row = -3, col = 2\n" +
            "validMove: row = -4, col = 3\n" +
            "playerAt: row = -3, col = 2\n" +
            "playerAt: row = -4, col = 2\n" +
            "playerAt: row = -5, col = 2\n" +
            "playerAt: row = -3, col = 3\n" +
            "validMove: row = -4, col = 4\n" +
            "validMove: row = -4, col = 5\n" +
            "validMove: row = -3, col = -5\n" +
            "validMove: row = -3, col = -4\n" +
            "playerAt: row = -4, col = -3\n" +
            "playerAt: row = -3, col = -3\n" +
            "playerAt: row = -2, col = -3\n" +
            "playerAt: row = -2, col = -4\n" +
            "validMove: row = -3, col = -3\n" +
            "playerAt: row = -4, col = -2\n" +
            "playerAt: row = -3, col = -2\n" +
            "playerAt: row = -2, col = -2\n" +
            "playerAt: row = -2, col = -4\n" +
            "playerAt: row = -3, col = -4\n" +
            "playerAt: row = -4, col = -3\n" +
            "playerAt: row = -2, col = -3\n" +
            "validMove: row = -3, col = -2\n" +
            "playerAt: row = -4, col = -1\n" +
            "playerAt: row = -3, col = -1\n" +
            "playerAt: row = -2, col = -1\n" +
            "playerAt: row = -2, col = -3\n" +
            "playerAt: row = -3, col = -3\n" +
            "playerAt: row = -4, col = -3\n" +
            "playerAt: row = -4, col = -2\n" +
            "playerAt: row = -2, col = -2\n" +
            "validMove: row = -3, col = -1\n" +
            "playerAt: row = -4, col = 0\n" +
            "playerAt: row = -3, col = 0\n" +
            "playerAt: row = -2, col = 0\n" +
            "playerAt: row = -2, col = -2\n" +
            "playerAt: row = -3, col = -2\n" +
            "playerAt: row = -4, col = -2\n" +
            "playerAt: row = -4, col = -1\n" +
            "playerAt: row = -2, col = -1\n" +
            "validMove: row = -3, col = 0\n" +
            "playerAt: row = -4, col = 1\n" +
            "playerAt: row = -3, col = 1\n" +
            "playerAt: row = -2, col = 1\n" +
            "playerAt: row = -2, col = -1\n" +
            "playerAt: row = -3, col = -1\n" +
            "playerAt: row = -4, col = -1\n" +
            "playerAt: row = -4, col = 0\n" +
            "playerAt: row = -2, col = 0\n" +
            "validMove: row = -3, col = 1\n" +
            "playerAt: row = -4, col = 2\n" +
            "playerAt: row = -3, col = 2\n" +
            "playerAt: row = -2, col = 2\n" +
            "playerAt: row = -2, col = 0\n" +
            "playerAt: row = -3, col = 0\n" +
            "playerAt: row = -4, col = 0\n" +
            "playerAt: row = -4, col = 1\n" +
            "playerAt: row = -2, col = 1\n" +
            "validMove: row = -3, col = 2\n" +
            "playerAt: row = -4, col = 3\n" +
            "playerAt: row = -3, col = 3\n" +
            "playerAt: row = -2, col = 3\n" +
            "playerAt: row = -2, col = 1\n" +
            "playerAt: row = -3, col = 1\n" +
            "playerAt: row = -4, col = 1\n" +
            "playerAt: row = -4, col = 2\n" +
            "playerAt: row = -2, col = 2\n" +
            "validMove: row = -3, col = 3\n" +
            "playerAt: row = -2, col = 4\n" +
            "playerAt: row = -2, col = 2\n" +
            "playerAt: row = -3, col = 2\n" +
            "playerAt: row = -4, col = 2\n" +
            "playerAt: row = -4, col = 3\n" +
            "playerAt: row = -2, col = 3\n" +
            "validMove: row = -3, col = 4\n" +
            "validMove: row = -3, col = 5\n" +
            "validMove: row = -2, col = -5\n" +
            "validMove: row = -2, col = -4\n" +
            "playerAt: row = -3, col = -3\n" +
            "playerAt: row = -2, col = -3\n" +
            "playerAt: row = -1, col = -3\n" +
            "playerAt: row = -1, col = -5\n" +
            "playerAt: row = -3, col = -4\n" +
            "playerAt: row = -1, col = -4\n" +
            "validMove: row = -2, col = -3\n" +
            "playerAt: row = -3, col = -2\n" +
            "playerAt: row = -2, col = -2\n" +
            "playerAt: row = -1, col = -2\n" +
            "playerAt: row = -1, col = -4\n" +
            "playerAt: row = -2, col = -4\n" +
            "playerAt: row = -3, col = -4\n" +
            "playerAt: row = -3, col = -3\n" +
            "playerAt: row = -1, col = -3\n" +
            "validMove: row = -2, col = -2\n" +
            "playerAt: row = -3, col = -1\n" +
            "playerAt: row = -2, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = 0, col = 0\n" +
            "playerAt: row = -1, col = -3\n" +
            "playerAt: row = -2, col = -3\n" +
            "playerAt: row = -3, col = -3\n" +
            "playerAt: row = -3, col = -2\n" +
            "playerAt: row = -1, col = -2\n" +
            "validMove: row = -2, col = -1\n" +
            "playerAt: row = -3, col = 0\n" +
            "playerAt: row = -2, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = -2\n" +
            "playerAt: row = -2, col = -2\n" +
            "playerAt: row = -3, col = -2\n" +
            "playerAt: row = -3, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "validMove: row = -2, col = 0\n" +
            "playerAt: row = -3, col = 1\n" +
            "playerAt: row = -2, col = 1\n" +
            "playerAt: row = -1, col = 1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = 0, col = -2\n" +
            "playerAt: row = -2, col = -1\n" +
            "playerAt: row = -3, col = -1\n" +
            "playerAt: row = -3, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "validMove: row = -2, col = 1\n" +
            "playerAt: row = -3, col = 2\n" +
            "playerAt: row = -2, col = 2\n" +
            "playerAt: row = -1, col = 2\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -2, col = 0\n" +
            "playerAt: row = -3, col = 0\n" +
            "playerAt: row = -3, col = 1\n" +
            "playerAt: row = -1, col = 1\n" +
            "validMove: row = -2, col = 2\n" +
            "playerAt: row = -3, col = 3\n" +
            "playerAt: row = -2, col = 3\n" +
            "playerAt: row = -1, col = 3\n" +
            "playerAt: row = -1, col = 1\n" +
            "playerAt: row = -2, col = 1\n" +
            "playerAt: row = -3, col = 1\n" +
            "playerAt: row = -3, col = 2\n" +
            "playerAt: row = -1, col = 2\n" +
            "validMove: row = -2, col = 3\n" +
            "playerAt: row = -2, col = 4\n" +
            "playerAt: row = -1, col = 4\n" +
            "playerAt: row = -1, col = 2\n" +
            "playerAt: row = -2, col = 2\n" +
            "playerAt: row = -3, col = 2\n" +
            "playerAt: row = -3, col = 3\n" +
            "playerAt: row = -1, col = 3\n" +
            "validMove: row = -2, col = 4\n" +
            "playerAt: row = -1, col = 3\n" +
            "playerAt: row = -2, col = 3\n" +
            "playerAt: row = -3, col = 3\n" +
            "playerAt: row = -1, col = 4\n" +
            "validMove: row = -2, col = 5\n" +
            "validMove: row = -1, col = -5\n" +
            "playerAt: row = -2, col = -4\n" +
            "playerAt: row = -1, col = -4\n" +
            "playerAt: row = 0, col = -4\n" +
            "playerAt: row = 0, col = -5\n" +
            "validMove: row = -1, col = -4\n" +
            "playerAt: row = -2, col = -3\n" +
            "playerAt: row = -1, col = -3\n" +
            "playerAt: row = 0, col = -3\n" +
            "playerAt: row = 0, col = -5\n" +
            "playerAt: row = -1, col = -5\n" +
            "playerAt: row = -2, col = -4\n" +
            "playerAt: row = 0, col = -4\n" +
            "validMove: row = -1, col = -3\n" +
            "playerAt: row = -2, col = -2\n" +
            "playerAt: row = -1, col = -2\n" +
            "playerAt: row = 0, col = -2\n" +
            "playerAt: row = 0, col = -4\n" +
            "playerAt: row = -1, col = -4\n" +
            "playerAt: row = -2, col = -4\n" +
            "playerAt: row = -2, col = -3\n" +
            "playerAt: row = 0, col = -3\n" +
            "validMove: row = -1, col = -2\n" +
            "playerAt: row = -2, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -3\n" +
            "playerAt: row = -1, col = -3\n" +
            "playerAt: row = -2, col = -3\n" +
            "playerAt: row = -2, col = -2\n" +
            "playerAt: row = 0, col = -2\n" +
            "validMove: row = -1, col = -1\n" +
            "validMove: row = -1, col = 0\n" +
            "validMove: row = -1, col = 1\n" +
            "playerAt: row = -2, col = 2\n" +
            "playerAt: row = -1, col = 2\n" +
            "playerAt: row = 0, col = 2\n" +
            "playerAt: row = 0, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -2, col = 0\n" +
            "playerAt: row = -2, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 1, col = 1\n" +
            "validMove: row = -1, col = 2\n" +
            "playerAt: row = -2, col = 3\n" +
            "playerAt: row = -1, col = 3\n" +
            "playerAt: row = 0, col = 3\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = -1, col = 1\n" +
            "playerAt: row = -2, col = 1\n" +
            "playerAt: row = -2, col = 2\n" +
            "playerAt: row = 0, col = 2\n" +
            "validMove: row = -1, col = 3\n" +
            "playerAt: row = -2, col = 4\n" +
            "playerAt: row = -1, col = 4\n" +
            "playerAt: row = 0, col = 4\n" +
            "playerAt: row = 0, col = 2\n" +
            "playerAt: row = -1, col = 2\n" +
            "playerAt: row = -2, col = 2\n" +
            "playerAt: row = -2, col = 3\n" +
            "playerAt: row = 0, col = 3\n" +
            "validMove: row = -1, col = 4\n" +
            "playerAt: row = 0, col = 5\n" +
            "playerAt: row = 0, col = 3\n" +
            "playerAt: row = -1, col = 3\n" +
            "playerAt: row = -2, col = 3\n" +
            "playerAt: row = -2, col = 4\n" +
            "playerAt: row = 0, col = 4\n" +
            "validMove: row = -1, col = 5\n" +
            "validMove: row = 0, col = -5\n" +
            "playerAt: row = -1, col = -4\n" +
            "playerAt: row = 0, col = -4\n" +
            "playerAt: row = 1, col = -4\n" +
            "playerAt: row = -1, col = -5\n" +
            "playerAt: row = 1, col = -5\n" +
            "validMove: row = 0, col = -4\n" +
            "playerAt: row = -1, col = -3\n" +
            "playerAt: row = 0, col = -3\n" +
            "playerAt: row = 1, col = -3\n" +
            "playerAt: row = 1, col = -5\n" +
            "playerAt: row = 0, col = -5\n" +
            "playerAt: row = -1, col = -5\n" +
            "playerAt: row = -1, col = -4\n" +
            "playerAt: row = 1, col = -4\n" +
            "validMove: row = 0, col = -3\n" +
            "playerAt: row = -1, col = -2\n" +
            "playerAt: row = 0, col = -2\n" +
            "playerAt: row = 1, col = -2\n" +
            "playerAt: row = 1, col = -4\n" +
            "playerAt: row = 0, col = -4\n" +
            "playerAt: row = -1, col = -4\n" +
            "playerAt: row = -1, col = -3\n" +
            "playerAt: row = 1, col = -3\n" +
            "validMove: row = 0, col = -2\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -2, col = 0\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 2, col = 0\n" +
            "playerAt: row = 1, col = -3\n" +
            "playerAt: row = 0, col = -3\n" +
            "playerAt: row = -1, col = -3\n" +
            "playerAt: row = -1, col = -2\n" +
            "playerAt: row = 1, col = -2\n" +
            "validMove: row = 0, col = -1\n" +
            "validMove: row = 0, col = 0\n" +
            "playerAt: row = -1, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 2\n" +
            "playerAt: row = 1, col = 1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 2, col = -2\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -2, col = -2\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "validMove: row = 0, col = 1\n" +
            "validMove: row = 0, col = 2\n" +
            "playerAt: row = -1, col = 3\n" +
            "playerAt: row = 0, col = 3\n" +
            "playerAt: row = 1, col = 3\n" +
            "playerAt: row = 1, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 0\n" +
            "playerAt: row = -1, col = 1\n" +
            "playerAt: row = -1, col = 2\n" +
            "playerAt: row = 1, col = 2\n" +
            "validMove: row = 0, col = 3\n" +
            "playerAt: row = -1, col = 4\n" +
            "playerAt: row = 0, col = 4\n" +
            "playerAt: row = 1, col = 4\n" +
            "playerAt: row = 1, col = 2\n" +
            "playerAt: row = 0, col = 2\n" +
            "playerAt: row = -1, col = 2\n" +
            "playerAt: row = -1, col = 3\n" +
            "playerAt: row = 1, col = 3\n" +
            "validMove: row = 0, col = 4\n" +
            "playerAt: row = 0, col = 5\n" +
            "playerAt: row = 1, col = 3\n" +
            "playerAt: row = 0, col = 3\n" +
            "playerAt: row = -1, col = 3\n" +
            "playerAt: row = -1, col = 4\n" +
            "playerAt: row = 1, col = 4\n" +
            "validMove: row = 0, col = 5\n" +
            "playerAt: row = 1, col = 4\n" +
            "playerAt: row = 0, col = 4\n" +
            "playerAt: row = -1, col = 4\n" +
            "validMove: row = 1, col = -5\n" +
            "playerAt: row = 0, col = -4\n" +
            "playerAt: row = 1, col = -4\n" +
            "playerAt: row = 2, col = -4\n" +
            "playerAt: row = 0, col = -5\n" +
            "validMove: row = 1, col = -4\n" +
            "playerAt: row = 0, col = -3\n" +
            "playerAt: row = 1, col = -3\n" +
            "playerAt: row = 2, col = -3\n" +
            "playerAt: row = 1, col = -5\n" +
            "playerAt: row = 0, col = -5\n" +
            "playerAt: row = 0, col = -4\n" +
            "playerAt: row = 2, col = -4\n" +
            "validMove: row = 1, col = -3\n" +
            "playerAt: row = 0, col = -2\n" +
            "playerAt: row = 1, col = -2\n" +
            "playerAt: row = 2, col = -2\n" +
            "playerAt: row = 2, col = -4\n" +
            "playerAt: row = 1, col = -4\n" +
            "playerAt: row = 0, col = -4\n" +
            "playerAt: row = 0, col = -3\n" +
            "playerAt: row = 2, col = -3\n" +
            "validMove: row = 1, col = -2\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 2, col = -1\n" +
            "playerAt: row = 2, col = -3\n" +
            "playerAt: row = 1, col = -3\n" +
            "playerAt: row = 0, col = -3\n" +
            "playerAt: row = 0, col = -2\n" +
            "playerAt: row = 2, col = -2\n" +
            "validMove: row = 1, col = -1\n" +
            "validMove: row = 1, col = 0\n" +
            "validMove: row = 1, col = 1\n" +
            "playerAt: row = 0, col = 2\n" +
            "playerAt: row = 1, col = 2\n" +
            "playerAt: row = 2, col = 2\n" +
            "playerAt: row = 2, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 0, col = 0\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = -1, col = 1\n" +
            "playerAt: row = 2, col = 1\n" +
            "validMove: row = 1, col = 2\n" +
            "playerAt: row = 0, col = 3\n" +
            "playerAt: row = 1, col = 3\n" +
            "playerAt: row = 2, col = 3\n" +
            "playerAt: row = 2, col = 1\n" +
            "playerAt: row = 1, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = 0, col = 2\n" +
            "playerAt: row = 2, col = 2\n" +
            "validMove: row = 1, col = 3\n" +
            "playerAt: row = 0, col = 4\n" +
            "playerAt: row = 1, col = 4\n" +
            "playerAt: row = 2, col = 4\n" +
            "playerAt: row = 2, col = 2\n" +
            "playerAt: row = 1, col = 2\n" +
            "playerAt: row = 0, col = 2\n" +
            "playerAt: row = 0, col = 3\n" +
            "playerAt: row = 2, col = 3\n" +
            "validMove: row = 1, col = 4\n" +
            "playerAt: row = 0, col = 5\n" +
            "playerAt: row = 2, col = 3\n" +
            "playerAt: row = 1, col = 3\n" +
            "playerAt: row = 0, col = 3\n" +
            "playerAt: row = 0, col = 4\n" +
            "playerAt: row = 2, col = 4\n" +
            "validMove: row = 1, col = 5\n" +
            "validMove: row = 2, col = -5\n" +
            "validMove: row = 2, col = -4\n" +
            "playerAt: row = 1, col = -3\n" +
            "playerAt: row = 2, col = -3\n" +
            "playerAt: row = 3, col = -3\n" +
            "playerAt: row = 1, col = -5\n" +
            "playerAt: row = 1, col = -4\n" +
            "playerAt: row = 3, col = -4\n" +
            "validMove: row = 2, col = -3\n" +
            "playerAt: row = 1, col = -2\n" +
            "playerAt: row = 2, col = -2\n" +
            "playerAt: row = 3, col = -2\n" +
            "playerAt: row = 3, col = -4\n" +
            "playerAt: row = 2, col = -4\n" +
            "playerAt: row = 1, col = -4\n" +
            "playerAt: row = 1, col = -3\n" +
            "playerAt: row = 3, col = -3\n" +
            "validMove: row = 2, col = -2\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 0, col = 0\n" +
            "playerAt: row = 2, col = -1\n" +
            "playerAt: row = 3, col = -1\n" +
            "playerAt: row = 3, col = -3\n" +
            "playerAt: row = 2, col = -3\n" +
            "playerAt: row = 1, col = -3\n" +
            "playerAt: row = 1, col = -2\n" +
            "playerAt: row = 3, col = -2\n" +
            "validMove: row = 2, col = -1\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 2, col = 0\n" +
            "playerAt: row = 3, col = 0\n" +
            "playerAt: row = 3, col = -2\n" +
            "playerAt: row = 2, col = -2\n" +
            "playerAt: row = 1, col = -2\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 3, col = -1\n" +
            "validMove: row = 2, col = 0\n" +
            "playerAt: row = 1, col = 1\n" +
            "playerAt: row = 2, col = 1\n" +
            "playerAt: row = 3, col = 1\n" +
            "playerAt: row = 3, col = -1\n" +
            "playerAt: row = 2, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 0, col = -2\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 3, col = 0\n" +
            "validMove: row = 2, col = 1\n" +
            "playerAt: row = 1, col = 2\n" +
            "playerAt: row = 2, col = 2\n" +
            "playerAt: row = 3, col = 2\n" +
            "playerAt: row = 3, col = 0\n" +
            "playerAt: row = 2, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 1\n" +
            "playerAt: row = 3, col = 1\n" +
            "validMove: row = 2, col = 2\n" +
            "playerAt: row = 1, col = 3\n" +
            "playerAt: row = 2, col = 3\n" +
            "playerAt: row = 3, col = 3\n" +
            "playerAt: row = 3, col = 1\n" +
            "playerAt: row = 2, col = 1\n" +
            "playerAt: row = 1, col = 1\n" +
            "playerAt: row = 1, col = 2\n" +
            "playerAt: row = 3, col = 2\n" +
            "validMove: row = 2, col = 3\n" +
            "playerAt: row = 1, col = 4\n" +
            "playerAt: row = 2, col = 4\n" +
            "playerAt: row = 3, col = 2\n" +
            "playerAt: row = 2, col = 2\n" +
            "playerAt: row = 1, col = 2\n" +
            "playerAt: row = 1, col = 3\n" +
            "playerAt: row = 3, col = 3\n" +
            "validMove: row = 2, col = 4\n" +
            "playerAt: row = 3, col = 3\n" +
            "playerAt: row = 2, col = 3\n" +
            "playerAt: row = 1, col = 3\n" +
            "playerAt: row = 1, col = 4\n" +
            "validMove: row = 2, col = 5\n" +
            "validMove: row = 3, col = -5\n" +
            "validMove: row = 3, col = -4\n" +
            "playerAt: row = 2, col = -3\n" +
            "playerAt: row = 3, col = -3\n" +
            "playerAt: row = 4, col = -3\n" +
            "playerAt: row = 2, col = -4\n" +
            "validMove: row = 3, col = -3\n" +
            "playerAt: row = 2, col = -2\n" +
            "playerAt: row = 3, col = -2\n" +
            "playerAt: row = 4, col = -2\n" +
            "playerAt: row = 3, col = -4\n" +
            "playerAt: row = 2, col = -4\n" +
            "playerAt: row = 2, col = -3\n" +
            "playerAt: row = 4, col = -3\n" +
            "validMove: row = 3, col = -2\n" +
            "playerAt: row = 2, col = -1\n" +
            "playerAt: row = 3, col = -1\n" +
            "playerAt: row = 4, col = -1\n" +
            "playerAt: row = 4, col = -3\n" +
            "playerAt: row = 3, col = -3\n" +
            "playerAt: row = 2, col = -3\n" +
            "playerAt: row = 2, col = -2\n" +
            "playerAt: row = 4, col = -2\n" +
            "validMove: row = 3, col = -1\n" +
            "playerAt: row = 2, col = 0\n" +
            "playerAt: row = 3, col = 0\n" +
            "playerAt: row = 4, col = 0\n" +
            "playerAt: row = 4, col = -2\n" +
            "playerAt: row = 3, col = -2\n" +
            "playerAt: row = 2, col = -2\n" +
            "playerAt: row = 2, col = -1\n" +
            "playerAt: row = 4, col = -1\n" +
            "validMove: row = 3, col = 0\n" +
            "playerAt: row = 2, col = 1\n" +
            "playerAt: row = 3, col = 1\n" +
            "playerAt: row = 4, col = 1\n" +
            "playerAt: row = 4, col = -1\n" +
            "playerAt: row = 3, col = -1\n" +
            "playerAt: row = 2, col = -1\n" +
            "playerAt: row = 2, col = 0\n" +
            "playerAt: row = 4, col = 0\n" +
            "validMove: row = 3, col = 1\n" +
            "playerAt: row = 2, col = 2\n" +
            "playerAt: row = 3, col = 2\n" +
            "playerAt: row = 4, col = 2\n" +
            "playerAt: row = 4, col = 0\n" +
            "playerAt: row = 3, col = 0\n" +
            "playerAt: row = 2, col = 0\n" +
            "playerAt: row = 2, col = 1\n" +
            "playerAt: row = 4, col = 1\n" +
            "validMove: row = 3, col = 2\n" +
            "playerAt: row = 2, col = 3\n" +
            "playerAt: row = 3, col = 3\n" +
            "playerAt: row = 4, col = 3\n" +
            "playerAt: row = 4, col = 1\n" +
            "playerAt: row = 3, col = 1\n" +
            "playerAt: row = 2, col = 1\n" +
            "playerAt: row = 2, col = 2\n" +
            "playerAt: row = 4, col = 2\n" +
            "validMove: row = 3, col = 3\n" +
            "playerAt: row = 2, col = 4\n" +
            "playerAt: row = 4, col = 2\n" +
            "playerAt: row = 3, col = 2\n" +
            "playerAt: row = 2, col = 2\n" +
            "playerAt: row = 2, col = 3\n" +
            "playerAt: row = 4, col = 3\n" +
            "validMove: row = 3, col = 4\n" +
            "validMove: row = 3, col = 5\n" +
            "validMove: row = 4, col = -5\n" +
            "validMove: row = 4, col = -4\n" +
            "validMove: row = 4, col = -3\n" +
            "playerAt: row = 3, col = -2\n" +
            "playerAt: row = 4, col = -2\n" +
            "playerAt: row = 5, col = -2\n" +
            "playerAt: row = 3, col = -4\n" +
            "playerAt: row = 3, col = -3\n" +
            "playerAt: row = 5, col = -3\n" +
            "validMove: row = 4, col = -2\n" +
            "playerAt: row = 3, col = -1\n" +
            "playerAt: row = 4, col = -1\n" +
            "playerAt: row = 5, col = -1\n" +
            "playerAt: row = 5, col = -3\n" +
            "playerAt: row = 4, col = -3\n" +
            "playerAt: row = 3, col = -3\n" +
            "playerAt: row = 3, col = -2\n" +
            "playerAt: row = 5, col = -2\n" +
            "validMove: row = 4, col = -1\n" +
            "playerAt: row = 3, col = 0\n" +
            "playerAt: row = 4, col = 0\n" +
            "playerAt: row = 5, col = 0\n" +
            "playerAt: row = 5, col = -2\n" +
            "playerAt: row = 4, col = -2\n" +
            "playerAt: row = 3, col = -2\n" +
            "playerAt: row = 3, col = -1\n" +
            "playerAt: row = 5, col = -1\n" +
            "validMove: row = 4, col = 0\n" +
            "playerAt: row = 3, col = 1\n" +
            "playerAt: row = 4, col = 1\n" +
            "playerAt: row = 5, col = 1\n" +
            "playerAt: row = 5, col = -1\n" +
            "playerAt: row = 4, col = -1\n" +
            "playerAt: row = 3, col = -1\n" +
            "playerAt: row = 3, col = 0\n" +
            "playerAt: row = 5, col = 0\n" +
            "validMove: row = 4, col = 1\n" +
            "playerAt: row = 3, col = 2\n" +
            "playerAt: row = 4, col = 2\n" +
            "playerAt: row = 5, col = 2\n" +
            "playerAt: row = 5, col = 0\n" +
            "playerAt: row = 4, col = 0\n" +
            "playerAt: row = 3, col = 0\n" +
            "playerAt: row = 3, col = 1\n" +
            "playerAt: row = 5, col = 1\n" +
            "validMove: row = 4, col = 2\n" +
            "playerAt: row = 3, col = 3\n" +
            "playerAt: row = 4, col = 3\n" +
            "playerAt: row = 5, col = 1\n" +
            "playerAt: row = 4, col = 1\n" +
            "playerAt: row = 3, col = 1\n" +
            "playerAt: row = 3, col = 2\n" +
            "playerAt: row = 5, col = 2\n" +
            "validMove: row = 4, col = 3\n" +
            "playerAt: row = 5, col = 2\n" +
            "playerAt: row = 4, col = 2\n" +
            "playerAt: row = 3, col = 2\n" +
            "playerAt: row = 3, col = 3\n" +
            "validMove: row = 4, col = 4\n" +
            "validMove: row = 4, col = 5\n" +
            "validMove: row = 5, col = -5\n" +
            "validMove: row = 5, col = -4\n" +
            "validMove: row = 5, col = -3\n" +
            "playerAt: row = 4, col = -2\n" +
            "playerAt: row = 5, col = -2\n" +
            "playerAt: row = 4, col = -3\n" +
            "validMove: row = 5, col = -2\n" +
            "playerAt: row = 4, col = -1\n" +
            "playerAt: row = 5, col = -1\n" +
            "playerAt: row = 5, col = -3\n" +
            "playerAt: row = 4, col = -3\n" +
            "playerAt: row = 4, col = -2\n" +
            "validMove: row = 5, col = -1\n" +
            "playerAt: row = 4, col = 0\n" +
            "playerAt: row = 5, col = 0\n" +
            "playerAt: row = 5, col = -2\n" +
            "playerAt: row = 4, col = -2\n" +
            "playerAt: row = 4, col = -1\n" +
            "validMove: row = 5, col = 0\n" +
            "playerAt: row = 4, col = 1\n" +
            "playerAt: row = 5, col = 1\n" +
            "playerAt: row = 5, col = -1\n" +
            "playerAt: row = 4, col = -1\n" +
            "playerAt: row = 4, col = 0\n" +
            "validMove: row = 5, col = 1\n" +
            "playerAt: row = 4, col = 2\n" +
            "playerAt: row = 5, col = 2\n" +
            "playerAt: row = 5, col = 0\n" +
            "playerAt: row = 4, col = 0\n" +
            "playerAt: row = 4, col = 1\n" +
            "validMove: row = 5, col = 2\n" +
            "playerAt: row = 4, col = 3\n" +
            "playerAt: row = 5, col = 1\n" +
            "playerAt: row = 4, col = 1\n" +
            "playerAt: row = 4, col = 2\n" +
            "validMove: row = 5, col = 3\n" +
            "validMove: row = 5, col = 4\n" +
            "validMove: row = 5, col = 5\n" +
            "startGame\n" +
            "startGame\n" +
            "getTopRow\n" +
            "getBottomRow\n" +
            "getLeftCol\n" +
            "getRightCol\n" +
            "playerAt: row = -5, col = -5\n" +
            "getRightCol\n" +
            "playerAt: row = -5, col = -4\n" +
            "getRightCol\n" +
            "playerAt: row = -5, col = -3\n" +
            "getRightCol\n" +
            "playerAt: row = -5, col = -2\n" +
            "getRightCol\n" +
            "playerAt: row = -5, col = -1\n" +
            "getRightCol\n" +
            "playerAt: row = -5, col = 0\n" +
            "getRightCol\n" +
            "playerAt: row = -5, col = 1\n" +
            "getRightCol\n" +
            "playerAt: row = -5, col = 2\n" +
            "getRightCol\n" +
            "playerAt: row = -5, col = 3\n" +
            "getRightCol\n" +
            "playerAt: row = -5, col = 4\n" +
            "getRightCol\n" +
            "playerAt: row = -5, col = 5\n" +
            "getRightCol\n" +
            "getBottomRow\n" +
            "getLeftCol\n" +
            "getRightCol\n" +
            "playerAt: row = -4, col = -5\n" +
            "getRightCol\n" +
            "playerAt: row = -4, col = -4\n" +
            "getRightCol\n" +
            "playerAt: row = -4, col = -3\n" +
            "getRightCol\n" +
            "playerAt: row = -4, col = -2\n" +
            "getRightCol\n" +
            "playerAt: row = -4, col = -1\n" +
            "getRightCol\n" +
            "playerAt: row = -4, col = 0\n" +
            "getRightCol\n" +
            "playerAt: row = -4, col = 1\n" +
            "getRightCol\n" +
            "playerAt: row = -4, col = 2\n" +
            "getRightCol\n" +
            "playerAt: row = -4, col = 3\n" +
            "getRightCol\n" +
            "playerAt: row = -4, col = 4\n" +
            "getRightCol\n" +
            "playerAt: row = -4, col = 5\n" +
            "getRightCol\n" +
            "getBottomRow\n" +
            "getLeftCol\n" +
            "getRightCol\n" +
            "playerAt: row = -3, col = -5\n" +
            "getRightCol\n" +
            "playerAt: row = -3, col = -4\n" +
            "getRightCol\n" +
            "playerAt: row = -3, col = -3\n" +
            "getRightCol\n" +
            "playerAt: row = -3, col = -2\n" +
            "getRightCol\n" +
            "playerAt: row = -3, col = -1\n" +
            "getRightCol\n" +
            "playerAt: row = -3, col = 0\n" +
            "getRightCol\n" +
            "playerAt: row = -3, col = 1\n" +
            "getRightCol\n" +
            "playerAt: row = -3, col = 2\n" +
            "getRightCol\n" +
            "playerAt: row = -3, col = 3\n" +
            "getRightCol\n" +
            "playerAt: row = -3, col = 4\n" +
            "getRightCol\n" +
            "playerAt: row = -3, col = 5\n" +
            "getRightCol\n" +
            "getBottomRow\n" +
            "getLeftCol\n" +
            "getRightCol\n" +
            "playerAt: row = -2, col = -5\n" +
            "getRightCol\n" +
            "playerAt: row = -2, col = -4\n" +
            "getRightCol\n" +
            "playerAt: row = -2, col = -3\n" +
            "getRightCol\n" +
            "playerAt: row = -2, col = -2\n" +
            "getRightCol\n" +
            "playerAt: row = -2, col = -1\n" +
            "getRightCol\n" +
            "playerAt: row = -2, col = 0\n" +
            "getRightCol\n" +
            "playerAt: row = -2, col = 1\n" +
            "getRightCol\n" +
            "playerAt: row = -2, col = 2\n" +
            "getRightCol\n" +
            "playerAt: row = -2, col = 3\n" +
            "getRightCol\n" +
            "playerAt: row = -2, col = 4\n" +
            "getRightCol\n" +
            "playerAt: row = -2, col = 5\n" +
            "getRightCol\n" +
            "getBottomRow\n" +
            "getLeftCol\n" +
            "getRightCol\n" +
            "playerAt: row = -1, col = -5\n" +
            "getRightCol\n" +
            "playerAt: row = -1, col = -4\n" +
            "getRightCol\n" +
            "playerAt: row = -1, col = -3\n" +
            "getRightCol\n" +
            "playerAt: row = -1, col = -2\n" +
            "getRightCol\n" +
            "playerAt: row = -1, col = -1\n" +
            "getRightCol\n" +
            "playerAt: row = -1, col = 0\n" +
            "getRightCol\n" +
            "playerAt: row = -1, col = 1\n" +
            "getRightCol\n" +
            "playerAt: row = -1, col = 2\n" +
            "getRightCol\n" +
            "playerAt: row = -1, col = 3\n" +
            "getRightCol\n" +
            "playerAt: row = -1, col = 4\n" +
            "getRightCol\n" +
            "playerAt: row = -1, col = 5\n" +
            "getRightCol\n" +
            "getBottomRow\n" +
            "getLeftCol\n" +
            "getRightCol\n" +
            "playerAt: row = 0, col = -5\n" +
            "getRightCol\n" +
            "playerAt: row = 0, col = -4\n" +
            "getRightCol\n" +
            "playerAt: row = 0, col = -3\n" +
            "getRightCol\n" +
            "playerAt: row = 0, col = -2\n" +
            "getRightCol\n" +
            "playerAt: row = 0, col = -1\n" +
            "getRightCol\n" +
            "playerAt: row = 0, col = 0\n" +
            "getRightCol\n" +
            "playerAt: row = 0, col = 1\n" +
            "getRightCol\n" +
            "playerAt: row = 0, col = 2\n" +
            "getRightCol\n" +
            "playerAt: row = 0, col = 3\n" +
            "getRightCol\n" +
            "playerAt: row = 0, col = 4\n" +
            "getRightCol\n" +
            "playerAt: row = 0, col = 5\n" +
            "getRightCol\n" +
            "getBottomRow\n" +
            "getLeftCol\n" +
            "getRightCol\n" +
            "playerAt: row = 1, col = -5\n" +
            "getRightCol\n" +
            "playerAt: row = 1, col = -4\n" +
            "getRightCol\n" +
            "playerAt: row = 1, col = -3\n" +
            "getRightCol\n" +
            "playerAt: row = 1, col = -2\n" +
            "getRightCol\n" +
            "playerAt: row = 1, col = -1\n" +
            "getRightCol\n" +
            "playerAt: row = 1, col = 0\n" +
            "getRightCol\n" +
            "playerAt: row = 1, col = 1\n" +
            "getRightCol\n" +
            "playerAt: row = 1, col = 2\n" +
            "getRightCol\n" +
            "playerAt: row = 1, col = 3\n" +
            "getRightCol\n" +
            "playerAt: row = 1, col = 4\n" +
            "getRightCol\n" +
            "playerAt: row = 1, col = 5\n" +
            "getRightCol\n" +
            "getBottomRow\n" +
            "getLeftCol\n" +
            "getRightCol\n" +
            "playerAt: row = 2, col = -5\n" +
            "getRightCol\n" +
            "playerAt: row = 2, col = -4\n" +
            "getRightCol\n" +
            "playerAt: row = 2, col = -3\n" +
            "getRightCol\n" +
            "playerAt: row = 2, col = -2\n" +
            "getRightCol\n" +
            "playerAt: row = 2, col = -1\n" +
            "getRightCol\n" +
            "playerAt: row = 2, col = 0\n" +
            "getRightCol\n" +
            "playerAt: row = 2, col = 1\n" +
            "getRightCol\n" +
            "playerAt: row = 2, col = 2\n" +
            "getRightCol\n" +
            "playerAt: row = 2, col = 3\n" +
            "getRightCol\n" +
            "playerAt: row = 2, col = 4\n" +
            "getRightCol\n" +
            "playerAt: row = 2, col = 5\n" +
            "getRightCol\n" +
            "getBottomRow\n" +
            "getLeftCol\n" +
            "getRightCol\n" +
            "playerAt: row = 3, col = -5\n" +
            "getRightCol\n" +
            "playerAt: row = 3, col = -4\n" +
            "getRightCol\n" +
            "playerAt: row = 3, col = -3\n" +
            "getRightCol\n" +
            "playerAt: row = 3, col = -2\n" +
            "getRightCol\n" +
            "playerAt: row = 3, col = -1\n" +
            "getRightCol\n" +
            "playerAt: row = 3, col = 0\n" +
            "getRightCol\n" +
            "playerAt: row = 3, col = 1\n" +
            "getRightCol\n" +
            "playerAt: row = 3, col = 2\n" +
            "getRightCol\n" +
            "playerAt: row = 3, col = 3\n" +
            "getRightCol\n" +
            "playerAt: row = 3, col = 4\n" +
            "getRightCol\n" +
            "playerAt: row = 3, col = 5\n" +
            "getRightCol\n" +
            "getBottomRow\n" +
            "getLeftCol\n" +
            "getRightCol\n" +
            "playerAt: row = 4, col = -5\n" +
            "getRightCol\n" +
            "playerAt: row = 4, col = -4\n" +
            "getRightCol\n" +
            "playerAt: row = 4, col = -3\n" +
            "getRightCol\n" +
            "playerAt: row = 4, col = -2\n" +
            "getRightCol\n" +
            "playerAt: row = 4, col = -1\n" +
            "getRightCol\n" +
            "playerAt: row = 4, col = 0\n" +
            "getRightCol\n" +
            "playerAt: row = 4, col = 1\n" +
            "getRightCol\n" +
            "playerAt: row = 4, col = 2\n" +
            "getRightCol\n" +
            "playerAt: row = 4, col = 3\n" +
            "getRightCol\n" +
            "playerAt: row = 4, col = 4\n" +
            "getRightCol\n" +
            "playerAt: row = 4, col = 5\n" +
            "getRightCol\n" +
            "getBottomRow\n" +
            "getLeftCol\n" +
            "getRightCol\n" +
            "playerAt: row = 5, col = -5\n" +
            "getRightCol\n" +
            "playerAt: row = 5, col = -4\n" +
            "getRightCol\n" +
            "playerAt: row = 5, col = -3\n" +
            "getRightCol\n" +
            "playerAt: row = 5, col = -2\n" +
            "getRightCol\n" +
            "playerAt: row = 5, col = -1\n" +
            "getRightCol\n" +
            "playerAt: row = 5, col = 0\n" +
            "getRightCol\n" +
            "playerAt: row = 5, col = 1\n" +
            "getRightCol\n" +
            "playerAt: row = 5, col = 2\n" +
            "getRightCol\n" +
            "playerAt: row = 5, col = 3\n" +
            "getRightCol\n" +
            "playerAt: row = 5, col = 4\n" +
            "getRightCol\n" +
            "playerAt: row = 5, col = 5\n" +
            "getRightCol\n" +
            "getBottomRow\n", output.toString());
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
                    new MachinePlayer(Name.O, new AvoidNextToCornersStrategy())),
            List.of(HexDirection.values()));
    CompleteReversiStrategyFromFallible avoidNextToCorners =
            new CompleteReversiStrategyFromFallible(new AvoidNextToCornersStrategy());
    LinearCoord bestMove = avoidNextToCorners.chooseMove(mockModel,
            new MachinePlayer(Name.X, new AvoidNextToCornersStrategy()));
    Assert.assertEquals(new BasicPoint(-2, 0), bestMove);
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
  public void maxSquareCaptureTranscript() {
    StringBuilder output = new StringBuilder();
    ReadOnlyReversiModel mockModel = new CaptureInputReversi(output,
            Arrays.asList(new MachinePlayer(Name.X, new CaptureMaxStrategy()),
                    new MachinePlayer(Name.O, new CaptureMaxStrategy())),
            List.of(SquareDirection.values()));
    CompleteReversiStrategyFromFallible captureMax =
            new CompleteReversiStrategyFromFallible(new CaptureMaxStrategy());
    LinearCoord bestMove = captureMax.chooseMove(mockModel,
            new MachinePlayer(Name.X, new CaptureMaxStrategy()));
    Assert.assertEquals(new BasicPoint(-2, -1), bestMove);
    Assert.assertEquals("getModel\n" +
            "startGame\n" +
            "getModel\n" +
            "startGame\n" +
            "getPlayerScore: player\n" +
            "startGame\n" +
            "getBottomRow\n" +
            "getRightCol\n" +
            "getLeftCol\n" +
            "getTopRow\n" +
            "validMove: row = -5, col = -5\n" +
            "validMove: row = -5, col = -4\n" +
            "validMove: row = -5, col = -3\n" +
            "playerAt: row = -5, col = -2\n" +
            "playerAt: row = -4, col = -2\n" +
            "playerAt: row = -4, col = -3\n" +
            "validMove: row = -5, col = -2\n" +
            "playerAt: row = -5, col = -1\n" +
            "playerAt: row = -4, col = -1\n" +
            "playerAt: row = -4, col = -3\n" +
            "playerAt: row = -5, col = -3\n" +
            "playerAt: row = -4, col = -2\n" +
            "validMove: row = -5, col = -1\n" +
            "playerAt: row = -5, col = 0\n" +
            "playerAt: row = -4, col = 0\n" +
            "playerAt: row = -4, col = -2\n" +
            "playerAt: row = -5, col = -2\n" +
            "playerAt: row = -4, col = -1\n" +
            "validMove: row = -5, col = 0\n" +
            "playerAt: row = -5, col = 1\n" +
            "playerAt: row = -4, col = 1\n" +
            "playerAt: row = -4, col = -1\n" +
            "playerAt: row = -5, col = -1\n" +
            "playerAt: row = -4, col = 0\n" +
            "validMove: row = -5, col = 1\n" +
            "playerAt: row = -5, col = 2\n" +
            "playerAt: row = -4, col = 2\n" +
            "playerAt: row = -4, col = 0\n" +
            "playerAt: row = -5, col = 0\n" +
            "playerAt: row = -4, col = 1\n" +
            "validMove: row = -5, col = 2\n" +
            "playerAt: row = -4, col = 3\n" +
            "playerAt: row = -4, col = 1\n" +
            "playerAt: row = -5, col = 1\n" +
            "playerAt: row = -4, col = 2\n" +
            "validMove: row = -5, col = 3\n" +
            "validMove: row = -5, col = 4\n" +
            "validMove: row = -5, col = 5\n" +
            "validMove: row = -4, col = -5\n" +
            "validMove: row = -4, col = -4\n" +
            "validMove: row = -4, col = -3\n" +
            "playerAt: row = -5, col = -2\n" +
            "playerAt: row = -4, col = -2\n" +
            "playerAt: row = -3, col = -2\n" +
            "playerAt: row = -3, col = -4\n" +
            "playerAt: row = -5, col = -3\n" +
            "playerAt: row = -3, col = -3\n" +
            "validMove: row = -4, col = -2\n" +
            "playerAt: row = -5, col = -1\n" +
            "playerAt: row = -4, col = -1\n" +
            "playerAt: row = -3, col = -1\n" +
            "playerAt: row = -3, col = -3\n" +
            "playerAt: row = -4, col = -3\n" +
            "playerAt: row = -5, col = -3\n" +
            "playerAt: row = -5, col = -2\n" +
            "playerAt: row = -3, col = -2\n" +
            "validMove: row = -4, col = -1\n" +
            "playerAt: row = -5, col = 0\n" +
            "playerAt: row = -4, col = 0\n" +
            "playerAt: row = -3, col = 0\n" +
            "playerAt: row = -3, col = -2\n" +
            "playerAt: row = -4, col = -2\n" +
            "playerAt: row = -5, col = -2\n" +
            "playerAt: row = -5, col = -1\n" +
            "playerAt: row = -3, col = -1\n" +
            "validMove: row = -4, col = 0\n" +
            "playerAt: row = -5, col = 1\n" +
            "playerAt: row = -4, col = 1\n" +
            "playerAt: row = -3, col = 1\n" +
            "playerAt: row = -3, col = -1\n" +
            "playerAt: row = -4, col = -1\n" +
            "playerAt: row = -5, col = -1\n" +
            "playerAt: row = -5, col = 0\n" +
            "playerAt: row = -3, col = 0\n" +
            "validMove: row = -4, col = 1\n" +
            "playerAt: row = -5, col = 2\n" +
            "playerAt: row = -4, col = 2\n" +
            "playerAt: row = -3, col = 2\n" +
            "playerAt: row = -3, col = 0\n" +
            "playerAt: row = -4, col = 0\n" +
            "playerAt: row = -5, col = 0\n" +
            "playerAt: row = -5, col = 1\n" +
            "playerAt: row = -3, col = 1\n" +
            "validMove: row = -4, col = 2\n" +
            "playerAt: row = -4, col = 3\n" +
            "playerAt: row = -3, col = 3\n" +
            "playerAt: row = -3, col = 1\n" +
            "playerAt: row = -4, col = 1\n" +
            "playerAt: row = -5, col = 1\n" +
            "playerAt: row = -5, col = 2\n" +
            "playerAt: row = -3, col = 2\n" +
            "validMove: row = -4, col = 3\n" +
            "playerAt: row = -3, col = 2\n" +
            "playerAt: row = -4, col = 2\n" +
            "playerAt: row = -5, col = 2\n" +
            "playerAt: row = -3, col = 3\n" +
            "validMove: row = -4, col = 4\n" +
            "validMove: row = -4, col = 5\n" +
            "validMove: row = -3, col = -5\n" +
            "validMove: row = -3, col = -4\n" +
            "playerAt: row = -4, col = -3\n" +
            "playerAt: row = -3, col = -3\n" +
            "playerAt: row = -2, col = -3\n" +
            "playerAt: row = -2, col = -4\n" +
            "validMove: row = -3, col = -3\n" +
            "playerAt: row = -4, col = -2\n" +
            "playerAt: row = -3, col = -2\n" +
            "playerAt: row = -2, col = -2\n" +
            "playerAt: row = -2, col = -4\n" +
            "playerAt: row = -3, col = -4\n" +
            "playerAt: row = -4, col = -3\n" +
            "playerAt: row = -2, col = -3\n" +
            "validMove: row = -3, col = -2\n" +
            "playerAt: row = -4, col = -1\n" +
            "playerAt: row = -3, col = -1\n" +
            "playerAt: row = -2, col = -1\n" +
            "playerAt: row = -2, col = -3\n" +
            "playerAt: row = -3, col = -3\n" +
            "playerAt: row = -4, col = -3\n" +
            "playerAt: row = -4, col = -2\n" +
            "playerAt: row = -2, col = -2\n" +
            "validMove: row = -3, col = -1\n" +
            "playerAt: row = -4, col = 0\n" +
            "playerAt: row = -3, col = 0\n" +
            "playerAt: row = -2, col = 0\n" +
            "playerAt: row = -2, col = -2\n" +
            "playerAt: row = -3, col = -2\n" +
            "playerAt: row = -4, col = -2\n" +
            "playerAt: row = -4, col = -1\n" +
            "playerAt: row = -2, col = -1\n" +
            "validMove: row = -3, col = 0\n" +
            "playerAt: row = -4, col = 1\n" +
            "playerAt: row = -3, col = 1\n" +
            "playerAt: row = -2, col = 1\n" +
            "playerAt: row = -2, col = -1\n" +
            "playerAt: row = -3, col = -1\n" +
            "playerAt: row = -4, col = -1\n" +
            "playerAt: row = -4, col = 0\n" +
            "playerAt: row = -2, col = 0\n" +
            "validMove: row = -3, col = 1\n" +
            "playerAt: row = -4, col = 2\n" +
            "playerAt: row = -3, col = 2\n" +
            "playerAt: row = -2, col = 2\n" +
            "playerAt: row = -2, col = 0\n" +
            "playerAt: row = -3, col = 0\n" +
            "playerAt: row = -4, col = 0\n" +
            "playerAt: row = -4, col = 1\n" +
            "playerAt: row = -2, col = 1\n" +
            "validMove: row = -3, col = 2\n" +
            "playerAt: row = -4, col = 3\n" +
            "playerAt: row = -3, col = 3\n" +
            "playerAt: row = -2, col = 3\n" +
            "playerAt: row = -2, col = 1\n" +
            "playerAt: row = -3, col = 1\n" +
            "playerAt: row = -4, col = 1\n" +
            "playerAt: row = -4, col = 2\n" +
            "playerAt: row = -2, col = 2\n" +
            "validMove: row = -3, col = 3\n" +
            "playerAt: row = -2, col = 4\n" +
            "playerAt: row = -2, col = 2\n" +
            "playerAt: row = -3, col = 2\n" +
            "playerAt: row = -4, col = 2\n" +
            "playerAt: row = -4, col = 3\n" +
            "playerAt: row = -2, col = 3\n" +
            "validMove: row = -3, col = 4\n" +
            "validMove: row = -3, col = 5\n" +
            "validMove: row = -2, col = -5\n" +
            "validMove: row = -2, col = -4\n" +
            "playerAt: row = -3, col = -3\n" +
            "playerAt: row = -2, col = -3\n" +
            "playerAt: row = -1, col = -3\n" +
            "playerAt: row = -1, col = -5\n" +
            "playerAt: row = -3, col = -4\n" +
            "playerAt: row = -1, col = -4\n" +
            "validMove: row = -2, col = -3\n" +
            "playerAt: row = -3, col = -2\n" +
            "playerAt: row = -2, col = -2\n" +
            "playerAt: row = -1, col = -2\n" +
            "playerAt: row = -1, col = -4\n" +
            "playerAt: row = -2, col = -4\n" +
            "playerAt: row = -3, col = -4\n" +
            "playerAt: row = -3, col = -3\n" +
            "playerAt: row = -1, col = -3\n" +
            "validMove: row = -2, col = -2\n" +
            "playerAt: row = -3, col = -1\n" +
            "playerAt: row = -2, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = 0, col = 0\n" +
            "playerAt: row = -1, col = -3\n" +
            "playerAt: row = -2, col = -3\n" +
            "playerAt: row = -3, col = -3\n" +
            "playerAt: row = -3, col = -2\n" +
            "playerAt: row = -1, col = -2\n" +
            "validMove: row = -2, col = -1\n" +
            "playerAt: row = -3, col = 0\n" +
            "playerAt: row = -2, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = -2\n" +
            "playerAt: row = -2, col = -2\n" +
            "playerAt: row = -3, col = -2\n" +
            "playerAt: row = -3, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "validMove: row = -2, col = 0\n" +
            "playerAt: row = -3, col = 1\n" +
            "playerAt: row = -2, col = 1\n" +
            "playerAt: row = -1, col = 1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = 0, col = -2\n" +
            "playerAt: row = -2, col = -1\n" +
            "playerAt: row = -3, col = -1\n" +
            "playerAt: row = -3, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "validMove: row = -2, col = 1\n" +
            "playerAt: row = -3, col = 2\n" +
            "playerAt: row = -2, col = 2\n" +
            "playerAt: row = -1, col = 2\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -2, col = 0\n" +
            "playerAt: row = -3, col = 0\n" +
            "playerAt: row = -3, col = 1\n" +
            "playerAt: row = -1, col = 1\n" +
            "validMove: row = -2, col = 2\n" +
            "playerAt: row = -3, col = 3\n" +
            "playerAt: row = -2, col = 3\n" +
            "playerAt: row = -1, col = 3\n" +
            "playerAt: row = -1, col = 1\n" +
            "playerAt: row = -2, col = 1\n" +
            "playerAt: row = -3, col = 1\n" +
            "playerAt: row = -3, col = 2\n" +
            "playerAt: row = -1, col = 2\n" +
            "validMove: row = -2, col = 3\n" +
            "playerAt: row = -2, col = 4\n" +
            "playerAt: row = -1, col = 4\n" +
            "playerAt: row = -1, col = 2\n" +
            "playerAt: row = -2, col = 2\n" +
            "playerAt: row = -3, col = 2\n" +
            "playerAt: row = -3, col = 3\n" +
            "playerAt: row = -1, col = 3\n" +
            "validMove: row = -2, col = 4\n" +
            "playerAt: row = -1, col = 3\n" +
            "playerAt: row = -2, col = 3\n" +
            "playerAt: row = -3, col = 3\n" +
            "playerAt: row = -1, col = 4\n" +
            "validMove: row = -2, col = 5\n" +
            "validMove: row = -1, col = -5\n" +
            "playerAt: row = -2, col = -4\n" +
            "playerAt: row = -1, col = -4\n" +
            "playerAt: row = 0, col = -4\n" +
            "playerAt: row = 0, col = -5\n" +
            "validMove: row = -1, col = -4\n" +
            "playerAt: row = -2, col = -3\n" +
            "playerAt: row = -1, col = -3\n" +
            "playerAt: row = 0, col = -3\n" +
            "playerAt: row = 0, col = -5\n" +
            "playerAt: row = -1, col = -5\n" +
            "playerAt: row = -2, col = -4\n" +
            "playerAt: row = 0, col = -4\n" +
            "validMove: row = -1, col = -3\n" +
            "playerAt: row = -2, col = -2\n" +
            "playerAt: row = -1, col = -2\n" +
            "playerAt: row = 0, col = -2\n" +
            "playerAt: row = 0, col = -4\n" +
            "playerAt: row = -1, col = -4\n" +
            "playerAt: row = -2, col = -4\n" +
            "playerAt: row = -2, col = -3\n" +
            "playerAt: row = 0, col = -3\n" +
            "validMove: row = -1, col = -2\n" +
            "playerAt: row = -2, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -3\n" +
            "playerAt: row = -1, col = -3\n" +
            "playerAt: row = -2, col = -3\n" +
            "playerAt: row = -2, col = -2\n" +
            "playerAt: row = 0, col = -2\n" +
            "validMove: row = -1, col = -1\n" +
            "validMove: row = -1, col = 0\n" +
            "validMove: row = -1, col = 1\n" +
            "playerAt: row = -2, col = 2\n" +
            "playerAt: row = -1, col = 2\n" +
            "playerAt: row = 0, col = 2\n" +
            "playerAt: row = 0, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -2, col = 0\n" +
            "playerAt: row = -2, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 1, col = 1\n" +
            "validMove: row = -1, col = 2\n" +
            "playerAt: row = -2, col = 3\n" +
            "playerAt: row = -1, col = 3\n" +
            "playerAt: row = 0, col = 3\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = -1, col = 1\n" +
            "playerAt: row = -2, col = 1\n" +
            "playerAt: row = -2, col = 2\n" +
            "playerAt: row = 0, col = 2\n" +
            "validMove: row = -1, col = 3\n" +
            "playerAt: row = -2, col = 4\n" +
            "playerAt: row = -1, col = 4\n" +
            "playerAt: row = 0, col = 4\n" +
            "playerAt: row = 0, col = 2\n" +
            "playerAt: row = -1, col = 2\n" +
            "playerAt: row = -2, col = 2\n" +
            "playerAt: row = -2, col = 3\n" +
            "playerAt: row = 0, col = 3\n" +
            "validMove: row = -1, col = 4\n" +
            "playerAt: row = 0, col = 5\n" +
            "playerAt: row = 0, col = 3\n" +
            "playerAt: row = -1, col = 3\n" +
            "playerAt: row = -2, col = 3\n" +
            "playerAt: row = -2, col = 4\n" +
            "playerAt: row = 0, col = 4\n" +
            "validMove: row = -1, col = 5\n" +
            "validMove: row = 0, col = -5\n" +
            "playerAt: row = -1, col = -4\n" +
            "playerAt: row = 0, col = -4\n" +
            "playerAt: row = 1, col = -4\n" +
            "playerAt: row = -1, col = -5\n" +
            "playerAt: row = 1, col = -5\n" +
            "validMove: row = 0, col = -4\n" +
            "playerAt: row = -1, col = -3\n" +
            "playerAt: row = 0, col = -3\n" +
            "playerAt: row = 1, col = -3\n" +
            "playerAt: row = 1, col = -5\n" +
            "playerAt: row = 0, col = -5\n" +
            "playerAt: row = -1, col = -5\n" +
            "playerAt: row = -1, col = -4\n" +
            "playerAt: row = 1, col = -4\n" +
            "validMove: row = 0, col = -3\n" +
            "playerAt: row = -1, col = -2\n" +
            "playerAt: row = 0, col = -2\n" +
            "playerAt: row = 1, col = -2\n" +
            "playerAt: row = 1, col = -4\n" +
            "playerAt: row = 0, col = -4\n" +
            "playerAt: row = -1, col = -4\n" +
            "playerAt: row = -1, col = -3\n" +
            "playerAt: row = 1, col = -3\n" +
            "validMove: row = 0, col = -2\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -2, col = 0\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 2, col = 0\n" +
            "playerAt: row = 1, col = -3\n" +
            "playerAt: row = 0, col = -3\n" +
            "playerAt: row = -1, col = -3\n" +
            "playerAt: row = -1, col = -2\n" +
            "playerAt: row = 1, col = -2\n" +
            "validMove: row = 0, col = -1\n" +
            "validMove: row = 0, col = 0\n" +
            "playerAt: row = -1, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 2\n" +
            "playerAt: row = 1, col = 1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 2, col = -2\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -2, col = -2\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "validMove: row = 0, col = 1\n" +
            "validMove: row = 0, col = 2\n" +
            "playerAt: row = -1, col = 3\n" +
            "playerAt: row = 0, col = 3\n" +
            "playerAt: row = 1, col = 3\n" +
            "playerAt: row = 1, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 0\n" +
            "playerAt: row = -1, col = 1\n" +
            "playerAt: row = -1, col = 2\n" +
            "playerAt: row = 1, col = 2\n" +
            "validMove: row = 0, col = 3\n" +
            "playerAt: row = -1, col = 4\n" +
            "playerAt: row = 0, col = 4\n" +
            "playerAt: row = 1, col = 4\n" +
            "playerAt: row = 1, col = 2\n" +
            "playerAt: row = 0, col = 2\n" +
            "playerAt: row = -1, col = 2\n" +
            "playerAt: row = -1, col = 3\n" +
            "playerAt: row = 1, col = 3\n" +
            "validMove: row = 0, col = 4\n" +
            "playerAt: row = 0, col = 5\n" +
            "playerAt: row = 1, col = 3\n" +
            "playerAt: row = 0, col = 3\n" +
            "playerAt: row = -1, col = 3\n" +
            "playerAt: row = -1, col = 4\n" +
            "playerAt: row = 1, col = 4\n" +
            "validMove: row = 0, col = 5\n" +
            "playerAt: row = 1, col = 4\n" +
            "playerAt: row = 0, col = 4\n" +
            "playerAt: row = -1, col = 4\n" +
            "validMove: row = 1, col = -5\n" +
            "playerAt: row = 0, col = -4\n" +
            "playerAt: row = 1, col = -4\n" +
            "playerAt: row = 2, col = -4\n" +
            "playerAt: row = 0, col = -5\n" +
            "validMove: row = 1, col = -4\n" +
            "playerAt: row = 0, col = -3\n" +
            "playerAt: row = 1, col = -3\n" +
            "playerAt: row = 2, col = -3\n" +
            "playerAt: row = 1, col = -5\n" +
            "playerAt: row = 0, col = -5\n" +
            "playerAt: row = 0, col = -4\n" +
            "playerAt: row = 2, col = -4\n" +
            "validMove: row = 1, col = -3\n" +
            "playerAt: row = 0, col = -2\n" +
            "playerAt: row = 1, col = -2\n" +
            "playerAt: row = 2, col = -2\n" +
            "playerAt: row = 2, col = -4\n" +
            "playerAt: row = 1, col = -4\n" +
            "playerAt: row = 0, col = -4\n" +
            "playerAt: row = 0, col = -3\n" +
            "playerAt: row = 2, col = -3\n" +
            "validMove: row = 1, col = -2\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 2, col = -1\n" +
            "playerAt: row = 2, col = -3\n" +
            "playerAt: row = 1, col = -3\n" +
            "playerAt: row = 0, col = -3\n" +
            "playerAt: row = 0, col = -2\n" +
            "playerAt: row = 2, col = -2\n" +
            "validMove: row = 1, col = -1\n" +
            "validMove: row = 1, col = 0\n" +
            "validMove: row = 1, col = 1\n" +
            "playerAt: row = 0, col = 2\n" +
            "playerAt: row = 1, col = 2\n" +
            "playerAt: row = 2, col = 2\n" +
            "playerAt: row = 2, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 0, col = 0\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = -1, col = 1\n" +
            "playerAt: row = 2, col = 1\n" +
            "validMove: row = 1, col = 2\n" +
            "playerAt: row = 0, col = 3\n" +
            "playerAt: row = 1, col = 3\n" +
            "playerAt: row = 2, col = 3\n" +
            "playerAt: row = 2, col = 1\n" +
            "playerAt: row = 1, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = 0, col = 2\n" +
            "playerAt: row = 2, col = 2\n" +
            "validMove: row = 1, col = 3\n" +
            "playerAt: row = 0, col = 4\n" +
            "playerAt: row = 1, col = 4\n" +
            "playerAt: row = 2, col = 4\n" +
            "playerAt: row = 2, col = 2\n" +
            "playerAt: row = 1, col = 2\n" +
            "playerAt: row = 0, col = 2\n" +
            "playerAt: row = 0, col = 3\n" +
            "playerAt: row = 2, col = 3\n" +
            "validMove: row = 1, col = 4\n" +
            "playerAt: row = 0, col = 5\n" +
            "playerAt: row = 2, col = 3\n" +
            "playerAt: row = 1, col = 3\n" +
            "playerAt: row = 0, col = 3\n" +
            "playerAt: row = 0, col = 4\n" +
            "playerAt: row = 2, col = 4\n" +
            "validMove: row = 1, col = 5\n" +
            "validMove: row = 2, col = -5\n" +
            "validMove: row = 2, col = -4\n" +
            "playerAt: row = 1, col = -3\n" +
            "playerAt: row = 2, col = -3\n" +
            "playerAt: row = 3, col = -3\n" +
            "playerAt: row = 1, col = -5\n" +
            "playerAt: row = 1, col = -4\n" +
            "playerAt: row = 3, col = -4\n" +
            "validMove: row = 2, col = -3\n" +
            "playerAt: row = 1, col = -2\n" +
            "playerAt: row = 2, col = -2\n" +
            "playerAt: row = 3, col = -2\n" +
            "playerAt: row = 3, col = -4\n" +
            "playerAt: row = 2, col = -4\n" +
            "playerAt: row = 1, col = -4\n" +
            "playerAt: row = 1, col = -3\n" +
            "playerAt: row = 3, col = -3\n" +
            "validMove: row = 2, col = -2\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 0, col = 0\n" +
            "playerAt: row = 2, col = -1\n" +
            "playerAt: row = 3, col = -1\n" +
            "playerAt: row = 3, col = -3\n" +
            "playerAt: row = 2, col = -3\n" +
            "playerAt: row = 1, col = -3\n" +
            "playerAt: row = 1, col = -2\n" +
            "playerAt: row = 3, col = -2\n" +
            "validMove: row = 2, col = -1\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 2, col = 0\n" +
            "playerAt: row = 3, col = 0\n" +
            "playerAt: row = 3, col = -2\n" +
            "playerAt: row = 2, col = -2\n" +
            "playerAt: row = 1, col = -2\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 3, col = -1\n" +
            "validMove: row = 2, col = 0\n" +
            "playerAt: row = 1, col = 1\n" +
            "playerAt: row = 2, col = 1\n" +
            "playerAt: row = 3, col = 1\n" +
            "playerAt: row = 3, col = -1\n" +
            "playerAt: row = 2, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 0, col = -2\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 3, col = 0\n" +
            "validMove: row = 2, col = 1\n" +
            "playerAt: row = 1, col = 2\n" +
            "playerAt: row = 2, col = 2\n" +
            "playerAt: row = 3, col = 2\n" +
            "playerAt: row = 3, col = 0\n" +
            "playerAt: row = 2, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 1\n" +
            "playerAt: row = 3, col = 1\n" +
            "validMove: row = 2, col = 2\n" +
            "playerAt: row = 1, col = 3\n" +
            "playerAt: row = 2, col = 3\n" +
            "playerAt: row = 3, col = 3\n" +
            "playerAt: row = 3, col = 1\n" +
            "playerAt: row = 2, col = 1\n" +
            "playerAt: row = 1, col = 1\n" +
            "playerAt: row = 1, col = 2\n" +
            "playerAt: row = 3, col = 2\n" +
            "validMove: row = 2, col = 3\n" +
            "playerAt: row = 1, col = 4\n" +
            "playerAt: row = 2, col = 4\n" +
            "playerAt: row = 3, col = 2\n" +
            "playerAt: row = 2, col = 2\n" +
            "playerAt: row = 1, col = 2\n" +
            "playerAt: row = 1, col = 3\n" +
            "playerAt: row = 3, col = 3\n" +
            "validMove: row = 2, col = 4\n" +
            "playerAt: row = 3, col = 3\n" +
            "playerAt: row = 2, col = 3\n" +
            "playerAt: row = 1, col = 3\n" +
            "playerAt: row = 1, col = 4\n" +
            "validMove: row = 2, col = 5\n" +
            "validMove: row = 3, col = -5\n" +
            "validMove: row = 3, col = -4\n" +
            "playerAt: row = 2, col = -3\n" +
            "playerAt: row = 3, col = -3\n" +
            "playerAt: row = 4, col = -3\n" +
            "playerAt: row = 2, col = -4\n" +
            "validMove: row = 3, col = -3\n" +
            "playerAt: row = 2, col = -2\n" +
            "playerAt: row = 3, col = -2\n" +
            "playerAt: row = 4, col = -2\n" +
            "playerAt: row = 3, col = -4\n" +
            "playerAt: row = 2, col = -4\n" +
            "playerAt: row = 2, col = -3\n" +
            "playerAt: row = 4, col = -3\n" +
            "validMove: row = 3, col = -2\n" +
            "playerAt: row = 2, col = -1\n" +
            "playerAt: row = 3, col = -1\n" +
            "playerAt: row = 4, col = -1\n" +
            "playerAt: row = 4, col = -3\n" +
            "playerAt: row = 3, col = -3\n" +
            "playerAt: row = 2, col = -3\n" +
            "playerAt: row = 2, col = -2\n" +
            "playerAt: row = 4, col = -2\n" +
            "validMove: row = 3, col = -1\n" +
            "playerAt: row = 2, col = 0\n" +
            "playerAt: row = 3, col = 0\n" +
            "playerAt: row = 4, col = 0\n" +
            "playerAt: row = 4, col = -2\n" +
            "playerAt: row = 3, col = -2\n" +
            "playerAt: row = 2, col = -2\n" +
            "playerAt: row = 2, col = -1\n" +
            "playerAt: row = 4, col = -1\n" +
            "validMove: row = 3, col = 0\n" +
            "playerAt: row = 2, col = 1\n" +
            "playerAt: row = 3, col = 1\n" +
            "playerAt: row = 4, col = 1\n" +
            "playerAt: row = 4, col = -1\n" +
            "playerAt: row = 3, col = -1\n" +
            "playerAt: row = 2, col = -1\n" +
            "playerAt: row = 2, col = 0\n" +
            "playerAt: row = 4, col = 0\n" +
            "validMove: row = 3, col = 1\n" +
            "playerAt: row = 2, col = 2\n" +
            "playerAt: row = 3, col = 2\n" +
            "playerAt: row = 4, col = 2\n" +
            "playerAt: row = 4, col = 0\n" +
            "playerAt: row = 3, col = 0\n" +
            "playerAt: row = 2, col = 0\n" +
            "playerAt: row = 2, col = 1\n" +
            "playerAt: row = 4, col = 1\n" +
            "validMove: row = 3, col = 2\n" +
            "playerAt: row = 2, col = 3\n" +
            "playerAt: row = 3, col = 3\n" +
            "playerAt: row = 4, col = 3\n" +
            "playerAt: row = 4, col = 1\n" +
            "playerAt: row = 3, col = 1\n" +
            "playerAt: row = 2, col = 1\n" +
            "playerAt: row = 2, col = 2\n" +
            "playerAt: row = 4, col = 2\n" +
            "validMove: row = 3, col = 3\n" +
            "playerAt: row = 2, col = 4\n" +
            "playerAt: row = 4, col = 2\n" +
            "playerAt: row = 3, col = 2\n" +
            "playerAt: row = 2, col = 2\n" +
            "playerAt: row = 2, col = 3\n" +
            "playerAt: row = 4, col = 3\n" +
            "validMove: row = 3, col = 4\n" +
            "validMove: row = 3, col = 5\n" +
            "validMove: row = 4, col = -5\n" +
            "validMove: row = 4, col = -4\n" +
            "validMove: row = 4, col = -3\n" +
            "playerAt: row = 3, col = -2\n" +
            "playerAt: row = 4, col = -2\n" +
            "playerAt: row = 5, col = -2\n" +
            "playerAt: row = 3, col = -4\n" +
            "playerAt: row = 3, col = -3\n" +
            "playerAt: row = 5, col = -3\n" +
            "validMove: row = 4, col = -2\n" +
            "playerAt: row = 3, col = -1\n" +
            "playerAt: row = 4, col = -1\n" +
            "playerAt: row = 5, col = -1\n" +
            "playerAt: row = 5, col = -3\n" +
            "playerAt: row = 4, col = -3\n" +
            "playerAt: row = 3, col = -3\n" +
            "playerAt: row = 3, col = -2\n" +
            "playerAt: row = 5, col = -2\n" +
            "validMove: row = 4, col = -1\n" +
            "playerAt: row = 3, col = 0\n" +
            "playerAt: row = 4, col = 0\n" +
            "playerAt: row = 5, col = 0\n" +
            "playerAt: row = 5, col = -2\n" +
            "playerAt: row = 4, col = -2\n" +
            "playerAt: row = 3, col = -2\n" +
            "playerAt: row = 3, col = -1\n" +
            "playerAt: row = 5, col = -1\n" +
            "validMove: row = 4, col = 0\n" +
            "playerAt: row = 3, col = 1\n" +
            "playerAt: row = 4, col = 1\n" +
            "playerAt: row = 5, col = 1\n" +
            "playerAt: row = 5, col = -1\n" +
            "playerAt: row = 4, col = -1\n" +
            "playerAt: row = 3, col = -1\n" +
            "playerAt: row = 3, col = 0\n" +
            "playerAt: row = 5, col = 0\n" +
            "validMove: row = 4, col = 1\n" +
            "playerAt: row = 3, col = 2\n" +
            "playerAt: row = 4, col = 2\n" +
            "playerAt: row = 5, col = 2\n" +
            "playerAt: row = 5, col = 0\n" +
            "playerAt: row = 4, col = 0\n" +
            "playerAt: row = 3, col = 0\n" +
            "playerAt: row = 3, col = 1\n" +
            "playerAt: row = 5, col = 1\n" +
            "validMove: row = 4, col = 2\n" +
            "playerAt: row = 3, col = 3\n" +
            "playerAt: row = 4, col = 3\n" +
            "playerAt: row = 5, col = 1\n" +
            "playerAt: row = 4, col = 1\n" +
            "playerAt: row = 3, col = 1\n" +
            "playerAt: row = 3, col = 2\n" +
            "playerAt: row = 5, col = 2\n" +
            "validMove: row = 4, col = 3\n" +
            "playerAt: row = 5, col = 2\n" +
            "playerAt: row = 4, col = 2\n" +
            "playerAt: row = 3, col = 2\n" +
            "playerAt: row = 3, col = 3\n" +
            "validMove: row = 4, col = 4\n" +
            "validMove: row = 4, col = 5\n" +
            "validMove: row = 5, col = -5\n" +
            "validMove: row = 5, col = -4\n" +
            "validMove: row = 5, col = -3\n" +
            "playerAt: row = 4, col = -2\n" +
            "playerAt: row = 5, col = -2\n" +
            "playerAt: row = 4, col = -3\n" +
            "validMove: row = 5, col = -2\n" +
            "playerAt: row = 4, col = -1\n" +
            "playerAt: row = 5, col = -1\n" +
            "playerAt: row = 5, col = -3\n" +
            "playerAt: row = 4, col = -3\n" +
            "playerAt: row = 4, col = -2\n" +
            "validMove: row = 5, col = -1\n" +
            "playerAt: row = 4, col = 0\n" +
            "playerAt: row = 5, col = 0\n" +
            "playerAt: row = 5, col = -2\n" +
            "playerAt: row = 4, col = -2\n" +
            "playerAt: row = 4, col = -1\n" +
            "validMove: row = 5, col = 0\n" +
            "playerAt: row = 4, col = 1\n" +
            "playerAt: row = 5, col = 1\n" +
            "playerAt: row = 5, col = -1\n" +
            "playerAt: row = 4, col = -1\n" +
            "playerAt: row = 4, col = 0\n" +
            "validMove: row = 5, col = 1\n" +
            "playerAt: row = 4, col = 2\n" +
            "playerAt: row = 5, col = 2\n" +
            "playerAt: row = 5, col = 0\n" +
            "playerAt: row = 4, col = 0\n" +
            "playerAt: row = 4, col = 1\n" +
            "validMove: row = 5, col = 2\n" +
            "playerAt: row = 4, col = 3\n" +
            "playerAt: row = 5, col = 1\n" +
            "playerAt: row = 4, col = 1\n" +
            "playerAt: row = 4, col = 2\n" +
            "validMove: row = 5, col = 3\n" +
            "validMove: row = 5, col = 4\n" +
            "validMove: row = 5, col = 5\n" +
            "getModel\n" +
            "startGame\n" +
            "placePiece: row = -2, col = -1\n" +
            "playerAt: row = -3, col = 0\n" +
            "playerAt: row = -2, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = -2\n" +
            "playerAt: row = -2, col = -2\n" +
            "playerAt: row = -3, col = -2\n" +
            "playerAt: row = -3, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "getPlayerScore: player\n" +
            "getModel\n" +
            "startGame\n" +
            "placePiece: row = -1, col = -2\n" +
            "playerAt: row = -2, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = -1\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -3\n" +
            "playerAt: row = -1, col = -3\n" +
            "playerAt: row = -2, col = -3\n" +
            "playerAt: row = -2, col = -2\n" +
            "playerAt: row = 0, col = -2\n" +
            "getPlayerScore: player\n" +
            "getModel\n" +
            "startGame\n" +
            "placePiece: row = -1, col = 2\n" +
            "playerAt: row = -2, col = 3\n" +
            "playerAt: row = -1, col = 3\n" +
            "playerAt: row = 0, col = 3\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = -1, col = 1\n" +
            "playerAt: row = -2, col = 1\n" +
            "playerAt: row = -2, col = 2\n" +
            "playerAt: row = 0, col = 2\n" +
            "getPlayerScore: player\n" +
            "getModel\n" +
            "startGame\n" +
            "placePiece: row = 1, col = -2\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 2, col = -1\n" +
            "playerAt: row = 2, col = -3\n" +
            "playerAt: row = 1, col = -3\n" +
            "playerAt: row = 0, col = -3\n" +
            "playerAt: row = 0, col = -2\n" +
            "playerAt: row = 2, col = -2\n" +
            "getPlayerScore: player\n" +
            "getModel\n" +
            "startGame\n" +
            "placePiece: row = 1, col = 2\n" +
            "playerAt: row = 0, col = 3\n" +
            "playerAt: row = 1, col = 3\n" +
            "playerAt: row = 2, col = 3\n" +
            "playerAt: row = 2, col = 1\n" +
            "playerAt: row = 1, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = 0, col = 1\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = -1, col = 0\n" +
            "playerAt: row = 0, col = 2\n" +
            "playerAt: row = 2, col = 2\n" +
            "getPlayerScore: player\n" +
            "getModel\n" +
            "startGame\n" +
            "placePiece: row = 2, col = -1\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 1, col = 0\n" +
            "playerAt: row = 2, col = 0\n" +
            "playerAt: row = 3, col = 0\n" +
            "playerAt: row = 3, col = -2\n" +
            "playerAt: row = 2, col = -2\n" +
            "playerAt: row = 1, col = -2\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 1, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 0, col = -1\n" +
            "playerAt: row = 3, col = -1\n" +
            "getPlayerScore: player\n", output.toString());
  }

}
