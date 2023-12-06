package cs3500.provider.strategy;

import java.util.List;

import cs3500.provider.IPlayer;
import cs3500.provider.model.Hexagon;
import cs3500.provider.model.IReadonlyReversiModel;

/**
 * Interface to represent strategies in the Reversi game.
 */
public interface IReversiStrategy {

  /**
   * Chooses the best move for the player using the strategy.
   * If the strategy has no valid moves, default to the asManyPiecesAsPossible strategy.
   * If there are no valid moves for the player, return an empty list of hexagons.
   * @param model Readonly ReversiModel representing the game being played
   * @param forWhom the IPlayer making the move
   * @return a list of hexagons representing the move to be made
   *         if empty list then no valid moves (player must pass)
   *         Will return at max one hexagon in the list (the hexagon to be moved to).
   */
  List<Hexagon> chooseMove(IReadonlyReversiModel model, IPlayer forWhom);

  /**
   * Helper method for the chooseMove method which returns a list of hexagons representing the
   * possible moves to be made by the player given the strategies and the possible hexagons to
   * choose from.
   * @param model Readonly ReversiModel representing the game being played
   * @param forWhom the IPlayer making the move
   * @param possibles list of hexagons to choose from for moves
   * @return a list of hexagons which are the best moves given the strategy
   *         if empty list then no valid moves to be made
   *         if more than one hexagon in the list then this strategy allows for ties.
   */
  List<Hexagon> chooseMoveWithPossibles(IReadonlyReversiModel model, IPlayer forWhom,
                                        List<Hexagon> possibles);
}
