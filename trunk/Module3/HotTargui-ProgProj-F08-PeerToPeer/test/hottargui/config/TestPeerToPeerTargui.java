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
	      System.out.println("Binding RED");
	      GameInitializer.bind("RED", redLocalGame, redLocalRepository);
		  
  	      greenLocalDie = new TestDie();
	      greenLocalRepository = new TestGameRepository();
	      greenLocalGame = new TestGame();
	      System.out.println("Binding GREEN");
	      GameInitializer.bind("GREEN", greenLocalGame, greenLocalRepository);

	      blueLocalDie = new TestDie();
	      blueLocalRepository = new TestGameRepository();
	      blueLocalGame = new TestGame();
	      System.out.println("Binding BLUE");
	      GameInitializer.bind("BLUE", blueLocalGame, blueLocalRepository);

	      yellowLocalDie = new TestDie();
	      yellowLocalRepository = new TestGameRepository();
	      yellowLocalGame = new TestGame();
	      System.out.println("Binding YELLOW");
	      GameInitializer.bind("YELLOW", yellowLocalGame, yellowLocalRepository);

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
          try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println();
	  }

	public static void main(String[] args) {
		try {
			TestPeerToPeerTargui p = new TestPeerToPeerTargui();
			p.runTransitionTour();
			p.finalize();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Killing");
        System.exit(0);
	}
	
	protected void finalize() throws Throwable
	{
	  System.out.println();
	  System.out.println("Cleaning up");
	  GameInitializer.unbind("RED");
	  GameInitializer.unbind("GREEN");
	  GameInitializer.unbind("BLUE");
	  GameInitializer.unbind("YELLOW");
	  super.finalize(); //not necessary if extending Object.¨
	}
	  
	  private void setupRed() throws RemoteException, MalformedURLException
	  {
	      System.out.println("Connecting RED to other applications");
		  redGameDecorator = GameInitializer.connect("RED", redLocalGame);
		  assertTrue(redGameDecorator != null);
	  }

	  private void setupGreen() throws RemoteException, MalformedURLException
	  {
	      System.out.println("Connecting GREEN to other applications");
		  greenGameDecorator = GameInitializer.connect("GREEN", greenLocalGame);
		  assertTrue(greenGameDecorator != null);
	  }

	  private void setupBlue() throws RemoteException, MalformedURLException
	  {
	      System.out.println("Connecting BLUE to other applications");
		  blueGameDecorator = GameInitializer.connect("BLUE", blueLocalGame);
		  assertTrue(blueGameDecorator != null);
	  }

	  private void setupYellow() throws RemoteException, MalformedURLException
	  {
	      System.out.println("Connecting YELLOW to other applications");
		  yellowGameDecorator = GameInitializer.connect("YELLOW", yellowLocalGame);
		  assertTrue(yellowGameDecorator != null);
	  }

	  public void runTransitionTour() throws RemoteException
	  {
		  System.out.println("Beginning test");
		  System.out.println();
		  validateAll();
		  newGamePropagatesToAll();
		  System.out.println("newGamePropagatesToAll: SUCCESS");
		  movePropagatesToAll();
		  System.out.println("movePropagatesToAll: SUCCESS");
		  invalidMovePropagatesToNone();
		  System.out.println("invalidMovePropagatesToNone: SUCCESS");
		  buyPropagatesToAll();
		  System.out.println("buyPropagatesToAll: SUCCESS");
		  invalidBuyPropagatesToNone();
		  System.out.println("invalidBuyPropagatesToNone: SUCCESS");
		  rollDiePropagatesToAll();
		  System.out.println("rollDiePropagatesToAll: SUCCESS");
		  System.out.println();
		  System.out.println("Test completed");
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
		  assertEquals(greenLocalGame.moveCount, 2);
		  assertEquals(blueLocalGame.moveCount, 2);
		  assertEquals(yellowLocalGame.moveCount, 2);
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

		  redGameDecorator.buy(2, new Position(3,3));
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
		  redLocalGame.getDieValueReturnValue = 5; // Requires as it is set for the other Die's
		  redLocalRepository.getDieReturnValue = redLocalDie; // Not needed
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
