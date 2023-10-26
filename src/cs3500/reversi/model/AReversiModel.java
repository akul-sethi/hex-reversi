package cs3500.reversi.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import cs3500.reversi.Player;

abstract class AReversiModel implements ReversiModel {
   protected final Map<CubeCoord, Player> tiles;
   protected boolean gameStarted;
   protected final Queue<Player> players;
   private int passCount = 0;

   protected AReversiModel(List<CubeCoord> hexs, List<Player> players) {
     this.tiles = new HashMap<>();
     this.gameStarted = false;
     this.players = new LinkedList<>();

   }

   @Override
   public void pass() {
     this.players.add(this.players.remove());
   }

  @Override
  public void placePiece(int row, int column) {
    requireValidMove(row, column);
  }

  private void requireValidMove(int row, int column) throws IllegalStateException,
          IllegalArgumentException {
    CubeCoord move = new CubeCoord(row, column);
    if(!this.tiles.containsKey(move)) { throw new IllegalArgumentException("Invalid coordinate");}
    if(this.tiles.get(move) != null) {throw new IllegalStateException("Someone is already here");}



    this.pass();
  }

  @Override
  public int getWidth() {
    int max = 0;
    for(CubeCoord c : this.tiles.keySet()) {
      if (c.row() > max) {
        max = c.column();
      }
    }
    return max;
  }

  @Override
  public int getHeight() {
     int max = 0;
     for(CubeCoord c : this.tiles.keySet()) {
       if (c.row() > max) {
         max = c.row();
       }
     }
    return max;
  }




}
