package hottargui.config;

import hottargui.framework.*;
import hottargui.standard.StandardPlayer;

import java.util.Collection;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * This implementation of the BoardLayoutStrategy defines a board layout, where the tiles are positioned in
 * random order.
 */
public class RandomBoardLayoutStrategy implements BoardLayoutStrategy {

    private Deck deck;

    public RandomBoardLayoutStrategy() {
        this.deck = new DeltaDeck();
    }

    /**
     * Return players. According to the specification, there's always four players on the board.
     * @return An array of the four players.
     */
    public Player[] createPlayers() {
        Player[] temp = new Player[4];
        temp[0] = new StandardPlayer(PlayerColor.Red);
        temp[1] = new StandardPlayer(PlayerColor.Green);
        temp[2] = new StandardPlayer(PlayerColor.Blue);
        temp[3] = new StandardPlayer(PlayerColor.Yellow);
        return temp;
    }

    public Tile[][] createTiles() {

        Tile[][] tilesAsArray = new Tile[7][7];

        Position position = null;
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 7; j++) {
                position = new Position(i, j);

                if (!position.equals(new Position(3,3))) {
                    Tile tile = deck.getTile(position);
                    tilesAsArray[i][j] = tile;
                } else {
                    DeltaTile saltmineTile = new DeltaTile(TileType.Saltmine, PlayerColor.None);
                    saltmineTile.setPosition(position);
                   tilesAsArray[i][j] = saltmineTile;
                }
            }
        }
/*


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

*/

        int row = 0;
        int column = 0;
        //ListIterator<Tile> tilesIterator = tiles.listIterator();


        Tile tile = null;

        for (int i=0; i<tilesAsArray.length; i++) {
            for (int j=0; j<tilesAsArray[i].length; j++) {
                tile = tilesAsArray[i][j];
                row = position.getRow();
                column = position.getColumn();

                if (row==0 && column==0) {
                    DeltaTile redSettlement = new DeltaTile(TileType.Settlement, PlayerColor.Red);
                    redSettlement.setPosition(new Position(0,0));
                    tilesAsArray[i][j] = redSettlement;
                } else if (row==0 && column==6) {
                    DeltaTile greenSettlement = new DeltaTile(TileType.Settlement, PlayerColor.Green);
                    greenSettlement.setPosition(new Position(0,6));
                    tilesAsArray[i][j] = greenSettlement;
                } else if (row==6 && column==0) {
                    DeltaTile blueSettlement = new DeltaTile(TileType.Settlement, PlayerColor.Blue);
                    blueSettlement.setPosition(new Position(6,0));
                    tilesAsArray[i][j] = blueSettlement;
                } else if (row==6 && column==6) {
                    DeltaTile yellowSettlement = new DeltaTile(TileType.Settlement, PlayerColor.Yellow);
                    yellowSettlement.setPosition(new Position(6,6));
                    tilesAsArray[i][j] = yellowSettlement;
                }
            }
        }


        //while (tilesIterator.hasNext()) {
        //    tile = tilesIterator.next();
        /**
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
         */
    return tilesAsArray;
    }

    public Tile createTile(TileType tileType, PlayerColor playerColor, int row, int column, int unitCount) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Player createPlayer(PlayerColor playerColor, int unitCount) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
