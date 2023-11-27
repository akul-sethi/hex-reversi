import org.junit.Assert;
import org.junit.Test;

import cs3500.reversi.player.HumanPlayer;
import cs3500.reversi.player.Name;
import cs3500.reversi.player.Player;

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
}
