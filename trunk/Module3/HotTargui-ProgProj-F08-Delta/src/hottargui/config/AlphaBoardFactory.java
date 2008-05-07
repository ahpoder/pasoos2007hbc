package hottargui.config;

import java.util.ArrayList;
import java.util.Collection;

import hottargui.framework.BoardFactory;
import hottargui.framework.Player;
import hottargui.framework.PlayerColor;
import hottargui.framework.Tile;
import hottargui.framework.TileType;
import hottargui.standard.StandardPlayer;
import hottargui.standard.StandardTile;

public class AlphaBoardFactory implements BoardFactory {

	public Collection<Tile> createTiles() {
		ArrayList<Tile> list = new ArrayList<Tile>();

		StandardTile t = new StandardTile(TileType.Settlement, PlayerColor.Red, 0, 0);
		t.changeUnitCount(7);
		list.add(t);
		  list.add(new StandardTile(TileType.Erg, PlayerColor.None, 0,1));
		  list.add(new StandardTile(TileType.Oasis, PlayerColor.None, 0,2));
		  list.add(new StandardTile(TileType.Reg, PlayerColor.None, 0,3));
		  list.add(new StandardTile(TileType.Oasis, PlayerColor.None, 0,4));
		  list.add(new StandardTile(TileType.Feshfesh, PlayerColor.None, 0,5));
		  t = new StandardTile(TileType.Settlement, PlayerColor.Green, 0, 6);
	      t.changeUnitCount(7);
		  list.add(t);
		  
		  list.add(new StandardTile(TileType.Erg, PlayerColor.None, 1,0));
		  list.add(new StandardTile(TileType.Oasis, PlayerColor.None, 1,1));
		  list.add(new StandardTile(TileType.Feshfesh, PlayerColor.None, 1,2));
		  list.add(new StandardTile(TileType.Erg, PlayerColor.None, 1,3));
		  list.add(new StandardTile(TileType.Feshfesh, PlayerColor.None, 1,4));
		  list.add(new StandardTile(TileType.Reg, PlayerColor.None, 1,5));
		  list.add(new StandardTile(TileType.Reg, PlayerColor.None, 1,6));

		  list.add(new StandardTile(TileType.Oasis, PlayerColor.None, 2,0));
		  list.add(new StandardTile(TileType.Saltlake, PlayerColor.None, 2,1));
		  list.add(new StandardTile(TileType.Feshfesh, PlayerColor.None, 2,2));
		  list.add(new StandardTile(TileType.Oasis, PlayerColor.None, 2,3));
		  list.add(new StandardTile(TileType.Reg, PlayerColor.None, 2,4));
		  list.add(new StandardTile(TileType.Feshfesh, PlayerColor.None, 2,5));
		  list.add(new StandardTile(TileType.Reg, PlayerColor.None, 2,6));

		  list.add(new StandardTile(TileType.Saltlake, PlayerColor.None, 3,0));
		  list.add(new StandardTile(TileType.Reg, PlayerColor.None, 3,1));
		  list.add(new StandardTile(TileType.Saltlake, PlayerColor.None, 3,2));
		  list.add(new StandardTile(TileType.Saltmine, PlayerColor.None, 3,3));
		  list.add(new StandardTile(TileType.Erg, PlayerColor.None, 3,4));
		  list.add(new StandardTile(TileType.Feshfesh, PlayerColor.None, 3,5));
		  list.add(new StandardTile(TileType.Mountain, PlayerColor.None, 3,6));

		  list.add(new StandardTile(TileType.Mountain, PlayerColor.None, 4,0));
		  list.add(new StandardTile(TileType.Erg, PlayerColor.None, 4,1));
		  list.add(new StandardTile(TileType.Saltlake, PlayerColor.None, 4,2));
		  list.add(new StandardTile(TileType.Erg, PlayerColor.None, 4,3));
		  list.add(new StandardTile(TileType.Mountain, PlayerColor.None, 4,4));
		  list.add(new StandardTile(TileType.Reg, PlayerColor.None, 4,5));
		  list.add(new StandardTile(TileType.Feshfesh, PlayerColor.None, 4,6));

		  list.add(new StandardTile(TileType.Oasis, PlayerColor.None, 5,0));
		  list.add(new StandardTile(TileType.Reg, PlayerColor.None, 5,1));
		  list.add(new StandardTile(TileType.Reg, PlayerColor.None, 5,2));
		  list.add(new StandardTile(TileType.Erg, PlayerColor.None, 5,3));
		  list.add(new StandardTile(TileType.Reg, PlayerColor.None, 5,4));
		  list.add(new StandardTile(TileType.Erg, PlayerColor.None, 5,5));
		  list.add(new StandardTile(TileType.Mountain, PlayerColor.None, 5,6));

		  t = new StandardTile(TileType.Settlement, PlayerColor.Blue, 6, 0);
	      t.changeUnitCount(7);
		  list.add(t);
		  list.add(new StandardTile(TileType.Erg, PlayerColor.None, 6,1));
		  list.add(new StandardTile(TileType.Reg, PlayerColor.None, 6,2));
		  list.add(new StandardTile(TileType.Feshfesh, PlayerColor.None, 6,3));
		  list.add(new StandardTile(TileType.Erg, PlayerColor.None, 6,4));
		  list.add(new StandardTile(TileType.Erg, PlayerColor.None, 6,5));
		  t = new StandardTile(TileType.Settlement, PlayerColor.Yellow, 6, 6);
	      t.changeUnitCount(7);
		  list.add(t);
		return list;
	}

	public Player[] createPlayers() {
		Player[] temp = new Player[4];
		temp[0] = new StandardPlayer(PlayerColor.Red);
		temp[1] = new StandardPlayer(PlayerColor.Green);
		temp[2] = new StandardPlayer(PlayerColor.Blue);
		temp[3] = new StandardPlayer(PlayerColor.Yellow);
		return temp;
	}
}
