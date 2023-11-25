package cs3500.reversi.controller;

import cs3500.reversi.player.Player;

public interface ModelObserver {

  void giveControlTo(Player player);

}
