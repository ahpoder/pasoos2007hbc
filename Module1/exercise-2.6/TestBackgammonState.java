import org.junit.*;
import static org.junit.Assert.*;

/** Test cases for Backgammon state exercise

    Author: (c) Henrik Bærbak Christensen 2007
*/


public class TestBackgammonState {
  private BackgammonStateMachine bs;

  /** fixture for testing the Backgammon statemachine */
  @Before
  public void setUp() {
    bs = new BackgammonStateMachineImpl();
  }
  
  @Test
  public void testInitialisation() {
    assertEquals( BackgammonState.StartState, bs.getState() );
  }
  
  @Test
  public void testNewGameReturnValue() {
    assertTrue( bs.newGame() );
  }

  @Test
  public void testNewGameToDiceShakeState() {
    bs.newGame();
	assertEquals( BackgammonState.DiceShakeState, bs.getState() );
  }

  @Test
  public void testMoveRedInDiceShakeState() {
    bs.newGame();
	assertFalse(bs.moveChecker(true) );
  }

  @Test
  public void testRollDiceInDiceShakeStateReturnTrue() {
    bs.newGame();
	assertTrue(bs.rollDice() );
  }

  private void gotoRedMoveState() {
    bs.newGame();
	bs.rollDice();
  }
  
  @Test
  public void testRollDiceInDiceShakeStateToRedMoveState() {
	gotoRedMoveState();
	assertEquals( BackgammonState.RedMoveState, bs.getState() );
  }
  
  @Test
  public void testRollDiceInFirstRedMoveState() {
	gotoRedMoveState();
	assertFalse( bs.rollDice() );
  }
  
  @Test
  public void testRedMoveCheckerInRedMoveStateReturnTrue() {
	gotoRedMoveState();
	assertTrue( bs.moveChecker(true) );
  }

  @Test
  public void testMoveBlackCheckerInRedMoveStateReturnFalse() {
	gotoRedMoveState();
	assertFalse( bs.moveChecker(false) );
  }

  private void moveRedChecker() {
	gotoRedMoveState();
	bs.moveChecker(true);
  }
  
  @Test
  public void testRedMoveCheckerInRedMoveStateStateChange() {
	moveRedChecker();
	assertEquals( BackgammonState.RedMoveState, bs.getState() );
  }
  
  
  
  @Test
  public void testMoveRedCheckerInRedMoveStateTwiceReturnTrue() {
	moveRedChecker();
	assertTrue(bs.moveChecker(true));
  }

  void moveRedCheckerTwice() {
	moveRedChecker();
	bs.moveChecker(true);
  }
  
  @Test
  public void testMoveRedCheckerInRedMoveStateTwiceNoDiceMatchToShakeDiceState() {
	moveRedCheckerTwice();
	assertEquals( BackgammonState.DiceShakeState, bs.getState() );
  }

  private void gotoBlackMoveState() {
	moveRedCheckerTwice();
	bs.rollDice();
  }
  
  @Test
  public void testMoveBlackCheckerInBlackMoveStateReturnTrue() {
	gotoBlackMoveState();
	assertTrue(bs.moveChecker(false));
  }
  
  private void moveBlackChecker() {
	gotoBlackMoveState();
	bs.moveChecker(false);
  }
  
  @Test
  public void testMoveBlackCheckerInBlackMoveStateRemainInState() {
	moveBlackChecker();
	assertEquals( BackgammonState.BlackMoveState, bs.getState() );
  }

  @Test
  public void testMoveBlackCheckerTwiceInBlackMoveStateNoDiceMatchToRollDiceState() {
	moveBlackChecker();
	bs.moveChecker(false);
	assertEquals( BackgammonState.DiceShakeState, bs.getState() );
  }

  @Test
  public void testMoveRedCheckerFourTimesInRedMoveStateDiceMatchReturnTrue() {
	moveBlackChecker();
	bs.moveChecker(false);
	bs.rollDice();
	bs.moveChecker(true);
	bs.moveChecker(true);
	assertTrue( bs.moveChecker(true) );
	assertTrue( bs.moveChecker(true) );
  }
  
  @Test
  public void testMoveRedCheckerFourTimesInRedMoveStateDiceMatchToRollDiceState() {
    bs.newGame();
	bs.rollDice();
	bs.moveChecker(true);
	bs.moveChecker(true);
	bs.rollDice();
	bs.moveChecker(false);
	bs.moveChecker(false);
	bs.rollDice();
	bs.moveChecker(true);
	bs.moveChecker(true);
	bs.moveChecker(true);
	bs.moveChecker(true);
	assertEquals( BackgammonState.DiceShakeState, bs.getState() );
  }
  
  /** This wrapper is only required for running the old JUnit 3.8
   * graphical user interface on new JUnit 4 test cases */
  public static junit.framework.Test suite() {
    return new junit.framework.JUnit4TestAdapter(TestBackgammonState.class);
  } 
}
