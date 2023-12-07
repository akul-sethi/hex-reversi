package cs3500.provider.model;
import java.util.HashMap;

public interface IReversiBoard {

  /**
   * Change a single hexagon's state in the board (the given hexagon) to the given new state.
   *
   * @param origin   the hexagon to be changed
   * @param newState the state the hexagon is being changed to
   * @throws IllegalArgumentException if the given hexagon is not in the board
   *                                  if given newState is null
   */
  void changeHexState(Hexagon origin, HexagonState newState);

  /**
   * Determines if the given hexagon is a valid move in this board for the given state.
   *
   * @param origin   the hexagon that is being checked in the move
   * @param newState the state that is being check in the move
   * @return true if the move is valid for the given hexagon and state
   * @throws IllegalArgumentException if the hexagon is not in the board
   *                                  if the newState is null
   */
  boolean validMove(Hexagon origin, HexagonState newState);

  /**
   * Method to set the start state of the board.
   * Method only called when starting the game (ReversiModel.startGame()).
   */
  void setStartState();

  /**
   * Method checks to see if there are any empty hexagons in the board.
   *
   * @return true if there are no empty hexagons in the board.
   */
  boolean noEmptyHexes();

  /**
   * Helper method to check if a valid move exists for the given HexagonState
   * in this board.
   *
   * @return false if there are no valid moves for the given HexagonState.
   */
  boolean validMoveExistsForState(HexagonState state);

  /**
   * Method that changes the board according to the provided origin point and new state.
   *
   * @param origin   the place of new Hexagon tile
   * @param newState the new Hexagon state.
   */
  void doMove(Hexagon origin, HexagonState newState);

  /**
   * Method that returns the HexagonState for the Hexagon in the board at coordinates s, q, r.
   *
   * @param s int representing s coordinate of the corresponding hexagon
   * @param q int representing q coordinate of the corresponding hexagon
   * @param r int representing r coordinate of the corresponding hexagon
   * @return the HexagonState for the Hexagon in the board at the coordinates s, q, r
   * @throws IllegalArgumentException if the hexagon at the coordinates s, q, and r is not in the
   *                                  board.
   */
  HexagonState getHexagonStateAt(int s, int q, int r);

  /**
   * Method that returns this board's HashMap of Hexagons -> HexagonStates, but creates
   * a new HashMap to prevent mutation.
   *
   * @return returns a copy of the HashMap which represents the board. The keys are hexagons, and
   *         the values are HexagonStates.
   */
  HashMap<Hexagon, HexagonState> getBoardHashMap();

  /**
   * Method that returns either HexagonState black or white, depending on which appears
   * more in this board.
   *
   * @return HexagonState (either black or white) which appears more
   * @throws IllegalStateException if the number of black and white tiles is equal
   */
  HexagonState getWinningHexState();

  /**
   * Returns the number of tiles in this board for the given HexState.
   *
   * @return int number of tiles of a given state
   */
  int getStateScore(HexagonState hexagonState);

  /**
   * Returns the number of potential pieces gained for the given state
   * if the given hexagon is changed to said state.
   *
   * @param h the changed hexagon
   * @param s the hexagon state of interest
   * @return int the number of pieces gained
   */
  int getNumPiecesGained(Hexagon h, HexagonState s);

}
