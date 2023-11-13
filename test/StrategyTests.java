import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import cs3500.reversi.model.GameType;
import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReversiCreator;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.CaptureMaxPlayer;
import cs3500.reversi.view.ReversiView;
import cs3500.reversi.view.TextReversiView;

public class StrategyTests {

  @Test
  public void gameJustStartedCaptureMaxTest() throws IOException {
    Appendable emptyBuilder = new StringBuilder();
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC,
            6, new CaptureMaxPlayer("X"), new CaptureMaxPlayer("O"));
    ReversiView textView = new TextReversiView(basicModel, emptyBuilder);
    for (int i = 0; i < 20; i += 1) {
      LinearCoord bestMove = basicModel.nextToPlay().getMove(basicModel);
      Assert.assertTrue(basicModel.validMove(bestMove.row, bestMove.col));
      basicModel.placePiece(bestMove.row, bestMove.col);
      textView.render();
    }
    Assert.assertEquals(emptyBuilder.toString(), "");
  }
}
