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

  @Test
  public void testGetRowCube() {
    Assert.assertEquals(new CubeCoord(-1, 0).row(),  -1);
    Assert.assertEquals(new CubeCoord(2, 1).row(), 2);
    Assert.assertEquals(new CubeCoord(1, -1, 0).row(), -1);
  }

  @Test
  public void testGetColCube() {
    Assert.assertEquals(new CubeCoord(-1, 0).column(),  1);
    Assert.assertEquals(new CubeCoord(2, 1).column(), 0);
    Assert.assertEquals(new CubeCoord(1, -1, 0).column(), 1);
  }

  @Test
  public void testCubeEquals() {
    Assert.assertEquals(new CubeCoord(1, -1, 0), new CubeCoord(1, -1, 0));
    Assert.assertEquals(new CubeCoord(2, -1, -1), new CubeCoord(2, -1, -1));
    Assert.assertEquals(new CubeCoord(0, 0, 0), new CubeCoord(0, 0, 0));
  }


}
