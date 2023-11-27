package cs3500.reversi.controller;

import cs3500.reversi.player.Player;

/**
 * Represents an object which observes a ReversiModel for events that are necessary to components
 * external to the model. This is necessary to allow the model to be able to notify controllers
 * whose turn it is to play next. */
public interface ModelObserver {

  /**
   * Allows a model to notify this observer as to which Player should be receiving control next.*/
  void giveControlTo(Player player);

}
