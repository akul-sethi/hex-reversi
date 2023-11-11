package cs3500.reversi.controller;

import cs3500.reversi.view.Features;
import cs3500.reversi.view.ReversiView;

public class ReversiController implements Features {
  private ReversiView view;
  @Override
  public void previewMove(int row, int column) {
      this.view.previewMove(row, column);
  }

  public void setView(ReversiView view) {
      this.view = view;

      view.addFeatures(this);
  }
}
