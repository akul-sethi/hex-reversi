package cs3500.reversi.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests for the row classes.
 */
public class RowTests {
  private CubeCoord center;

  @Before
  public void init() {
    this.center = new CubeCoord(0, 0, 0);
  }

  @Test
  public void testRightNext() {
    Row right = new Right(0, this.center);
    Assert.assertEquals(right.next(), new CubeCoord(1, 0, -1));
  }

  @Test
  public void testUpRightNext() {
    Row right = new Right(0, this.center);
    Assert.assertEquals(right.next(), new CubeCoord(1, -1, 0));
  }

  @Test
  public void testDownRightNext() {
    Row right = new Right(0, this.center);
    Assert.assertEquals(right.next(), new CubeCoord(0, 1, -1));
  }

  @Test
  public void testDownLeftNext() {
    Row right = new Right(0, this.center);
    Assert.assertEquals(right.next(), new CubeCoord(-1, 0, 1));
  }

  @Test
  public void testLeftNext() {
    Row right = new Right(0, this.center);
    Assert.assertEquals(right.next(), new CubeCoord(-1, 0, 1));
  }

  @Test
  public void testUpLeftNext() {
    Row right = new Right(0, this.center);
    Assert.assertEquals(right.next(), new CubeCoord(0, -1, 1));
  }


}
