package hottargui.config;

import java.util.Iterator;

import hottargui.framework.*;

import org.junit.*;
import static org.junit.Assert.*;

public class TestBoard {

  Board board;
  AlphaBoardFactory boardFactory;

  @Before 
  public void setUp() {
	boardFactory = new AlphaBoardFactory();
    board = new Board(boardFactory);
  }

  @Test 
  public void testPlayersCount() {
    assertEquals( 4, board.getPlayerCount() );
  }

  @Test 
  public void testTileIterationCount() {
	  Iterator<? extends Tile> itt = board.getBoardIterator();
	  int count = 0;
	  while (itt.hasNext())
	  {
		  ++count;
		  itt.next();
	  }
	  
	  assertEquals( 7 * 7, count);
  }
  
  void testTile(int r, int c, PlayerColor colour, TileType type) {
	  Tile t = board.getTile(new Position(r,c));
	  assertEquals(colour, t.getOwnerColor());
	  assertEquals(new Position(r,c), t.getPosition());
	  assertEquals(type, t.getType());
	  assertEquals(0, t.getUnitCount());
  }
  

  @Test 
  public void testTileIterationLayout() {
	  testTile(0,0,PlayerColor.Red, TileType.Settlement);
	  testTile(0,1,PlayerColor.None, TileType.Erg);
	  testTile(0,2,PlayerColor.None, TileType.Oasis);
	  testTile(0,3,PlayerColor.None, TileType.Reg);
	  testTile(0,4,PlayerColor.None, TileType.Oasis);
	  testTile(0,5,PlayerColor.None, TileType.Feshfesh);
	  testTile(0,6,PlayerColor.Green, TileType.Settlement);
	  
	  testTile(1,0,PlayerColor.None, TileType.Erg);
	  testTile(1,1,PlayerColor.None, TileType.Oasis);
	  testTile(1,2,PlayerColor.None, TileType.Feshfesh);
	  testTile(1,3,PlayerColor.None, TileType.Erg);
	  testTile(1,4,PlayerColor.None, TileType.Feshfesh);
	  testTile(1,5,PlayerColor.None, TileType.Reg);
	  testTile(1,6,PlayerColor.None, TileType.Reg);

	  testTile(2,0,PlayerColor.None, TileType.Oasis);
	  testTile(2,1,PlayerColor.None, TileType.Saltlake);
	  testTile(2,2,PlayerColor.None, TileType.Feshfesh);
	  testTile(2,3,PlayerColor.None, TileType.Oasis);
	  testTile(2,4,PlayerColor.None, TileType.Reg);
	  testTile(2,5,PlayerColor.None, TileType.Feshfesh);
	  testTile(2,6,PlayerColor.None, TileType.Reg);

	  testTile(3,0,PlayerColor.None, TileType.Saltlake);
	  testTile(3,1,PlayerColor.None, TileType.Reg);
	  testTile(3,2,PlayerColor.None, TileType.Saltlake);
	  testTile(3,3,PlayerColor.None, TileType.Saltmine);
	  testTile(3,4,PlayerColor.None, TileType.Erg);
	  testTile(3,5,PlayerColor.None, TileType.Feshfesh);
	  testTile(3,6,PlayerColor.None, TileType.Mountain);
	  
	  testTile(4,0,PlayerColor.None, TileType.Mountain);
	  testTile(4,1,PlayerColor.None, TileType.Erg);
	  testTile(4,2,PlayerColor.None, TileType.Saltlake);
	  testTile(4,3,PlayerColor.None, TileType.Erg);
	  testTile(4,4,PlayerColor.None, TileType.Mountain);
	  testTile(4,5,PlayerColor.None, TileType.Reg);
	  testTile(4,6,PlayerColor.None, TileType.Feshfesh);
	  
	  testTile(5,0,PlayerColor.None, TileType.Oasis);
	  testTile(5,1,PlayerColor.None, TileType.Reg);
	  testTile(5,2,PlayerColor.None, TileType.Reg);
	  testTile(5,3,PlayerColor.None, TileType.Erg);
	  testTile(5,4,PlayerColor.None, TileType.Reg);
	  testTile(5,5,PlayerColor.None, TileType.Erg);
	  testTile(5,6,PlayerColor.None, TileType.Mountain);
	  
	  testTile(6,0,PlayerColor.Blue, TileType.Settlement);
	  testTile(6,1,PlayerColor.None, TileType.Erg);
	  testTile(6,2,PlayerColor.None, TileType.Reg);
	  testTile(6,3,PlayerColor.None, TileType.Feshfesh);
	  testTile(6,4,PlayerColor.None, TileType.Erg);
	  testTile(6,5,PlayerColor.None, TileType.Erg);
	  testTile(6,6,PlayerColor.Yellow, TileType.Settlement);
  }

  @Test(expected=IllegalArgumentException.class)
  public void testFindInvalidPosition() {
	  try {
		  board.getTile(new Position(-1,0));
		  assert(false);
	  }
	  catch (IllegalArgumentException ex) { }
	  try {
		  board.getTile(new Position(0,-1));
		  assert(false);
	  }
	  catch (IllegalArgumentException ex) { }
	  
	  try {
		  board.getTile(new Position(7,0));
		  assert(false);
	  }
	  catch (IllegalArgumentException ex) { }
	  board.getTile(new Position(0,7));
  }
}
