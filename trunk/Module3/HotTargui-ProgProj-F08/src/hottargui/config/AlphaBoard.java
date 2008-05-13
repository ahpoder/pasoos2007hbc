package hottargui.config;

import hottargui.framework.*;

import java.util.*;

public class AlphaBoard implements TestBoard {
	private Tile[][] tiles;

	private void updateBoard()
	{
		Iterator<PlayerColor> playerItt = players.keySet().iterator();
		while (playerItt.hasNext())
		{
			PlayerColor pc = playerItt.next();
			boolean found = false;
			Iterator<Tile> itt = getBoardIterator();
			while (itt.hasNext() && !found)
			{
				found = (pc == itt.next().getOwnerColor());
			}
			if (!found) // The player is dead
			{
				players.remove(pc);
				// This may validate the same players more than
				// once, yet as it happens very rarely and 
				// it increases readability it is OK.
				playerItt = players.keySet().iterator();
			}
		}
	}
	
	private BoardFactory boardFactory;
	public AlphaBoard(BoardFactory boardFactory)
	{
		this.boardFactory = boardFactory;
		tiles = boardFactory.createTiles();
		players = new HashMap<PlayerColor, Player>();
		Player[] ps = boardFactory.createPlayers();
		for (int i = 0; i < ps.length; ++i)
		{
			Player p = ps[i];
			players.put(p.getColor(), p);
		}
		updateBoard();
	}
	public int getPlayerCount() {
		return players.size();
	}
	
	HashMap<PlayerColor, Player> players;
	public Player getPlayer(PlayerColor color) {
		return players.get(color);
	}

	public Iterator<Tile> getBoardIterator()
	{
		// Create iterator from array - this can probably be done more efficiently
		ArrayList<Tile> t = new ArrayList<Tile>();
		for (int i = 0; i < 7; ++i)
			for (int ii = 0; ii < 7; ++ii)
				t.add(tiles[i][ii]);

		return t.iterator();
	}
	
	public Tile getTile( Position p )
	{
		return tiles[p.getRow()][p.getColumn()];
	}

	public boolean hasPlayer(PlayerColor pc) {
		return players.containsKey(pc);
	}

	public Tile updateOwnership(Tile t, PlayerColor pc) {
		Position p = t.getPosition();
		Tile tt = boardFactory.createTile(t.getType(), pc, p.getRow(), p.getColumn(), t.getUnitCount());
		tiles[p.getRow()][p.getColumn()] = tt;
		updateBoard();
		return tiles[p.getRow()][p.getColumn()];
	}

	public Tile updateUnitsOnTile(Tile t, int newCount) {
		Position p = t.getPosition();
		Tile tt = boardFactory.createTile(t.getType(), t.getOwnerColor(), p.getRow(), p.getColumn(), newCount);
		tiles[p.getRow()][p.getColumn()] = tt;
		return tiles[p.getRow()][p.getColumn()];
	}

	public Player updatePlayerUnits(Player p, int newCoinCount) {
		players.remove(p.getColor());
		players.put(p.getColor(), boardFactory.createPlayer(p.getColor(), newCoinCount));
		return getPlayer(p.getColor());
	}

	public Iterator<PlayerColor> getPlayers() {
		ArrayList<PlayerColor> l = new ArrayList<PlayerColor>();
		l.addAll(players.keySet());
		return l.iterator();
	}
}

