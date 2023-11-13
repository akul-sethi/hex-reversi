package cs3500.reversi.strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.spi.LocaleNameProvider;

import cs3500.reversi.model.LinearCoord;
import cs3500.reversi.model.ReadOnlyReversiModel;
import cs3500.reversi.model.ReversiModel;
import cs3500.reversi.player.Player;

public class MiniMaxStrategy implements InfallibleReversiStrategy {
  static final FallibleReversiStrategy SuperStrategy =
          new TryTwo(new CaptureCornersStrategy(), new TryTwo(new AvoidNextToCornersStrategy(),
                  new CaptureMaxStrategy()));
  @Override
  public LinearCoord chooseMove(ReadOnlyReversiModel model, Player forWhom) {
    ReversiModel testModel = model.getModel();
    int myScore = testModel.getPlayerScore(forWhom);
    testModel.pass();
    Player opponent = testModel.nextToPlay();
    int opponentScore = testModel.getPlayerScore(opponent);
    testModel.pass();
    int startDifference = myScore - opponentScore;
    int maxDifference = Integer.MIN_VALUE;
    ArrayList<LinearCoord> bestMoves = new ArrayList<>();
    ArrayList<LinearCoord> allMoves = Utils.allLegalMoves(testModel);
    for (LinearCoord lm : allMoves) {
      ReversiModel tempModel = testModel.getModel();
      tempModel.placePiece(lm.row, lm.col);
      ArrayList<LinearCoord> opponentLegalMoves = Utils.allLegalMoves(tempModel);
      ArrayList<LinearCoord>
      for (LinearCoord olm : opponentLegalMoves) {
        ArrayList<Integer> opponentMoves = new ArrayList<>();
        ReversiModel tempTempModel = tempModel.getModel();
        tempTempModel.placePiece(olm.row, olm.col);
        int tempOpponentScore = tempTempModel.getPlayerScore(opponent);
        int tempMyScore = tempTempModel.getPlayerScore(forWhom);
        int tempDifference = tempMyScore - tempOpponentScore;
        opponentMoves.add(tempDifference);
        opponentMoves.sort(Collections.reverseOrder());
      }
    }
  }
}
