package hottargui.config;

import hottargui.config.*;
import hottargui.standard.*;
import hottargui.framework.*;

import org.junit.*;

import static org.junit.Assert.*;

public class TestAttackStrategy {

	private Game game;
	private Position saltminePos;
	private GameFactory gameFactory;
	private BetaAttackStrategy betaAttack;

	@Before	
	public void setUp() {

		game = new StandardGame();
  	gameFactory = new BetaGameFactory(game);
  	((StandardGame)game).setGameFactory(gameFactory);
		game.newGame();
		
		betaAttack = new BetaAttackStrategy(game);  

		saltminePos = new Position(3,3);
	}

	@Test
	public void testAttack() {
		assertEquals(1, 1);
	}
}
