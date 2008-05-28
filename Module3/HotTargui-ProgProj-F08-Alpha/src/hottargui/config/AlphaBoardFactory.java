package hottargui.config;

import hottargui.framework.*;
import hottargui.standard.*;

public class AlphaBoardFactory implements BoardFactory {

	public Tile[][] createTiles() {
		Tile[][] tiles = new Tile[7][7];

		tiles[0][0] = createTile(TileType.Settlement, PlayerColor.Red, 0, 0, 10);
		tiles[0][1] = createTile(TileType.Erg, PlayerColor.None, 0,1, 0);
		tiles[0][2] = createTile(TileType.Oasis, PlayerColor.None, 0,2, 0);
		tiles[0][3] = createTile(TileType.Reg, PlayerColor.None, 0,3, 0);
		tiles[0][4] = createTile(TileType.Oasis, PlayerColor.None, 0,4, 0);
		tiles[0][5] = createTile(TileType.Feshfesh, PlayerColor.None, 0,5, 0);
		tiles[0][6] = createTile(TileType.Settlement, PlayerColor.Green, 0, 6, 10);
		  
		tiles[1][0] = createTile(TileType.Erg, PlayerColor.None, 1,0, 0);
		tiles[1][1] = createTile(TileType.Oasis, PlayerColor.None, 1,1, 0);
		tiles[1][2] = createTile(TileType.Feshfesh, PlayerColor.None, 1,2, 0);
		tiles[1][3] = createTile(TileType.Erg, PlayerColor.None, 1,3, 0);
		tiles[1][4] = createTile(TileType.Feshfesh, PlayerColor.None, 1,4, 0);
		tiles[1][5] = createTile(TileType.Reg, PlayerColor.None, 1,5, 0);
		tiles[1][6] = createTile(TileType.Reg, PlayerColor.None, 1,6, 0);

		tiles[2][0] = createTile(TileType.Oasis, PlayerColor.None, 2,0, 0);
		tiles[2][1] = createTile(TileType.Saltlake, PlayerColor.None, 2,1, 0);
		tiles[2][2] = createTile(TileType.Feshfesh, PlayerColor.None, 2,2, 0);
		tiles[2][3] = createTile(TileType.Oasis, PlayerColor.None, 2,3, 0);
		tiles[2][4] = createTile(TileType.Reg, PlayerColor.None, 2,4, 0);
		tiles[2][5] = createTile(TileType.Feshfesh, PlayerColor.None, 2,5, 0);
		tiles[2][6] = createTile(TileType.Reg, PlayerColor.None, 2,6, 0);

		tiles[3][0] = createTile(TileType.Saltlake, PlayerColor.None, 3,0, 0);
		tiles[3][1] = createTile(TileType.Reg, PlayerColor.None, 3,1, 0);
		tiles[3][2] = createTile(TileType.Saltlake, PlayerColor.None, 3,2, 0);
		tiles[3][3] = createTile(TileType.Saltmine, PlayerColor.None, 3,3, 0);
		tiles[3][4] = createTile(TileType.Erg, PlayerColor.None, 3,4, 0);
		tiles[3][5] = createTile(TileType.Feshfesh, PlayerColor.None, 3,5, 0);
		tiles[3][6] = createTile(TileType.Mountain, PlayerColor.None, 3,6, 0);

		tiles[4][0] = createTile(TileType.Mountain, PlayerColor.None, 4,0, 0);
		tiles[4][1] = createTile(TileType.Erg, PlayerColor.None, 4,1, 0);
		tiles[4][2] = createTile(TileType.Saltlake, PlayerColor.None, 4,2, 0);
		tiles[4][3] = createTile(TileType.Erg, PlayerColor.None, 4,3, 0);
		tiles[4][4] = createTile(TileType.Mountain, PlayerColor.None, 4,4, 0);
		tiles[4][5] = createTile(TileType.Reg, PlayerColor.None, 4,5, 0);
		tiles[4][6] = createTile(TileType.Feshfesh, PlayerColor.None, 4,6, 0);

		tiles[5][0] = createTile(TileType.Oasis, PlayerColor.None, 5,0, 0);
		tiles[5][1] = createTile(TileType.Reg, PlayerColor.None, 5,1, 0);
		tiles[5][2] = createTile(TileType.Reg, PlayerColor.None, 5,2, 0);
		tiles[5][3] = createTile(TileType.Erg, PlayerColor.None, 5,3, 0);
		tiles[5][4] = createTile(TileType.Reg, PlayerColor.None, 5,4, 0);
		tiles[5][5] = createTile(TileType.Erg, PlayerColor.None, 5,5, 0);
		tiles[5][6] = createTile(TileType.Mountain, PlayerColor.None, 5,6, 0);

		tiles[6][0] = createTile(TileType.Settlement, PlayerColor.Blue, 6, 0, 10);
		tiles[6][1] = createTile(TileType.Erg, PlayerColor.None, 6,1, 0);
		tiles[6][2] = createTile(TileType.Reg, PlayerColor.None, 6,2, 0);
		tiles[6][3] = createTile(TileType.Feshfesh, PlayerColor.None, 6,3, 0);
		tiles[6][4] = createTile(TileType.Erg, PlayerColor.None, 6,4, 0);
		tiles[6][5] = createTile(TileType.Erg, PlayerColor.None, 6,5, 0);
		tiles[6][6] = createTile(TileType.Settlement, PlayerColor.Yellow, 6, 6, 10);
		return tiles;
	}

	public Tile createTile(TileType tt, PlayerColor pc, int r, int c, int unitCount)
	{
		StandardTile st = new StandardTile(tt, pc, r, c);
		st.changeUnitCount(unitCount);
		return st;
	}
	
	public Player[] createPlayers() {
		Player[] temp = new Player[4];
		temp[0] = createPlayer(PlayerColor.Red, 10);
		temp[1] = createPlayer(PlayerColor.Green, 10);
		temp[2] = createPlayer(PlayerColor.Blue, 10);
		temp[3] = createPlayer(PlayerColor.Yellow, 10);
		return temp;
	}

	public Player createPlayer(PlayerColor pc, int unitCount) {
		StandardPlayer sp = new StandardPlayer(pc);
		sp.withdraw(sp.getCoins()); // A default value is hard-coded in standardPlayer, so we remove it
		sp.add(unitCount);
		return sp;
	}
}
