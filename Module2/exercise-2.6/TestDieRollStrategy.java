import org.junit.*;
import static org.junit.Assert.*;

public class TestDieRollStrategy {
  private DieRollStrategy ps;

  /** fixture for testing the Backgammon statemachine */
  @Before
  public void setUp() {
    ps = new PredefinedDieRollStrategy();
  }
  
  @Test
  public void testDieRollStrategyReturnFalse() {
    assertEquals( false, ps.dieRoll() );
  }
  
  /** This wrapper is only required for running the old JUnit 3.8
   * graphical user interface on new JUnit 4 test cases */
  public static junit.framework.Test suite() {
    return new junit.framework.JUnit4TestAdapter(TestDieRollStrategy.class);
  } 
}
