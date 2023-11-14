package cs3500.reversi.view;

import cs3500.reversi.player.Player;

/**
 * Represents a Tile on the board. Contains its bounding Hexagon and the Player which is within it.
 * Fields are public as they are essential to the definition of the class. */
class Tile {
  public final Hexagon hex;
  public final Player player;

  /**
   * Creates a Tile with the given Hexagon and Player.*/
  Tile(Hexagon hex, Player player) {
    this.hex = hex;
    this.player = player;
  }

}
