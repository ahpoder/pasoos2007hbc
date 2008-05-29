package hottargui.config;

import hottargui.framework.*;
import hottargui.standard.StandardPlayer;

import java.util.*;

/**
 * This implementation of the BoardFactory supports correct 
 * placement of the different tile types on the board.
 * The tiles are positioned according the specification.
 */
public class DeltaBoardFactory implements BoardFactory {

    private BoardLayoutStrategy boardLayoutStrategy;

    public BoardLayoutStrategy createBoardLayoutStrategy() {
        return new RandomBoardLayoutStrategy();
    }

    public Player[] createPlayers() {
        return boardLayoutStrategy.createPlayers();
    }

    public Tile[][] createTiles() {
        return boardLayoutStrategy.createTiles();
    }

    public Tile createTile(TileType tileType, PlayerColor playerColor, int row, int column, int unitCount) {
        return boardLayoutStrategy.createTile(tileType, playerColor, row, column, unitCount);
    }

    public Player createPlayer(PlayerColor playerColor, int unitCount) {
        return boardLayoutStrategy.createPlayer(playerColor, unitCount);
    }
}
