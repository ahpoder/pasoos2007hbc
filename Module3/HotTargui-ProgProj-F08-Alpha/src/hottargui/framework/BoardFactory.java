package hottargui.framework;


import java.util.Collection;

public interface BoardFactory {
	Player[] createPlayers();

	Tile[][] createTiles();
	
	Tile createTile(TileType tt, PlayerColor pc, int r, int c, int unitCount);
	
	Player createPlayer(PlayerColor pc, int unitCount);
}
