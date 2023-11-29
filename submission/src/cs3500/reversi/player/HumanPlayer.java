package cs3500.reversi.player;

import cs3500.reversi.model.ReadOnlyReversiModel;

/**
 * To represent a human player which uses a view for input rather than a strategy.
 */
public class HumanPlayer extends AbstractPlayer {

  /**
   * The constructor, initializes name and strategy to those provided.
   *
   * @param name The name to give this player
   */
  public HumanPlayer(Name name) {
    super(name);
  }

  @Override
  public void startTurn(ReadOnlyReversiModel model) {
    /*
    DO NOTHING. HUMAN PLAYERS SEND INPUT TO THE CONTROLLER VIA A VIEW RATHER THAN THE PLAYER
    INTERFACE
    */
  }

}
