package paystation.domain;

import junit.framework.*;

public class TestPayStationAlpha extends TestCase {

  private PayStation ps;
  public void setUp() {
    PayStationFactory factory = new AlphaTownFactory();
    ps = new PayStationImpl( factory );
  }
  public void testEnterNickel() { 
    try {
      ps.addPayment( 5 );
    } catch ( IllegalCoinException e ) {
      fail("Pay station did not accept valid coin");
    }
    assertEquals( 2, ps.readDisplay() ); 
  }
  public void testEnterQuarter() { 
    try {
      ps.addPayment( 25 );
    } catch ( IllegalCoinException e ) {
      fail("Pay station did not accept valid coin");
    }
    assertEquals( 25 / 5 * 2 , ps.readDisplay() ); 
    // 2 minutes per 5 cents
  }
  public void testEnterIllegalCoin() {
    try {
      ps.addPayment(17);
      fail("Pay station accepted a 17cent coin");
    } catch ( IllegalCoinException e ) {}
  }
  public void testBuy() {
    try {
      ps.addPayment(5);
      ps.addPayment(10);
      ps.addPayment(25);
    } catch ( IllegalCoinException e ) {
      fail("Pay station did not accept valid coin");
    }
    Receipt receipt;
    receipt = ps.pushBuyButton();
    assertTrue( receipt != null );
    assertEquals((5+10+25) / 5 * 2 , receipt.value() );
  }
  public void testBuy100cent() {
    try {
      ps.addPayment(5);
      ps.addPayment(5);
      ps.addPayment(5);
      ps.addPayment(10);
      ps.addPayment(25);
      ps.addPayment(25);
      ps.addPayment(25);
    } catch ( IllegalCoinException e ) {
      fail("Pay station did not accept valid coin");
    }
    Receipt receipt;
    receipt = ps.pushBuyButton();
    assertTrue( receipt != null );
    assertEquals( 100 / 5 * 2 , receipt.value() );
  }
  public void testReceiptValue() {
    Receipt receipt = new ReceiptImpl(30);
    assertEquals( 30, receipt.value() );
  }
  public void testClearing() {
    try {
      ps.addPayment(25);
    } catch ( IllegalCoinException e ) {}
    ps.pushBuyButton(); // don't care about result
    assertEquals( 0 , ps.readDisplay() ); 
  }
  public static Test suite() {
    return new TestSuite(TestPayStationAlpha.class);
  }
}
