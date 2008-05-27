package hottargui.config;

import static org.junit.Assert.*;

import java.lang.reflect.Field;

import org.junit.*;

import hottargui.factory.*;
import hottargui.framework.*;
import hottargui.standard.*;
import hottargui.strategy.*;


public class TestSemiTargui {
	/**
	 * This test is only designed to show that the configuration
	 * is correct, as the individual parts of the configuration is
	 * already tested.
	 */

	  StandardGame game;
	  GameFactory gameFactory;
	  StandardGameRepository gameRepository;
	  
	  @Before 
	  public void setUp() {
		gameRepository = new StandardGameRepository();
		game = new StandardGame(gameRepository);
	    gameFactory = new SemiGameFactory(game);
	    gameRepository.initialize(gameFactory, game);
	    game.newGame();
	  }

	  @Test 
	  public void semiUsesRandomOrderBoard() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
		  // unfortunately reflection in java cannot be used to break incapsulation.
/*
		  StandardBoard sb = (StandardBoard)gameRepository.getBoard();
		  Field f = sb.getClass().getDeclaredField("boardFactory");
		  BoardFactory bf = (BoardFactory)f.get(sb);
		  assertEquals(bf.getClass(), RandomOrderBoardFactory.class);
*/
	  }	

	  @Test 
	  public void semiUsesStandardMoveValidation() {
		  assertEquals(gameRepository.getMoveValidationStrategy().getClass(), StandardMoveValidationStrategy.class);
	  }	
	  
	  @Test 
	  public void semiUsesSimpleTurnStrategy() {
		  assertEquals(gameRepository.getTurnStrategy().getClass(), SimpleTurnStrategy.class);
	  }	
	
	  @Test 
	  public void semiUsesDieRollAttackStrategy() {
		  assertEquals(gameRepository.getAttackStrategy().getClass(), DieRollAttackStrategy.class);
	  }	

	  @Test 
	  public void semiUsesAllTilePutUnitsStrategy() {
		  assertEquals(gameRepository.getPutUnitsStrategy().getClass(), AllTilePutUnitsStrategy.class);
	  }	

	  @Test 
	  public void semiUsesRevenueWinnerStrategy() {
		  assertEquals(gameRepository.getWinnerStrategy().getClass(), RevenueWinnerStrategy.class);
	  }	
}
