package hottargui.config;

import hottargui.config.*;
import hottargui.standard.*;
import hottargui.framework.*;

import org.junit.*;

import static org.junit.Assert.*;

public class TestWinnerStrategy {

	private StandardGame game;
//	private Game game;
	private Position saltminePos;
	private GameFactory gameFactory;
	private BetaWinnerStrategy betaWinner;

	@Before	
	public void setUp() {

		game = new StandardGame();
  	gameFactory = new BetaGameFactory(game);
  	game.setGameFactory(gameFactory);
//  	((StandardGame)game).setGameFactory(gameFactory);
		game.newGame();

		betaWinner = new BetaWinnerStrategy(game);  

		saltminePos = new Position(3,3);
	}

	@Test
	public void testWinnerEqualRevenue() {
		// Red and Green have Eqaul revenue, but Green owns the saltmine. Green is the winner.
		
		StandardTile oasisTile = (StandardTile) game.getTile(new Position (1,1));
//    System.out.println(oasisTile.getType() + " has economic value " + saltMineTile.getEconomicValue());
		oasisTile.changePlayerColor(PlayerColor.Red);
		
		StandardTile regTile = (StandardTile) game.getTile(new Position (0,3));
		regTile.changePlayerColor(PlayerColor.Red);		

		StandardTile saltmineTile = (StandardTile) game.getTile(saltminePos);
		saltmineTile.changePlayerColor(PlayerColor.Green);

		assertEquals(PlayerColor.Green, betaWinner.getWinner());
	}

	@Test
	public void testWinnerRed() {
		// Red is Winner has 2 x Oasis (4 revenue). Other Players have 1 x Reg (3 revenue each)

		Tile regTile1 = game.getTile(new Position (0,3));
		((StandardTile)regTile1).changePlayerColor(PlayerColor.Red);

		Tile regTile2 = game.getTile(new Position (3,1));
		((StandardTile)regTile2).changePlayerColor(PlayerColor.Red);

		Tile oasisTile1 = game.getTile(new Position (2,0));
		((StandardTile)oasisTile1).changePlayerColor(PlayerColor.Green);

		Tile oasisTile2 = game.getTile(new Position (1,1));
		((StandardTile)oasisTile2).changePlayerColor(PlayerColor.Yellow);

		Tile oasisTile3 = game.getTile(new Position (0,4));
		((StandardTile)oasisTile3).changePlayerColor(PlayerColor.Blue);

		assertEquals(PlayerColor.Red, betaWinner.getWinner());
	}

	@Test
	public void testWinnerGreen() {
		// Green is Winner has 2 x Oasis (4 revenue). Other Players have 1 x Reg (3 revenue each)

		Tile regTile1 = game.getTile(new Position (0,3));
		((StandardTile)regTile1).changePlayerColor(PlayerColor.Green);

		Tile regTile2 = game.getTile(new Position (3,1));
		((StandardTile)regTile2).changePlayerColor(PlayerColor.Blue);

		Tile oasisTile1 = game.getTile(new Position (2,0));
		((StandardTile)oasisTile1).changePlayerColor(PlayerColor.Blue);

		Tile oasisTile2 = game.getTile(new Position (1,1));
		((StandardTile)oasisTile2).changePlayerColor(PlayerColor.Yellow);

		Tile oasisTile3 = game.getTile(new Position (0,4));
		((StandardTile)oasisTile3).changePlayerColor(PlayerColor.Red);

		assertEquals(PlayerColor.Blue, betaWinner.getWinner());
	}
	
	@Test
	public void testWinnerBlue() {
		// Blue is Winner has 2 x Oasis (4 revenue). Other Players have 1 x Reg (3 revenue each)

		Tile regTile1 = game.getTile(new Position (0,3));
		((StandardTile)regTile1).changePlayerColor(PlayerColor.Blue);

		Tile regTile2 = game.getTile(new Position (3,1));
		((StandardTile)regTile2).changePlayerColor(PlayerColor.Blue);

		Tile oasisTile1 = game.getTile(new Position (2,0));
		((StandardTile)oasisTile1).changePlayerColor(PlayerColor.Green);

		Tile oasisTile2 = game.getTile(new Position (1,1));
		((StandardTile)oasisTile2).changePlayerColor(PlayerColor.Yellow);

		Tile oasisTile3 = game.getTile(new Position (0,4));
		((StandardTile)oasisTile3).changePlayerColor(PlayerColor.Red);

		/*Get the right winner */
		assertEquals(PlayerColor.Blue, betaWinner.getWinner());
	}

	@Test
	public void testWinnerYellow() {
		// Yellow is Winner has 2 x Reg (4 revenue). Other Players have 1 x Oasis (3 revenue each)

		StandardTile regTile1 = (StandardTile) game.getTile(new Position (0,3));
		regTile1.changePlayerColor(PlayerColor.Yellow);

		StandardTile regTile2 = (StandardTile) game.getTile(new Position (3,1));
		regTile2.changePlayerColor(PlayerColor.Yellow);

		StandardTile oasisTile1 = (StandardTile) game.getTile(new Position (2,0));
		oasisTile1.changePlayerColor(PlayerColor.Green);

		StandardTile oasisTile2 = (StandardTile) game.getTile(new Position (1,1));
		oasisTile2.changePlayerColor(PlayerColor.Red);

		StandardTile oasisTile3 = (StandardTile) game.getTile(new Position (0,4));
		oasisTile3.changePlayerColor(PlayerColor.Blue);

		assertEquals(PlayerColor.Yellow, betaWinner.getWinner());
	}
	
/*		
	@Test
	public void testWinner() {
		StandardTile tile = (StandardTile) game.getTile(new Position(2, 2)); //Fesh Fesh,0
    tile.changePlayerColor(PlayerColor.Red);

    tile = (StandardTile) game.getTile(new Position(2, 3)); //Oasis,3
    tile.changePlayerColor(PlayerColor.Red);

    tile = (StandardTile) game.getTile(new Position(6, 3)); //Fesh Fesh,0
    tile.changePlayerColor(PlayerColor.Green);

    tile = (StandardTile) game.getTile(new Position(5, 0)); //Oasis,3
    tile.changePlayerColor(PlayerColor.Green);

    game.setState(State.endGame);

    //both players should collect the same revenue -> no winner
    assertEquals(PlayerColor.None, game.getWinner());

    tile = (StandardTile) game.getTile(new Position(3, 1)); //REG,2
    tile.changePlayerColor(PlayerColor.Green);

    tile = (StandardTile) game.getTile(new Position(1, 1)); //Oasis,3
    tile.changePlayerColor(PlayerColor.Green);

    //now green player should collect more revenue
    assertEquals(PlayerColor.Green, game.getWinner());

    tile = (StandardTile) game.getTile(new Position(3, 3)); //Salt mine,5
    tile.changePlayerColor(PlayerColor.Red);

    //both player shoud collect same amount, but red player owns the salt mine
    assertEquals(PlayerColor.Red, game.getWinner());

    tile = (StandardTile) game.getTile(new Position(4, 5)); //reg,2
    tile.changePlayerColor(PlayerColor.Green);

    //green player collect most revenue, but red player owns the salt mine
    assertEquals(PlayerColor.Green, game.getWinner());
  }
*/	
}
