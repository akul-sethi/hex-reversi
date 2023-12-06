package cs3500.reversi.model;

import cs3500.provider.model.Hexagon;
import cs3500.provider.model.HexagonState;
import cs3500.reversi.player.HumanPlayer;
import cs3500.reversi.player.Name;
import cs3500.reversi.player.Player;

public class AdapterUtils {
   public static HexagonState playerToHexagonState(Player player) {
     if(player == null) {
       return HexagonState.EMPTY;
     } else if(player.equals(new HumanPlayer(Name.X))) {
       return HexagonState.BLACK;
     } else {
       return HexagonState.WHITE;
     }
   }
   public static Player hexagonStateToPlayer(HexagonState state) {
     if (state == HexagonState.EMPTY) {
        return null;
     } else if(state == HexagonState.BLACK) {
       return new HumanPlayer(Name.X);
     } else {
       return new HumanPlayer(Name.O);
     }
   }

   public static Hexagon cubeCoordToHexagon(CubeCoord coord){
     return new Hexagon(coord.q, coord.s, coord.r);
   }

   public static CubeCoord hexagonToCubeCoord(Hexagon hex) {
     return new CubeCoord(hex.getS(), hex.getR(), hex.getQ());
   }

   public static LinearCoord hexagonToLinearCoord(Hexagon hex) {
     CubeCoord cube = hexagonToCubeCoord(hex);
     return new BasicPoint(cube.row(), cube.column());
   }
}
