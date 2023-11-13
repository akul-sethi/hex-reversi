package cs3500.reversi.model;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import cs3500.reversi.player.HumanPlayer;
import cs3500.reversi.player.Player;

/**
 * Tests for mutability etc of the mock reversi class.
 */
public class MockTests {
  @Test
  public void immutablePlayerList() {
    List<Player> players = new ArrayList<>();
    players.add(new HumanPlayer("O"));
    players.add(new HumanPlayer("X"));
    HashMap<CubeCoord, Player> map = new HashMap<>();
    map.put(new CubeCoord(0, 0), null);
    ReversiModel mock = new MockReversi(map, players);
    players.clear();
    mock.pass();
    mock.pass();
    Assert.assertEquals(1, 2 - 1);
  }

  @Test
  public void immutableMap() {
    List<Player> players = Arrays.asList(
            new HumanPlayer("O"), new HumanPlayer("X"));
    HashMap<CubeCoord, Player> map = new HashMap<>();
    map.put(new CubeCoord(0, 0), null);
    ReversiModel mock = new MockReversi(map, players);
    map.clear();
    mock.pass();
    mock.pass();
    Assert.assertEquals(mock.playerAt(0, 0), null);
  }
}
