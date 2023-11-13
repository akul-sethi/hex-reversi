package cs3500.reversi.strategy;

import java.util.ArrayList;
import java.util.Optional;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.Player;

public class CaptureCornersStrategy implements FallibleReversiStrategy {
  @Override
  public Optional<ArrayList<LinearCoord>> chooseMove(ReadOnlyReversiModel model, Player forWhom,
                                                     ArrayList<LinearCoord> legalMoves) {
    ReversiModel testModel = model.getModel();
    ArrayList<LinearCoord> bestMoves = new ArrayList<>();
    if (legalMoves.isEmpty()) {
      legalMoves = Utils.allLegalMoves(testModel);
    }
    ArrayList<LinearCoord> corners = Utils.getCorners(testModel);
    for (LinearCoord move : legalMoves) {
      if (corners.contains(move)) {
        bestMoves.add(move);
      }
    }
    System.out.println("ALL MOVES POSSIBLE FROM CAPTURE CORNERS:");
    for (LinearCoord lc : bestMoves) {
      System.out.println(lc);
    }
    if (!bestMoves.isEmpty()) {
      bestMoves.sort(new Utils.upperLefterCoordComparer());
      return Optional.of(bestMoves);
    }
    return Optional.empty();
  }
}
