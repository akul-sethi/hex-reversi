package cs3500.provider.strategy;

import java.util.ArrayList;
import java.util.List;

import cs3500.provider.IPlayer;
import cs3500.provider.model.Hexagon;
import cs3500.provider.model.IReadonlyReversiModel;

/**
 * Abstract class for all strategies in the reversi game to extend.
 * Has abstract classes ChooseMove, chooseMoveHelper, possibleMoves, and BreakTie.
 * Abstract class was created to avoid duplication and simplify code.
 */
public class AbstractReversiStrategy implements IReversiStrategy {

  @Override
  public List<Hexagon> chooseMove(IReadonlyReversiModel model, IPlayer forWhom) {
    List<Hexagon> possibles = this.possibleMoves(model, forWhom);
    if (possibles.isEmpty()) {
      return possibles;
    }
    List<Hexagon> answer = this.chooseMoveWithPossibles(model, forWhom, possibles);
    // if strategy used does not yield a valid move then default to asManyPiecesAsPossible strategy
    if (answer.isEmpty()) {
      answer = (new AsManyPiecesAsPossible()).chooseMove(model, forWhom);
    }
    // breaks the tie on the output of the strategy used
    return this.breakTie(answer);
  }

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
  @Override
  public List<Hexagon> chooseMoveWithPossibles(IReadonlyReversiModel model, IPlayer forWhom,
                                               List<Hexagon> possibles) {
    return possibles;
  }

  /**
   * Creates a list of hexagons which includes all hexagons that the player can move to with a
   * valid move.
   * @param model ReversiModel representing the game being played
   * @param forWhom the IPlayer making the move
   * @return list of hexagons that the player can move to with a valid move.
   */
  private List<Hexagon> possibleMoves(IReadonlyReversiModel model, IPlayer forWhom) {
    List<Hexagon> possibles = new ArrayList<>();
    for (Hexagon h : model.getCopyBoardHashMap().keySet()) {
      // if the hexagon (h) is valid move for this player, add it to list of possible hexagons
      if (model.isValidMove(h, forWhom.getHexagonState())) {
        possibles.add(h);
      }
    }
    return possibles;
  }

  /**
   * Method breaks ties given a list of hexagons. Chooses the top-most, left-most hexagon in the
   * list. Top-most takes priority over left-most. If hexagon A is to the right of hexagon B but
   * above B, then hexagon A wins.
   * @param hexagons list of hexagons in the tie.
   * @return a list of hexagons
   *         if list is empty then no valid hexagons to move to (given list hexagons was empty)
   *         or returns a list of one hexagon (the hexagon that won the tie).
   */
  private List<Hexagon> breakTie(List<Hexagon> hexagons) {

    if (hexagons.isEmpty()) {
      return hexagons;
    }
    Hexagon answer = hexagons.get(0);
    int minR = hexagons.get(0).getR();
    int minS = hexagons.get(0).getS();

    for (int i = 1; i < hexagons.size(); i++) {
      // top-most takes precedence (lowest r value)
      int thisS = hexagons.get(i).getS();
      int thisR = hexagons.get(i).getR();
      // if hexagon is higher than current hexagon or is equal height and more left, make in ans
      if (thisR < minR || (thisR == minR && thisS < minS)) {
        answer = hexagons.get(i);
        minR = hexagons.get(i).getR();
        minS = hexagons.get(i).getS();
      }
    }
    return new ArrayList<>(List.of(answer));
  }

}
