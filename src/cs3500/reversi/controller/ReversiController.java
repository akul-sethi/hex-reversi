package cs3500.reversi.controller;

import java.io.IOException;

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

    view.setVisible(true);
  }

  @Override
  public void giveControlTo(Player player) {
    System.out.println(player + " wants control");
      if(this.player.equals(player)) {
         this.hasControl = true;
         if(this.player.usesStrategy()) {
           playComputerMove();
         }
      } else {
        this.hasControl = false;
      }

      try {
        this.view.render();
      } catch(IOException e) {
        System.out.println("IO failed");
      }
  }

  private void playComputerMove() {
    try {
      LinearCoord move = this.player.getMove(this.model);
      try {
        this.model.placePiece(move);
      } catch(IllegalStateException | IllegalArgumentException e) {
        //DO NOTHING
      }
    } catch(IllegalStateException e) {
      this.model.pass();
    }
  }

  @Override
  public void moveHere(LinearCoord coord) {
     if(this.hasControl) {
       try {
         this.model.placePiece(coord);
       } catch(IllegalStateException | IllegalArgumentException e) {
         //DO NOTHING
       }
     }
  }

  @Override
  public void pass() {
    if(this.hasControl) {
      this.model.pass();
    }
  }
}
