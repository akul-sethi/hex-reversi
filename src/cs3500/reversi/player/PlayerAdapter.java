package cs3500.reversi.player;

import java.util.List;
import java.util.Objects;

import cs3500.provider.IPlayer;
import cs3500.provider.model.Hexagon;
import cs3500.provider.model.HexagonState;
import cs3500.reversi.controller.InputObserver;
import cs3500.reversi.model.AdapterUtils;
import cs3500.reversi.model.ReadOnlyReversiModel;

public class PlayerAdapter implements IPlayer, Player {

  final private Player delegate;
  public PlayerAdapter(Player player) {
    Objects.requireNonNull(player);
    this.delegate = player;
  }

  public PlayerAdapter(IPlayer p) {
    Objects.requireNonNull(p);
    this.delegate = AdapterUtils.hexagonStateToPlayer(p.getHexagonState());
  }
  @Override
  public void startTurn(ReadOnlyReversiModel model) {
    this.delegate.startTurn(model);
  }

  @Override
  public void addObserver(InputObserver observer) {
    this.delegate.addObserver(observer);
  }

  @Override
  public List<Hexagon> play() {
    throw new IllegalStateException();
  }

  @Override
  public HexagonState getHexagonState() {
    throw new IllegalStateException();
  }

  @Override
  public void setHexagonState(HexagonState state) {
    throw new IllegalStateException();
  }
}

