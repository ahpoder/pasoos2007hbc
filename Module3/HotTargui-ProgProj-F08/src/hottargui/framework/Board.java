package hottargui.framework;

import java.util.*;

public class Board {
	private Collection<Tile> tiles;
	
	public Board(BoardFactory boardFactory)
	{
		tiles = boardFactory.create();
	}
	public int getPlayerCount() {
		return 4;
	}

	public Iterator<? extends Tile> getBoardIterator()
	{
		return tiles.iterator();
	}
	
	public Tile getTile( Position p )
	{
		Tile res = null;
		Iterator<? extends Tile> itt = getBoardIterator();
		while (itt.hasNext())
		{
			Tile t = itt.next();
			if (t.getPosition().equals(p))
			{
				res = t;
				break;
			}
		}
		if (res == null)
		{
			throw new IllegalArgumentException("Position not found in collection");
		}
		return res;
	}
}

