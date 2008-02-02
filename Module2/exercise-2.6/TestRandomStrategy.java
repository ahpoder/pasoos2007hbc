import org.junit.*;
import static org.junit.Assert.*;

public class TestRandomStrategy {
  private RandomStrategy ps;

  /** fixture for testing the Backgammon statemachine */
  @Before
  public void setUp() {
    ps = new PredefinedRandomStrategy();
  }
  
  @Test
  public void testRandomStrategyReturnOneMinusOne() {
    assertEquals( 0, ps.nextInt(6) );
  }
  
  /** This wrapper is only required for running the old JUnit 3.8
   * graphical user interface on new JUnit 4 test cases */
  public static junit.framework.Test suite() {
    return new junit.framework.JUnit4TestAdapter(TestDieRollStrategy.class);
  } 
}
