import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import cs3500.reversi.controller.BasicReversiController;
import cs3500.reversi.controller.ReversiController;
import cs3500.reversi.model.BasicPoint;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.HumanPlayer;
import cs3500.reversi.player.Name;

/**
 * Tests the controller to make sure that it makes the correct requests to the model and view.
 */
public class ControllerTests {
  private MockModelLogging model;
  private MockViewLogging view;
  private MockPlayerLogging player;
  private ReversiController controller;

  @Before
  public void init() {
    this.model = new MockModelLogging();
    this.view = new MockViewLogging();
    this.player = new MockPlayerLogging(Name.X);
    this.controller = new BasicReversiController(player, view, model);
  }

  @Test
  public void testGiveControlToDoesNotControlPlayer() {
    controller.giveControlTo(new HumanPlayer(Name.O));
    Assert.assertEquals(model.log, "addObserver()\n");
    Assert.assertEquals(view.log, "addObserver()\nsetVisible(true)\nrender()\n");
    Assert.assertEquals(player.log, "addObserver()\n");
  }

  @Test
  public void testGiveControlToDoesControlPlayer() {
    controller.giveControlTo(new HumanPlayer(Name.X));
    Assert.assertEquals(model.log, "addObserver()\n");
    Assert.assertEquals(view.log, "addObserver()\nsetVisible(true)\nrender()\n");
    Assert.assertEquals(player.log, "addObserver()\nstartTurn()\n");
  }

  @Test
  public void testMoveHereHasControl() {
    controller.giveControlTo(new HumanPlayer(Name.X));
    controller.moveHere(new BasicPoint(1, 1));
    Assert.assertEquals(model.log, "addObserver()\nplacePiece(1, 1)\n");
    Assert.assertEquals(view.log, "addObserver()\nsetVisible(true)\nrender()\n");
    Assert.assertEquals(player.log, "addObserver()\nstartTurn()\n");
  }

  @Test
  public void testMoveHereNoControl() {
    controller.giveControlTo(new HumanPlayer(Name.O));
    controller.moveHere(new BasicPoint(1, 1));
    Assert.assertEquals(model.log, "addObserver()\n");
    Assert.assertEquals(view.log, "addObserver()\n" +
            "setVisible(true)\n" +
            "render()\n" +
            "alertMessage(It is not your turn)\n");
    Assert.assertEquals(player.log, "addObserver()\n");
  }

  @Test
  public void testPassNoControl() {
    controller.giveControlTo(new HumanPlayer(Name.O));
    controller.pass();
    Assert.assertEquals(model.log, "addObserver()\n");
    Assert.assertEquals(view.log, "addObserver()\n" +
            "setVisible(true)\n" +
            "render()\n" +
            "alertMessage(It is not your turn)\n");
    Assert.assertEquals(player.log, "addObserver()\n");
  }

  @Test
  public void testPassHasControl() {
    controller.giveControlTo(new HumanPlayer(Name.X));
    controller.pass();
    Assert.assertEquals(model.log, "addObserver()\npass()\n");
    Assert.assertEquals(view.log, "addObserver()\nsetVisible(true)\nrender()\n");
    Assert.assertEquals(player.log, "addObserver()\nstartTurn()\n");
  }

  @Test
  public void testMoveThrowsError() {
    ReversiModel errorThrowingModel = new FakeDataThrowingMockModel();
    MockPlayerLogging player = new MockPlayerLogging(Name.X);
    MockViewLogging view = new MockViewLogging();

    ReversiController controller = new BasicReversiController(player, view, errorThrowingModel);

    controller.giveControlTo(new HumanPlayer(Name.X));
    controller.moveHere(new BasicPoint(0, 0));
    Assert.assertEquals(view.log, "addObserver()\nsetVisible(true)\n" +
            "render()\nalertMessage(I ALWAYS DO THIS)\n");
    Assert.assertEquals(player.log, "addObserver()\nstartTurn()\n");
  }

  @Test
  public void testGameOver() {
    FakeDataThrowingMockModel fakeDataModel = new FakeDataThrowingMockModel();
    MockViewLogging view = new MockViewLogging();
    MockPlayerLogging player = new MockPlayerLogging(Name.X);
    ReversiController controller = new BasicReversiController(player, view, fakeDataModel);
    controller.gameOver();
    Assert.assertEquals(view.log, "addObserver()\nsetVisible(true)\n" +
            "alertMessage(Player X won! Score is X: 5 O: 1)\n");
    Assert.assertEquals(player.log, "addObserver()\n");
  }


}
