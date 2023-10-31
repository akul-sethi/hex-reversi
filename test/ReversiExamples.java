import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import cs3500.reversi.model.BasicReversi;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.view.ReversiView;
import cs3500.reversi.view.TextReversiView;

public class ReversiExamples {

  @Test
  public void basicGameTestDimensions() throws IOException {
    ReversiModel basicModel = new BasicReversi(6);
    int[] colExs = new int[]{-5, 5};
    Assert.assertEquals(-5, basicModel.getColExtremes()[0]);
    Assert.assertEquals(5, basicModel.getColExtremes()[1]);
    Assert.assertEquals(-5, basicModel.getRowExtremes()[0]);
    Assert.assertEquals(5, basicModel.getRowExtremes()[1]);
  }
}
