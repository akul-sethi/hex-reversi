package cs3500.reversi.view;

import cs3500.reversi.player.Player;

class Tile {
  public final Hexagon hex;
  public final Player player;

  Tile(Hexagon hex, Player player) {
    this.hex = hex;
    this.player = player;
  }

}
