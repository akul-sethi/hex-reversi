package cs3500.reversi.model;

import cs3500.reversi.HumanPlayer;

public class ReversiCreator {

  public static ReversiModel create(GameType type, int... params) throws IllegalArgumentException {
    switch (type) {
      case BASIC: {
        if (params.length != 1) {
          throw new IllegalArgumentException("Incorrect num params");
        }
        return new BasicReversi(params[0], new HumanPlayer("X"), new HumanPlayer("O"));
      }
      default:
        throw new IllegalArgumentException("Something incorrect was provided");
    }

  }
}
