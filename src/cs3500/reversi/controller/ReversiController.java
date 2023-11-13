package cs3500.reversi.controller;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.Player;
import cs3500.reversi.strategy.CaptureMaxStrategy;
import cs3500.reversi.strategy.FallibleReversiStrategy;
import cs3500.reversi.view.Features;
import cs3500.reversi.view.ReversiView;

public class ReversiController implements Features {
  private ReversiView view;
  private final ReversiModel model;

  public ReversiController(ReversiModel model) {
    this.model = model;
  }
  @Override
  public void previewMove(int row, int column) {
    this.view.previewMove(row, column);
  }

  @Override
  public void moveHere(LinearCoord move) {
  }

  @Override
  public void move() {
    Player toPlay =  this.model.nextToPlay();
    try {
      LinearCoord coord = toPlay.getMove(this.model);
      this.model.placePiece(coord.row, coord.col);
    }
    catch (IllegalStateException ise) {
      this.model.pass();
    }
    view.refresh();
  }

  public void setView(ReversiView view) {
      this.view = view;
      view.addFeatures(this);
  }


}
