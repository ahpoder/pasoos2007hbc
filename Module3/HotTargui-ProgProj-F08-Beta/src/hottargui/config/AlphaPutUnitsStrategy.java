package hottargui.config;

import hottargui.framework.*;

public class AlphaPutUnitsStrategy implements PutUnitsStrategy {
	Tile t;
	Player p;


	public boolean isPutValid(Player p, Tile t) {
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
