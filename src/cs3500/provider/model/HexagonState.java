package cs3500.provider.model;

/**
 * An Enum class to represent the three possible states for a hexagon within a hexagonal board:
 * empty: no tile has been placed within the hexagon,
 * black: a black tile has been placed within the hexagon,
 * white: a white tile has been placed within the hexagon.
 */
public enum HexagonState {

  EMPTY("empty"), BLACK("black"), WHITE("white");

  private final String value;

  /**
   * Instantiate a HexagonState according to a String value.
   *
   * @param value the string value of the hexagon state
   */
  HexagonState(String value) {
    this.value = value;
  }

  /**
   * Returns String value of this HexagonState.
   *
   * @return this.value
   */
  @Override
  public String toString() {
    return this.value;
  }
}
