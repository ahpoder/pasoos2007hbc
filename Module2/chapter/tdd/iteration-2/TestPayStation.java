import org.junit.*;
import static org.junit.Assert.*;

/** Testcases for the Pay Station system.
    Author: (c) Henrik B�rbak Christensen 2006 */

public class TestPayStation {
  PayStation ps;
  /** Fixture for pay station testing. */
  @Before
  public void setUp() {
    ps = new PayStationImpl();
  }

  /** Testing that a nickel gives two minutes parking time */
  @Test
  public void testEnterNickel() throws IllegalCoinException {
    ps.addPayment( 5 );
    assertEquals( 2, ps.readDisplay() ); 
  }
  /** Testing that a quarter gives ten minutes parking time */
  @Test
  public void testEnterQuarter() throws IllegalCoinException {
    ps.addPayment( 25 );
    assertEquals( 10, ps.readDisplay() ); 
  }

  /** This wrapper is only required for running the old JUnit 3.8
   * graphical user interface on new JUnit 4 test cases */
  public static junit.framework.Test suite() {
    return new junit.framework.JUnit4TestAdapter(TestPayStation.class);
  } 
}
