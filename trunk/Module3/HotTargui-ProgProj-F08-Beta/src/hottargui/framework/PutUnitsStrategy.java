package hottargui.framework;

/*
*	Responsibility: Determine if tile is a valid place to put units
*
*	isPutValid() returns true if put is valid.
*/

public interface PutUnitsStrategy {

	boolean isPutValid(Player p, Tile t);
}
