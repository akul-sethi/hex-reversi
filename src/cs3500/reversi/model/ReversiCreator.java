package cs3500.reversi.model;

import java.util.Objects;

import cs3500.reversi.HumanPlayer;

/**
 * A Factory class for creating Reversi models without exposing their constructors.
 */
public final class ReversiCreator {

  /**
   * Creates a model given a GameType and a variadic list of integer parameters.
   *
   * @param type   The type of the game to create.
   * @param params The parameters necessary for this type of game. This can be found in the GameType
   *               enum.
   */
  public static ReversiModel create(GameType type, int... params) throws IllegalArgumentException {
    //WRITTEN AS IF STATEMENT FOR COMPLIANCE WITH STYlE GUIDE!
    //WILL CHANGE TO SWITCH STATEMENT IN FUTURE
    if (Objects.requireNonNull(type) == GameType.BASIC) {
      if (params.length != 1) {
        throw new IllegalArgumentException("Incorrect num params");
      }
      return new BasicReversi(params[0], new HumanPlayer("X"), new HumanPlayer("O"));
    }
    throw new IllegalArgumentException("Something incorrect was provided");

  }
}
