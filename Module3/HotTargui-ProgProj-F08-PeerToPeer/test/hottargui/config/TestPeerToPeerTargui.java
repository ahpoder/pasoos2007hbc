package hottargui.config;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

import org.junit.*;

import hottargui.framework.*;
import hottargui.net.*;
import hottargui.standard.*;


public class TestPeerToPeerTargui {
	/**
	 * This test is only designed to show that the configuration
	 * is correct, as the individual parts of the configuration is
	 * already tested.
	 */

	  Game redGameDecorator;
	  Game redLocalGame;
	  GameFactory redLocalFactory;
	  StandardGameRepository redLocalRepository;

	  Game greenGameDecorator;
	  Game greenLocalGame;
	  GameFactory greenLocalFactory;
	  StandardGameRepository greenLocalRepository;

	  Game blueGameDecorator;
	  Game blueLocalGame;
	  GameFactory blueLocalFactory;
	  StandardGameRepository blueLocalRepository;

	  Game yellowGameDecorator;
	  Game yellowLocalGame;
	  GameFactory yellowLocalFactory;
	  StandardGameRepository yellowLocalRepository;

	  @Before 
	  public void setUp() throws RemoteException, MalformedURLException {
		  // Run RED setup
		  new Thread(new Runnable() {
			    public void run() {
			      try {
			         setupRed();
			      }
			      catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    }
			  }).start();	  

		  // Run GREEN setup
		  new Thread(new Runnable() {
			    public void run() {
			      try {
			         setupGreen();
			      }
			      catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    }
			  }).start();	  

		  // Run BLUE setup
		  new Thread(new Runnable() {
			    public void run() {
			      try {
			         setupBlue();
			      }
			      catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    }
			  }).start();	  

		  // Run YELLOW setup
          setupYellow();
	  }
	  
	  private void setupRed() throws RemoteException, MalformedURLException
	  {
	      redLocalRepository = new StandardGameRepository();
	      redLocalGame = new StandardGame(redLocalRepository);
	      redLocalFactory = new SemiGameFactory(redLocalGame);
	      redLocalRepository.initialize(redLocalFactory, redLocalGame);
		  GameInitializer gi = new GameInitializer();
		  redGameDecorator = gi.initialize("RED", redLocalGame, redLocalRepository);
		  redGameDecorator.newGame();
	  }

	  private void setupGreen() throws RemoteException, MalformedURLException
	  {
	      greenLocalRepository = new StandardGameRepository();
	      greenLocalGame = new StandardGame(greenLocalRepository);
	      greenLocalFactory = new SemiGameFactory(greenLocalGame);
	      greenLocalRepository.initialize(greenLocalFactory, greenLocalGame);
		  GameInitializer gi = new GameInitializer();
		  greenGameDecorator = gi.initialize("GREEN", greenLocalGame, greenLocalRepository);
		  greenGameDecorator.newGame();
	  }

	  private void setupBlue() throws RemoteException, MalformedURLException
	  {
	      blueLocalRepository = new StandardGameRepository();
	      blueLocalGame = new StandardGame(blueLocalRepository);
	      blueLocalFactory = new SemiGameFactory(blueLocalGame);
	      blueLocalRepository.initialize(blueLocalFactory, blueLocalGame);
		  GameInitializer gi = new GameInitializer();
		  blueGameDecorator = gi.initialize("BLUE", blueLocalGame, blueLocalRepository);
		  blueGameDecorator.newGame();
	  }

	  private void setupYellow() throws RemoteException, MalformedURLException
	  {
	      yellowLocalRepository = new StandardGameRepository();
	      yellowLocalGame = new StandardGame(yellowLocalRepository);
	      yellowLocalFactory = new SemiGameFactory(yellowLocalGame);
	      yellowLocalRepository.initialize(yellowLocalFactory, yellowLocalGame);
		  GameInitializer gi = new GameInitializer();
		  yellowGameDecorator = gi.initialize("YELLOW", yellowLocalGame, yellowLocalRepository);
		  yellowGameDecorator.newGame();
	  }

	  @Test 
	  public void fourGamesInMoveState() throws RemoteException {
		  assertEquals(State.move, redGameDecorator.getState());
		  assertEquals(State.move, greenGameDecorator.getState());
		  assertEquals(State.move, blueGameDecorator.getState());
		  assertEquals(State.move, yellowGameDecorator.getState());
	  }	

	  @Test 
	  public void redMoveToBoarderingErgResultsInRedOwnershipOnAllFourBoards() throws RemoteException {
		  redGameDecorator.move(new Position(0,0), new Position(1,0), 2);
		  assertEquals(PlayerColor.Red, redLocalGame.getTile(new Position(1,0)).getOwnerColor());
		  assertEquals(PlayerColor.Red, greenLocalGame.getTile(new Position(1,0)).getOwnerColor());
		  assertEquals(PlayerColor.Red, blueLocalGame.getTile(new Position(1,0)).getOwnerColor());
		  assertEquals(PlayerColor.Red, yellowLocalGame.getTile(new Position(1,0)).getOwnerColor());
	  }	
}
