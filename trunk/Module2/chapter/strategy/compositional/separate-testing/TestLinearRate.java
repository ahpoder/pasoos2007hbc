import org.junit.*;
import static org.junit.Assert.*;

/** Testcases for the Alphatown rate policy
    Author: (c) Henrik Bærbak Christensen 2006 */

public class TestLinearRate {
  RateStrategy rs;

  @Before
  public void setUp() {
    rs = new LinearRateStrategy();
  }

  /** Test 35 cents */
  @Test public void test35() {
    assertEquals( 35 / 5 * 2, rs.calculateTime(35) ); 
  }
   /** Test a 100 cents */
  @Test public void test100() throws IllegalCoinException { 
    assertEquals( 100 / 5 * 2, rs.calculateTime(100) ); 
  }
}
