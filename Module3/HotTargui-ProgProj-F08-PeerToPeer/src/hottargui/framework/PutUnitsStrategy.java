package hottargui.framework;

import java.rmi.Remote;

/*
*	Responsibility: Determine if tile is avalid place to put units
*
*	isPutValid() returns true if put is valid.
*/

public interface PutUnitsStrategy extends Remote {

	boolean isPutValid(Player p, Tile t, int count);
}
