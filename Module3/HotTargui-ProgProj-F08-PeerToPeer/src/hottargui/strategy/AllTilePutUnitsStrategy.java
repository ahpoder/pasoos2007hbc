package hottargui.strategy;

import hottargui.framework.*;

public class AllTilePutUnitsStrategy implements PutUnitsStrategy {
	Tile t;
	Player p;

	public boolean isPutValid(Player p, Tile t, int count) {
		this.t = t;
		this.p = p;
		if (t.getOwnerColor() != p.getColor()){
			return false;
		}
		else{ 
			return true;
		}			
	}
}
