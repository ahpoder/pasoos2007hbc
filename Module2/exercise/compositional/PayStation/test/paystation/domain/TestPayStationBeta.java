package paystation.domain;

import junit.framework.*;

public class TestPayStationBeta extends TestCase {

  private PayStation ps;
  public void setUp() {
    ps = new PayStationImpl( new BetaTownFactory() );
  }
  public void testFirstHour() { 
    // First hour: $1.5
    try {
      ps.addPayment( 25 );
      ps.addPayment( 25 );

      ps.addPayment( 25 );
      ps.addPayment( 25 ); 

      ps.addPayment( 25 ); 
      ps.addPayment( 25 ); 
    } catch ( IllegalCoinException e ) {
      fail("Pay station did not accept valid coin");
    }
    assertEquals( 60 /*minutes*/, ps.readDisplay() ); 
  }
  public void testSecondHour1() { 
    // Two hours: $1.5+2.0
    try {
      ps.addPayment( 25 ); ps.addPayment( 25 );
      ps.addPayment( 25 ); ps.addPayment( 25 );
      ps.addPayment( 25 ); ps.addPayment( 25 );

      ps.addPayment( 25 ); ps.addPayment( 25 );
      ps.addPayment( 25 ); ps.addPayment( 25 );
      ps.addPayment( 25 ); ps.addPayment( 25 );
      ps.addPayment( 25 ); ps.addPayment( 25 );

    } catch ( IllegalCoinException e ) {
      fail("Pay station did not accept valid coin");
    }
    assertEquals( 120 /*minutes*/ , ps.readDisplay() ); 
  }
  public void testSecondHour2() { 
    // Two and a half hours: $1.5+2.0+ (2.0/2)
    try {
      // $1.5 = 1st hour
      ps.addPayment( 25 ); ps.addPayment( 25 );
      ps.addPayment( 25 ); ps.addPayment( 25 );
      ps.addPayment( 25 ); ps.addPayment( 25 );

      // $2.0 = 2nd hour
      ps.addPayment( 25 ); ps.addPayment( 25 );
      ps.addPayment( 25 ); ps.addPayment( 25 );
      ps.addPayment( 25 ); ps.addPayment( 25 );
      ps.addPayment( 25 ); ps.addPayment( 25 );

      // $1.5 = half hour in 2-3 hour interval
      ps.addPayment( 25 ); ps.addPayment( 25 );
      ps.addPayment( 25 ); ps.addPayment( 25 );
      ps.addPayment( 25 ); ps.addPayment( 25 );
    } catch ( IllegalCoinException e ) {
      fail("Pay station did not accept valid coin");
    }
    assertEquals( 150 /*minutes*/ , ps.readDisplay() ); 
  }
  public void testThirdHour() { 
    // Three hours: $1.5+2.0+3.0
    try {
      // $1.5
      ps.addPayment( 25 ); ps.addPayment( 25 );
      ps.addPayment( 25 ); ps.addPayment( 25 );
      ps.addPayment( 25 ); ps.addPayment( 25 );
      // $2.0
      ps.addPayment( 25 ); ps.addPayment( 25 );
      ps.addPayment( 25 ); ps.addPayment( 25 );
      ps.addPayment( 25 ); ps.addPayment( 25 );
      ps.addPayment( 25 ); ps.addPayment( 25 );
      // $3.0
      ps.addPayment( 25 ); ps.addPayment( 25 );
      ps.addPayment( 25 ); ps.addPayment( 25 );
      ps.addPayment( 25 ); ps.addPayment( 25 );
      ps.addPayment( 25 ); ps.addPayment( 25 );
      ps.addPayment( 25 ); ps.addPayment( 25 );
      ps.addPayment( 25 ); ps.addPayment( 25 );
    } catch ( IllegalCoinException e ) {
      fail("Pay station did not accept valid coin");
    }
    assertEquals( 180 /*minutes*/ , ps.readDisplay() ); 
  }
  public void testBarCodeReceiptCreation() {
     try {
      ps.addPayment(25);
    } catch ( IllegalCoinException e ) {
      fail("Pay station did not accept valid coin");
    }
    Receipt receipt;
    receipt = ps.pushBuyButton();
    assertTrue( receipt instanceof BarCodeReceipt );
  }
  public static Test suite() {
    return new TestSuite(TestPayStationBeta.class);
  }
}
