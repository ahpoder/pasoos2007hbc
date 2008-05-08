package hottargui.framework;

/** Tile represents a single territory tile of a given type.

    Responsibilities:
    1) Know its type and position on the board.
    2) Know its owner and the number of units on it.

 */

public interface Tile {

  /** return position of this tile on the board. 
   * @return position of tile.
   */
  public Position getPosition();

  /** return the color of the player who owns this tile.
   * @return color of owner, may be NONE.
   */
  public PlayerColor getOwnerColor();
  
  /** return the number of units on this tile.
   * @return the number of units on this tile.
   */
  public int getUnitCount();
  
  /** return the type of this tile: one of the enumeration
   * constants: TileType.Settlement etc. 
   * @return tile type.
   */
  public TileType getType();

  public int getStrategicValue();
}
