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
  /** This wrapper is only required for running the old JUnit 3.8
   * graphical user interface on new JUnit 4 test cases */
  public static junit.framework.Test suite() {
    return new junit.framework.JUnit4TestAdapter(TestBackgammonState.class);
  } 
}
