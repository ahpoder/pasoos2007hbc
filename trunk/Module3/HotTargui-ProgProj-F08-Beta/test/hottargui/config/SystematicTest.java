package hottargui.config;

import hottargui.config.*;
import hottargui.standard.*;
import hottargui.framework.*;

import org.junit.*;

import static org.junit.Assert.*;

public class SystematicTest {

	private StandardGame game;
	private Position saltminePos;
	private GameFactory gameFactory;

	@Before	
	public void setUp() {

		game = new StandardGame();
  	gameFactory = new BetaGameFactory(game);
  	game.setGameFactory(gameFactory);
		game.newGame();

		saltminePos = new Position(3,3);
	}

	@Test
	public void testCase1() {
		assertEquals(1, 1);
	}
	
	
	@Test
	public void testCase2() {
		assertEquals(1, 1);
	}
	
	
	@Test
	public void testCase3() {
		assertEquals(1, 1);
	}
	
	
	@Test
	public void testCase4() {
		assertEquals(1, 1);
	}
	
	
	@Test
	public void testCase5() {
		assertEquals(1, 1);
	}
	
	
	@Test
	public void testCase6() {
		assertEquals(1, 1);
	}
	
	
	@Test
	public void testCase7() {
		assertEquals(1, 1);
	}
	
	
	@Test
	public void testCase8() {
		assertEquals(1, 1);
	}
	
	
	@Test
	public void testCase9() {
		assertEquals(1, 1);
	}
	
	
	@Test
	public void testCase10() {
		assertEquals(1, 1);
	}
	
	
	@Test
	public void testCase11() {
		assertEquals(1, 1);
	}
	
	
	@Test
	public void testCase12() {
		assertEquals(1, 1);
	}
	
	
	@Test
	public void testCase13() {
		assertEquals(1, 1);
	}
}
