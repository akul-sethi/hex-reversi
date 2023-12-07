package cs3500.reversi.model;

import cs3500.provider.model.Hexagon;
import cs3500.provider.model.HexagonState;
import cs3500.reversi.player.HumanPlayer;
import cs3500.reversi.player.Name;
import cs3500.reversi.player.Player;
import cs3500.reversi.player.PlayerAdapter;

/**
 * A utility class for adapting enums and value-classes*/
public class AdapterUtils {
  /**
   * Converts a Player to a HexagonState as they both serve the same role in their respective
   * codebases.*/
   public static HexagonState playerToHexagonState(Player player) {
     if(player == null) {
       return HexagonState.EMPTY;
     } else if(player.equals(new HumanPlayer(Name.X))) {
       return HexagonState.BLACK;
     } else {
       return HexagonState.WHITE;
     }
   }

  /**
   * Converts a HexagonState to a Player as they both serve the same role in their respective
   * codebases.*/
   public static HumanPlayer hexagonStateToPlayer(HexagonState state) {
     if (state == HexagonState.EMPTY) {
        return null;
     } else if(state == HexagonState.BLACK) {
       return new HumanPlayer(Name.X);
     } else {
       return new HumanPlayer(Name.O);
     }
   }

   /**
    * Converts a CubeCoord to a Hexagon as they both serve the same purpose.*/
   public static Hexagon cubeCoordToHexagon(CubeCoord coord){
     return new Hexagon(coord.q, coord.s, coord.r);
   }

   /**
    * Converts a Hexagon to a CubeCoord as they both serve the same purpose.*/
   public static CubeCoord hexagonToCubeCoord(Hexagon hex) {
     return new CubeCoord(hex.getS(), hex.getR(), hex.getQ());
   }

   /**
    * A helper method for converting a hexagon to its equivalent linear coordinate as the providers
    * implementation uses cube coordinates and ours uses linear.*/
   public static LinearCoord hexagonToLinearCoord(Hexagon hex) {
     CubeCoord cube = hexagonToCubeCoord(hex);
     return new BasicPoint(cube.row(), cube.column());
   }
}
