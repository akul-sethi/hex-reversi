package cs3500.reversi.model;

import java.util.HashMap;
import java.util.Map;

import cs3500.reversi.Player;

abstract class AReversiModel implements ReversiModel {
   protected final Map<CubeCord, Player> tiles;
   protected boolean gameStarted;
   protected Player turn;

   protected AReversiModel() {
     this.tiles = new HashMap<>();
     this.gameStarted = false;
     this.turn = null;
   }
}
