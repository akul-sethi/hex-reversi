package cs3500.reversi;


import java.util.HashMap;
import java.util.function.Function;

import javax.swing.*;

import cs3500.provider.model.Hexagon;
import cs3500.provider.model.HexagonState;
import cs3500.provider.strategy.AsManyPiecesAsPossible;
import cs3500.provider.strategy.AvoidNextToCorners;
import cs3500.provider.strategy.GoForCorners;
import cs3500.provider.view.IReversiView;
import cs3500.reversi.controller.BasicReversiController;
import cs3500.reversi.model.GameType;
import cs3500.reversi.model.ReadOnlyModelAdapter;
import cs3500.reversi.model.ReversiCreator;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.HumanPlayer;
import cs3500.reversi.player.MachinePlayer;
import cs3500.reversi.player.Name;
import cs3500.reversi.player.Player;
import cs3500.reversi.player.PlayerAdapter;
import cs3500.reversi.strategy.AvoidNextToCornersStrategy;
import cs3500.reversi.strategy.CaptureCornersStrategy;
import cs3500.reversi.strategy.CaptureMaxStrategy;
import cs3500.reversi.strategy.FallibleStrategyAdapter;
import cs3500.reversi.strategy.MiniMaxStrategy;
import cs3500.reversi.view.GUIReversiView;
import cs3500.reversi.view.ReversiView;
import cs3500.reversi.view.TwoWayViewAdapter;

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
 * provider-capture-max -> A machine player which follows the strategy of capturing the max number
 * of pieces on its turn.
 * provider-corners -> A machine player which follows the strategy of capturing the corners.
 * provider-avoid-corners -> A machine player which follows the strategy of avoiding the spots
 * next to corners.
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
    playerTypes.put("avoid-near-corners", (name) -> new MachinePlayer(name,
            new AvoidNextToCornersStrategy()));
    playerTypes.put("minimax", (name) -> new MachinePlayer(name, new MiniMaxStrategy()));
    playerTypes.put("provider-capture-max", (name) -> new MachinePlayer(name,
            new FallibleStrategyAdapter(new AsManyPiecesAsPossible())));
    playerTypes.put("provider-corners", (name) -> new MachinePlayer(name,
            new FallibleStrategyAdapter(new GoForCorners())));
    playerTypes.put("provider-avoid-corners", (name) -> new MachinePlayer(name,
            new FallibleStrategyAdapter(new AvoidNextToCorners())));
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

    Player p1 = playerTypes.get(args[0]).apply(Name.X);
    Player p2 = playerTypes.get(args[1]).apply(Name.O);

    ReversiModel model = ReversiCreator.create(GameType.BASIC, 6);
    ReversiView view1 = new GUIReversiView(model, "Player 1");

    //PROVIDER VIEW SETUP
    IReversiView view2 = new cs3500.provider.view.ReversiView(new ReadOnlyModelAdapter(model));
    view2.setHotKey(KeyStroke.getKeyStroke("typed m"), "movePlay");
    view2.setHotKey(KeyStroke.getKeyStroke("typed p"), "movePass");
    view2.setTitle(HexagonState.WHITE);

    BasicReversiController controller1 = new BasicReversiController(p1, view1, model);
    BasicReversiController controller2 = new BasicReversiController(p2, new TwoWayViewAdapter(view2), model);
    model.startGame();
  }


  /**
   * Returns true if the args provided are of the correct number and type else returns false.
   * Provides error messages to the console depending on the type of error in the input.
   */
  private static boolean correctInputProvided(String[] args) {
    if (args.length != 2) {
      System.out.println("Incorrect number of args provided; requires 2");
      return false;
    }

    if (!playerTypes.containsKey(args[0]) || !playerTypes.containsKey(args[1])) {
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

