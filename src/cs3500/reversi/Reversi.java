package cs3500.reversi;

import java.util.HashMap;
import java.util.function.Function;

import cs3500.reversi.controller.BasicReversiController;
import cs3500.reversi.model.GameType;
import cs3500.reversi.model.ReversiCreator;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.HumanPlayer;
import cs3500.reversi.player.MachinePlayer;
import cs3500.reversi.player.Name;
import cs3500.reversi.player.Player;
import cs3500.reversi.strategy.AvoidNextToCornersStrategy;
import cs3500.reversi.strategy.CaptureCornersStrategy;
import cs3500.reversi.strategy.CaptureMaxStrategy;
import cs3500.reversi.strategy.MiniMaxStrategy;
import cs3500.reversi.strategy.TryTwo;
import cs3500.reversi.view.GUIReversiView;
import cs3500.reversi.view.ReversiView;

/**
 * Entry point to a game of Reversi game is played by provided two command line arguments describing
 * the types of players in the game. The order of play is determined by the order in which the
 * arguments are provided. The following arguments are allowed:
 * human -> A human player
 * capture-max -> A machine player which follows the strategy of capturing the max number of pieces
 * on its turn
 * corners -> A machine player which follows the strategy of capturing corners if it can on its
 * turn.
 * avoid-corners -> A machine player which tries to avoid the spots next to a corner on its turn
 * minimax -> A machine player which tries to minimize the max capture the opponent can do on its
 * turn.
 */
public final class Reversi {
  /**
   * Represents a mapping from description: String -> generator: Function which associates a
   * description with a Function that generates a specific construction of a Player (given a Name)
   * based on the strategy they use or lack thereof.
   */
  private static final HashMap<String, Function<Name, Player>> playerTypes = new HashMap<>();

  /**
   * Initializes the playerTypes map with the various different constructions of players and
   * their associated name.
   */
  private static void createPlayerTypes() {
    playerTypes.put("human", (name) -> new HumanPlayer(name));
    playerTypes.put("capture-max", (name) -> new MachinePlayer(name, new CaptureMaxStrategy()));
    playerTypes.put("corners", (name) -> new MachinePlayer(name, new CaptureCornersStrategy()));
    playerTypes.put("avoid-corners", (name) -> new MachinePlayer(name,
            new AvoidNextToCornersStrategy()));
    playerTypes.put("minimax", (name) -> new MachinePlayer(name, new MiniMaxStrategy()));
    playerTypes.put("super", (name) -> new MachinePlayer(name, new TryTwo(
            new CaptureCornersStrategy(), new TryTwo(new AvoidNextToCornersStrategy(),
            new TryTwo(new MiniMaxStrategy(), new CaptureMaxStrategy())))));
  }

  /**
   * Actual entry point method.
   *
   * @param args The system args to create the game from.
   */
  public static void main(String[] args) {
    createPlayerTypes();
    if (!correctInputProvided(args)) {
      return;
    }

    Player p1 = playerTypes.get(args[1]).apply(Name.X);
    Player p2 = playerTypes.get(args[2]).apply(Name.O);
    GameType type = GameType.HEX;
    int size = 6;
    if (args[0].equals("hex")) {
      type = GameType.HEX;
      size = 6;
    } else if (args[0].equals("square")) {
      type = GameType.SQUARE;
      size = 10;
    }
    ReversiModel model = ReversiCreator.create(GameType.BASIC, args[0], size);
    ReversiView view1 = new GUIReversiView(model, "Player 1", type);
    ReversiView view2 = new GUIReversiView(model, "Player 2", type);
    BasicReversiController controller1 = new BasicReversiController(p1, view1, model);
    BasicReversiController controller2 = new BasicReversiController(p2, view2, model);
    model.startGame();
  }


  /**
   * Returns true if the args provided are of the correct number and type else returns false.
   * Provides error messages to the console depending on the type of error in the input.
   */
  private static boolean correctInputProvided(String[] args) {
    if (args.length != 3) {
      System.out.println("Incorrect number of args provided; requires game type, and two players");
      return false;
    }
    if (!args[0].equals("square") && !args[0].equals("hex")) {
      System.out.println("Incorrect game type provided. Only accepts the following: \n" +
              "square\n" + "hex\n");
      return false;
    }
    if (!playerTypes.containsKey(args[1]) || !playerTypes.containsKey(args[2])) {
      System.out.println("Cannot recognize one of the player names provided. Only excepts the " +
              "following: ");
      for (String name : playerTypes.keySet()) {
        System.out.println("-" + name);
      }
      return false;
    }

    return true;
  }

}

