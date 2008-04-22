package hottargui.config;

import hottargui.framework.*;
import hottargui.standard.StandardPlayer;
import hottargui.standard.StandardTile;

import org.junit.*;
import static org.junit.Assert.*;

public class TestAlphaTargui {

  Game game;

  @Before 
  public void setUp() {
    game = new AlphaGame();
    game.newGame();
  }

  @Test 
  public void testInitialPlayerInTurnIsRed() {
    Player p = game.getPlayerInTurn();
    assertEquals( PlayerColor.Red, p.getColor() );
  }

  @Test
  public void testMoveFirstAllowed() {
    // As it is impossible to move another colour, except as already tested in board
	// Only the order needs to be tested.
	assertTrue(game.move(new Position(0, 0), new Position(0,1), 2));
  }

  @Test
  public void testBuyFirstNotAllowed() {
    // Ensure that player has money
	Player p = game.getPlayerInTurn();
    ((StandardPlayer)p).add(10);
    
    assertFalse(game.buy(5, new Position(0, 0)));
  }

  @Test
  public void testBuySecondAllowed() { // This does not test that the buy was successful, only that it was performed.
    game.move(new Position(0, 0), new Position(0,1), 2);

	// Ensure that player has money
	Player p = game.getPlayerInTurn();
    ((StandardPlayer)p).add(10);
    
    assertTrue(game.buy(5, new Position(0, 0)));
  }

  void performMoves(int moves)
  {
	  for (int i = 0; i < moves; ++i)
	  {
		  Player p = game.getPlayerInTurn();
	      ((StandardPlayer)p).add(1);
		  if (p.getColor() == PlayerColor.Red)
		  {
			  assertTrue(game.move(new Position(0, 0), new Position(0,1), 1));
			  assertTrue(game.buy(1, new Position(0, 0)));
		  }
		  else if (p.getColor() == PlayerColor.Green)
		  {
			  assertTrue(game.move(new Position(0, 6), new Position(1,6), 1));
			  assertTrue(game.buy(1, new Position(0, 6)));
		  }
		  else if (p.getColor() == PlayerColor.Blue)
		  {
			  assertTrue(game.move(new Position(6, 0), new Position(5,0), 1));
			  assertTrue(game.buy(1, new Position(6, 0)));
		  }
		  else // PlayerColor.Yellow
		  {
			  assertTrue(game.move(new Position(6, 6), new Position(5,6), 1));
			  assertTrue(game.buy(1, new Position(6, 6)));
		  }
	  }
  }
  
  @Test
  public void testSecondPlayerGreen() {
	performMoves(1);
    Player p = game.getPlayerInTurn();
    assertEquals( PlayerColor.Green, p.getColor() ); 
  }
  
  @Test
  public void testThirdPlayerBlue() {
	performMoves(2);
    Player p = game.getPlayerInTurn();
    assertEquals( PlayerColor.Blue, p.getColor() ); 
  }

  @Test
  public void testThirdPlayerYellow() {
	performMoves(3);
    Player p = game.getPlayerInTurn();
    assertEquals( PlayerColor.Yellow, p.getColor() ); 
  }

  @Test
  public void testWraparoundToRedPlayer() {
	performMoves(4);
    Player p = game.getPlayerInTurn();
    assertEquals( PlayerColor.Red, p.getColor() ); 
  }
  
  // Attack
  
  @Test
  public void testRedAttacksBlueAndLoose() {
	// Setup
	Tile t = game.getTile(new Position(0,1));
	((StandardTile)t).changePlayerColor(PlayerColor.Blue);
	((StandardTile)t).changeUnitCount(8);
	assertTrue(game.move(new Position(0,0), new Position(0,1), 7));
	
	// Validate result
	t = game.getTile(new Position(0,1)); // Not really needed as all are references
	assertEquals(PlayerColor.Blue, t.getOwnerColor());
	assertEquals(1, t.getUnitCount());
	t = game.getTile(new Position(0,0));
	assertEquals(PlayerColor.Red, t.getOwnerColor());
	assertEquals(0, t.getUnitCount());
  }
  
  @Test
  public void testRedAttacksBlueAndWin() {
		// Setup
		Tile t = game.getTile(new Position(0,1));
		((StandardTile)t).changePlayerColor(PlayerColor.Blue);
		((StandardTile)t).changeUnitCount(5);
		assertTrue(game.move(new Position(0,0), new Position(0,1), 7));
		
		// Validate result
		t = game.getTile(new Position(0,1)); // Not really needed as all are references
		assertEquals(PlayerColor.Red, t.getOwnerColor());
		assertEquals(2, t.getUnitCount());
		t = game.getTile(new Position(0,0));
		assertEquals(PlayerColor.Red, t.getOwnerColor());
		assertEquals(0, t.getUnitCount());
  }

  @Test
  public void testRedAttacksBlueAndIsInDraw() {
		// Setup
		Tile t = game.getTile(new Position(0,1));
		((StandardTile)t).changePlayerColor(PlayerColor.Blue);
		((StandardTile)t).changeUnitCount(7);
		assertTrue(game.move(new Position(0,0), new Position(0,1), 7));
		
		// Validate result
		t = game.getTile(new Position(0,1)); // Not really needed as all are references
		assertEquals(PlayerColor.Blue, t.getOwnerColor());
		assertEquals(0, t.getUnitCount());
		t = game.getTile(new Position(0,0));
		assertEquals(PlayerColor.Red, t.getOwnerColor());
		assertEquals(0, t.getUnitCount());
  }
  
  // Calculate revenue
  
}
