package hottargui.framework;

import java.rmi.Remote;
import java.util.Iterator;

public interface Board extends Remote {
	public boolean hasPlayer(PlayerColor pc);
	public Player getPlayer(PlayerColor pc);
	public Iterator<PlayerColor> getPlayers();
	public Iterator<? extends Tile> getBoardIterator();
	public Tile getTile( Position p );
	Tile updateOwnership(Tile t, PlayerColor pc);
	Tile updateUnitsOnTile(Tile t, int newCount);
	Player updatePlayerUnits(Player p, int newUnitCount);
}
