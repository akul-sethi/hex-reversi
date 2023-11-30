package cs3500.reversi.view;

import cs3500.reversi.controller.InputObserver;

/**
 * Represents a view of the board and methods it must implement.
 * This can be nested within a full Reversi view.
 */
public interface BoardView {

  /**
   * Adds an InputObserver object to the board so that game level events can be
   * sent to it when a user interacts with the board.
   */
  void addObserver(InputObserver features);

  /**
   * Refreshes what the board looks like.
   */
  void refresh();

  /**
   * Resets the focus of the application to the board so that it can receive key events.
   */
  void resetFocus();

}
