package cs3500.reversi.view;

import java.io.IOException;

import javax.swing.KeyStroke;

/**
 * Represents a view for a Reversi game.
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
   */
  void addFeatures(Features features);

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
}
