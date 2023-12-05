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
    Row right = new Row(0, Direction.RIGHT, this.center);
    Assert.assertEquals(right.next(), new CubeCoord(1, 0, -1));
  }

  @Test
  public void testUpRightNext() {
    Row right = new Row(0, Direction.UP_RIGHT, this.center);
    Assert.assertEquals(right.next(), new CubeCoord(1, -1, 0));
  }

  @Test
  public void testDownRightNext() {
    Row right = new Row(0, Direction.DOWN_RIGHT, this.center);
    Assert.assertEquals(right.next(), new CubeCoord(0, 1, -1));
  }

  @Test
  public void testDownLeftNext() {
    Row right = new Row(0, Direction.DOWN_LEFT, this.center);
    Assert.assertEquals(right.next(), new CubeCoord(-1, 1, 0));
  }

  @Test
  public void testLeftNext() {
    Row right = new Row(0, Direction.LEFT, this.center);
    Assert.assertEquals(right.next(), new CubeCoord(-1, 0, 1));
  }

  @Test
  public void testUpLeftNext() {
    Row right = new Row(0, Direction.UP_LEFT, this.center);
    Assert.assertEquals(right.next(), new CubeCoord(0, -1, 1));
  }


}
