package cs3500.reversi.model;

/**
 * Represents a direction on a hex grid. Has 3 public fields which describes the direction vector.
 * Fields are public as they are essential to the data's definition.*/
 enum Direction {
   UP_RIGHT(1, -1, 0), RIGHT(1, 0, -1),
   DOWN_RIGHT(0, 1, -1), DOWN_LEFT(-1, 1, 0),
   LEFT(-1, 0, 1), UP_LEFT(0, -1, 1);

   public int deltaQ;
   public int deltaS;
   public int deltaR;

   Direction(int deltaQ, int deltaR, int deltaS) {
     this.deltaQ = deltaQ;
     this.deltaR = deltaR;
     this.deltaS = deltaS;
   }
}
