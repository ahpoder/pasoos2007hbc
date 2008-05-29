package hottargui.config;

import hottargui.framework.Player;
import hottargui.framework.Tile;
import hottargui.framework.TileType;
import hottargui.framework.PlayerColor;

import java.util.Collection;

/**
 * This strategy defines the responsabilities related to the board.
 * The board is defined by the tiles and the players.
 */
public interface BoardLayoutStrategy {

    /**
     * Create the players to position on the board
     * @return An array of players
     */
    Player[] createPlayers();

    /**
     * Get the created tiles for the board
     * @return A collection of tiles for the board
     */
    Tile[][] createTiles();

    /**
     * Create a Tile
     * @param tileType The type of Tile
     * @param playerColor The player color
     * @param row The row for the position
     * @param column The column for the position
     * @param unitCount The count of units on the Tile
     * @return The created Tile
     */
    Tile createTile(TileType tileType, PlayerColor playerColor, int row, int column, int unitCount);

    /**
     * Create a Player
     * @param playerColor Color of the Player
     * @param unitCount The unit count
     * @return A player
     */
    Player createPlayer(PlayerColor playerColor, int unitCount);
}
