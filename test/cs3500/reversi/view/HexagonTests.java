package cs3500.reversi.view;

import org.junit.Assert;
import org.junit.Test;

/**
 * To represent tests for the Hexagon class.
 */
public class HexagonTests {

  @Test
  public void testContains() {
    Hexagon zeroZero = new Hexagon(0, 0, 20);
    Hexagon zeroHundred = new Hexagon(0, 100, 20);

    Assert.assertTrue(zeroZero.contains(0, 0));
    Assert.assertTrue(zeroZero.contains(0, 15));
    Assert.assertFalse(zeroZero.contains(0, 30));
    Assert.assertTrue(zeroHundred.contains(0, 100));
    Assert.assertTrue(zeroHundred.contains(0, 115));
    Assert.assertFalse(zeroZero.contains(0, 200));
  }
}
