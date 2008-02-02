import org.junit.*;
import static org.junit.Assert.*;

/** Testcases for the BetaTown pay station with progressive rate policy.
    Author: (c) Henrik Bærbak Christensen 2006 */

public class TestProgressiveRate {
  PayStation ps;
  /** Fixture for pay station testing. */
  @Before
  public void setUp() {
    ps = new PayStationImpl(new LinearRateStrategy());
  }

  /** Test a single hour parking */
  @Test public void testOneHour() throws IllegalCoinException { 
    // First hour: $1.5
    addOneDollar();
    addHalfDollar();

    assertEquals( 60 /*minutes*/, ps.readDisplay() ); 
  }

  private void addHalfDollar() throws IllegalCoinException  {
    ps.addPayment( 25 ); ps.addPayment( 25 ); 
  }
  private void addOneDollar() throws IllegalCoinException {
    addHalfDollar(); addHalfDollar();
  }
}
