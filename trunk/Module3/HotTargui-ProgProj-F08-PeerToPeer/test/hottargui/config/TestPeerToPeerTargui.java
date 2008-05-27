package hottargui.config;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

import org.junit.*;

import hottargui.framework.*;
import hottargui.net.*;

public class TestPeerToPeerTargui {
	/**
	 * This test is only designed to show that the configuration
	 * is correct, as the individual parts of the configuration is
	 * already tested.
	 */

	  static Game redGameDecorator;
	  static TestGame redLocalGame;
	  static TestDie redLocalDie;
	  static TestGameRepository redLocalRepository;

	  static Game greenGameDecorator;
	  static TestGame greenLocalGame;
	  static TestDie greenLocalDie;
	  static GameRepository greenLocalRepository;

	  static Game blueGameDecorator;
	  static TestGame blueLocalGame;
	  static TestDie blueLocalDie;
	  static GameRepository blueLocalRepository;

	  static Game yellowGameDecorator;
	  static TestGame yellowLocalGame;
	  static TestDie yellowLocalDie;
	  static GameRepository yellowLocalRepository;

	  @BeforeClass 
	  public static void setUp() throws RemoteException, MalformedURLException {
		  System.out.println(System.getProperty("java.security.policy"));

          if (System.getSecurityManager() == null) {
	          System.setSecurityManager(new RMISecurityManager());
		  }

	      redLocalDie = new TestDie();
		  redLocalRepository = new TestGameRepository(redLocalDie);
	      redLocalGame = new TestGame();

	      greenLocalDie = new TestDie();
	      greenLocalRepository = new TestGameRepository(greenLocalDie);
	      greenLocalGame = new TestGame();

	      blueLocalDie = new TestDie();
	      blueLocalRepository = new TestGameRepository(blueLocalDie);
	      blueLocalGame = new TestGame();

	      yellowLocalDie = new TestDie();
	      yellowLocalRepository = new TestGameRepository(yellowLocalDie);
	      yellowLocalGame = new TestGame();

		  
		  // Run network RED setup
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
	  
	  private static void setupRed() throws RemoteException, MalformedURLException
	  {
		  GameInitializer gi = new GameInitializer();
		  redGameDecorator = gi.initialize("RED", redLocalGame, redLocalRepository);
	  }

	  private static void setupGreen() throws RemoteException, MalformedURLException
	  {
		  GameInitializer gi = new GameInitializer();
		  greenGameDecorator = gi.initialize("GREEN", greenLocalGame, greenLocalRepository);
	  }

	  private static void setupBlue() throws RemoteException, MalformedURLException
	  {
		  GameInitializer gi = new GameInitializer();
		  blueGameDecorator = gi.initialize("BLUE", blueLocalGame, blueLocalRepository);
	  }

	  private static void setupYellow() throws RemoteException, MalformedURLException
	  {
		  GameInitializer gi = new GameInitializer();
		  yellowGameDecorator = gi.initialize("YELLOW", yellowLocalGame, yellowLocalRepository);
	  }

	  @Test
	  public void runTransitionTour() throws RemoteException
	  {
		  validateAll();
		  newGamePropagatesToAll();
		  movePropagatesToAll();
	  }
	  
	  private void validateAll()
	  {
		  assertTrue(redLocalGame.validateAllFalse());
		  assertTrue(greenLocalGame.validateAllFalse());
		  assertTrue(blueLocalGame.validateAllFalse());
		  assertTrue(yellowLocalGame.validateAllFalse());
	  }
	  
	  public void newGamePropagatesToAll() throws RemoteException {
		  redGameDecorator.newGame();
		  assertTrue(redLocalGame.newGameCalled);
		  redLocalGame.newGameCalled = false;
		  assertTrue(greenLocalGame.newGameCalled);
		  greenLocalGame.newGameCalled = false;
		  assertTrue(blueLocalGame.newGameCalled);
		  blueLocalGame.newGameCalled = false;
		  assertTrue(yellowLocalGame.newGameCalled);
		  yellowLocalGame.newGameCalled = false;
		  validateAll(); // Ensure that nothing else was called
	  }	

	  public void movePropagatesToAll() throws RemoteException {
		  redGameDecorator.move(new Position(0,0), new Position(1,0), 2);
		  assertTrue(redLocalGame.moveCalled);
		  redLocalGame.moveCalled = false;
		  assertTrue(greenLocalGame.moveCalled);
		  greenLocalGame.moveCalled = false;
		  assertTrue(blueLocalGame.moveCalled);
		  blueLocalGame.moveCalled = false;
		  assertTrue(yellowLocalGame.moveCalled);
		  yellowLocalGame.moveCalled = false;
		  validateAll(); // Ensure that nothing else was called

		  // Test values
		  assertEquals(redLocalGame.moveFrom, new Position(0,0));
		  assertEquals(greenLocalGame.moveFrom, new Position(0,0));
		  assertEquals(blueLocalGame.moveFrom, new Position(0,0));
		  assertEquals(yellowLocalGame.moveFrom, new Position(0,0));

		  assertEquals(redLocalGame.moveTo, new Position(1,0));
		  assertEquals(greenLocalGame.moveTo, new Position(1,0));
		  assertEquals(blueLocalGame.moveTo, new Position(1,0));
		  assertEquals(yellowLocalGame.moveTo, new Position(1,0));

		  assertEquals(redLocalGame.moveCount, 2);
		  assertEquals(greenLocalGame.moveTo, 2);
		  assertEquals(blueLocalGame.moveTo, 2);
		  assertEquals(yellowLocalGame.moveTo, 2);
	  }
}
