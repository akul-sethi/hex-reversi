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

public class ModelAdapter extends BasicReversi implements IReversiModel {

  ModelAdapter(int sideLength, PlayerAdapter one, PlayerAdapter two) {
    super(sideLength, one, two);
  }

  @Override
  public boolean isGameOver() {
    return false;
  }

  @Override
  public HexagonState getHexagonStateAt(int s, int q, int r) {
    return AdapterUtils.playerToHexagonState(super.playerAt(new CubeCoord(q, r, s)));
  }

  @Override
  public int getMaxCoordinate() {
    return (super.getRightCol() - super.getLeftCol() - 1) / 2;
  }

  @Override
  public IPlayer getTurn() {
    return new PlayerAdapter(super.nextToPlay());
  }

  @Override
  public int getPlayerScore(IPlayer p) {
    return super.getPlayerScore(AdapterUtils.hexagonStateToPlayer(p.getHexagonState()));
  }

  @Override
  public IReversiBoard getCopyBoardObject() {
    return new r
  }

  @Override
  public HashMap<Hexagon, HexagonState> getCopyBoardHashMap() {
    return null;
  }

  @Override
  public IPlayer getPlayer(HexagonState state) {
    return null;
  }

  @Override
  public int getNumPiecesGainedForMove(Hexagon h, HexagonState s) {
    return 0;
  }

  @Override
  public boolean isHexagonNextToCorner(Hexagon h) {
    return false;
  }

  @Override
  public boolean isHexagonInCorner(Hexagon h) {
    return false;
  }

  @Override
  public boolean isValidMove(Hexagon h, HexagonState s) {
    return false;
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
}