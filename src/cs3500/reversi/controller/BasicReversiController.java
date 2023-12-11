package cs3500.reversi.controller;

import java.io.IOException;
import java.util.Objects;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.HumanPlayer;
import cs3500.reversi.player.Name;
import cs3500.reversi.player.Player;
import cs3500.reversi.view.ReversiView;

/**
 * A controller to control a game of Reversi. Is necessary to manage input requests from players and
 * views to the model and to decouple the view from the model. A ReversiController is an
 * InputObserver and a ModelObserver so that it can subscribe itself and receive game-level events
 * from the model, players, and views. */
public class BasicReversiController implements ReversiController {

  private final Player player;
  private final ReversiView view;
  private final ReversiModel model;
  private boolean hasControl;
  private boolean gameOver;
  private boolean hints;

  /**
   * Creates a controller which is responsible for given player and view and uses the given model
   * for gameplay.
   * @throws NullPointerException If any arguments are null.*/
  public BasicReversiController(Player p, ReversiView view, ReversiModel model) {
    Objects.requireNonNull(p);
    Objects.requireNonNull(view);
    Objects.requireNonNull(model);

    this.player = p;
    this.view = view;
    this.model = model;
    this.hasControl = false;
    this.gameOver = false;
    this.hints = false;

    p.addObserver(this);
    view.addObserver(this);
    model.addObserver(this);

    view.setVisible(true);
  }

  @Override
  public void giveControlTo(Player player) {
    try {
      this.view.render();
    } catch (IOException e) {
      System.out.println("IO failed");
    }
    if (this.player.equals(player)) {

      this.hasControl = true;
      if (!gameOver) {
        this.player.startTurn(this.model);
      }

    } else {
      this.hasControl = false;
    }
  }

  @Override
  public void gameOver() {
    if (gameOver) {
      return;
    }
    this.gameOver = true;
    this.view.alertMessage("Player " + this.model.getWinner() + " won! Score is X: "
            + this.model.getPlayerScore(new HumanPlayer(Name.X)) + " O: "
            + this.model.getPlayerScore(new HumanPlayer(Name.O)));
  }


  @Override
  public void moveHere(LinearCoord coord) {
    if (gameOver) {
      return;
    }
    if (!this.hasControl) {
      this.view.alertMessage("It is not your turn");
      return;
    }
    try {
      this.model.placePiece(coord);
    } catch (IllegalStateException | IllegalArgumentException e) {
      this.view.alertMessage(e.getLocalizedMessage());
    }

  }

  @Override
  public void pass() {
    if (gameOver) {
      return;
    }
    if (this.hasControl) {
      try {
        this.model.pass();
      } catch (IllegalStateException | IllegalArgumentException e) {
        this.view.alertMessage(e.getLocalizedMessage());
      }

    } else {
      this.view.alertMessage("It is not your turn");
    }
  }

  @Override
  public void previewMove(LinearCoord coord) {
    if(hasControl) {
      if(hints) {
        int before = model.getPlayerScore(player);
        ReversiModel newModel = model.getModel();
        newModel.startGame();
        try {
          newModel.placePiece(coord);
        }
        catch(Exception e) {
          //DID NOT WORK
        }

        int after = newModel.getPlayerScore(player);
        this.view.previewMove(coord, after - before);
      } else {
        this.view.previewMove(coord);
      }

    } else {
      this.view.alertMessage("It is not your turn");
    }
  }

  @Override
  public void hints() {

    this.hints = !this.hints;
    try {
      view.render();
    } catch (IOException e){
      //ASDASDD
    }
  }
}
