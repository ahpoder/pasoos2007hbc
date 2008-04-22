package hottargui.framework;


import java.util.Collection;

public interface BoardFactory {
	Player[] createPlayers();

	Collection<Tile> createTiles();
}
