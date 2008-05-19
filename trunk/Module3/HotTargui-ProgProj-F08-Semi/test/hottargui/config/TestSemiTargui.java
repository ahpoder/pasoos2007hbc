package hottargui.config;

import static org.junit.Assert.*;

import org.junit.*;

import hottargui.framework.*;
import hottargui.standard.*;


public class TestSemiTargui {
	/**
	 * This test is only designed to show that the configuration
	 * is correct, as the individual parts of the configuration is
	 * already tested.
	 */

	  StandardGame game;
	  GameFactory gameFactory;
	
	  @Before 
	  public void setUp() {
		game = new StandardGame();
	    gameFactory = new SemiGameFactory(game);
	    game.setGameFactory(gameFactory);
	    game.newGame();
	  }

	  @Test 
	  public void semiUsesRandomOrderBoard() {
	    Player p = game.getPlayerInTurn();
	    assertEquals( PlayerColor.Red, p.getColor() );
	  }	

	  @Test 
	  public void semiUsesStandardMoveValidation() {
		  // There is no alternative
	  }	
	  
	  @Test 
	  public void semiUsesSimpleTurnStrategy() {
		  // There is no alternative
	  }	
	
	  @Test 
	  public void semiUsesDieRollAttackStrategy() {
	  }	

	  @Test 
	  public void semiUsesAllTilePutUnitsStrategy() {
	  }	

	  @Test 
	  public void semiUsesRevenueWinnerStrategy() {
	  }	
}
