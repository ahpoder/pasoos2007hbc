package hottargui.config;

import hottargui.config.*;
import hottargui.standard.*;
import hottargui.framework.*;

import org.junit.*;

import static org.junit.Assert.*;

public class SystematicTest {

	private StandardGame game;
	private StandardTile toTile;
  private StandardTile fromTile;
	private Position saltminePos;
	private Position fromPos;
	private Position toPos;
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
	}
	
	private void initialize()
  {
    game.setGameFactory(gameFactory);
		game.newGame();
  }

	@Test
	public void testCase1() {
	 	// Xdist=-2 and Ydist=0 => Testing moving from postion (4,4) to (2,4) 
		fromTile = (StandardTile)game.getTile(new Position(4,4));
		fromTile.changePlayerColor(PlayerColor.Red);
		fromTile.changeUnitCount(2);
    toTile = (StandardTile)game.getTile(new Position(2,4));		
		toTile.changePlayerColor(PlayerColor.Green);
    toTile.changeUnitCount(2);
    //Test if move invalid
	 	assertEquals(MoveAttemptResult.INVALID_MOVE, moveValidationStrategy.validateMove(fromTile.getPosition(), toTile.getPosition(), PlayerColor.Red));
	}
	
	@Test
	public void testCase2() {
	 	// Xdist=0 and Ydist=-2 => Testing moving from postion (4,4) to (4,2)
		fromTile = (StandardTile)game.getTile(new Position(4,4));
		fromTile.changePlayerColor(PlayerColor.Red);
		fromTile.changeUnitCount(2);
    toTile = (StandardTile)game.getTile(new Position(4,2));		
		toTile.changePlayerColor(PlayerColor.Green);
    toTile.changeUnitCount(2);
    //Test if move invalid
	 	assertEquals(MoveAttemptResult.INVALID_MOVE, moveValidationStrategy.validateMove(fromTile.getPosition(), toTile.getPosition(), PlayerColor.Red));
 	}
	
	
	@Test
	public void testCase3() {
	 	// Xdist=0 and Ydist=0 => Testing moving from postion (4,4) to (4,4) 
		fromTile = (StandardTile)game.getTile(new Position(4,4));
		fromTile.changePlayerColor(PlayerColor.Red);
		fromTile.changeUnitCount(2);
    toTile = (StandardTile)game.getTile(new Position(4,4));		
		toTile.changePlayerColor(PlayerColor.Green);
    toTile.changeUnitCount(2);
    //Test if move invalid
	 	assertEquals(MoveAttemptResult.INVALID_MOVE, moveValidationStrategy.validateMove(fromTile.getPosition(), toTile.getPosition(), PlayerColor.Red));
	}
	
	
	@Test
	public void testCase4() {
	 	// Xdist=2 and Ydist=0 => Testing moving from postion (4,4) to (6,4) 
		fromTile = (StandardTile)game.getTile(new Position(4,4));
		fromTile.changePlayerColor(PlayerColor.Red);
		fromTile.changeUnitCount(2);
    toTile = (StandardTile)game.getTile(new Position(6,4));		
		toTile.changePlayerColor(PlayerColor.Green);
    toTile.changeUnitCount(2);
    //Test if move invalid
	 	assertEquals(MoveAttemptResult.INVALID_MOVE, moveValidationStrategy.validateMove(fromTile.getPosition(), toTile.getPosition(), PlayerColor.Red));
	}
	
	
	@Test
	public void testCase5() {
	 	// Xdist=0 and Ydist=2 => Testing moving from postion (4,4) to (4,6) 
		fromTile = (StandardTile)game.getTile(new Position(4,4));
		fromTile.changePlayerColor(PlayerColor.Red);
		fromTile.changeUnitCount(2);
    toTile = (StandardTile)game.getTile(new Position(4,6));		
		toTile.changePlayerColor(PlayerColor.Green);
    toTile.changeUnitCount(2);
    //Test if move invalid
	 	assertEquals(MoveAttemptResult.INVALID_MOVE, moveValidationStrategy.validateMove(fromTile.getPosition(), toTile.getPosition(), PlayerColor.Red));
	}
	
	
	@Test
	public void testCase6() {
	 	// Xdist=-1 and Ydist=-1 => Testing moving from postion (3,4) to (4,5)
	 	fromPos = new Position(3,4);
	 	toPos = new Position(4,5);
		fromTile = (StandardTile)game.getTile(fromPos);
		fromTile.changePlayerColor(PlayerColor.Red);
		fromTile.changeUnitCount(2);
    toTile = (StandardTile)game.getTile(toPos);		
		toTile.changePlayerColor(PlayerColor.Green);
    toTile.changeUnitCount(2);
//    System.out.println(fromTile.getOwnerColor() + " on " + fromTile.getType() + ": Units=" + fromTile.getUnitCount() + ", Strategic value=" + fromTile.getStrategicValue());
//	  System.out.println(toTile.getOwnerColor() + " on " + toTile.getType() + ": Units " + toTile.getUnitCount() + ", Strategic value=" + toTile.getStrategicValue()); 
   
    //Test if move valid
	 	assertEquals(MoveAttemptResult.ATTACK_NEEDED, moveValidationStrategy.validateMove(fromPos, toPos, PlayerColor.Red));
	 	
	 	assertTrue(game.move(fromTile.getPosition(), toTile.getPosition(), 2)); 
		// Check if state = attack
    assertEquals(State.attack, game.getState());
    
    //Red (attacker) throws 1
    assertTrue(game.dieRolled(1));
    // Get Tiles to validate result of move
    fromTile = (StandardTile)game.getTile(fromPos);
    toTile = (StandardTile)game.getTile(toPos);   
    // Check how many units the Green (defender) has left: 2 - (1-1)/2 = 2
    assertEquals(2, toTile.getUnitCount());
		// Check if state = defend
    assertEquals(State.defend, game.getState());
    
    //Green (defender) throws 1
    assertTrue(game.dieRolled(1));
    // Get Tiles to validate result of move
    fromTile = (StandardTile)game.getTile(fromPos);
    toTile = (StandardTile)game.getTile(toPos);   
    // Check how many units the Red (attacker) has left: 2 - (1-1)/2 = 2
    assertEquals(2, fromTile.getUnitCount());
		// Check if state = attack
    assertEquals(State.attack, game.getState());
    
    // Red (attacker) gives up
    assertTrue(game.givingUp());
    assertEquals(State.buy, game.getState());
    fromTile = (StandardTile)game.getTile(fromPos);
    toTile = (StandardTile)game.getTile(toPos);   
    // Check how many units are left
    assertEquals(2, fromTile.getUnitCount());
    assertEquals(2, toTile.getUnitCount());
	}
	
	
	@Test
	public void testCase7() {
	 	// Xdist=-1 and Ydist=0 and Sf=1 and Sf=1
	 	// Define new test case because no neigbour tiles comply with the above specifications 
		assertEquals(1, 1);
	}
		
	@Test
	public void testCase8() {
	 	// Xdist=-1 and Ydist=1 => Testing moving from postion (5,0) to (4,1)
	 	fromPos = new Position(5,0);
	 	toPos = new Position(4,1);
		fromTile = (StandardTile)game.getTile(fromPos);
		fromTile.changePlayerColor(PlayerColor.Red);
		fromTile.changeUnitCount(2);
    toTile = (StandardTile)game.getTile(toPos);		
		toTile.changePlayerColor(PlayerColor.Green);
    toTile.changeUnitCount(2);
//    System.out.println(fromTile.getOwnerColor() + " on " + fromTile.getType() + ": Units=" + fromTile.getUnitCount() + ", Strategic value=" + fromTile.getStrategicValue());
//	  System.out.println(toTile.getOwnerColor() + " on " + toTile.getType() + ": Units " + toTile.getUnitCount() + ", Strategic value=" + toTile.getStrategicValue()); 
   
    //Test if move valid
	 	assertEquals(MoveAttemptResult.ATTACK_NEEDED, moveValidationStrategy.validateMove(fromPos, toPos, PlayerColor.Red));
	 	
	 	assertTrue(game.move(fromTile.getPosition(), toTile.getPosition(), 2)); 
		// Check if state = attack
    assertEquals(State.attack, game.getState());
    
    //Red (attacker) throws 1
    assertTrue(game.dieRolled(1));
    // Get Tiles to validate result of move
    fromTile = (StandardTile)game.getTile(fromPos);
    toTile = (StandardTile)game.getTile(toPos);   
    // Check how many units the Green (defender) has left: 2 - (1-1)/2 = 2
    assertEquals(0, toTile.getUnitCount());
		// Check if state = defend
    assertEquals(State.buy, game.getState());
	}
	
	
	@Test
	public void testCase9() {
	 	// Xdist=0 and Ydist=-1 and Sf=3 and Sf=3
	 	// Define new test case because no neigbour tiles comply with the above specifications 
		assertEquals(1, 1);
	}
	
	
	@Test
	public void testCase10() {
	 	// Xdist=0 and Ydist=1 and Sf=4 and St=4
	 	// Define new test case because no neigbour tiles comply with the above specifications 
		assertEquals(1, 1);
	}
	
	
	@Test
	public void testCase11() {
	 	// Xdist=1 and Ydist=-1 and Sf=5 and St=5
	 	// Define new test case because no neigbour tiles comply with the above specifications 
		assertEquals(1, 1);
	}
	
	
	@Test
	public void testCase12() {
	 	// Xdist=1 and Ydist=0 and Sf=0 and St=0
	 	// => Testing moving from postion (5,1) to (6,1)
	 	fromPos = new Position(5,1);
	 	toPos = new Position(6,1);
		fromTile = (StandardTile)game.getTile(fromPos);
		fromTile.changePlayerColor(PlayerColor.Red);
		fromTile.changeUnitCount(2);
    toTile = (StandardTile)game.getTile(toPos);		
		toTile.changePlayerColor(PlayerColor.Green);
    toTile.changeUnitCount(2);
//    System.out.println(fromTile.getOwnerColor() + " on " + fromTile.getType() + ": Units=" + fromTile.getUnitCount() + ", Strategic value=" + fromTile.getStrategicValue());
//	  System.out.println(toTile.getOwnerColor() + " on " + toTile.getType() + ": Units " + toTile.getUnitCount() + ", Strategic value=" + toTile.getStrategicValue()); 
   
    //Test if move valid
	 	assertEquals(MoveAttemptResult.ATTACK_NEEDED, moveValidationStrategy.validateMove(fromPos, toPos, PlayerColor.Red));
	 	
	 	assertTrue(game.move(fromTile.getPosition(), toTile.getPosition(), 2)); 
		// Check if state = attack
    assertEquals(State.attack, game.getState());
    
    //Red (attacker) throws 1
    assertTrue(game.dieRolled(1));
    // Get Tiles to validate result of move
    fromTile = (StandardTile)game.getTile(fromPos);
    toTile = (StandardTile)game.getTile(toPos);   
    // Check how many units the Green (defender) has left: 2 - (1-1)/2 = 2
    assertEquals(2, toTile.getUnitCount());
		// Check if state = defend
    assertEquals(State.defend, game.getState());
    
    //Green (defender) throws 1
    assertTrue(game.dieRolled(1));
    // Get Tiles to validate result of move
    fromTile = (StandardTile)game.getTile(fromPos);
    toTile = (StandardTile)game.getTile(toPos);   
    // Check how many units the Red (attacker) has left: 2 - (1-1)/2 = 2
    assertEquals(2, fromTile.getUnitCount());
		// Check if state = attack
    assertEquals(State.attack, game.getState());
    
    // Red (attacker) gives up
    assertTrue(game.givingUp());
    assertEquals(State.buy, game.getState());
    fromTile = (StandardTile)game.getTile(fromPos);
    toTile = (StandardTile)game.getTile(toPos);   
    // Check how many units are left
    assertEquals(2, fromTile.getUnitCount());
    assertEquals(2, toTile.getUnitCount());
	}
	
	
	@Test
	public void testCase13() {
	 	// Xdist=1 and Ydist =1 and Sf=0 and St=0
	 	// => Testing moving from postion (5,1) to (6,2)
	 	fromPos = new Position(5,1);
	 	toPos = new Position(6,2);
		fromTile = (StandardTile)game.getTile(fromPos);
		fromTile.changePlayerColor(PlayerColor.Red);
		fromTile.changeUnitCount(2);
    toTile = (StandardTile)game.getTile(toPos);		
		toTile.changePlayerColor(PlayerColor.Green);
    toTile.changeUnitCount(2);
//    System.out.println(fromTile.getOwnerColor() + " on " + fromTile.getType() + ": Units=" + fromTile.getUnitCount() + ", Strategic value=" + fromTile.getStrategicValue());
//	  System.out.println(toTile.getOwnerColor() + " on " + toTile.getType() + ": Units " + toTile.getUnitCount() + ", Strategic value=" + toTile.getStrategicValue()); 
   
    //Test if move valid
	 	assertEquals(MoveAttemptResult.ATTACK_NEEDED, moveValidationStrategy.validateMove(fromPos, toPos, PlayerColor.Red));
	 	
	 	assertTrue(game.move(fromTile.getPosition(), toTile.getPosition(), 2)); 
		// Check if state = attack
    assertEquals(State.attack, game.getState());
    
    //Red (attacker) throws 1
    assertTrue(game.dieRolled(1));
    // Get Tiles to validate result of move
    fromTile = (StandardTile)game.getTile(fromPos);
    toTile = (StandardTile)game.getTile(toPos);   
    // Check how many units the Green (defender) has left: 2 - (1-1)/2 = 2
    assertEquals(2, toTile.getUnitCount());
		// Check if state = defend
    assertEquals(State.defend, game.getState());
    
    //Green (defender) throws 1
    assertTrue(game.dieRolled(1));
    // Get Tiles to validate result of move
    fromTile = (StandardTile)game.getTile(fromPos);
    toTile = (StandardTile)game.getTile(toPos);   
    // Check how many units the Red (attacker) has left: 2 - (1-1)/2 = 2
    assertEquals(2, fromTile.getUnitCount());
		// Check if state = attack
    assertEquals(State.attack, game.getState());
    
    // Red (attacker) gives up
    assertTrue(game.givingUp());
    assertEquals(State.buy, game.getState());
    fromTile = (StandardTile)game.getTile(fromPos);
    toTile = (StandardTile)game.getTile(toPos);   
    // Check how many units are left
    assertEquals(2, fromTile.getUnitCount());
    assertEquals(2, toTile.getUnitCount());
  }
}
