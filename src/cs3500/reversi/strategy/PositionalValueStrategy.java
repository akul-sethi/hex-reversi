package cs3500.reversi.strategy;

import java.util.ArrayList;
import java.util.Optional;

import cs3500.reversi.model.BasicPoint;
import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.Player;

public class PositionalValueStrategy implements FallibleReversiStrategy {

  @Override
  public Optional<ArrayList<LinearCoord>> chooseMove(ReadOnlyReversiModel model, Player forWhom, ArrayList<LinearCoord> legalMoves) {
    ArrayList<ArrayList<Integer>> valueArray = getPositionValueArray(model.getModel());
    return Optional.empty();
  }

  private ArrayList<ArrayList<Integer>> getPositionValueArray(ReversiModel model) {
    int width = Math.abs(model.getLeftCol() - model.getRightCol());
    int height = Math.abs(model.getTopRow() - model.getBottomRow());
    int edgeVal = Math.min(width, height) / 2;
    ArrayList<ArrayList<Integer>> posValArray = new ArrayList<>();
    for (int row = 0; row < height; row += 1) {
      posValArray.add(new ArrayList<>());
      for (int col = 0; col < width; col += 1) {
        try {
          model.playerAt(new BasicPoint(row, col));
          posValArray.get(row).add(Math.abs(edgeVal -  Math.max(row, col)));
          
        }
        catch (Exception e) {
          // do nothing!
        }
      }
    }
    for (ArrayList<Integer> row : posValArray) {
      String rowStr = "";
      for (Integer col : row) {
        rowStr += " " + col;
      }
      System.out.println(rowStr);
    }
    return posValArray;
  }
}
