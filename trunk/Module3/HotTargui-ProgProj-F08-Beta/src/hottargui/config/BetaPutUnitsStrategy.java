package hottargui.config;

import hottargui.framework.*;

/*
* When units are bought they can be put in any tile that is in the player's possession
 */

public class BetaPutUnitsStrategy implements PutUnitsStrategy {

	public boolean isPutValid(Player p, Tile t) {
		
		// Is player color identical to tile color?
		if (p.getColor() == t.getOwnerColor()){
			return true;
		}
		else{ 
			return false;
		}			
	}
}
