package hottargui.config;

import hottargui.framework.*;
import hottargui.standard.StandardPlayer;

import java.util.*;

/**
 * This implementation of the BoardFactory supports correct placement of the different tile types on the board.
 * The tiles are positioned according the specification.
 */
public class RandomOrderBoardFactory implements BoardFactory {

    private Deck deck;

    public RandomOrderBoardFactory() {
        deck = new DeltaDeck();
    }

	public Player[] createPlayers() {
		Player[] temp = new Player[4];
		temp[0] = new StandardPlayer(PlayerColor.Red);
		temp[1] = new StandardPlayer(PlayerColor.Green);
		temp[2] = new StandardPlayer(PlayerColor.Blue);
		temp[3] = new StandardPlayer(PlayerColor.Yellow);
		return temp;
	}

    public Collection<Tile> createTiles() {

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
                    DeltaTile saltmineTile = new DeltaTile(TileType.Saltmine, PlayerColor.None);
                    saltmineTile.setPosition(position);
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
                DeltaTile redSettlement = new DeltaTile(TileType.Settlement, PlayerColor.Red);
                redSettlement.setPosition(new Position(0,0));
                tilesIterator.add(redSettlement);
            } else if (row==0 && column==6) {
                tilesIterator.remove();
                DeltaTile greenSettlement = new DeltaTile(TileType.Settlement, PlayerColor.Green);
                greenSettlement.setPosition(new Position(0,6));
                tilesIterator.add(greenSettlement);
            } else if (row==6 && column==0) {
                tilesIterator.remove();
                DeltaTile blueSettlement = new DeltaTile(TileType.Settlement, PlayerColor.Blue);
                blueSettlement.setPosition(new Position(6,0));
                tilesIterator.add(blueSettlement);
            } else if (row==6 && column==6) {
                tilesIterator.remove();
                DeltaTile yellowSettlement = new DeltaTile(TileType.Settlement, PlayerColor.Yellow);
                yellowSettlement.setPosition(new Position(6,6));
                tilesIterator.add(yellowSettlement);
            }
        }
        return tiles;
    }
}
