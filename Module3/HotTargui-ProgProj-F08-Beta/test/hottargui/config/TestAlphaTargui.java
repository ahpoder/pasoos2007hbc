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
    game = new StandardGame(new AlphaGameFactory());
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
		  if (p.getColor() == PlayerColor.Red)
		  {
			  assertTrue(game.buy(0, new Position(0, 0)));
		  }
		  else if (p.getColor() == PlayerColor.Green)
		  {
			  assertTrue(game.buy(0, new Position(0, 6)));
		  }
		  else if (p.getColor() == PlayerColor.Blue)
		  {
			  assertTrue(game.buy(0, new Position(6, 0)));
		  }
		  else // PlayerColor.Yellow
		  {
			  assertTrue(game.buy(0, new Position(6, 6)));
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
  public void testFourthPlayerYellow() {
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
    
  // Purchases
  
  @Test
  public void testPurchaseOnTileNotOwned() {
      assertFalse(game.buy(5, new Position(1,0)));
  }

  @Test
  public void testPurchaseOnTileOwnedByOther() {
      assertFalse(game.buy(5, new Position(0,6)));
  }

  @Test
  public void testPurchaseMoreThanOwned() {
      assertFalse(game.buy(15, new Position(0,0)));
  }

  @Test
  public void testPurchase() {
      assertFalse(game.buy(5, new Position(0,6)));
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
	
		// Force revenue - this will not alter the revenue, as nothing is moved
		performMoves(4);
		
		// Validate result Red = 10 (original) + 4 (settlement) + 5 (Salt mine) + 0 (Fesh-fesh) = 19
		assertEquals(19, game.getPlayerInTurn().getCoins());
		performMoves(1);
		// Validate result Green = 10 (original) + 4 (settlement) + 3 (Oasis) + 0 (Mountain) = 17
		assertEquals(17, game.getPlayerInTurn().getCoins());
		performMoves(1);
		// Validate result Blue = 10 (original) + 4 (settlement) + 1 (Erg) = 15
		assertEquals(15, game.getPlayerInTurn().getCoins());
		performMoves(1);
		// Validate result Yellow = 10 (original) + 4 (settlement) + 2 (Reg) = 16
		assertEquals(16, game.getPlayerInTurn().getCoins());
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
	    t = game.getTile(new Position(6,6));
		((StandardTile)t).changePlayerColor(PlayerColor.Red);

		performMoves(3);

		// Yellow cannot buy as normal, as he/she does not own the Settlement where the purchase is normally placed.
		assertTrue(game.buy(0, new Position(3, 6)));

		// Force revenue - this will also move some pieces, thereby changing the revenue.
		
		// Validate result Red = 10 (orriginal) + 4 (settlement) + 4 (settlement) = 18
		assertEquals(18, game.getPlayerInTurn().getCoins());
		performMoves(1);
		// Validate result Green = 10 (orriginal) + 4 (settlement) = 14
		assertEquals(14, game.getPlayerInTurn().getCoins());
		performMoves(1);
		// Validate result Blue = 10 (orriginal) + 4 (settlement) = 14
		assertEquals(14, game.getPlayerInTurn().getCoins());
		performMoves(1);
		// Validate result Yellow = 10 (orriginal) + 0 (No settlement)  = 10
		assertEquals(10, game.getPlayerInTurn().getCoins());
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

  // Dead player
  
  @Test
  public void testMoveAfterPlayerDead() {
	  // Setup - Kill yellow player
	  Tile t = game.getTile(new Position(6,5));
	  ((StandardTile)t).changePlayerColor(PlayerColor.Red);
	  ((StandardTile)t).changeUnitCount(10);
	  game.move(new Position(6,5), new Position(6,6), 10);
	  game.buy(0, new Position(0,0));

	  // Yellow will never have a turn, and moving two times
	  // will bring the turn back to Red.
	  performMoves(2);
	  assertEquals(PlayerColor.Red, game.getPlayerInTurn().getColor());
  }

  @Test
  public void testCalculateRevenueForThreePlayers() {
	// Setup - Kill yellow player
	Tile t = game.getTile(new Position(6,5));
	((StandardTile)t).changePlayerColor(PlayerColor.Red);
	((StandardTile)t).changeUnitCount(10);
	game.move(new Position(6,5), new Position(6,6), 10);
	game.buy(1, new Position(0, 0));
	  
	// Only two moves required to end turn
	performMoves(2);
	  
	// Validate result Red = 10 (original) + 4 (settlement) + 4 (settlement) = 18
	assertEquals(18, game.getPlayerInTurn().getCoins());
	performMoves(1);
	// Validate result Green = 10 (original) + 4 (settlement) = 14
	assertEquals(14, game.getPlayerInTurn().getCoins());
	performMoves(1);
	// Validate result Blue = 10 (original) + 4 (settlement) = 14
	assertEquals(14, game.getPlayerInTurn().getCoins());
  }
}
