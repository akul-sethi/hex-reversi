package cs3500.reversi.controller;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.player.Player;

public class ControllerWithLog implements ReversiController {

  public String log;
  public ControllerWithLog() {
    this.log = "";
  }


  @Override
  public void giveControlTo(Player player) {
    this.log += "giveControlTo(" + player + ")\n";
  }

  @Override
  public void gameOver() {
    this.log += "gameOver()\n";
  }

  @Override
  public void moveHere(LinearCoord coord) {
      this.log += "moveHere(" + coord.row() + ", " + coord.column() + ")\n";
  }

  @Override
  public void pass() {
      this.log += "pass()\n";
  }
}
