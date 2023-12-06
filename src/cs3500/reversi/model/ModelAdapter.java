package cs3500.reversi.model;

import java.util.HashMap;

import cs3500.provider.IPlayer;
import cs3500.provider.IReversiFeatures;
import cs3500.provider.model.Hexagon;
import cs3500.provider.model.HexagonState;
import cs3500.provider.model.IReversiBoard;
import cs3500.provider.model.IReversiModel;
import cs3500.reversi.controller.ModelObserver;
import cs3500.reversi.player.Player;
import cs3500.reversi.player.PlayerAdapter;

public class ModelAdapter extends ReadOnlyModelAdapter implements IReversiModel, ReversiModel {

  private final ReversiModel delegate;
  ModelAdapter(ReversiModel adaptee) {
    super(adaptee);
    this.delegate = adaptee;
  }

  @Override
  public void startGame(IPlayer player1, IPlayer player2) {

  }

  @Override
  public void movePlay(IPlayer p, int s, int q, int r) {

  }

  @Override
  public void movePass(IPlayer p) {

  }

  @Override
  public boolean validMoveExistsForCurrentPlayer() {
    return false;
  }

  @Override
  public void addFeatureListener(IReversiFeatures features) {

  }

  @Override
  public void addObserver(ModelObserver obs) {

  }

  @Override
  public void startGame() {

  }

  @Override
  public void placePiece(LinearCoord coord) {

  }

  @Override
  public void pass() {

  }
}