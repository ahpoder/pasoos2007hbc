package hottargui.config;

import hottargui.framework.*;

import org.junit.*;
import static org.junit.Assert.*;

public class TestAlphaBoard {

  Board board;
  BoardFactory boardFactory;

  @Before 
  public void setUp() {
	boardFactory = new AlphaBoardFactory();
    board = new AlphaBoard(boardFactory);
  }

  @Test 
  public void playerCountEqualsFour() {
    assertEquals( 4, board.getPlayerCount() );
  }

  @Test 
  public void redPlayerExistInGame()
  {
// Test case before refactoring during revenue calculation.
//	  assertNotNull(board.getPlayer(PlayerColor.Red));
	  assertTrue(board.hasPlayer(PlayerColor.Red));
  }
  
  @Test 
  public void redPlayerHasRedPlayerColour()
  {
	  assertEquals(PlayerColor.Red, board.getPlayer(PlayerColor.Red).getColor());
  }
  
  @Test 
  public void greenPlayerHasGreenPlayerColour()
  {
	  assertEquals(PlayerColor.Green, board.getPlayer(PlayerColor.Green).getColor());
  }
  
  @Test 
  public void tiler0c0EqualsSettlement()
  {
	  assertEquals(TileType.Settlement, board.getTile(new Position(0, 0)).getType());
  }
  
  @Test 
  public void tiler0c0OwnedByRed()
  {
	  assertEquals(PlayerColor.Red, board.getTile(new Position(0, 0)).getOwnerColor());
  }
  
  @Test 
  public void tiler0c0Has10Units()
  {
	  assertEquals(10, board.getTile(new Position(0, 0)).getUnitCount());
  }

/*
  
  
  
  
  
  @Test 
  public void iterateTilesCountEqualsSevenTimesSeven() {
	  Iterator<? extends Tile> itt = board.getBoardIterator();
	  int count = 0;
	  while (itt.hasNext())
	  {
		  ++count;
		  itt.next();
	  }
	  
	  assertEquals( 7 * 7, count);
  }
  
  void tiler1c1EqualsSettlement()
  {
	  assertEquals(TileType.Settlement, board.getTile(new Position(0,0)).getType());
  }
  
  
  
  
  void testTile(int r, int c, PlayerColor colour, TileType type, int unitCount) {
	  Tile t = board.getTile(new Position(r,c));
	  assertEquals(colour, t.getOwnerColor());
	  assertEquals(new Position(r,c), t.getPosition());
	  assertEquals(type, t.getType());
	  assertEquals(unitCount, t.getUnitCount());
  }
  

  @Test 
  public void testTileIterationLayout() {
	  testTile(0,0,PlayerColor.Red, TileType.Settlement, 7);
	  testTile(0,1,PlayerColor.None, TileType.Erg, 0);
	  testTile(0,2,PlayerColor.None, TileType.Oasis, 0);
	  testTile(0,3,PlayerColor.None, TileType.Reg, 0);
	  testTile(0,4,PlayerColor.None, TileType.Oasis, 0);
	  testTile(0,5,PlayerColor.None, TileType.Feshfesh, 0);
	  testTile(0,6,PlayerColor.Green, TileType.Settlement, 7);
	  
	  testTile(1,0,PlayerColor.None, TileType.Erg, 0);
	  testTile(1,1,PlayerColor.None, TileType.Oasis, 0);
	  testTile(1,2,PlayerColor.None, TileType.Feshfesh, 0);
	  testTile(1,3,PlayerColor.None, TileType.Erg, 0);
	  testTile(1,4,PlayerColor.None, TileType.Feshfesh, 0);
	  testTile(1,5,PlayerColor.None, TileType.Reg, 0);
	  testTile(1,6,PlayerColor.None, TileType.Reg, 0);

	  testTile(2,0,PlayerColor.None, TileType.Oasis, 0);
	  testTile(2,1,PlayerColor.None, TileType.Saltlake, 0);
	  testTile(2,2,PlayerColor.None, TileType.Feshfesh, 0);
	  testTile(2,3,PlayerColor.None, TileType.Oasis, 0);
	  testTile(2,4,PlayerColor.None, TileType.Reg, 0);
	  testTile(2,5,PlayerColor.None, TileType.Feshfesh, 0);
	  testTile(2,6,PlayerColor.None, TileType.Reg, 0);

	  testTile(3,0,PlayerColor.None, TileType.Saltlake, 0);
	  testTile(3,1,PlayerColor.None, TileType.Reg, 0);
	  testTile(3,2,PlayerColor.None, TileType.Saltlake, 0);
	  testTile(3,3,PlayerColor.None, TileType.Saltmine, 0);
	  testTile(3,4,PlayerColor.None, TileType.Erg, 0);
	  testTile(3,5,PlayerColor.None, TileType.Feshfesh, 0);
	  testTile(3,6,PlayerColor.None, TileType.Mountain, 0);
	  
	  testTile(4,0,PlayerColor.None, TileType.Mountain, 0);
	  testTile(4,1,PlayerColor.None, TileType.Erg, 0);
	  testTile(4,2,PlayerColor.None, TileType.Saltlake, 0);
	  testTile(4,3,PlayerColor.None, TileType.Erg, 0);
	  testTile(4,4,PlayerColor.None, TileType.Mountain, 0);
	  testTile(4,5,PlayerColor.None, TileType.Reg, 0);
	  testTile(4,6,PlayerColor.None, TileType.Feshfesh, 0);
	  
	  testTile(5,0,PlayerColor.None, TileType.Oasis, 0);
	  testTile(5,1,PlayerColor.None, TileType.Reg, 0);
	  testTile(5,2,PlayerColor.None, TileType.Reg, 0);
	  testTile(5,3,PlayerColor.None, TileType.Erg, 0);
	  testTile(5,4,PlayerColor.None, TileType.Reg, 0);
	  testTile(5,5,PlayerColor.None, TileType.Erg, 0);
	  testTile(5,6,PlayerColor.None, TileType.Mountain, 0);
	  
	  testTile(6,0,PlayerColor.Blue, TileType.Settlement, 7);
	  testTile(6,1,PlayerColor.None, TileType.Erg, 0);
	  testTile(6,2,PlayerColor.None, TileType.Reg, 0);
	  testTile(6,3,PlayerColor.None, TileType.Feshfesh, 0);
	  testTile(6,4,PlayerColor.None, TileType.Erg, 0);
	  testTile(6,5,PlayerColor.None, TileType.Erg, 0);
	  testTile(6,6,PlayerColor.Yellow, TileType.Settlement, 7);
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
  
  @Test
  public void testMoveOwnColor() {
	  assertEquals(AlphaBoard.MoveAttemptResult.MOVE_VALID, board.validateMove(new Position(0, 0), new Position(0, 1), PlayerColor.Red));
  }

  @Test
  public void testMoveOtherColor() {
	  assertEquals(AlphaBoard.MoveAttemptResult.INVALID_MOVE, board.validateMove(new Position(0, 0), new Position(1, 6), PlayerColor.Blue));
  }

  @Test
  public void testMoveUnownedTile() {
	  assertEquals(AlphaBoard.MoveAttemptResult.INVALID_MOVE, board.validateMove(new Position(0, 1), new Position(0, 2), PlayerColor.Red));
  }

  @Test
  public void testMoveToSaltLake() {
	  assertEquals(AlphaBoard.MoveAttemptResult.INVALID_MOVE, board.validateMove(new Position(6, 1), new Position(6, 0), PlayerColor.Blue));
  }

  @Test
  public void testMoveFromOwnedEmptyTile() {
	  // First setup the situation
	  StandardTile t = (StandardTile)board.getTile(new Position(0, 2));
	  t.changePlayerColor(PlayerColor.Red);
	  
	  assertEquals(AlphaBoard.MoveAttemptResult.INVALID_MOVE, board.validateMove(new Position(0, 2), new Position(0, 3), PlayerColor.Red));
  }

  @Test
  public void testMoveOntoEmptyOccupiedTile() {
	  // First setup the situation
	  StandardTile t = (StandardTile)board.getTile(new Position(0, 1));
	  t.changePlayerColor(PlayerColor.Blue);
	  
	  assertEquals(AlphaBoard.MoveAttemptResult.MOVE_VALID, board.validateMove(new Position(0, 0), new Position(0, 1), PlayerColor.Red));
  }

  @Test
  public void testMoveOntoOccupiedTile() {
	  // First setup the situation
	  StandardTile t = (StandardTile)board.getTile(new Position(0, 1));
	  t.changePlayerColor(PlayerColor.Blue);
	  t.changeUnitCount(10);
	  
	  assertEquals(AlphaBoard.MoveAttemptResult.ATTACK_NEEDED, board.validateMove(new Position(0, 0), new Position(0, 1), PlayerColor.Red));
  }
  
  @Test
  public void testMoveToUnadjoiningTile() {
	  assertEquals(AlphaBoard.MoveAttemptResult.INVALID_MOVE, board.validateMove(new Position(0, 0), new Position(0, 2), PlayerColor.Red));
  }
  
  // TODO As there are a specific collection of tiles one should test that the players may move onto each of them in turn
  // et least that is what the EQ classes state, yet due to White-box consurns it is known that there is tested for 
  // difference from salt-lake, and it is therefore not necesarry.
   
   
*/
}
