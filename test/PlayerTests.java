import org.junit.Assert;
import org.junit.Test;

import cs3500.reversi.HumanPlayer;
import cs3500.reversi.Player;

/**
 * Represents tests for the player classes.
 */
public class PlayerTests {
  @Test(expected = AssertionError.class)
  public void testPlayerConstructorLongName() {
    Player myPlayer = new HumanPlayer("ab");
    Assert.assertEquals(0, 1 - 1);
  }

  @Test(expected = AssertionError.class)
  public void testPlayerConstructorShortName() {
    Player myPlayer = new HumanPlayer("");
    Assert.assertEquals(0, 1 - 1);
  }

  @Test(expected = AssertionError.class)
  public void testPlayerConstructorNullName() {
    String nullString = null;
    Player myPlayer = new HumanPlayer(nullString);
    Assert.assertEquals(0, 1 - 1);
  }

  @Test
  public void testPlayerEqualsValid() {
    Player myPlayer = new HumanPlayer("X");
    Player myPlayer2 = new HumanPlayer("X");
    Assert.assertEquals(myPlayer, myPlayer2);
  }

  @Test
  public void testPlayerEqualsInvalid() {
    Player myPlayer = new HumanPlayer("O");
    Player myPlayer2 = new HumanPlayer("X");
    Assert.assertNotEquals(myPlayer, myPlayer2);
  }

  @Test
  public void testPlayerHashValid() {
    Player myPlayer = new HumanPlayer("X");
    Player myPlayer2 = new HumanPlayer("X");
    Assert.assertEquals(myPlayer.hashCode(), myPlayer2.hashCode());
  }

  @Test
  public void testPlayerHashInvalid() {
    Player myPlayer = new HumanPlayer("O");
    Player myPlayer2 = new HumanPlayer("X");
    Assert.assertNotEquals(myPlayer.hashCode(), myPlayer2.hashCode());
  }
}
