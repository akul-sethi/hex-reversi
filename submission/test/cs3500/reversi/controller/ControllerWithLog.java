package cs3500.reversi.controller;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.player.Player;

/**
 * A mock implementation of a ReversiController which logs all method
 * calls to it. Necessary to test if models, views, and players publish the correct
 * events to their observers and that they do them at the correct time.*/
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
