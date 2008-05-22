package hottargui.config;

import hottargui.config.*;
import hottargui.standard.*;
import hottargui.framework.*;

import org.junit.*;

import static org.junit.Assert.*;

public class TestAttackStrategy {

	private StandardGame game;
	private Position saltminePos;
	private Position anOasisPos;
	private Position aFeshFeshPos;
	private GameFactory gameFactory;
	private BetaAttackStrategy betaAttack;
	private MoveValidationStrategy moveValidationStrategy;

	@Before	
	public void setUp() {

		game = new StandardGame();
  	gameFactory = new BetaGameFactory(game);
  	initialize();
		betaAttack = new BetaAttackStrategy(game);  
		moveValidationStrategy = gameFactory.createMoveValidationStrategy();
        
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
	public void testAttackSuccessfull() {
		StandardTile toTile;
  	StandardTile fromTile;
		fromTile = (StandardTile)game.getTile(anOasisPos);
		fromTile.changePlayerColor(PlayerColor.Red);
		fromTile.changeUnitCount(8);
    toTile = (StandardTile)game.getTile(aFeshFeshPos);		
		toTile.changePlayerColor(PlayerColor.Green);
    toTile.changeUnitCount(5);
    System.out.println(fromTile.getOwnerColor() + " on " + fromTile.getType() + ": Units=" + fromTile.getUnitCount() + ", Strategic value=" + fromTile.getStrategicValue());
    System.out.println(toTile.getOwnerColor() + " on " + toTile.getType() + ": Units " + toTile.getUnitCount() + ", Strategic value=" + toTile.getStrategicValue());
    
    //Red throws 4
    assertTrue(game.dieRolled(4));

	 	//Testing if move valid - not really necessary to test
	 	assertEquals(MoveAttemptResult.ATTACK_NEEDED, moveValidationStrategy.validateMove(fromTile.getPosition(), toTile.getPosition(), PlayerColor.Red));

    assertTrue(game.move(fromTile.getPosition(), toTile.getPosition(), 8));
    
    // Validate result of move
    fromTile = (StandardTile)game.getTile(anOasisPos);
    toTile = (StandardTile)game.getTile(aFeshFeshPos);
		// Check if state = defend
    assertEquals(State.defend, game.getState());
    // Check how many units the defender has left: 5 - (7-1)/2 = 2
    assertEquals(2, toTile.getUnitCount());
    
    //Green throws 6
    assertTrue(game.dieRolled(6));
    
	 	//Testing if move valid - not really necessary to test
	 	assertEquals(MoveAttemptResult.ATTACK_NEEDED, moveValidationStrategy.validateMove(toTile.getPosition(), fromTile.getPosition(), PlayerColor.Green));
    
        
        //To be continued......
    }

}
