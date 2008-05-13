package hottargui.config;

import hottargui.framework.*;
import hottargui.standard.*;

public class TestBoardFactory implements BoardFactory {
	public AlphaBoardFactory abf = new AlphaBoardFactory();
	public StandardTile[][] tiles = new StandardTile[7][7];
	public TestBoardFactory(TileType tileType)
	{
		for (int i = 0; i < 7; ++i)
		{
			for (int ii = 0; ii < 7; ++ii)
			{
				tiles[i][ii] = new StandardTile(tileType, PlayerColor.None, i, ii);
			}
		}
		
		tiles[0][0] = new StandardTile(TileType.Settlement, PlayerColor.Red, 0, 0);
		tiles[0][0].changeUnitCount(10);
		tiles[0][6] = new StandardTile(TileType.Settlement, PlayerColor.Green, 0, 6);
		tiles[0][6].changeUnitCount(10);
		tiles[6][0] = new StandardTile(TileType.Settlement, PlayerColor.Blue, 6, 0);
		tiles[6][0].changeUnitCount(10);
		tiles[6][6] = new StandardTile(TileType.Settlement, PlayerColor.Yellow, 6, 6);
		tiles[6][6].changeUnitCount(10);
	}

	public Player[] createPlayers() {
		return abf.createPlayers();
	}
		
	public Tile createTile(TileType tt, PlayerColor pc, int r, int c,
			int unitCount) {
		return abf.createTile(tt, pc, r, c, unitCount);
	}

	public Tile[][] createTiles() {
		return tiles;
	}

	public Player createPlayer(PlayerColor pc, int unitCount) {
		return abf.createPlayer(pc, unitCount);
	}
}
