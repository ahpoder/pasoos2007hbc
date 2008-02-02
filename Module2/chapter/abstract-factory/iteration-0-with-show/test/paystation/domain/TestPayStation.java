package paystation.domain;

import java.io.*;

import org.junit.*;
import static org.junit.Assert.*;

/** Testcases for the Pay Station that 
    are not related to rate calculations.
    Author: (c) Henrik Bærbak Christensen 2006 */

public class TestPayStation {
  PayStation ps;
  /** Fixture for pay station testing. */
  @Before
  public void setUp() {
    ps = new PayStationImpl(new One2OneRateStrategy());
  }

  /** Test that all three types of coins are accepted */
  @Test
  public void testAllCoins() throws IllegalCoinException {
    ps.addPayment(5);
    ps.addPayment(10);
    ps.addPayment(25);
    assertEquals( 5+10+25 , ps.readDisplay() ); 
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
    assertEquals( 5+10+25 , receipt.value() );
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
    assertEquals((3*5+10+3*25), receipt.value() );
  }
  /** Test receipt */
  @Test public void testReceiptValue() {
    Receipt receipt = new ReceiptImpl(30);
    assertEquals( 30, receipt.value() );
  }
  /** Test that buy clears for a new transaction */
  @Test public void testClearingAfterBuy() throws IllegalCoinException {
    ps.addPayment(25);
    ps.buy(); // don't care about result
    assertEquals( 0 , ps.readDisplay() ); 
  }
  /** Test that cancel resets the display */
  @Test public void testCancel() throws IllegalCoinException {
    ps.addPayment(25);
    ps.cancel(); 
    assertEquals( 0 , ps.readDisplay() ); 
  }
  /** Test that timebought reflect what is in the display */
  @Test public void testTimeBought() throws IllegalCoinException {
    ps.addPayment(25);
    ps.addPayment(5);
    ps.addPayment(10);
    assertEquals( (25+5+10) , ps.timeBought() ); 
  }
  /** Test that the receipt's show method prints proper information */
  @Test public void testReceiptShow() {
    Receipt receipt = new ReceiptImpl(30);
    // Prepare a PrintStream instance that lets me inspect the 
    // data written to it.
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    // let the 30 minute print itself
    receipt.show(ps);

    // get the string printed to the stream
    String output = baos.toString();
    // split the string into individual lines
    String[] lines = output.split("\n");
    // test to see that the receipt consist of five lines
    assertEquals( 5, lines.length );
    // test parts of the contents
    assertEquals( "---", lines[0].substring(0,3) );
    assertEquals( "---", lines[4].substring(0,3) );
    assertEquals( "P A R K I N G", lines[1].substring(9,22) );
    // test the receipt's value 
    assertEquals( "030", lines[2].substring(22,25) );
    // test that the format of the "parking starts at" time
    // is plausible
    String parkedAtString = lines[3].substring(28,33);
    assertEquals( ':', parkedAtString.charAt(2) );
    // if the substring below is not an integer a
    // NumberFormatException is thrown which will 
    // make JUnit fail this test
    Integer.parseInt( parkedAtString.substring(0,2) );
    Integer.parseInt( parkedAtString.substring(3,5) );
   }
}
