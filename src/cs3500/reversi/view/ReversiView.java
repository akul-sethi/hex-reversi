package cs3500.reversi.view;

import java.io.IOException;

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
}
