package hottargui.framework;

import java.util.*;

public class Board {
	private Collection<Tile> tiles;
	
	public enum MoveAttemptResult
	{
		MOVE_VALID,
		ATTACK_NEEDED,
		INVALID_MOVE,
	}

	
	public Board(BoardFactory boardFactory)
	{
		tiles = boardFactory.createTiles();
		players = boardFactory.createPlayers();
	}
	public int getPlayerCount() {
		return 4;
	}

	Player[] players;
	public Player getPlayer(int count) {
		return players[count];
	}

	public Iterator<Tile> getBoardIterator()
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
	
	public MoveAttemptResult validateMove(Position from, Position to, PlayerColor player)
	{
		Tile fromTile = getTile(from);
		Tile toTile = getTile(to);
		if (to.equals(from) ||
			toTile.getType() == TileType.Saltlake || 
			player != fromTile.getOwnerColor() ||
			fromTile.getUnitCount() <= 0 ||
			java.lang.Math.abs(fromTile.getPosition().getColumn() - toTile.getPosition().getColumn()) > 1 ||
			java.lang.Math.abs(fromTile.getPosition().getRow() - toTile.getPosition().getRow()) > 1)
		{
			return MoveAttemptResult.INVALID_MOVE;
		}
		else if (toTile.getOwnerColor() != PlayerColor.None && 
		    toTile.getOwnerColor() != fromTile.getOwnerColor() && 
			toTile.getUnitCount() != 0) {
			return MoveAttemptResult.ATTACK_NEEDED;
		}
		else
		{
			return MoveAttemptResult.MOVE_VALID;
		}
	}
}

