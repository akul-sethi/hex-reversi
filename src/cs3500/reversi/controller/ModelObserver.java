package cs3500.reversi.controller;

import cs3500.reversi.player.Player;

/**
 * Represents an object which observes a ReversiModel for events that are necessary to components
 * external to the model. This is necessary to allow the model to be able to notify controllers
 * whose turn it is to play next and when someone wins the game.
 */
public interface ModelObserver {

  /**
   * Allows a model to notify this observer as to which Player should be receiving control next.
   */
  void giveControlTo(Player player);

  /**
   * Allows a model to notify its observer that the game is over.
   */
  void gameOver();

}
