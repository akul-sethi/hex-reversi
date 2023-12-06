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
    this.delegate.startGame();
  }

  @Override
  public void movePlay(IPlayer p, int s, int q, int r) {
    if(!this.delegate.nextToPlay().equals(new PlayerAdapter(p))) {
      throw new IllegalStateException("Not given players turn");
    }
    CubeCoord cube = new CubeCoord(q, r, s);
    this.delegate.placePiece(new BasicPoint(cube.row(), cube.column()));
  }

  @Override
  public void movePass(IPlayer p) {
    if(!this.delegate.nextToPlay().equals(new PlayerAdapter(p))) {
      throw new IllegalStateException("Not given players turn");
    }
    this.delegate.pass();
  }

  @Override
  public boolean validMoveExistsForCurrentPlayer() {
    //TODO
    throw new IllegalArgumentException("METHOD NOT IMPLEMENTED");
  }

  @Override
  public void addFeatureListener(IReversiFeatures features) {
    this.addObserver(new FeaturesAdaptor(features));
  }

  @Override
  public void addObserver(ModelObserver obs) {
    this.delegate.addObserver(obs);
  }

  @Override
  public void startGame() {
    this.delegate.startGame();
  }

  @Override
  public void placePiece(LinearCoord coord) {
    this.delegate.placePiece(coord);
  }

  @Override
  public void pass() {
    this.delegate.pass();
  }
}