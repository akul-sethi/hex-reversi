package cs3500.reversi.view;

import cs3500.reversi.model.ReversiModel;

/**
 * Represents a view for a Reversi game. */
public interface ReversiView {

  /**
   * Renders the view given a model.
   * @param model The model which is being rendered.
   */
  void render(ReversiModel model);
}
