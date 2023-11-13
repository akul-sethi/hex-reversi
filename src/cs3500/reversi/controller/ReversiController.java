package cs3500.reversi.controller;

import cs3500.reversi.strategy.CaptureMaxStrategy;
import cs3500.reversi.strategy.FallibleReversiStrategy;
import cs3500.reversi.view.Features;
import cs3500.reversi.view.ReversiView;

public class ReversiController implements Features {
  private ReversiView view;
  private final FallibleReversiStrategy strat;
  @Override
  public void previewMove(int row, int column) {

    this.view.previewMove(row, column);
    this.strat = new CaptureMaxStrategy();
  }

  public void setView(ReversiView view) {
      this.view = view;
      view.addFeatures(this);
  }


}
