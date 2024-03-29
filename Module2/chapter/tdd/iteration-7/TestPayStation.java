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
  /** Testing for illegal coin entry. */
  @Test(expected=IllegalCoinException.class)
  public void testEnterIllegalCoin() throws IllegalCoinException {
    ps.addPayment(17);
  }
  /** Test buying. */
  @Test public void testBuy() throws IllegalCoinException {
    ps.addPayment(5);
    ps.addPayment(10);
    ps.addPayment(25);
    Receipt receipt;
    receipt = ps.buy();
    assertNotNull( receipt );
    assertEquals((5+10+25) / 5 * 2 , receipt.value() );
  }
  /** Test buying for 100 cents */
  @Test public void testBuy100cent() throws IllegalCoinException {
    ps.addPayment(5);
    ps.addPayment(5);
    ps.addPayment(5);
    ps.addPayment(10);
    ps.addPayment(25);
    ps.addPayment(25);
    ps.addPayment(25);

    Receipt receipt;
    receipt = ps.buy();
    assertEquals((3*5+10+3*25) / 5 * 2 , receipt.value() );
  }
  /** Test receipt */
  @Test public void testReceiptValue() {
    Receipt receipt = new ReceiptImpl(30);
    assertEquals( 30, receipt.value() );
  }
  /** Test that buy clears for a new transaction */
  @Test public void testClearing() throws IllegalCoinException {
    ps.addPayment(25);
    ps.buy(); // don't care about result
    assertEquals( 0 , ps.readDisplay() ); 
  }
  
  /** This wrapper is only required for running the old JUnit 3.8
   * graphical user interface on new JUnit 4 test cases */
  public static junit.framework.Test suite() {
    return new junit.framework.JUnit4TestAdapter(TestPayStation.class);
  } 
}
