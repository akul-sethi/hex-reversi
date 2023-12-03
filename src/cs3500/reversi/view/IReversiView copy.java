package cs3500.reversi.view;

import javax.swing.KeyStroke;

import reversi.controller.IReversiFeatures;
import reversi.model.Hexagon;
import reversi.model.HexagonState;

/**
 * Interface to represent a view of a Reversi game.
 */
public interface IReversiView {

  /**
   * Displays the current game according to the given boolean.
   * @param show should the view be visible?
   */
  void display(boolean show);

  /**
   * Sets a new hot key according to the given parameter, associating the keyStroke
   * with the name of the feature.
   * @param key the key pressed
   * @param featuresName the name of the feature being set.
   */
  void setHotKey(KeyStroke key, String featuresName);

  /**
   * Adds a new FeatureListener (controller) to this IReversiView, in order to allow
   * inputs to the view to be handled in the controller.
   * @param features the feature lsitener being set.
   */
  void addFeatureListener(IReversiFeatures features);

  /**
   * Repaints the panel in the view.
   */
  void repaint();

  /**
   * Used to get a copy of the current selected hexagon.
   * @return the current selected hexagon
   * @throw IllegalStateException if no hexagon selected
   */
  Hexagon getSelectedHex();

  /**
   * Deselects the current selected hexagon (sets selectedHex field in the panel to null).
   */
  void deselect();

  /**
   * Displays the given error message.
   * @param error String of the message to be displayed.
   */
  void showErrorMessage(String error);

  /**
   * Sets the title (format: Player x) for the view given the hexagon state.
   * Black is player 1, White is player 2, Empty is Player.
   * @param hexState the hexagon state corresponding to the player name being displayed
   */
  void setTitle(HexagonState hexState);

}
