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
  public void testBuyWithoutMoveAllowed() {
    // Ensure that player has money
	Player p = game.getPlayerInTurn();
    ((StandardPlayer)p).add(10);
    
    assertTrue(game.buy(5, new Position(0, 0)));
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
  @Test
  public void testCalculateRevenueForFourPlayers() {
		// Setup - Make sure all types of Tiles are represented
		// Salt mine to Red = 5
	    Tile t = game.getTile(new Position(3,3));
		((StandardTile)t).changePlayerColor(PlayerColor.Red);
		// Oasis to Green = 3
		t = game.getTile(new Position(0,2));
		((StandardTile)t).changePlayerColor(PlayerColor.Green);
		// Erg to Blue = 1
		t = game.getTile(new Position(6,5));
		((StandardTile)t).changePlayerColor(PlayerColor.Blue);
		// Reg to Yellow = 2
		t = game.getTile(new Position(5,4));
		((StandardTile)t).changePlayerColor(PlayerColor.Yellow);
		// Fesh-fesh to Red = 0
		t = game.getTile(new Position(6,3));
		((StandardTile)t).changePlayerColor(PlayerColor.Red);
		// Mountain to Green = 0
		t = game.getTile(new Position(3,6));
		((StandardTile)t).changePlayerColor(PlayerColor.Green);
	
		// Force revenue - this will also move some pieces, thereby changing the revenue.
		// The changes are:
		// Red ownes Erg = 1
		// Green ownes Reg = 2
		// Blue ownes Oasis = 3
		// Yellow ownes Mountain = 0
		performMoves(4);
		
		// Validate result Red = 10 (orriginal) + 4 (settlement) + 5 (Salt mine) + 0 (Fesh-fesh) + 1 (Erg) = 20
		assertEquals(20, game.getPlayerInTurn().getCoins());
		performMoves(1);
		// Validate result Green = 10 (orriginal) + 4 (settlement) + 3 (Oasis) + 0 (Mountain) + 2 (Reg) = 19
		assertEquals(19, game.getPlayerInTurn().getCoins());
		performMoves(1);
		// Validate result Blue = 10 (orriginal) + 4 (settlement) + 1 (Erg) + 3 (Oasis) = 18
		assertEquals(18, game.getPlayerInTurn().getCoins());
		performMoves(1);
		// Validate result Yellow = 10 (orriginal) + 4 (settlement) + 2 (Reg) + 0 (Mountain) = 16
		assertEquals(16, game.getPlayerInTurn().getCoins());
  }

  @Test
  public void testCalculateRevenueForThreePlayers() {
	  // Setup - Kill yellow player
	  // TODO implement	
	  assertFalse(true);
  }

  class ResultListener implements GameListener
  {
	public String result;
	public void report(String text) {
		result = text;
	}
	
	public void updateDie(int value) {
		assertTrue(false);
	}
	
	public void updatePlayer(PlayerColor color) {
		assertTrue(false);
	}
	
	public void updateTile(Tile t) {
		assertTrue(false);
	}
  }
  
  @Test
  public void testCalculateRevenueWithYellowMissingSettlement() {
	  	// Setup - Take away Yellows Settlement and give it Oasis
	  	Tile t = game.getTile(new Position(3,6)); // Oasis
		((StandardTile)t).changePlayerColor(PlayerColor.Yellow);
		((StandardTile)t).changeUnitCount(5);
	    t = game.getTile(new Position(6,6));
		((StandardTile)t).changePlayerColor(PlayerColor.Red);

		// The changes are:
		// Red ownes Erg = 1
		// Green ownes Reg = 2
		// Blue ownes Oasis = 3
		performMoves(3);

		// Yellow cannot move as normal and has to be given another tile
		assertTrue(game.move(new Position(3, 6), new Position(2,6), 1));
		assertTrue(game.buy(1, new Position(3, 6)));

		// Force revenue - this will also move some pieces, thereby changing the revenue.
		
		// Validate result Red = 10 (orriginal) + 4 (settlement) + 4 (settlement) + 1 (Erg) = 19
		assertEquals(19, game.getPlayerInTurn().getCoins());
		performMoves(1);
		// Validate result Green = 10 (orriginal) + 4 (settlement) + 2 (Reg) = 16
		assertEquals(16, game.getPlayerInTurn().getCoins());
		performMoves(1);
		// Validate result Blue = 10 (orriginal) + 4 (settlement) + 3 (Oasis) = 17
		assertEquals(17, game.getPlayerInTurn().getCoins());
		performMoves(1);
		// Validate result Yellow = 10 (orriginal) + 0 (No settlement) - 1 (purchase) = 9
		assertEquals(9, game.getPlayerInTurn().getCoins());
  }

  @Test
  public void testGameOverRedYellow() {
    performMoves(24 * 4 + 3);
  	Tile t = game.getTile(new Position(3,3)); // Salt mine
	((StandardTile)t).changePlayerColor(PlayerColor.Yellow);
	((StandardTile)t).changeUnitCount(5);
	// Setup listener
	ResultListener res = new ResultListener();
	game.addGameListener(res);
	// Finish game
	performMoves(1);
	assertEquals("GAME OVER - Yellow WON", res.result);
  }
}
