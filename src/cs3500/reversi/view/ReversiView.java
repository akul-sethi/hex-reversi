package cs3500.reversi.view;

import java.io.IOException;

import javax.swing.KeyStroke;

import cs3500.reversi.controller.InputObserver;

/**
 * Represents a view for a Reversi game. It is observed by an InputObserver and sends the observer
 * requests based on user interactions.
 */
public interface ReversiView {

  /**
   * Renders the view in some manner. (Text, graphic, etc.)
   *
   * @throws IOException of the rendering fails for some reason.
   */
  void render() throws IOException;

  /**
   * Adds a Features object to this view.
   * @throws NullPointerException If features is <code>null</code>
   */
  void addObserver(InputObserver features);

  /**
   * Makes the view visible.
   */
  void setVisible(boolean b);

  /**
   * Resets focus to the view. Necessary for registering key events to the view.
   */
  void resetFocus();

  /**
   * Sets an action to be performed for a given keystroke.
   */
  void setHotKey(KeyStroke keyStroke, String featureName);

  void alertMessage(String message);
}
