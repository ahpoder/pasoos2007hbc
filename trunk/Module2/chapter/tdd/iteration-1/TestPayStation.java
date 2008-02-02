import org.junit.*;
import static org.junit.Assert.*;

/** Testcases for the Pay Station system.

    Author: (c) Henrik Bærbak Christensen 2006
*/

public class TestPayStation {

  /** Testing that a nickel gives two minutes parking time */
  @Test
  public void testEnterNickel() throws IllegalCoinException {
    PayStation ps = new PayStationImpl();
    ps.addPayment( 5 );
    assertEquals( 2, ps.readDisplay() ); 
  }

  /** This wrapper is only required for running the old JUnit 3.8
   * graphical user interface on new JUnit 4 test cases */
  public static junit.framework.Test suite() {
    return new junit.framework.JUnit4TestAdapter(TestPayStation.class);
  } 
}
