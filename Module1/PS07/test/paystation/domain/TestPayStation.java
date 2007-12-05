package paystation.domain;

import java.rmi.*;
import java.rmi.server.*;
import java.net.*;

import org.junit.*;
import static org.junit.Assert.*;

/** Testcases for the Pay Station system.
    Author: (c) Henrik Bærbak Christensen 2006 */

public class TestPayStation {
  PayStation ps;
  StatusObservable so;
  /** Fixture for pay station testing. */
  @Before
  public void setUp() {
    try
	{
		ps = new PayStationImpl();
		so = (StatusObservable)ps;
	}
	catch (RemoteException re)
	{
	}
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

  /** Test that the observer behaviour works */
  @Test public void testListenersAreNotified() throws IllegalCoinException {
    LocalStatusListener l1, l2;
    l1 = new LocalStatusListener();
    l2 = new LocalStatusListener();

	try
	{
		so.addStatusListener( l1 );
		so.addStatusListener( l2 );
	}
	catch (RemoteException re)
	{
	}

    ps.addPayment(25);
    ps.buy(); // don't care about result

    ps.addPayment(25);
    ps.addPayment(10);
    ps.buy(); // don't care about result

    assertEquals( 25+25+10, l1.earned );
  }

  class LocalStatusListener implements StatusListener {
    public int earned, vacant;
    public void update(StatusEvent e) {
      earned = e.earned; vacant = e.vacant;
    }
  }

  /** This wrapper is only required for running the old JUnit 3.8
   * graphical user interface on new JUnit 4 test cases */
  public static junit.framework.Test suite() {
    return new junit.framework.JUnit4TestAdapter(TestPayStation.class);
  } 
}
