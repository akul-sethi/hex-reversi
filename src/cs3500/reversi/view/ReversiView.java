package cs3500.reversi.view;

import java.io.IOException;

import cs3500.reversi.model.LinearCoord;

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
   * Adds a Features object to this view.*/
  void addFeatures(Features features);

  void setVisible(boolean b);

  void previewMove(int row, int column);

  void refresh();
  void resetFocus();
}
