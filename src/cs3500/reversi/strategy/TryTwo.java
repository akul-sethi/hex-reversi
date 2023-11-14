package cs3500.reversi.strategy;

import java.util.ArrayList;
import java.util.Optional;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.Player;

/**
 * This class allows for the combination of two different Fallible Strategies.
 */
public class TryTwo implements FallibleReversiStrategy {
  //the two strategies to store.
  private final FallibleReversiStrategy first, second;

  /**
   * The constructor. Stores both strategies given to it, in order. The first strategy is queried
   * before the second.
   * @param first The first strategy to store.
   * @param second The second strategy to store.
   */
  public TryTwo(FallibleReversiStrategy first, FallibleReversiStrategy second) {
    this.first = first;
    this.second = second;
  }

  /**
   * The chooseMove function. In TryTwo, this works by first querying the first strategy. If the
   * first strategy returns a single move, it returns that move. If the first strategy returns no
   * moves, it returns the second strategies move. If the first strategy returns multiple
   * equally good (deemed equal by that strategy) moves, then it passes those moves to the second
   * strategy to act as a tiebreaker. The second strategy determines which of the moves is best by
   * its own rules.
   * @param model The model for look for a move in.
   * @param forWhom The player to look for a move for.
   * @param legalMoves All the current legal moves to look through for the best move. If this is
   *                   empty, the strategy will re-find all the legal moves for the player, and sort
   *                   through those.
   * @return An optional list of the best moves that the strategy given chooses.
   */
  @Override
  public Optional<ArrayList<LinearCoord>> chooseMove(ReadOnlyReversiModel model, Player forWhom,
                                                     ArrayList<LinearCoord> legalMoves) {
    ReversiModel testModel = model.getModel();
    if (legalMoves.isEmpty()) {
      legalMoves = Utils.allLegalMoves(testModel);
    }
    Optional<ArrayList<LinearCoord>> ans = this.first.chooseMove(model, forWhom, legalMoves);
    if (ans.isPresent()) {
      if (ans.get().size() > 1) {
        return this.second.chooseMove(model, forWhom, ans.get());
      }
      return ans;
    }
    return this.second.chooseMove(model, forWhom, new ArrayList<LinearCoord>());
  }
}
