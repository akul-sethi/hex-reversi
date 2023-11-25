package cs3500.reversi.controller;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.Player;
import cs3500.reversi.view.InputObserver;
import cs3500.reversi.view.ReversiView;

public class ReversiController implements InputObserver, ModelObserver {

  private final Player player;
  private final ReversiView view;
  private final ReversiModel model;
  private boolean hasControl;

  public ReversiController(Player p, ReversiView view, ReversiModel model) {
    this.player = p;
    this.view = view;
    this.model = model;
    this.hasControl = false;

    p.addObserver(this);
    view.addObserver(this);
    model.addObserver(this);
  }

  @Override
  public void giveControlTo(Player player) {
      if(this.player.equals(player)) {
         this.hasControl = true;
         if(this.player.usesStrategy()) {
            this.model.placePiece(this.player.getMove(this.model));
         }
      } else {
        this.hasControl = false;
      }
  }

  @Override
  public void moveHere(LinearCoord coord) {
     if(this.hasControl) {
       this.model.placePiece(coord);
     }
  }

  @Override
  public void pass() {
    if(this.hasControl) {
      this.model.pass();
    }
  }
}
