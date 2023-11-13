package cs3500.reversi.strategy;

import java.util.ArrayList;
import java.util.Optional;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.Player;

public class AvoidNextToCornersStrategy implements FallibleReversiStrategy {
  @Override
  public Optional<ArrayList<LinearCoord>> chooseMove(ReadOnlyReversiModel model, Player forWhom,
                                                     ArrayList<LinearCoord> legalMoves) {
    ReversiModel testModel = model.getModel();
    if (legalMoves.isEmpty()) {
      legalMoves = Utils.allLegalMoves(testModel);
    }
    ArrayList<LinearCoord> goodMoves = new ArrayList<>();
    ArrayList<LinearCoord> nextToCorners = Utils.getNextToCorners(testModel);
    for (LinearCoord lm : legalMoves) {
      if (!nextToCorners.contains(lm)) {
        goodMoves.add(lm);
      }
    }
    if (goodMoves.isEmpty()) {
      return Optional.empty();
    }
    goodMoves.sort(new Utils.upperLefterCoordComparer());
    System.out.println("ALL MOVES POSSIBLE FROM AVOID NEXT TO CORNERS:");
    for (LinearCoord lc : goodMoves) {
      System.out.println(lc);
    }
    return Optional.of(goodMoves);
  }
}
