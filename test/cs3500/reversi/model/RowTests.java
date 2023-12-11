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
    Row right = new Row(0, HexDirection.RIGHT, this.center);
    Assert.assertEquals(right.next(), new CubeCoord(1, 0, -1));
  }

  @Test
  public void testUpRightNext() {
    Row right = new Row(0, HexDirection.UP_RIGHT, this.center);
    Assert.assertEquals(right.next(), new CubeCoord(1, -1, 0));
  }

  @Test
  public void testDownRightNext() {
    Row right = new Row(0, HexDirection.DOWN_RIGHT, this.center);
    Assert.assertEquals(right.next(), new CubeCoord(0, 1, -1));
  }

  @Test
  public void testDownLeftNext() {
    Row right = new Row(0, HexDirection.DOWN_LEFT, this.center);
    Assert.assertEquals(right.next(), new CubeCoord(-1, 1, 0));
  }

  @Test
  public void testLeftNext() {
    Row right = new Row(0, HexDirection.LEFT, this.center);
    Assert.assertEquals(right.next(), new CubeCoord(-1, 0, 1));
  }

  @Test
  public void testUpLeftNext() {
    Row right = new Row(0, HexDirection.UP_LEFT, this.center);
    Assert.assertEquals(right.next(), new CubeCoord(0, -1, 1));
  }

  @Test
  public void testSquareRightNext() {
    Row right = new Row(0, SquareDirection.RIGHT, this.center);
    Assert.assertEquals(right.next(), new CubeCoord(0, 1));
  }

  @Test
  public void testSquareUpRightNext() {
    Row right = new Row(0, SquareDirection.UP_RIGHT, this.center);
    Assert.assertEquals(right.next(), new CubeCoord(-1, 1));
  }

  @Test
  public void testSquareDownRightNext() {
    Row right = new Row(0, SquareDirection.DOWN_RIGHT, this.center);
    Assert.assertEquals(right.next(), new CubeCoord(1, 1));
  }

  @Test
  public void testSquareDownLeftNext() {
    Row right = new Row(0, SquareDirection.DOWN_LEFT, this.center);
    Assert.assertEquals(right.next(), new CubeCoord(1, -1));
  }

  @Test
  public void testSquareLeftNext() {
    Row right = new Row(0, SquareDirection.LEFT, this.center);
    Assert.assertEquals(right.next(), new CubeCoord(0, -1));
  }

  @Test
  public void testSquareUpLeftNext() {
    Row right = new Row(0, SquareDirection.UP_LEFT, this.center);
    Assert.assertEquals(right.next(), new CubeCoord(-1, -1));
  }

  @Test
  public void testSquareUpNext() {
    Row right = new Row(0, SquareDirection.UP, this.center);
    Assert.assertEquals(right.next(), new CubeCoord(-1, 0));
  }@Test
  public void testSquareDownNext() {
    Row right = new Row(0, SquareDirection.DOWN, this.center);
    Assert.assertEquals(right.next(), new CubeCoord(1, 0));
  }
}
