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

   protected AReversiModel(List<CubeCoord> hexs) {

     this.tiles = new HashMap<>();
     this.gameStarted = false;
     this.players = new LinkedList<>();

   }

   public void pass() {
     this.players.add(this.players.remove());
   }
}
