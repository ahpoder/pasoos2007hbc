package hottargui.config;

import hottargui.framework.*;

/*
* When units are bought the only valid place to put them is on the player's home settlement
 */
 
public class AlphaPutUnitsStrategy implements PutUnitsStrategy {

	public boolean isPutValid(Player p, Tile t) {
		// Is player color == tile color and is tile type == settlement?
		if ((p.getColor() == t.getOwnerColor()) && t.getType() == TileType.Settlement) {
			return true;
		}
		else {
			return false;
		}			
	}

}
