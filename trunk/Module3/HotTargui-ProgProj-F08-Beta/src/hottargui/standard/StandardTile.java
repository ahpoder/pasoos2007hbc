package	hottargui.standard;

import hottargui.framework.*;

/** Standard tile implementation. */

public class StandardTile implements Tile {
  
  protected Position position;
  protected TileType type;
  protected PlayerColor player;

  /** number of units on this tile */
  private int unit;

  public StandardTile(TileType t, PlayerColor p, int r, int c) {
    position = new Position(r,c);
    type = t;
    player = p;
    unit = 0;
  }

  public void changeUnitCount( int newValue ) {
    unit = newValue;
  }
  public void changePlayerColor( PlayerColor newColor ) {
    player = newColor;
  }
  public Position getPosition() { return position; }
  public PlayerColor getOwnerColor() { return player; }
  public int getUnitCount() { return unit; }
  public TileType getType() { return type; }

  public String toString() {
    return "Tile ("+position.getRow()+","+position.getColumn()+") "+
	   "units="+ unit;
  }
	
  public int getStrategicValue(){
	return 0; //fake it
  };
}
