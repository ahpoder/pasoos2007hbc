package hottargui.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import hottargui.framework.Game;
import hottargui.framework.Position;
import hottargui.net.GameInitializer;

import java.net.MalformedURLException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

public class TestPeerToPeerTargui {

	/**
	 * @param args
	 */
	  Game redGameDecorator;
	  TestGame redLocalGame;
	  TestDie redLocalDie;
	  TestGameRepository redLocalRepository;

	  Game greenGameDecorator;
	  TestGame greenLocalGame;
	  TestDie greenLocalDie;
	  TestGameRepository greenLocalRepository;

	  Game blueGameDecorator;
	  TestGame blueLocalGame;
	  TestDie blueLocalDie;
	  TestGameRepository blueLocalRepository;

	  Game yellowGameDecorator;
	  TestGame yellowLocalGame;
	  TestDie yellowLocalDie;
	  TestGameRepository yellowLocalRepository;
	  
	  public TestPeerToPeerTargui() throws RemoteException, MalformedURLException
	  {
		  System.out.println(System.getProperty("java.security.policy"));

          if (System.getSecurityManager() == null) {
	          System.setSecurityManager(new RMISecurityManager());
		  }

	      redLocalDie = new TestDie();
		  redLocalRepository = new TestGameRepository();
	      redLocalGame = new TestGame();

  	      greenLocalDie = new TestDie();
	      greenLocalRepository = new TestGameRepository();
	      greenLocalGame = new TestGame();

	      blueLocalDie = new TestDie();
	      blueLocalRepository = new TestGameRepository();
	      blueLocalGame = new TestGame();

	      yellowLocalDie = new TestDie();
	      yellowLocalRepository = new TestGameRepository();
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

	public static void main(String[] args) {
		try {
			TestPeerToPeerTargui p = new TestPeerToPeerTargui();
			p.runTransitionTour();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	  
	  private void setupRed() throws RemoteException, MalformedURLException
	  {
		  GameInitializer gi = new GameInitializer();
		  redGameDecorator = gi.initialize("RED", redLocalGame, redLocalRepository);
	  }

	  private void setupGreen() throws RemoteException, MalformedURLException
	  {
		  GameInitializer gi = new GameInitializer();
		  greenGameDecorator = gi.initialize("GREEN", greenLocalGame, greenLocalRepository);
	  }

	  private void setupBlue() throws RemoteException, MalformedURLException
	  {
		  GameInitializer gi = new GameInitializer();
		  blueGameDecorator = gi.initialize("BLUE", blueLocalGame, blueLocalRepository);
	  }

	  private void setupYellow() throws RemoteException, MalformedURLException
	  {
		  GameInitializer gi = new GameInitializer();
		  yellowGameDecorator = gi.initialize("YELLOW", yellowLocalGame, yellowLocalRepository);
	  }

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
	  
	  public void invalidMovePropagatesToNone() throws RemoteException {
		  // Setup
		  redLocalGame.moveResult = false;

		  redGameDecorator.move(new Position(0,0), new Position(1,0), 2);
		  redLocalGame.moveResult = true;
		  assertTrue(redLocalGame.moveCalled);
		  redLocalGame.moveCalled = false;
		  assertFalse(greenLocalGame.moveCalled);
		  assertFalse(blueLocalGame.moveCalled);
		  assertFalse(yellowLocalGame.moveCalled);
		  validateAll(); // Ensure that nothing else was called
	  }
	  
	  public void buyPropagatesToAll() throws RemoteException {
		  redGameDecorator.buy(10, new Position(0,0));
		  assertTrue(redLocalGame.buyCalled);
		  redLocalGame.buyCalled = false;
		  assertTrue(greenLocalGame.buyCalled);
		  greenLocalGame.buyCalled = false;
		  assertTrue(blueLocalGame.buyCalled);
		  blueLocalGame.buyCalled = false;
		  assertTrue(yellowLocalGame.buyCalled);
		  yellowLocalGame.buyCalled = false;
		  validateAll(); // Ensure that nothing else was called

		  // Test values
		  assertEquals(redLocalGame.buyCount, 10);
		  assertEquals(greenLocalGame.buyCount, 10);
		  assertEquals(blueLocalGame.buyCount, 10);
		  assertEquals(yellowLocalGame.buyCount, 10);

		  assertEquals(redLocalGame.buyDeploy, new Position(0,0));
		  assertEquals(greenLocalGame.buyDeploy, new Position(0,0));
		  assertEquals(blueLocalGame.buyDeploy, new Position(0,0));
		  assertEquals(yellowLocalGame.buyDeploy, new Position(0,0));
	  }
	  
	  public void invalidBuyPropagatesToNone() throws RemoteException {
		  // Setup
		  redLocalGame.buyResult = false;

		  redGameDecorator.move(new Position(0,0), new Position(1,0), 2);
		  redLocalGame.buyResult = true;
		  assertTrue(redLocalGame.buyCalled);
		  redLocalGame.buyCalled = false;
		  assertFalse(greenLocalGame.buyCalled);
		  assertFalse(blueLocalGame.buyCalled);
		  assertFalse(yellowLocalGame.buyCalled);
		  validateAll(); // Ensure that nothing else was called
	  }
	  
	  public void rollDiePropagatesToAll() throws RemoteException {
		  // Setup
		  redLocalDie.getValueReturnValue = 5; // Requires as it is set for the other Die's
		  redLocalRepository.getDieReturnValue = redLocalDie;
		  greenLocalRepository.getDieReturnValue = greenLocalDie;
		  blueLocalRepository.getDieReturnValue = blueLocalDie;
		  yellowLocalRepository.getDieReturnValue = yellowLocalDie;
		  
		  redGameDecorator.rollDie();
		  
		  // Validate result
		  assertTrue(redLocalGame.getDieValueCalled); // The local call performed by the decorator
		  
		  assertFalse(redLocalRepository.getDieStrategyCalled); // This is not called as it is red that instigates
		  assertTrue(greenLocalRepository.getDieStrategyCalled);
		  assertTrue(blueLocalRepository.getDieStrategyCalled);
		  assertTrue(yellowLocalRepository.getDieStrategyCalled);

		  assertFalse(redLocalDie.setValueCalled); // This is not called as it is red that instigates
		  assertTrue(greenLocalDie.setValueCalled);
		  assertTrue(blueLocalDie.setValueCalled);
		  assertTrue(yellowLocalDie.setValueCalled);
		  
		  assertEquals(5, greenLocalDie.setValueVal);
		  assertEquals(5, blueLocalDie.setValueVal);
		  assertEquals(5, yellowLocalDie.setValueVal);
	  }

}
