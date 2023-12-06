package cs3500.reversi.player;

import java.util.List;
import java.util.Objects;

import cs3500.provider.IPlayer;
import cs3500.provider.model.Hexagon;
import cs3500.provider.model.HexagonState;
import cs3500.reversi.controller.InputObserver;
import cs3500.reversi.model.AdapterUtils;
import cs3500.reversi.model.ReadOnlyReversiModel;

/**
 * A two way adapter which can behave as an IPlayer and Player. Necessary so that the
 * provider model, which uses IPlayers, could be implemented using our model, which
 * uses Players.
 */
public class PlayerAdapter implements IPlayer, Player {

  final private Player delegate;

  /**
   * Creates a PlayerAdapter given a Player.
   */
  public PlayerAdapter(Player player) {
    Objects.requireNonNull(player);
    this.delegate = player;
  }

  /**
   * Creates a PlayerAdapter given an IPlayer.
   */
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

  /**
   * Their view or strategies should never be calling these methods, otherwise
   * the code would not properly be encapsulated, thus we do not need to implement them
   * nor is there any way to as our Player does not have analogous methods.
   */
  @Override
  public List<Hexagon> play() {
    throw new IllegalStateException();
  }

  /**
   * Their view or strategies should never be calling these methods, otherwise
   * the code would not properly be encapsulated, thus we do not need to implement them
   * nor is there any way to as our Player does not have analogous methods.
   */
  @Override
  public HexagonState getHexagonState() {
    throw new IllegalStateException();
  }

  /**
   * Their view or strategies should never be calling these methods, otherwise
   * the code would not properly be encapsulated, thus we do not need to implement them
   * nor is there any way to as our Player does not have analogous methods.
   */
  @Override
  public void setHexagonState(HexagonState state) {
    throw new IllegalStateException();
  }
}

