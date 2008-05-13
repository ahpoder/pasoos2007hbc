package hottargui.config;

import hottargui.framework.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * This class tests the Delta Board.
 */
public class TestDeltaBoard {

    private Board board;
    private DeltaBoardFactory boardFactory;

    @Before
    public void setUp() {
      boardFactory = new DeltaBoardFactory();
      board = new Board(boardFactory);
    }


    /**
     * Test that the Saltmine is in the center position (3,3)
     */
    @Test
    public void testSaltmineCenterPosition() {
        // Get tile at center position
        Tile tile = board.getTile(new Position(3,3));
        // Get tile type
        TileType tileType = tile.getType();
        // Assert it's the Saltmine
        assertTrue(tileType.equals(TileType.Saltmine));
    }

    /**
     * Test the Settlement positions
     */
    @Test
    public void testSettlementPositions() {
        // Test for the red Settlement
        Tile tile = board.getTile(new Position(0,0));
        // Get tile type
        TileType tileType = tile.getType();
        // Assert it's the Saltmine
        assertTrue(tileType.equals(TileType.Settlement));
        assertTrue(tile.getOwnerColor().equals(PlayerColor.Red));

        // Test for the green Settlement
        tile = board.getTile(new Position(0,6));
        // Get tile type
        tileType = tile.getType();
        // Assert it's the Saltmine
        assertTrue(tileType.equals(TileType.Settlement));
        assertTrue(tile.getOwnerColor().equals(PlayerColor.Green));

        // Test for the blue Settlement
        tile = board.getTile(new Position(6,0));
        // Get tile type
        tileType = tile.getType();
        // Assert it's the Saltmine
        assertTrue(tileType.equals(TileType.Settlement));
        assertTrue(tile.getOwnerColor().equals(PlayerColor.Blue));

        // Test for the yellow Settlement
        tile = board.getTile(new Position(6,6));
        // Get tile type
        tileType = tile.getType();
        // Assert it's the Saltmine
        assertTrue(tileType.equals(TileType.Settlement));
        assertTrue(tile.getOwnerColor().equals(PlayerColor.Yellow));
    }

    @Test
    public void testPositions() {

        Iterator iterator = board.getBoardIterator();

        int count = 0;
        while(iterator.hasNext()) {
            Tile tile = (Tile)iterator.next();
            System.out.println("Tile no. [" + count + "] has type: " + tile.getType() + " on position " + tile.getPosition());
            count++;
        }

        assertTrue(count==49);
    }

}
