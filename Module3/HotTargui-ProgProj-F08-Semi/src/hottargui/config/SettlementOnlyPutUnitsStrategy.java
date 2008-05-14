package hottargui.config;

import hottargui.framework.*;

public class SettlementOnlyPutUnitsStrategy implements PutUnitsStrategy {
	public boolean isPutValid(Player p, Tile t) {
		if (t.getOwnerColor() != p.getColor()){
			return false;
		}
		else{ 
			return true;
		}			
	}
}
