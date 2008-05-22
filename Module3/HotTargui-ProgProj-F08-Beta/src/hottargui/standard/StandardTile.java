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

	public int getEconomicValue() {
		switch (type) {
    	case Settlement:
      	return 4;
      case Saltmine:
        return 5;
      case Oasis:
      	return 3;
      case Erg:
        return 1;
      case Reg:
        return 2;
      default:
        return 0;
    }
	}

  public int getStrategicValue(){
  	switch (type) {
    	case Settlement:
      	return 4;
      case Saltmine:
        return 5;
      case Oasis:
        return 3;
      case Feshfesh:
        return 2;
      case Mountain:
        return 1;
      default:
        return 0;
      }
  }
}
