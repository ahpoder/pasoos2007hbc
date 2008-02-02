/** A testing tool written from scratch.

    Author: (c) Henrik Bærbak Christensen 2006
*/

public class PlainTest {
  public static void main(String[] args) {
    PayStation ps;
    ps = new PayStationImpl();
    // Test entering 5 cents
    try {
      ps.addPayment( 5 );
    } catch ( IllegalCoinException e ) {
      System.out.println("Pay station did not accept valid coin");
    }
    if ( ps.readDisplay() != 2 ) {
      System.out.println("5 cents does not give 2 min parking time.");
    }
    // ... fill in more tests
  }
}
