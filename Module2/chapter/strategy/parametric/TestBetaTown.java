import org.junit.*;
import static org.junit.Assert.*;

/** Testcases for the Pay Station system.
    Author: (c) Henrik Bærbak Christensen 2006 */

public class TestBetaTown {
  PayStation ps;
  /** Fixture for pay station testing. */
  @Before
  public void setUp() {
    ps = new PayStationImpl(PayStationImpl.Town.BETATOWN);
  }

  /** Test a single hour parking */
  @Test public void testOneHour() throws IllegalCoinException { 
    // First hour: $1.5
    addHalfDollar();
    addOneDollar();

    assertEquals( 60 /*minutes*/, ps.readDisplay() ); 
  }
   /** Test a 1.5 hour parking */
  @Test public void testOneHourHalf() throws IllegalCoinException { 
    // First hour: $1.5, next half hour = $1
    addTwoDollar();
    addHalfDollar();
      
    assertEquals( 60 + 30 /*minutes*/, ps.readDisplay() ); 
  }
  /** Test two hours parking */
  @Test public void testTwoHour() throws IllegalCoinException { 
    // Two hours: $1.5+2.0
    addTwoDollar();
    addOneDollar();
    addHalfDollar();

    assertEquals( 2 * 60 /*minutes*/ , ps.readDisplay() ); 
  }
  /** Test two and a half hour parking */
  @Test public void testTwoHourHalf() throws IllegalCoinException { 
    // Two and a half hours: $1.5+2.0+ (3.0/2)
    
    // $1.5 = 1st hour
    addOneDollar(); addHalfDollar();
    // $2.0 = 2nd hour
    addTwoDollar();
    // $1.5 = half hour in 2-3 hour interval
    addOneDollar(); addHalfDollar();
    
    assertEquals( 2 * 60 + 30 /*minutes*/ , ps.readDisplay() ); 
  }
  /** Test three hours */
  @Test public void testThreeHour() throws IllegalCoinException { 
    // Three hours: $1.5+2.0+3.0 = $6.5
    addFiveDollar();
    addOneDollar();
    addHalfDollar();
    
    assertEquals( 3 * 60 /*minutes*/ , ps.readDisplay() ); 
  }
  /** Test four hours */
  @Test public void testFourHour() throws IllegalCoinException { 
    // Three hours: $1.5+2.0+ 2 * 3.0 = $9.5
    addFiveDollar();
    addTwoDollar(); 
    addTwoDollar();
    addHalfDollar();

    assertEquals( 4 * 60 /*minutes*/ , ps.readDisplay() ); 
  }

  // Make coin insertions more readable...
  private void addHalfDollar() throws IllegalCoinException {
    ps.addPayment( 25 ); ps.addPayment( 25 );
  }
  private void addOneDollar() throws IllegalCoinException {
    addHalfDollar(); addHalfDollar();
  }
  private void addTwoDollar() throws IllegalCoinException {
    addOneDollar(); addOneDollar();
  }
  private void addFiveDollar() throws IllegalCoinException {
    addTwoDollar(); addTwoDollar(); addOneDollar();
  }
}
