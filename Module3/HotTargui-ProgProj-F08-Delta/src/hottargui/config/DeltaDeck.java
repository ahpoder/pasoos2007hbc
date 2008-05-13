package hottargui.config;

import hottargui.framework.*;

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
        deck.push(new DeltaTile(TileType.Saltmine, PlayerColor.None));

        // Create six Oasis tiles
        for (int i=0; i<6; i++) {
            deck.push(new DeltaTile(TileType.Oasis, PlayerColor.None));
        }

        // Create the twelve Erg tiles
        for (int i=0; i<12; i++) {
            deck.push(new DeltaTile(TileType.Erg, PlayerColor.None));
        }

        // Create the twelve Reg tiles
        for (int i=0; i<12; i++) {
            deck.push(new DeltaTile(TileType.Reg, PlayerColor.None));
        }

        // Create the eight Fesh-fesh
        for (int i=0; i<8; i++) {
            deck.push(new DeltaTile(TileType.Feshfesh, PlayerColor.None));
        }

        // Create six Mountain tiles
        for (int i=0; i<6; i++) {
            deck.push(new DeltaTile(TileType.Mountain, PlayerColor.None));
        }

        // Create the four Saltlakes
        for (int i=0; i<4; i++) {
            deck.push(new DeltaTile(TileType.Saltlake, PlayerColor.None));
        }
    }

    public Tile getTile(Position position) {
        DeltaTile tile = (DeltaTile)deck.pop();
        tile.setPosition(position);
        return tile;
    }

    public void shuffleDeck() {
        Collections.shuffle(deck);
    }

    public boolean isEmpty() {
        return deck.isEmpty();
    }
}
