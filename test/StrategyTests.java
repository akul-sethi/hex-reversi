import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import cs3500.reversi.model.GameType;
import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReversiCreator;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.MachinePlayer;
import cs3500.reversi.player.Name;
import cs3500.reversi.player.Player;
import cs3500.reversi.strategy.AvoidNextToCornersStrategy;
import cs3500.reversi.strategy.CaptureCornersStrategy;
import cs3500.reversi.strategy.CaptureMaxStrategy;
import cs3500.reversi.strategy.CompleteReversiStrategyFromFallible;
import cs3500.reversi.strategy.MiniMaxStrategy;
import cs3500.reversi.strategy.TryTwo;
import cs3500.reversi.view.ReversiView;
import cs3500.reversi.view.TextReversiView;

/**
 * Class to represent tests for the strategies.
 */
public class StrategyTests {

  @Test
  public void gameJustStartedCaptureMaxTest() throws IOException {
    Appendable emptyBuilder = new StringBuilder();
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC,
            6, new MachinePlayer(Name.X, new CaptureMaxStrategy()), new MachinePlayer(
                    Name.O, new CaptureMaxStrategy()));
    ReversiView textView = new TextReversiView(basicModel, emptyBuilder);
    basicModel.startGame();
    CompleteReversiStrategyFromFallible captureMax =
            new CompleteReversiStrategyFromFallible(new CaptureMaxStrategy());
    for (int i = 0; i < 8; i += 1) {
      LinearCoord bestMove = captureMax.chooseMove(basicModel,
              basicModel.nextToPlay());
      basicModel.placePiece(bestMove);
      textView.render();
    }
    Assert.assertEquals(emptyBuilder.toString(), "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - - - - - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - X X - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - O - - - -     \n" +
            "  - - - - O - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - O - - - -     \n" +
            "  - - - - O - - - -   \n" +
            " - - - - X X X - - -   \n" +
            "- - - - X - X - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - O - - - -     \n" +
            "  - - - - O - - - -   \n" +
            " - - - - X O X - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O O O - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - O - - - -     \n" +
            "  - - - - O - - - -   \n" +
            " - - - - X O X - - -   \n" +
            "- - - - X - X - - - - \n" +
            " - - - - X X O - - -   \n" +
            "  - - - - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - O - - - -     \n" +
            "  - - - - O - - - -   \n" +
            " - - - - O O X - - -   \n" +
            "- - - - O - X - - - - \n" +
            " - - - O O O O - - -   \n" +
            "  - - - - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - O - - - -     \n" +
            "  - - - - O - - - -   \n" +
            " - - - X X X X - - -   \n" +
            "- - - - X - X - - - - \n" +
            " - - - O X O O - - -   \n" +
            "  - - - - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - O - - - -     \n" +
            "  - - - - O - O - -   \n" +
            " - - - X X X O - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - O X O O - - -   \n" +
            "  - - - - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n");
  }

  //should just capture top left, no corners
  @Test
  public void gameJustStartedCaptureCornersTest() throws IOException {
    Appendable emptyBuilder = new StringBuilder();
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC,
            6, new MachinePlayer(Name.X, new CaptureCornersStrategy()),
            new MachinePlayer(Name.O, new CaptureCornersStrategy()));
    ReversiView textView = new TextReversiView(basicModel, emptyBuilder);
    basicModel.startGame();
    CompleteReversiStrategyFromFallible captureCorners =
            new CompleteReversiStrategyFromFallible(new CaptureCornersStrategy());
    for (int i = 0; i < 40; i += 1) {
      LinearCoord bestMove = captureCorners.chooseMove(basicModel,
              basicModel.nextToPlay());
      basicModel.placePiece(bestMove);
      textView.render();
    }
    Assert.assertEquals(emptyBuilder.toString(), "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - - - - - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - X X - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - O - - - -     \n" +
            "  - - - - O - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - O X - - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - O O O - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - - - -       \n" +
            "    - - X - - - -     \n" +
            "   - - - X O O - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - - - -       \n" +
            "    - - O - - - -     \n" +
            "   - - - O O O - -     \n" +
            "  - - - - O - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - - - -       \n" +
            "    - - O - X - -     \n" +
            "   - - - O X O - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - O - -       \n" +
            "    - - O - O - -     \n" +
            "   - - - O X O - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - O X -       \n" +
            "    - - O - X - -     \n" +
            "   - - - O X O - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - O O O       \n" +
            "    - - O - X - -     \n" +
            "   - - - O X O - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - O O O       \n" +
            "    - - O - X - -     \n" +
            "   - - X X X O - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - O O O       \n" +
            "    - - O - X - -     \n" +
            "   - O O O O O - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - O O O       \n" +
            "    - - O - X - -     \n" +
            "   - O O O O X - -     \n" +
            "  - - - - X - X - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - O O O       \n" +
            "    - - O - X - -     \n" +
            "   - O O O O O O -     \n" +
            "  - - - - X - X - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - O O O       \n" +
            "    - - O - X - X     \n" +
            "   - O O O O O X -     \n" +
            "  - - - - X - X - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - O O O       \n" +
            "    - - O - X - O     \n" +
            "   - O O O O O O O     \n" +
            "  - - - - X - X - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - O O O       \n" +
            "    - - O - X - O     \n" +
            "   - O O O O O O O     \n" +
            "  - - - - X - X - -   \n" +
            " - - - - X X X - - -   \n" +
            "- - - - X - X - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - O O O       \n" +
            "    - - O - X - O     \n" +
            "   - O O O O O O O     \n" +
            "  - - - - X - X - -   \n" +
            " - - - O X X X - - -   \n" +
            "- - - - O - X - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - O O O       \n" +
            "    - - O - X - O     \n" +
            "   - O O O O O O O     \n" +
            "  - - - - X - X - -   \n" +
            " - - X X X X X - - -   \n" +
            "- - - - O - X - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - O O O       \n" +
            "    - - O - X - O     \n" +
            "   - O O O O O O O     \n" +
            "  - - O - X - X - -   \n" +
            " - - X O X X X - - -   \n" +
            "- - - - O - X - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O X O O O       \n" +
            "    - - X - X - O     \n" +
            "   - O X O O O O O     \n" +
            "  - - X - X - X - -   \n" +
            " - - X O X X X - - -   \n" +
            "- - - - O - X - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O X O O O       \n" +
            "    - - X - X - O     \n" +
            "   - O X O O O O O     \n" +
            "  - - X - X - X - -   \n" +
            " - O O O X X X - - -   \n" +
            "- - - - O - X - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     X X X O O O       \n" +
            "    - - X - X - O     \n" +
            "   - O X O O O O O     \n" +
            "  - - X - X - X - -   \n" +
            " - O O O X X X - - -   \n" +
            "- - - - O - X - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     X X X O O O       \n" +
            "    - - X - X - O     \n" +
            "   - O X O O O O O     \n" +
            "  - - X - X - O - -   \n" +
            " - O O O O O O O - -   \n" +
            "- - - - O - X - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     X X X O O O       \n" +
            "    X - X - X - O     \n" +
            "   - X X O O O O O     \n" +
            "  - - X - X - O - -   \n" +
            " - O O O O O O O - -   \n" +
            "- - - - O - X - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     X X X O O O       \n" +
            "    X - X - X - O     \n" +
            "   O O O O O O O O     \n" +
            "  - - X - X - O - -   \n" +
            " - O O O O O O O - -   \n" +
            "- - - - O - X - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     X X X O O O       \n" +
            "    X - X - X - O     \n" +
            "   X O O O O O O O     \n" +
            "  X - X - X - O - -   \n" +
            " - O O O O O O O - -   \n" +
            "- - - - O - X - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     X X X O O O       \n" +
            "    X - X - X - O     \n" +
            "   X O O O O O O O     \n" +
            "  X - X - X - O - -   \n" +
            " - O O O O O O O - -   \n" +
            "- - - - O - O - - - - \n" +
            " - - - - O O O - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     X X X O O O       \n" +
            "    X - X - X - O     \n" +
            "   X O O O O O O O     \n" +
            "  X - X - X - O - -   \n" +
            " - X X O O O O O - -   \n" +
            "- - X - O - O - - - - \n" +
            " - - - - O O O - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     X X X O O O       \n" +
            "    X - X - X - O     \n" +
            "   X O O O O O O O     \n" +
            "  X - X - X - O - -   \n" +
            " O O O O O O O O - -   \n" +
            "- - X - O - O - - - - \n" +
            " - - - - O O O - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     X X X O O O       \n" +
            "    X - X - X - O     \n" +
            "   X O O O O O O O     \n" +
            "  X - X - X - O - -   \n" +
            " X O O O O O O O - -   \n" +
            "X - X - O - O - - - - \n" +
            " - - - - O O O - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     X X X O O O       \n" +
            "    X - X - X - O     \n" +
            "   X O O O O O O O     \n" +
            "  X - X - X - O - -   \n" +
            " X O O O O O O O - -   \n" +
            "X - O - O - O - - - - \n" +
            " - O - - O O O - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     X X X O O O       \n" +
            "    X - X - X - O     \n" +
            "   X O O O O O O O     \n" +
            "  X - X - X - O - -   \n" +
            " X X X X X X X X X -   \n" +
            "X - O - O - O - - - - \n" +
            " - O - - O O O - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     X X X O O O       \n" +
            "    X - X - X - O     \n" +
            "   X O O O O O O O     \n" +
            "  X - X - X - O - -   \n" +
            " X X X X X X X O X -   \n" +
            "X - O - O - O - O - - \n" +
            " - O - - O O O - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     X X X O O O       \n" +
            "    X - X - X - O     \n" +
            "   X O O O O O O O     \n" +
            "  X - X - X - O - -   \n" +
            " X X X X X X X O X -   \n" +
            "X - X - O - O - O - - \n" +
            " - O X - O O O - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     X X X O O O       \n" +
            "    X - X - X - O     \n" +
            "   X O O O O O O O     \n" +
            "  X - X - X - O - O   \n" +
            " X X X X X X X O O -   \n" +
            "X - X - O - O - O - - \n" +
            " - O X - O O O - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     X X X O O O       \n" +
            "    X - X - X - O     \n" +
            "   X O O O O O O O     \n" +
            "  X - X - X - O - O   \n" +
            " X X X X X X X X X X   \n" +
            "X - X - O - O - O - - \n" +
            " - O X - O O O - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     X X X O O O       \n" +
            "    X - X - X - O     \n" +
            "   X O O O O O O O     \n" +
            "  X - X - X - O - O   \n" +
            " X X X X X X X X X O   \n" +
            "X - X - O - O - O - O \n" +
            " - O X - O O O - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     X X X O O O       \n" +
            "    X - X - X - O     \n" +
            "   X O O O O O O O     \n" +
            "  X - X - X - O - O   \n" +
            " X X X X X X X X X O   \n" +
            "X - X - O - O - O - O \n" +
            " X X X - O O O - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     X X X O O O       \n" +
            "    X - X - X - O     \n" +
            "   X O O O O O O O     \n" +
            "  X - O - X - O - O   \n" +
            " X X O X X X X X X O   \n" +
            "X - O - O - O - O - O \n" +
            " X O X - O O O - - -   \n" +
            "  O - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n");
  }

  @Test
  public void gameJustStartedAvoidNextToCornersTest() throws IOException {
    Appendable emptyBuilder = new StringBuilder();
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC,
            6, new MachinePlayer(Name.X, new AvoidNextToCornersStrategy()),
            new MachinePlayer(Name.O, new AvoidNextToCornersStrategy()));
    ReversiView textView = new TextReversiView(basicModel, emptyBuilder);
    basicModel.startGame();
    CompleteReversiStrategyFromFallible avoidCorners =
            new CompleteReversiStrategyFromFallible(new AvoidNextToCornersStrategy());
    for (int i = 0; i < 23; i += 1) {
      LinearCoord bestMove = avoidCorners.chooseMove(basicModel,
              basicModel.nextToPlay());
      basicModel.placePiece(bestMove);
      textView.render();
    }
    Assert.assertEquals(emptyBuilder.toString(), "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - - - - - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - X X - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - O - - - -     \n" +
            "  - - - - O - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - O X - - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - O O O - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - - - -       \n" +
            "    - - X - - - -     \n" +
            "   - - - X O O - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - - - -       \n" +
            "    - - X - - - -     \n" +
            "   - - O O O O - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - - - -       \n" +
            "    - - X - X - -     \n" +
            "   - - O O X O - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - O - - -       \n" +
            "    - - O - X - -     \n" +
            "   - - O O X O - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - O - - -       \n" +
            "    - - O - X - -     \n" +
            "   - X X X X O - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - O O - -       \n" +
            "    - - O - O - -     \n" +
            "   - X X X X O - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - O O - -       \n" +
            "    - - O - O - -     \n" +
            "   - X X X X X X -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - O O - -       \n" +
            "    - - O - O - -     \n" +
            "   - X O X X X X -     \n" +
            "  - - O - X - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - O O - -       \n" +
            "    - - O - O - -     \n" +
            "   - X O X X X X -     \n" +
            "  - - X - X - - - -   \n" +
            " - - - X X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - O O - -       \n" +
            "    - - O - O - -     \n" +
            "   O O O X X X X -     \n" +
            "  - - X - X - - - -   \n" +
            " - - - X X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - O O - -       \n" +
            "    - - O - O - -     \n" +
            "   O O O X X X X -     \n" +
            "  - - X - X - - - -   \n" +
            " - - - X X X X - - -   \n" +
            "- - - - X - X - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - O O - -       \n" +
            "    - - O - O - -     \n" +
            "   O O O O O O O O     \n" +
            "  - - X - X - - - -   \n" +
            " - - - X X X X - - -   \n" +
            "- - - - X - X - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - O O - -       \n" +
            "    - - O - O - -     \n" +
            "   O O O O O O O O     \n" +
            "  - - X - X - - - -   \n" +
            " - - - X X X X - - -   \n" +
            "- - - - X - X - - - - \n" +
            " - - - X X X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - O O - -       \n" +
            "    - - O - O - -     \n" +
            "   O O O O O O O O     \n" +
            "  - - O - X - - - -   \n" +
            " - - O X X X X - - -   \n" +
            "- - - - X - X - - - - \n" +
            " - - - X X X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - O O - -       \n" +
            "    - - O - O - -     \n" +
            "   O O O O O O O O     \n" +
            "  - - O - X - - - -   \n" +
            " - X X X X X X - - -   \n" +
            "- - - - X - X - - - - \n" +
            " - - - X X X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - O O - -       \n" +
            "    - - O - O - -     \n" +
            "   O O O O O O O O     \n" +
            "  - - O - X - - - -   \n" +
            " - X O X X X X - - -   \n" +
            "- - O - X - X - - - - \n" +
            " - - - X X X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - O O - -       \n" +
            "    - - O - O - -     \n" +
            "   O O O O O O O O     \n" +
            "  - - O - X - - - -   \n" +
            " - X O X X X X - - -   \n" +
            "- - X - X - X - - - - \n" +
            " - - X X X X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - O O - -       \n" +
            "    - - O - O - -     \n" +
            "   O O O O O O O O     \n" +
            "  - - O - X - - - -   \n" +
            " - X O O O O O O - -   \n" +
            "- - X - X - X - - - - \n" +
            " - - X X X X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - O O - -       \n" +
            "    - - O - O - -     \n" +
            "   O O O O O O O O     \n" +
            "  - - O - X - X - -   \n" +
            " - X O O O O X O - -   \n" +
            "- - X - X - X - - - - \n" +
            " - - X X X X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n");
  }

  @Test
  public void gameJustStartedMiniMaxTest() throws IOException {
    Appendable emptyBuilder = new StringBuilder();
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC,
            6, new MachinePlayer(Name.X, new MiniMaxStrategy()),
            new MachinePlayer(Name.O, new MiniMaxStrategy()));
    ReversiView textView = new TextReversiView(basicModel, emptyBuilder);
    basicModel.startGame();
    CompleteReversiStrategyFromFallible miniMax =
            new CompleteReversiStrategyFromFallible(new MiniMaxStrategy());
    for (int i = 0; i < 23; i += 1) {
      LinearCoord bestMove = miniMax.chooseMove(basicModel,
              basicModel.nextToPlay());
      basicModel.placePiece(bestMove);
      textView.render();
    }
    Assert.assertEquals(emptyBuilder.toString(), "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - - - - - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - X X - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - O - - - -     \n" +
            "  - - - - O - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - O X - - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - O O O - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - - - -       \n" +
            "    - - - - X - -     \n" +
            "   - - - O X O - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - O - -       \n" +
            "    - - - - O - -     \n" +
            "   - - - O X O - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - O X -       \n" +
            "    - - - - X - -     \n" +
            "   - - - O X O - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - O O O       \n" +
            "    - - - - X - -     \n" +
            "   - - - O X O - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - O O O       \n" +
            "    - - - - X - -     \n" +
            "   - - - O X O - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - X X X - - -   \n" +
            "- - - - X - X - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - O O O       \n" +
            "    - - - - O - -     \n" +
            "   - - - O O O - -     \n" +
            "  - - - - O - - - -   \n" +
            " - - - - O X X - - -   \n" +
            "- - - - O - X - - - - \n" +
            " - - - O O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - O O O       \n" +
            "    - - X - O - -     \n" +
            "   - - - X O O - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - O X X - - -   \n" +
            "- - - - O - X - - - - \n" +
            " - - - O O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - O O O       \n" +
            "    - - X - O - -     \n" +
            "   - - - X O O - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - O O O O - -   \n" +
            "- - - - O - X - - - - \n" +
            " - - - O O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - O O O       \n" +
            "    - - X - O - -     \n" +
            "   - - - X X X X -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - O O O O - -   \n" +
            "- - - - O - X - - - - \n" +
            " - - - O O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - O O O       \n" +
            "    - - O - O - -     \n" +
            "   - - - O X X X -     \n" +
            "  - - - - O - - - -   \n" +
            " - - - - O O O O - -   \n" +
            "- - - - O - X - - - - \n" +
            " - - - O O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - O O O       \n" +
            "    - - O - O - -     \n" +
            "   - - X X X X X -     \n" +
            "  - - - - O - - - -   \n" +
            " - - - - O O O O - -   \n" +
            "- - - - O - X - - - - \n" +
            " - - - O O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - O O O       \n" +
            "    - - O - O - -     \n" +
            "   - - X X X X X -     \n" +
            "  - - - - O - - - -   \n" +
            " - - - - O O O O - -   \n" +
            "- - - - O - O - - - - \n" +
            " - - - O O O O - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O X O O O       \n" +
            "    - - X - O - -     \n" +
            "   - - X X X X X -     \n" +
            "  - - - - O - - - -   \n" +
            " - - - - O O O O - -   \n" +
            "- - - - O - O - - - - \n" +
            " - - - O O O O - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O X O O O       \n" +
            "    - - X - O - -     \n" +
            "   - - X X X O X -     \n" +
            "  - - - - O - O - -   \n" +
            " - - - - O O O O - -   \n" +
            "- - - - O - O - - - - \n" +
            " - - - O O O O - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O X O O O       \n" +
            "    - - X - O - -     \n" +
            "   - - X X X O X -     \n" +
            "  - - - - O - X - -   \n" +
            " - - - - O O X O - -   \n" +
            "- - - - O - X - - - - \n" +
            " - - - O O X O - - -   \n" +
            "  - - - - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O X O O O       \n" +
            "    - - X - O - -     \n" +
            "   - O O O O O X -     \n" +
            "  - - - - O - X - -   \n" +
            " - - - - O O X O - -   \n" +
            "- - - - O - X - - - - \n" +
            " - - - O O X O - - -   \n" +
            "  - - - - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O X O O O       \n" +
            "    - - X - O - -     \n" +
            "   X X X X X X X -     \n" +
            "  - - - - O - X - -   \n" +
            " - - - - O O X O - -   \n" +
            "- - - - O - X - - - - \n" +
            " - - - O O X O - - -   \n" +
            "  - - - - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O X O O O       \n" +
            "    - - X - O - -     \n" +
            "   X X X X X X X -     \n" +
            "  - - - - O - X - -   \n" +
            " - - - - O O X O - -   \n" +
            "- - - - O - X - - - - \n" +
            " - - - O O X O - - -   \n" +
            "  - - - - O - - - -   \n" +
            "   - - - - O - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O X O O O       \n" +
            "    - - X - O - -     \n" +
            "   X X X X X X X -     \n" +
            "  - - - - O - X - -   \n" +
            " - - - - O O X O - -   \n" +
            "- - - - O - X - - - - \n" +
            " - - - O O X O - - -   \n" +
            "  - - - - X - - - -   \n" +
            "   - - - X O - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n");
  }

  @Test
  public void superStrategyVsCaptureMaxGame() throws IOException {
    Appendable emptyBuilder = new StringBuilder();
    Player superPlayer = new MachinePlayer(Name.X, new TryTwo(new CaptureCornersStrategy(),
            new TryTwo(new AvoidNextToCornersStrategy(),
                    new TryTwo(new MiniMaxStrategy(),
                            new CaptureMaxStrategy()))));
    Player captureMaxPlayer = new MachinePlayer(Name.O, new CaptureMaxStrategy());
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC,
            6, superPlayer, captureMaxPlayer);
    ReversiView textView = new TextReversiView(basicModel, emptyBuilder);
    basicModel.startGame();
    CompleteReversiStrategyFromFallible captureMax =
            new CompleteReversiStrategyFromFallible(new CaptureMaxStrategy());
    CompleteReversiStrategyFromFallible superStrat =
            new CompleteReversiStrategyFromFallible(new TryTwo(new CaptureCornersStrategy(),
                    new TryTwo(new AvoidNextToCornersStrategy(),
                            new TryTwo(new MiniMaxStrategy(),
                                    new CaptureMaxStrategy()))));
    for (int i = 0; i < 100; i += 1) {
      try {
        LinearCoord bestMove;
        if (basicModel.nextToPlay().equals(superPlayer)) {
          bestMove = superStrat.chooseMove(basicModel,
                  basicModel.nextToPlay());
        } else {
          bestMove = captureMax.chooseMove(basicModel,
                  basicModel.nextToPlay());
        }
        basicModel.placePiece(bestMove);
        textView.render();
      } catch (Exception pass) {
        try {
          basicModel.pass();
        } catch (Exception gameover) {
          break;
        }
      }
    }
    //textView.render();
    Assert.assertEquals(basicModel.getWinner(), superPlayer);
    Assert.assertEquals("     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - - - - - -     \n" +
            "  - - - - X - - - -   \n" +
            " - - - - X X - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - O - - - -     \n" +
            "  - - - - O - - - -   \n" +
            " - - - - X O - - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - O - - - -     \n" +
            "  - - - - O - - - -   \n" +
            " - - - - X X X - - -   \n" +
            "- - - - X - X - - - - \n" +
            " - - - - O X - - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - O - - - -     \n" +
            "  - - - - O - - - -   \n" +
            " - - - - X O X - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - - O O O - - -   \n" +
            "  - - - - - - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - O - - - -     \n" +
            "  - - - - O - - - -   \n" +
            " - - - - X O X - - -   \n" +
            "- - - - X - X - - - - \n" +
            " - - - - X X O - - -   \n" +
            "  - - - - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - O - - - -     \n" +
            "  - - - - O - - - -   \n" +
            " - - - - O O X - - -   \n" +
            "- - - - O - X - - - - \n" +
            " - - - O O O O - - -   \n" +
            "  - - - - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - O - - - -     \n" +
            "  - - - - O - - - -   \n" +
            " - - - X X X X - - -   \n" +
            "- - - - X - X - - - - \n" +
            " - - - O X O O - - -   \n" +
            "  - - - - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - O - - - -     \n" +
            "  - - - - O - O - -   \n" +
            " - - - X X X O - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - O X O O - - -   \n" +
            "  - - - - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - - - -       \n" +
            "    - - - - - - -     \n" +
            "   - - - O - - X -     \n" +
            "  - - - - O - X - -   \n" +
            " - - - X X X X - - -   \n" +
            "- - - - X - X - - - - \n" +
            " - - - O X X O - - -   \n" +
            "  - - - - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - - - - - -       \n" +
            "    - - X - - - -     \n" +
            "   - - - X - - X -     \n" +
            "  - - - - X - X - -   \n" +
            " - - - X X X X - - -   \n" +
            "- - - - X - X - - - - \n" +
            " - - - O X X O - - -   \n" +
            "  - - - - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - - - -       \n" +
            "    - - O - - - -     \n" +
            "   - - - O - - X -     \n" +
            "  - - - - O - X - -   \n" +
            " - - - X X O X - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - O X X O - - -   \n" +
            "  - - - - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - - - -       \n" +
            "    - - O - - - -     \n" +
            "   - - - O X - X -     \n" +
            "  - - - - X - X - -   \n" +
            " - - - X X O X - - -   \n" +
            "- - - - X - O - - - - \n" +
            " - - - O X X O - - -   \n" +
            "  - - - - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - - - -       \n" +
            "    - - O - O - -     \n" +
            "   - - - O O - X -     \n" +
            "  - - - - O - X - -   \n" +
            " - - - X O O X - - -   \n" +
            "- - - - O - O - - - - \n" +
            " - - - O X X O - - -   \n" +
            "  - - - - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - - - -       \n" +
            "    - - O - O - -     \n" +
            "   - - - O O - X -     \n" +
            "  - - - - O - X - -   \n" +
            " - - - X O O X - - -   \n" +
            "- - - - O - O - - - - \n" +
            " - - X X X X O - - -   \n" +
            "  - - - - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - - - -       \n" +
            "    - - O - O - -     \n" +
            "   - - - O O - X -     \n" +
            "  - - - - O - X - -   \n" +
            " - - - X O O X - - -   \n" +
            "- - - - O - O - - - - \n" +
            " - O O O O O O - - -   \n" +
            "  - - - - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - - - -       \n" +
            "    - - O - O - O     \n" +
            "   - - - O O - O -     \n" +
            "  - - - - O - O - -   \n" +
            " - - - X O O O - - -   \n" +
            "- - - - O - O - - - - \n" +
            " - O O O O O O - - -   \n" +
            "  - - - - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - - - -       \n" +
            "    - - O - O - O     \n" +
            "   - - - O O - O -     \n" +
            "  - - - - O - O - -   \n" +
            " - - - X X X X X - -   \n" +
            "- - - - O - O - - - - \n" +
            " - O O O O O O - - -   \n" +
            "  - - - - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - - - -       \n" +
            "    - - O - O - O     \n" +
            "   - - - O O - O -     \n" +
            "  - - O - O - O - -   \n" +
            " - - - O X X X X - -   \n" +
            "- - - - O - O - - - - \n" +
            " - O O O O O O - - -   \n" +
            "  - - - - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - - - -       \n" +
            "    - - O - O - O     \n" +
            "   - X - O O - O -     \n" +
            "  - - X - O - O - -   \n" +
            " - - - X X X X X - -   \n" +
            "- - - - X - O - - - - \n" +
            " - O O O X O O - - -   \n" +
            "  - - - - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - - - -       \n" +
            "    - - O - O - O     \n" +
            "   - X - O O - O -     \n" +
            "  - - X - O - O - -   \n" +
            " - - - X X X X O - -   \n" +
            "- - - - X - O - O - - \n" +
            " - O O O X O O - - -   \n" +
            "  - - - - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - - - -       \n" +
            "    - - O - O - O     \n" +
            "   - X - O O - O -     \n" +
            "  - - X - O - O - -   \n" +
            " - - - X X X X X X -   \n" +
            "- - - - X - O - O - - \n" +
            " - O O O X O O - - -   \n" +
            "  - - - - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - - - -       \n" +
            "    - - O - O - O     \n" +
            "   - X - O O - O -     \n" +
            "  - - X - O - O - O   \n" +
            " - - - X X X X X O -   \n" +
            "- - - - X - O - O - - \n" +
            " - O O O X O O - - -   \n" +
            "  - - - - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - - - -       \n" +
            "    - - O - O - O     \n" +
            "   - X - O O - O -     \n" +
            "  - - X - O - O - O   \n" +
            " - - - X X X X X O -   \n" +
            "- - - - X - O - O - - \n" +
            " - O O O X X X X - -   \n" +
            "  - - - - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - - - -       \n" +
            "    - - O - O - O     \n" +
            "   - X - O O - O -     \n" +
            "  - - X - O - O - O   \n" +
            " - - O O O O O O O -   \n" +
            "- - - - X - O - O - - \n" +
            " - O O O X X X X - -   \n" +
            "  - - - - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - - - -       \n" +
            "    - - O - O - O     \n" +
            "   - X - O O - O -     \n" +
            "  - - X - O - O - O   \n" +
            " - - X O O O O O O -   \n" +
            "- - X - X - O - O - - \n" +
            " - O O O X X X X - -   \n" +
            "  - - - - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - - - -       \n" +
            "    - - O - O - O     \n" +
            "   - X - O O - O -     \n" +
            "  - - X - O - O - O   \n" +
            " - - X O O O O O O -   \n" +
            "- - X - X - O - O - - \n" +
            " - O O O O O O O O -   \n" +
            "  - - - - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - - - -       \n" +
            "    - - O - O - O     \n" +
            "   - X - O O - O -     \n" +
            "  - - X - O - O - O   \n" +
            " - - X O O O O O O -   \n" +
            "- - X - X - O - O - - \n" +
            " - O X X O O O O O -   \n" +
            "  - - X - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - - - -       \n" +
            "    - - O - O - O     \n" +
            "   - X O O O - O -     \n" +
            "  - - O - O - O - O   \n" +
            " - - O O O O O O O -   \n" +
            "- - O - X - O - O - - \n" +
            " - O X X O O O O O -   \n" +
            "  - - X - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - - - -       \n" +
            "    - - O - O - O     \n" +
            "   - X X X X X O -     \n" +
            "  - - O - O - O - O   \n" +
            " - - O O O O O O O -   \n" +
            "- - O - X - O - O - - \n" +
            " - O X X O O O O O -   \n" +
            "  - - X - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - - - -       \n" +
            "    - - O - O - O     \n" +
            "   O O O O O O O -     \n" +
            "  - - O - O - O - O   \n" +
            " - - O O O O O O O -   \n" +
            "- - O - X - O - O - - \n" +
            " - O X X O O O O O -   \n" +
            "  - - X - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - - - -       \n" +
            "    - - O - O - O     \n" +
            "   O O O O O O O -     \n" +
            "  - - O - O - O - O   \n" +
            " - X O O O O O O O -   \n" +
            "- - X - X - O - O - - \n" +
            " - O X X O O O O O -   \n" +
            "  - - X - X - - - -   \n" +
            "   - - - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O - - - -       \n" +
            "    - - O - O - O     \n" +
            "   O O O O O O O -     \n" +
            "  - - O - O - O - O   \n" +
            " - X O O O O O O O -   \n" +
            "- - X - O - O - O - - \n" +
            " - O X O O O O O O -   \n" +
            "  - - O - X - - - -   \n" +
            "   - O - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O X - - -       \n" +
            "    - - X - O - O     \n" +
            "   O O X O O O O -     \n" +
            "  - - X - O - O - O   \n" +
            " - X X O O O O O O -   \n" +
            "- - X - O - O - O - - \n" +
            " - O X O O O O O O -   \n" +
            "  - - O - X - - - -   \n" +
            "   - O - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     - O X - - -       \n" +
            "    - - X - O - O     \n" +
            "   O O X O O O O -     \n" +
            "  O - X - O - O - O   \n" +
            " - O X O O O O O O -   \n" +
            "- - O - O - O - O - - \n" +
            " - O O O O O O O O -   \n" +
            "  - - O - X - - - -   \n" +
            "   - O - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     X X X - - -       \n" +
            "    - - X - O - O     \n" +
            "   O O X O O O O -     \n" +
            "  O - X - O - O - O   \n" +
            " - O X O O O O O O -   \n" +
            "- - O - O - O - O - - \n" +
            " - O O O O O O O O -   \n" +
            "  - - O - X - - - -   \n" +
            "   - O - - - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     X X X - - -       \n" +
            "    - - X - O - O     \n" +
            "   O O X O O O O -     \n" +
            "  O - X - O - O - O   \n" +
            " - O X O O O O O O -   \n" +
            "- - O - O - O - O - - \n" +
            " - O O O O O O O O -   \n" +
            "  - - O - O - - - -   \n" +
            "   - O - O - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     X X X - - -       \n" +
            "    - - X - O - O     \n" +
            "   O O X X X X X X     \n" +
            "  O - X - O - O - O   \n" +
            " - O X O O O O O O -   \n" +
            "- - O - O - O - O - - \n" +
            " - O O O O O O O O -   \n" +
            "  - - O - O - - - -   \n" +
            "   - O - O - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     X X X - - X       \n" +
            "    - - X - O - X     \n" +
            "   O O X X X X X X     \n" +
            "  O - X - O - O - O   \n" +
            " - O X O O O O O O -   \n" +
            "- - O - O - O - O - - \n" +
            " - O O O O O O O O -   \n" +
            "  - - O - O - - - -   \n" +
            "   - O - O - - - -     \n" +
            "    - - - - - - -     \n" +
            "     - - - - - -       \n" +
            "     X X X - - X       \n" +
            "    - - X - O - X     \n" +
            "   O O X X X X X X     \n" +
            "  O - X - O - X - O   \n" +
            " - O X O O O X O O -   \n" +
            "- - O - O - X - O - - \n" +
            " - O O O O X O O O -   \n" +
            "  - - O - X - - - -   \n" +
            "   - O - X - - - -     \n" +
            "    - - X - - - -     \n" +
            "     - - - - - -       \n" +
            "     X X X - - X       \n" +
            "    - - X - O - X     \n" +
            "   O O X X X X X X     \n" +
            "  O - X - O - X - O   \n" +
            " - O X O O O X O O -   \n" +
            "- - O - O - X - O - - \n" +
            " - O O O O X O O O -   \n" +
            "  - - O - O - - - -   \n" +
            "   - O - X O - - -     \n" +
            "    - - X - - - -     \n" +
            "     - - - - - -       \n" +
            "     X X X - - X       \n" +
            "    - - X - O - X     \n" +
            "   O O X X X X X X     \n" +
            "  O - X - O - X - O   \n" +
            " - O X O O O X X O -   \n" +
            "- - O - O - X - X - - \n" +
            " - O O O O X O O X -   \n" +
            "  - - O - O - - - X   \n" +
            "   - O - X O - - -     \n" +
            "    - - X - - - -     \n" +
            "     - - - - - -       \n" +
            "     X X X - - X       \n" +
            "    - - X - O - X     \n" +
            "   O O X X X X X X     \n" +
            "  O - X - O - X - O   \n" +
            " - O X O O O X X O -   \n" +
            "- - O - O - X - X - - \n" +
            " - O O O O X O O X -   \n" +
            "  - - O - O - - - X   \n" +
            "   - O - O O - - -     \n" +
            "    - - O - - - -     \n" +
            "     - O - - - -       \n" +
            "     X X X - - X       \n" +
            "    - - X - O - X     \n" +
            "   O O X X X X X X     \n" +
            "  O - X - O - X - O   \n" +
            " - O X O O O X X O -   \n" +
            "- - X - O - X - X - - \n" +
            " - X O O O X O O X -   \n" +
            "  X - O - O - - - X   \n" +
            "   - O - O O - - -     \n" +
            "    - - O - - - -     \n" +
            "     - O - - - -       \n" +
            "     X X X - - X       \n" +
            "    - - X - O - X     \n" +
            "   O O X X X X X X     \n" +
            "  O - X - O - X - O   \n" +
            " - O X O O O X X O -   \n" +
            "- - X - O - X - X - - \n" +
            " O O O O O X O O X -   \n" +
            "  X - O - O - - - X   \n" +
            "   - O - O O - - -     \n" +
            "    - - O - - - -     \n" +
            "     - O - - - -       \n" +
            "     X X X - - X       \n" +
            "    - - X - O - X     \n" +
            "   O O X X X X X X     \n" +
            "  O - X - O - X - O   \n" +
            " - O X O O O X X O -   \n" +
            "X - X - O - X - X - - \n" +
            " X O O O O X O O X -   \n" +
            "  X - O - O - - - X   \n" +
            "   - O - O O - - -     \n" +
            "    - - O - - - -     \n" +
            "     - O - - - -       \n" +
            "     X X X - - X       \n" +
            "    - - X - O - X     \n" +
            "   O O X X X X X X     \n" +
            "  O - X - O - X - O   \n" +
            " - O X O O O X X O -   \n" +
            "X - X - O - X - X - - \n" +
            " X O O O O X O O O O   \n" +
            "  X - O - O - - - X   \n" +
            "   - O - O O - - -     \n" +
            "    - - O - - - -     \n" +
            "     - O - - - -       \n" +
            "     X X X - - X       \n" +
            "    - - X - O - X     \n" +
            "   O O X X X X X X     \n" +
            "  O - X - O - X - O   \n" +
            " - O X O O O X X O -   \n" +
            "X - X - O - X - X - X \n" +
            " X O O O O X O O O X   \n" +
            "  X - O - O - - - X   \n" +
            "   - O - O O - - -     \n" +
            "    - - O - - - -     \n" +
            "     - O - - - -       \n" +
            "     X X X - - X       \n" +
            "    - - X - O - X     \n" +
            "   O O X X X X X X     \n" +
            "  O - X - O - X - O   \n" +
            " - O X O O O X X O -   \n" +
            "X - X - O - X - X - X \n" +
            " X O X O O X O O O X   \n" +
            "  X - X - O - - - X   \n" +
            "   - O X O O - - -     \n" +
            "    - - O - - - -     \n" +
            "     - O - - - -       \n" +
            "     X X X - - X       \n" +
            "    - - X - O - X     \n" +
            "   O O X X X X X X     \n" +
            "  O - X - O - X - O   \n" +
            " - O X O O O X X O -   \n" +
            "X - X - O - X - X - X \n" +
            " X O X O O X O O O X   \n" +
            "  X - X - O - - - X   \n" +
            "   - O X O O - - -     \n" +
            "    - - X - - - -     \n" +
            "     - O X - - -       \n" +
            "     X X X - - X       \n" +
            "    - - X - O - X     \n" +
            "   O O X X X X X X     \n" +
            "  O - X - O - X - O   \n" +
            " - O X O O O X X O -   \n" +
            "X - X - O - X - X - X \n" +
            " X O X O O X O O O X   \n" +
            "  X - X - O - - - X   \n" +
            "   - O X O O - - -     \n" +
            "    - - X - - - -     \n" +
            "     - O O O - -       \n" +
            "     X X X - - X       \n" +
            "    - - X - O - X     \n" +
            "   O O X X X X X X     \n" +
            "  O - X - O - X - O   \n" +
            " - O X O O O X X O -   \n" +
            "X - X - O - X - X - X \n" +
            " X O X O O X X X O X   \n" +
            "  X - X - O - X - X   \n" +
            "   - O X O O - - -     \n" +
            "    - - X - - - -     \n" +
            "     - O O O - -       \n" +
            "     X X X - - X       \n" +
            "    - - X - O - X     \n" +
            "   O O X X X X X X     \n" +
            "  O - X - O - X - O   \n" +
            " - O X O O O X X O -   \n" +
            "X - X - O - X - O - X \n" +
            " X O X O O X X O O X   \n" +
            "  X - X - O - O - X   \n" +
            "   - O X O O O - -     \n" +
            "    - - X - - - -     \n" +
            "     - O O O - -       \n" +
            "     X X X - - X       \n" +
            "    - - X - O - X     \n" +
            "   O O X X X X X X     \n" +
            "  O - X - O - X - O   \n" +
            " - O X O O O X X O -   \n" +
            "X - X - O - X - O - X \n" +
            " X O X O O X X O O X   \n" +
            "  X - X - O - X - X   \n" +
            "   - O X X X X X -     \n" +
            "    - - X - - - -     \n" +
            "     - O O O - -       \n" +
            "     X X X - - X       \n" +
            "    - - X - O - X     \n" +
            "   O O X X X X X X     \n" +
            "  O - X - O - X - O   \n" +
            " - O X O O O X X O -   \n" +
            "X - X - O - X - O - X \n" +
            " X O X O O X X O O X   \n" +
            "  X - X - O - X - X   \n" +
            "   - O O O O O O O     \n" +
            "    - - X - - - -     \n" +
            "     - O O O - -       \n" +
            "     X X X - - X       \n" +
            "    - - X - O - X     \n" +
            "   O O X X X X X X     \n" +
            "  O - X - O - X - O   \n" +
            " - O X X O O X X O -   \n" +
            "X - X - X - X - O - X \n" +
            " X O X O X X X O O X   \n" +
            "  X - X - X - X - X   \n" +
            "   - O O O X X O O     \n" +
            "    - - X - X - -     \n" +
            "     - O O O - -       \n" +
            "     X X X - - X       \n" +
            "    - - X - O - X     \n" +
            "   O O X X X X X X     \n" +
            "  O - O - O - X - O   \n" +
            " - O X O O O X X O -   \n" +
            "X - X - O - X - O - X \n" +
            " X O X O O X X O O X   \n" +
            "  X - X - O - X - X   \n" +
            "   - O O O O X O O     \n" +
            "    - - X - O - -     \n" +
            "     - O O O O -       \n" +
            "     X X X - - X       \n" +
            "    - - X - O - X     \n" +
            "   O O X X X X X X     \n" +
            "  O - O - O - X - O   \n" +
            " - O X O O O X X O -   \n" +
            "X - X - O - X - O - X \n" +
            " X O X O O X X O O X   \n" +
            "  X - X - O - X - X   \n" +
            "   X X X X X X O O     \n" +
            "    - - X - O - -     \n" +
            "     - O O O O -       \n" +
            "     X X X - - X       \n" +
            "    - - X - O - X     \n" +
            "   O O X X X X X X     \n" +
            "  O - O - O - X - O   \n" +
            " - O X O O O X X O -   \n" +
            "X - X - O - X - O - X \n" +
            " X O X O O X X O O X   \n" +
            "  X - O - O - X - X   \n" +
            "   X O X X X X O O     \n" +
            "    O - X - O - -     \n" +
            "     - O O O O -       \n" +
            "     X X X - - X       \n" +
            "    - - X - O - X     \n" +
            "   O O X X X X X X     \n" +
            "  O - O - O - X - O   \n" +
            " - O X O O O X X O -   \n" +
            "X - X - O - X - O - X \n" +
            " X O X O O X X O O X   \n" +
            "  X - O - O - X - X   \n" +
            "   X O X X X X O O     \n" +
            "    X - X - O - -     \n" +
            "     X O O O O -       \n" +
            "     X X X - - X       \n" +
            "    - - X - O - X     \n" +
            "   O O X X X X X X     \n" +
            "  O - O - O - X - O   \n" +
            " - O X O O O X X O -   \n" +
            "X - X - O - X - O - X \n" +
            " X O X O O X X O O X   \n" +
            "  X - O - O - X - X   \n" +
            "   X O X X X X O O     \n" +
            "    X - X - O - -     \n" +
            "     X X X X X X       \n" +
            "     X X X X - X       \n" +
            "    - - X - X - X     \n" +
            "   O O X X X X X X     \n" +
            "  O - O - O - X - O   \n" +
            " - O X O O O X X O -   \n" +
            "X - X - O - X - O - X \n" +
            " X O X O O X X O O X   \n" +
            "  X - O - O - X - X   \n" +
            "   X O X X X X O O     \n" +
            "    X - X - O - -     \n" +
            "     X X X X X X       \n" +
            "     X X X X O X       \n" +
            "    - - X - O - X     \n" +
            "   O O X X O X X X     \n" +
            "  O - O - O - X - O   \n" +
            " - O X O O O X X O -   \n" +
            "X - X - O - X - O - X \n" +
            " X O X O O X X O O X   \n" +
            "  X - O - O - X - X   \n" +
            "   X O X X X X O O     \n" +
            "    X - X - O - -     \n" +
            "     X X X X X X       \n" +
            "     X X X X O X       \n" +
            "    X - X - O - X     \n" +
            "   O X X X O X X X     \n" +
            "  O - X - O - X - O   \n" +
            " - O X X O O X X O -   \n" +
            "X - X - X - X - O - X \n" +
            " X O X O X X X O O X   \n" +
            "  X - O - X - X - X   \n" +
            "   X O X X X X O O     \n" +
            "    X - X - O - -     \n" +
            "     X X X X X X       \n" +
            "     X X X X O X       \n" +
            "    X - X - O - X     \n" +
            "   X X X X O X X X     \n" +
            "  X - X - O - X - O   \n" +
            " X X X X O O X X O -   \n" +
            "X - X - X - X - O - X \n" +
            " X O X O X X X O O X   \n" +
            "  X - O - X - X - X   \n" +
            "   X O X X X X O O     \n" +
            "    X - X - O - -     \n" +
            "     X X X X X X       \n" +
            "     X X X X O X       \n" +
            "    X - X - O - X     \n" +
            "   X X X X O X X X     \n" +
            "  X - X - O - X - X   \n" +
            " X X X X O O X X X X   \n" +
            "X - X - X - X - O - X \n" +
            " X O X O X X X O O X   \n" +
            "  X - O - X - X - X   \n" +
            "   X O X X X X O O     \n" +
            "    X - X - O - -     \n" +
            "     X X X X X X       \n" +
            "     X X X X O X       \n" +
            "    X - X - O - X     \n" +
            "   X X X X O X X X     \n" +
            "  X - X - O - X - X   \n" +
            " X X X X O O X X X X   \n" +
            "X - X - X - X - O - X \n" +
            " X O X O X X X O O X   \n" +
            "  X - O - X - X - X   \n" +
            "   X O X X X X X X     \n" +
            "    X - X - O - X     \n" +
            "     X X X X X X       \n", emptyBuilder.toString());
  }
}
