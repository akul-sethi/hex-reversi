import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.locks.ReentrantReadWriteLock;

import cs3500.reversi.controller.ControllerWithLog;
import cs3500.reversi.model.GameType;
import cs3500.reversi.model.ReversiCreator;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.CaptureMaxPlayer;
import cs3500.reversi.player.HumanPlayer;
import cs3500.reversi.player.MachinePlayer;
import cs3500.reversi.player.Name;
import cs3500.reversi.player.Player;
import cs3500.reversi.strategy.CaptureMaxStrategy;

/**
 * Represents tests for the player classes.
 */
public class PlayerTests {
  @Test (expected = AssertionError.class)
  public void testPlayerConstructorNullName() {
    Player myPlayer = new HumanPlayer(null);
    Assert.assertEquals(0, 1 - 1);
  }

  @Test
  public void testPlayerEqualsValid() {
    Player myPlayer = new HumanPlayer(Name.X);
    Player myPlayer2 = new HumanPlayer(Name.X);
    Assert.assertEquals(myPlayer, myPlayer2);
  }

  @Test
  public void testPlayerEqualsInvalid() {
    Player myPlayer = new HumanPlayer(Name.O);
    Player myPlayer2 = new HumanPlayer(Name.X);
    Assert.assertNotEquals(myPlayer, myPlayer2);
  }

  @Test
  public void testPlayerHashValid() {
    Player myPlayer = new HumanPlayer(Name.X);
    Player myPlayer2 = new HumanPlayer(Name.X);
    Assert.assertEquals(myPlayer.hashCode(), myPlayer2.hashCode());
  }

  @Test
  public void testPlayerHashInvalid() {
    Player myPlayer = new HumanPlayer(Name.O);
    Player myPlayer2 = new HumanPlayer(Name.X);
    Assert.assertNotEquals(myPlayer.hashCode(), myPlayer2.hashCode());
  }

  @Test
  public void testMachinePlayerNotifyObserver(){
    ReversiModel model = ReversiCreator.create(GameType.BASIC, 6);
    Player machine = new MachinePlayer(Name.X, new CaptureMaxStrategy());
    ControllerWithLog controller = new ControllerWithLog();
    machine.addObserver(controller);
    machine.startTurn(model);
    Assert.assertEquals("", controller.log);
  }

  @Test
  public void testHumanPlayNotifyObserver() {
    ReversiModel model = ReversiCreator.create(GameType.BASIC, 6);
    Player human = new HumanPlayer(Name.O);
    ControllerWithLog controller = new ControllerWithLog();
    human.addObserver(controller);
    human.startTurn(model);
    Assert.assertEquals("", controller.log);
  }
  @Test
  public void nullObserverHuman() {
    Player player = new HumanPlayer(Name.O);
    Assert.assertThrows(NullPointerException.class, () -> {
      player.addObserver(null);
    });
  }

  @Test
  public void nullObserverMachine() {
    Player player = new MachinePlayer(Name.X, new CaptureMaxStrategy());
    Assert.assertThrows(NullPointerException.class, () -> {
      player.addObserver(null);
    });
  }

  @Test
  public void nullNameForPlayer() {
    Assert.assertThrows(NullPointerException.class, () -> {
      Player player = new HumanPlayer(null);
    });
  }

}
