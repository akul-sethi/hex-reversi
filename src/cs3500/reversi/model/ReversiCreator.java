package cs3500.reversi.model;

import java.util.Objects;

import cs3500.reversi.player.HumanPlayer;
import cs3500.reversi.player.Name;
import cs3500.reversi.player.Player;

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

  public static ReversiModel create(GameType type, Object... params)
          throws IllegalArgumentException {
    //WRITTEN AS IF STATEMENT FOR COMPLIANCE WITH STYlE GUIDE!
    //WILL CHANGE TO SWITCH STATEMENT IN FUTURE
    if (Objects.requireNonNull(type) == GameType.BASIC) {
      if (params.length != 4 && params.length != 2) {
        throw new IllegalArgumentException("Incorrect num params");
      }
      if (params.length == 2) {
        if (params[0].equals("hex")) {
          return new BasicHexReversi((Integer) params[1], new HumanPlayer(Name.X),
                  new HumanPlayer(Name.O));
        } else if (params[0].equals("square")) {
          return new BasicSquareReversi((Integer) params[1], new HumanPlayer(Name.X),
                  new HumanPlayer(Name.O));
        }
      }
      if (params.length == 4) {
        if (params[0].equals("hex")) {
          return new BasicHexReversi((Integer) params[1], (Player) params[2], (Player) params[3]);
        } else if (params[0].equals("square")) {
          return new BasicSquareReversi((Integer) params[1], (Player) params[2], (Player) params[3]);
        }
      }
    }
    throw new IllegalArgumentException("Something incorrect was provided");

  }
}
