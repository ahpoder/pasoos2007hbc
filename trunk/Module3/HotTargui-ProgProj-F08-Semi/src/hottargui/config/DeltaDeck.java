package hottargui.config;

import hottargui.framework.*;
import hottargui.standard.*;

import java.util.*;

/**
 * Fill the required tiles in a deck.
 * 
 * Tile type  | Number of
 * ---------------------
 * Settlement | 2-4
 * Salt mine  | 1
 * Oasis      | 6
 * Erg        | 12
 * Reg        | 12
 * Fesh-fesh  |  8
 * Mountains  |  6
 * Salt lake  |  4
 */
public class DeltaDeck implements Deck {

    private Stack<Tile> deck = new Stack<Tile>();

    public DeltaDeck() {
        populateDeck();
        shuffleDeck();
    }

    private void populateDeck() {
        // Add the Saltmine
        deck.push(new StandardTile(TileType.Saltmine, PlayerColor.None, 0, 0));

        // Create six Oasis tiles
        for (int i=0; i<6; i++) {
            deck.push(new StandardTile(TileType.Oasis, PlayerColor.None, 0, 0));
        }

        // Create the twelve Erg tiles
        for (int i=0; i<12; i++) {
            deck.push(new StandardTile(TileType.Erg, PlayerColor.None, 0, 0));
        }

        // Create the twelve Reg tiles
        for (int i=0; i<12; i++) {
            deck.push(new StandardTile(TileType.Reg, PlayerColor.None, 0, 0));
        }

        // Create the eight Fesh-fesh
        for (int i=0; i<8; i++) {
            deck.push(new StandardTile(TileType.Feshfesh, PlayerColor.None, 0, 0));
        }

        // Create six Mountain tiles
        for (int i=0; i<6; i++) {
            deck.push(new StandardTile(TileType.Mountain, PlayerColor.None, 0, 0));
        }

        // Create the four Saltlakes
        for (int i=0; i<4; i++) {
            deck.push(new StandardTile(TileType.Saltlake, PlayerColor.None, 0, 0));
        }
    }

    public Tile getTile(Position position) {
        Tile tile = deck.pop();
        return new StandardTile(tile.getType(), tile.getOwnerColor(), position.getRow(), position.getColumn());
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    public boolean isEmpty() {
        return deck.isEmpty();
    }
}
