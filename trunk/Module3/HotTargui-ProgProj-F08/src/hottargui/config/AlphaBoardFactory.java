package hottargui.config;

import java.util.ArrayList;
import java.util.Collection;

import hottargui.framework.BoardFactory;
import hottargui.framework.PlayerColor;
import hottargui.framework.Position;
import hottargui.framework.Tile;
import hottargui.framework.TileImpl;
import hottargui.framework.TileType;

public class AlphaBoardFactory implements BoardFactory {

	public Collection<Tile> create() {
		ArrayList<Tile> list = new ArrayList<Tile>();

		  list.add(new TileImpl(PlayerColor.Red, new Position(0,0), TileType.Settlement, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(0,1), TileType.Erg, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(0,2), TileType.Oasis, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(0,3), TileType.Reg, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(0,4), TileType.Oasis, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(0,5), TileType.Feshfesh, 0));
		  list.add(new TileImpl(PlayerColor.Green, new Position(0,6), TileType.Settlement, 0));
		  
		  list.add(new TileImpl(PlayerColor.None, new Position(1,0), TileType.Erg, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(1,1), TileType.Oasis, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(1,2), TileType.Feshfesh, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(1,3), TileType.Erg, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(1,4), TileType.Feshfesh, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(1,5), TileType.Reg, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(1,6), TileType.Reg, 0));

		  list.add(new TileImpl(PlayerColor.None, new Position(2,0), TileType.Oasis, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(2,1), TileType.Saltlake, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(2,2), TileType.Feshfesh, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(2,3), TileType.Oasis, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(2,4), TileType.Reg, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(2,5), TileType.Feshfesh, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(2,6), TileType.Reg, 0));

		  list.add(new TileImpl(PlayerColor.None, new Position(3,0), TileType.Saltlake, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(3,1), TileType.Reg, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(3,2), TileType.Saltlake, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(3,3), TileType.Saltmine, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(3,4), TileType.Erg, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(3,5), TileType.Feshfesh, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(3,6), TileType.Mountain, 0));

		  list.add(new TileImpl(PlayerColor.None, new Position(4,0), TileType.Mountain, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(4,1), TileType.Erg, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(4,2), TileType.Saltlake, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(4,3), TileType.Erg, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(4,4), TileType.Mountain, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(4,5), TileType.Reg, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(4,6), TileType.Feshfesh, 0));

		  list.add(new TileImpl(PlayerColor.None, new Position(5,0), TileType.Oasis, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(5,1), TileType.Reg, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(5,2), TileType.Reg, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(5,3), TileType.Erg, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(5,4), TileType.Reg, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(5,5), TileType.Erg, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(5,6), TileType.Mountain, 0));

		  list.add(new TileImpl(PlayerColor.Blue, new Position(6,0), TileType.Settlement, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(6,1), TileType.Erg, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(6,2), TileType.Reg, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(6,3), TileType.Feshfesh, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(6,4), TileType.Erg, 0));
		  list.add(new TileImpl(PlayerColor.None, new Position(6,5), TileType.Erg, 0));
		  list.add(new TileImpl(PlayerColor.Yellow, new Position(6,6), TileType.Settlement, 0));
		return list;
	}
}
