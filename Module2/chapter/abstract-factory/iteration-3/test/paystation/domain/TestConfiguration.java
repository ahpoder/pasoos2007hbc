package paystation.domain;

import java.io.*;

import org.junit.*;
import static org.junit.Assert.*;

/** Testcases for the Pay Station configurations.
    Author: (c) Henrik Bærbak Christensen 2006 */

public class TestConfiguration {
  PayStation ps;

  // === BETA TOWN ===
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

  /** Test that BetaTown pay stations has proper
      rate strategy. */
  @Test public void testBetaTownRateConfiguration() 
    throws IllegalCoinException {
    ps = new PayStationImpl( new BetaTownFactory() );
    // BetaTown has a progressive rate strategy and
    // Receipts with bar codes.

    // To ensure testing the progressive rate I have
    // to add enough to get past the first hour where
    // the rate calculation will yield the same
    addOneDollar(ps);
    addOneDollar(ps);
    ps.addPayment(25); ps.addPayment(25);

    assertEquals( 60+30 /*minutes*/ , ps.readDisplay() ); 
  }

  // === ALPHA TOWN ===
  /** Test Alphatown aspects */
  @Test public void testAlphaTownConfiguration() 
    throws IllegalCoinException {
    ps = new PayStationImpl( new AlphaTownFactory() );
    // test the rate configuration
    addOneDollar(ps);
    addOneDollar(ps);
    ps.addPayment(25); ps.addPayment(25);
    assertEquals( 250 / 5 * 2 /*minutes*/ , ps.readDisplay() ); 
    
    // test the use of standard receipt with no bar code
    Receipt receipt = ps.buy();

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    receipt.show(ps);
    String output = baos.toString();
    String[] lines = output.split("\n");

    assertEquals( 5, lines.length );
  }

  // === GAMMA TOWN ===
  /** Test Gammatown aspects */
  @Test public void testGammaTownConfiguration() 
    throws IllegalCoinException {
    ps = new PayStationImpl( new GammaTownFactory() );
    // test the rate configuration
    addOneDollar(ps);
    addOneDollar(ps);
    ps.addPayment(25); ps.addPayment(25);
    assertEquals( 250 / 5 * 2 /*minutes*/ , ps.readDisplay() ); 
    
    // test the use of standard receipt with no bar code
    Receipt receipt = ps.buy();

    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    PrintStream ps = new PrintStream(baos);
    receipt.show(ps);
    String output = baos.toString();
    String[] lines = output.split("\n");

    assertEquals( 5, lines.length );
  }

  private void addOneDollar(PayStation ps) throws IllegalCoinException {
    ps.addPayment(25); ps.addPayment(25);
    ps.addPayment(25); ps.addPayment(25);
  }

  
}
