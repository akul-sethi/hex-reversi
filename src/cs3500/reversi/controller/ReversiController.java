package cs3500.reversi.controller;

import java.io.IOException;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.Player;
import cs3500.reversi.view.InputObserver;
import cs3500.reversi.view.ReversiView;

/**
 * A controller to control a game of Reversi. Is necessary to manage input requests from players and
 * views to the model and to decouple the view from the model. A ReversiController is an
 * InputObserver and a ModelObserver so that it can subscribe itself and receive game-level events
 * from the model, players, and views. */
public class ReversiController implements InputObserver, ModelObserver {

  private final Player player;
  private final ReversiView view;
  private final ReversiModel model;
  private boolean hasControl;

  /**
   * Creates a controller which is responsible for given player and view and uses the given model
   * for gameplay.*/
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
      if(this.player.equals(player)) {
         this.hasControl = true;
         this.player.startTurn(this.model);
      } else {
        this.hasControl = false;
      }

    try {
      this.view.render();
    } catch(IOException e) {
      System.out.println("IO failed");
    }
  }


  @Override
  public void moveHere(LinearCoord coord) {
     if(!this.hasControl) { return;}
     try {
       this.model.placePiece(coord);
     } catch(IllegalStateException | IllegalArgumentException e) {
         //DO NOTHING
     }

  }

  @Override
  public void pass() {
    if(this.hasControl) {
      this.model.pass();
    }
  }
}
