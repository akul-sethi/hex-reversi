package reversi.controller;

/**
 * A features interface, which is implemented by a ReversiController.
 */
public interface IReversiFeatures {

  /**
   * Displays the view with a title associated to the player.
   */
  void start();

  /**
   * Trys to call the model's movePlay method with the coordinates of the view's selected hexagon,
   * and displays an error message if an exception is thrown.
   * Repaints the view.
   */
  void movePlay();

  /**
   * Trys to call the model's movePass method, and displays an error message if an exception is
   * thrown.
   * Repaints the view.
   */
  void movePass();

  /**
   * Quits the game.
   */
  void quit();

  /**
   * Displays the game is over if it is over.
   * Calls the player's play() method if not, and in turn calls the model's movePlay or movePass
   * method if it determines the IPlayer is AI.
   * Repaints the view.
   */
  void goMove();

}
