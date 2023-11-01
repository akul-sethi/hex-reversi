package cs3500.reversi.model;

import org.junit.Assert;
import org.junit.Test;

/**
 * Represents tests for cubeCoord class.
 */
public class CubeCoordTests {
  @Test
  public void testRowColToCube() {
    Assert.assertEquals(new CubeCoord(-1, 0).q, 1);
    Assert.assertEquals(new CubeCoord(-1, 0).r, -1);
    Assert.assertEquals(new CubeCoord(-1, 0).s, 0);
  }
}
