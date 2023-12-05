import org.junit.Assert;
import org.junit.Test;

import cs3500.reversi.model.GameType;
import cs3500.reversi.model.ReversiCreator;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.view.GUIReversiView;
import cs3500.reversi.view.ReversiView;

/**
 * A class for testing GUI views.
 */
public class GUIViewTests {

  @Test
  public void testNullModel() {
    Assert.assertThrows(NullPointerException.class, () -> {
      ReversiView view = new GUIReversiView(null);
    });
  }

  @Test
  public void testNullObserver() {
    Assert.assertThrows(NullPointerException.class, () -> {
      ReversiModel model = ReversiCreator.create(GameType.BASIC, 6);
      ReversiView view = new GUIReversiView(model);
      view.addObserver(null);
    });
  }


}
