package paystation.domain;

import java.io.*;

import org.junit.*;
import static org.junit.Assert.*;

/** Testcases for the Pay Station configurations.
    Author: (c) Henrik Bærbak Christensen 2006 */

public class TestConfiguration {
  PayStation ps;

  /** Test BetaTown receipts with bar codes */
  @Test public void testBetaTownReceipt() throws IllegalCoinException {
    ps = new PayStationImpl( new BetaTownFactory() );
    addOneDollar(ps);
    Receipt receipt = ps.buy();

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    receipt.show(ps);
    String output = baos.toString();
    String[] lines = output.split("\n");

    assertEquals( 6, lines.length );
  }

  private void addOneDollar(PayStation ps) throws IllegalCoinException {
    ps.addPayment(25); ps.addPayment(25);
    ps.addPayment(25); ps.addPayment(25);
  }
}
