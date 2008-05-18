package hottargui.strategy;

import hottargui.framework.*;

public class SettlementOnlyPutUnitsStrategy implements PutUnitsStrategy {
	public boolean isPutValid(Player p, Tile t, int count) {
	    if ((p.getCoins() >= count) && 
	        (t.getOwnerColor() == p.getColor()) && 
	        (t.getType() == TileType.Settlement || count == 0)) { 
			return false;
		}
		else{ 
			return true;
		}			
	}
}
