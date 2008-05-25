package hottargui.config;

import hottargui.config.*;
import hottargui.standard.*;
import hottargui.framework.*;

import org.junit.*;

import static org.junit.Assert.*;

public class TestAttackStrategy {

	private StandardGame game;
	private StandardTile toTile;
  private StandardTile fromTile;
	private Position saltminePos;
	private Position anOasisPos;
	private Position aFeshFeshPos;
	private GameFactory gameFactory;
	private MoveValidationStrategy moveValidationStrategy;
	private BetaAttackStrategy betaAttack;

	@Before	
	public void setUp() {

		game = new StandardGame();
  	gameFactory = new BetaGameFactory(game);
  	initialize();
		moveValidationStrategy = gameFactory.createMoveValidationStrategy();
    betaAttack = new BetaAttackStrategy(game); 
        
		saltminePos = new Position(3,3);
		anOasisPos = new Position(1,1);
		aFeshFeshPos = new Position(1,2); 
	}
	
	private void initialize()
  {
    game.setGameFactory(gameFactory);
		game.newGame();
  }
  
  @Test
  public void testDieValue() {
        Die die = new StandardDie();
        die.rollDie();
        int res = die.getValue();
        assertTrue(1 <= res && res <= 6);
  }
	
	@Test
	public void testAttackerIsWinner() {
		// Test of example given in specification
		fromTile = (StandardTile)game.getTile(anOasisPos);
		fromTile.changePlayerColor(PlayerColor.Red);
		fromTile.changeUnitCount(8);
    toTile = (StandardTile)game.getTile(aFeshFeshPos);		
		toTile.changePlayerColor(PlayerColor.Green);
    toTile.changeUnitCount(5);
//    System.out.println(fromTile.getOwnerColor() + " on " + fromTile.getType() + ": Units=" + fromTile.getUnitCount() + ", Strategic value=" + fromTile.getStrategicValue());
//    System.out.println(toTile.getOwnerColor() + " on " + toTile.getType() + ": Units " + toTile.getUnitCount() + ", Strategic value=" + toTile.getStrategicValue()); 

	 	//Testing if move valid - not really necessary to test
	 	assertEquals(MoveAttemptResult.ATTACK_NEEDED, moveValidationStrategy.validateMove(fromTile.getPosition(), toTile.getPosition(), PlayerColor.Red));

    assertTrue(game.move(fromTile.getPosition(), toTile.getPosition(), 8)); 
		// Check if state = attack
    assertEquals(State.attack, game.getState());
    
    //Red (attacker) throws 4
    assertTrue(game.dieRolled(4));
    // Get Tiles to validate result of move
    fromTile = (StandardTile)game.getTile(anOasisPos);
    toTile = (StandardTile)game.getTile(aFeshFeshPos);   
    // Check how many units the Green (defender) has left: 5 - (7-1)/2 = 2
    assertEquals(2, toTile.getUnitCount());
		// Check if state = defend
    assertEquals(State.defend, game.getState());
    
    //Green (defender) throws 6
    assertTrue(game.dieRolled(6));
        // Get Tiles to validate result of move
    fromTile = (StandardTile)game.getTile(anOasisPos);
    toTile = (StandardTile)game.getTile(aFeshFeshPos);   
    // Check how many units the Red (attacker) has left: 8 - (8-0)/2 = 4
    assertEquals(4, fromTile.getUnitCount());
		// Check if state = attack
    assertEquals(State.attack, game.getState());
 	
    //Red (attacker) throws 3
    assertTrue(game.dieRolled(3));
    // Get Tiles to validate result of move
    fromTile = (StandardTile)game.getTile(anOasisPos);
    toTile = (StandardTile)game.getTile(aFeshFeshPos);   
    // Check how many units the Green (defender) has left: 5 - (6)/2 = 2
    assertEquals(2, toTile.getUnitCount());
		// Check if state = buy
    assertEquals(State.buy, game.getState());	 	
	}
	
	@Test
	public void testAttackerIsLooser() {
		// Test of example given in specification
		fromTile = (StandardTile)game.getTile(anOasisPos);
		fromTile.changePlayerColor(PlayerColor.Red);
		fromTile.changeUnitCount(3);
    toTile = (StandardTile)game.getTile(aFeshFeshPos);		
		toTile.changePlayerColor(PlayerColor.Green);
    toTile.changeUnitCount(10);
    
    //Testing if move valid - not really necessary to test
	 	assertEquals(MoveAttemptResult.ATTACK_NEEDED, moveValidationStrategy.validateMove(fromTile.getPosition(), toTile.getPosition(), PlayerColor.Red));

    assertTrue(game.move(fromTile.getPosition(), toTile.getPosition(), 8)); 
		// Check if state = attack
    assertEquals(State.attack, game.getState());
    
    //Red (attacker) throws 2
    assertTrue(game.dieRolled(2));
    // Get Tiles to validate result of move
    fromTile = (StandardTile)game.getTile(anOasisPos);
    toTile = (StandardTile)game.getTile(aFeshFeshPos);   
    // Check how many units the Green (defender) has left: 10 - (5-1)/2 = 8
    assertEquals(8, toTile.getUnitCount());
		// Check if state = defend
    assertEquals(State.defend, game.getState());
    
    //Green (defender) throws 5 which should be enough to kill the 3 attackers
    assertTrue(game.dieRolled(5));
    // Get Tiles to validate result of move
    fromTile = (StandardTile)game.getTile(anOasisPos);
    toTile = (StandardTile)game.getTile(aFeshFeshPos);   
    // Check that Red has 0 units left and Green has 8 units left
    assertEquals(0, fromTile.getUnitCount());
    assertEquals(8, toTile.getUnitCount());
		// Check if state = buy
    assertEquals(State.buy, game.getState());
  }

  @Test
  public void testAttackerGivesUp() {
		fromTile = (StandardTile)game.getTile(anOasisPos);
		fromTile.changePlayerColor(PlayerColor.Red);
		fromTile.changeUnitCount(8);
    toTile = (StandardTile)game.getTile(aFeshFeshPos);		
		toTile.changePlayerColor(PlayerColor.Green);
    toTile.changeUnitCount(5);

	 	//Testing if move valid - not really necessary to test
	 	assertEquals(MoveAttemptResult.ATTACK_NEEDED, moveValidationStrategy.validateMove(fromTile.getPosition(), toTile.getPosition(), PlayerColor.Red));

    assertTrue(game.move(fromTile.getPosition(), toTile.getPosition(), 8)); 
		// Check if state = attack
    assertEquals(State.attack, game.getState());
    
    //Red (attacker) throws 4
    assertTrue(game.dieRolled(4));
    // Get Tiles to validate result of move
    fromTile = (StandardTile)game.getTile(anOasisPos);
    toTile = (StandardTile)game.getTile(aFeshFeshPos);   
    // Check how many units the Green (defender) has left: 5 - (7-1)/2 = 2
    assertEquals(2, toTile.getUnitCount());
		// Check if state = defend
    assertEquals(State.defend, game.getState());
    
    //Green (defender) throws 6
    assertTrue(game.dieRolled(6));
    // Get Tiles to validate result of move
    fromTile = (StandardTile)game.getTile(anOasisPos);
    toTile = (StandardTile)game.getTile(aFeshFeshPos);   
    // Check how many units the Red (attacker) has left: 8 - (8-0)/2 = 4
    assertEquals(4, fromTile.getUnitCount());
		// Check if state = attack
    assertEquals(State.attack, game.getState());
    
    // Red (attacker) gives up
    assertTrue(game.givingUp());
    assertEquals(State.buy, game.getState());
    fromTile = (StandardTile)game.getTile(anOasisPos);
    toTile = (StandardTile)game.getTile(aFeshFeshPos);   
    // Check how many units the Red (attacker) has left: 8 - (8-0)/2 = 4
    assertEquals(4, fromTile.getUnitCount());
    assertEquals(2, toTile.getUnitCount());
	}
  
}
