package hottargui.config;

import hottargui.framework.*;

import java.util.*;

/**
 * This implementation of the BoardFactory supports correct placement of the different tile types on the board.
 * The tiles are positioned according the specification.
 */
public class RandomOrderBoardFactory implements BoardFactory {

    private Deck deck;

    private StaticBoardFactory sgf = new StaticBoardFactory();
    public RandomOrderBoardFactory() {
        deck = new DeltaDeck();
    }

    public Tile[][] createTiles() {

        // Generate all the available Positions
        ArrayList<Tile> tiles = new ArrayList<Tile>();

        Position position;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                position = new Position(i, j);

                if (!position.equals(new Position(3,3))) {
                    Tile tile = deck.getTile(position);
                    tiles.add(tile);
                } else {
                    Tile saltmineTile = createTile(TileType.Saltmine, PlayerColor.None, position.getRow(), position.getColumn(), 0);
                    tiles.add(saltmineTile);
                }
            }
        }

        int row = 0;
        int column = 0;
        ListIterator<Tile> tilesIterator = tiles.listIterator();
        Tile tile = null;

        while (tilesIterator.hasNext()) {
            tile = tilesIterator.next();
            position = tile.getPosition();
            row = position.getRow();
            column = position.getColumn();

            if (row==0 && column==0) {
                tilesIterator.remove();
                Tile redSettlement = createTile(TileType.Settlement, PlayerColor.Red, 0, 0, 10);
                tilesIterator.add(redSettlement);
            } else if (row==0 && column==6) {
                tilesIterator.remove();
                Tile greenSettlement = createTile(TileType.Settlement, PlayerColor.Green, 0, 6, 10);
                tilesIterator.add(greenSettlement);
            } else if (row==6 && column==0) {
                tilesIterator.remove();
                Tile blueSettlement = createTile(TileType.Settlement, PlayerColor.Blue, 6, 0, 10);
                tilesIterator.add(blueSettlement);
            } else if (row==6 && column==6) {
                tilesIterator.remove();
                Tile yellowSettlement = createTile(TileType.Settlement, PlayerColor.Yellow, 6, 6, 10);
                tilesIterator.add(yellowSettlement);
            }
        }
        
        Tile[][] tilesArray = new Tile[7][7];
        Iterator<Tile> itt = tiles.iterator();
        while (itt.hasNext())
        {
        	Tile t = itt.next();
        	Position pos = t.getPosition();
        	tilesArray[pos.getRow()][pos.getColumn()] = t;
        }
        
        return tilesArray;
    }

	public Player[] createPlayers() {
		return sgf.createPlayers();
	}

	public Player createPlayer(PlayerColor pc, int unitCount) {
		return sgf.createPlayer(pc, unitCount);
	}

	public Tile createTile(TileType tt, PlayerColor pc, int r, int c,
			int unitCount) {
		return sgf.createTile(tt, pc, r, c, unitCount);
	}
}
