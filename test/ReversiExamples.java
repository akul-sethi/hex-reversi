import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import cs3500.reversi.model.GameType;
import cs3500.reversi.model.ReversiCreator;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.view.ReversiView;
import cs3500.reversi.view.TextReversiView;

public class ReversiExamples {

  @Test
  public void basicGameTestDimensions() throws IOException {
    ReversiModel basicModel = ReversiCreator.create(GameType.BASIC, 6);;
    int[] colExs = new int[]{-5, 5};
    Assert.assertEquals(-5, basicModel.getLeftCol());
    Assert.assertEquals(5, basicModel.getRightCol());
    Assert.assertEquals(-5, basicModel.getBottomRow());
    Assert.assertEquals(5, basicModel.getTopRow());
  }
}
