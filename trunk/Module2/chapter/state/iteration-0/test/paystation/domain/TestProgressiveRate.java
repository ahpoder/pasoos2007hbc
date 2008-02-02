package paystation.domain;

import org.junit.*;
import static org.junit.Assert.*;

/** Testcases for the BetaTown progressive rate algorithm.
    Author: (c) Henrik Bærbak Christensen 2006 */

public class TestProgressiveRate {
  RateStrategy rs;
  @Before
  public void setUp() {
    rs = new ProgressiveRateStrategy();
  }

  /** Test a single hour parking */
  @Test public void testOneHour() { 
    // First hour: $1.5
    assertEquals( 60 /*minutes*/, rs.calculateTime(150) ); 
  }
  /** Test a 1.5 hour parking */
  @Test public void testOneHourHalf() { 
    // First hour: $1.5, next half hour = $1   
    assertEquals( 60 + 30 /*minutes*/, rs.calculateTime(250) ); 
  }
  /** Test two hours parking */
  @Test public void testTwoHour()  { 
    // Two hours: $1.5+2.0
    assertEquals( 2 * 60 /*minutes*/ , rs.calculateTime(350) ); 
  }
  /** Test two and a half hour parking */
  @Test public void testTwoHourHalf() { 
    // Two and a half hours: $1.5+2.0+ (3.0/2)
    assertEquals( 2 * 60 + 30 /*minutes*/ , rs.calculateTime(500) ); 
  }
  /** Test three hours */
  @Test public void testThreeHour() { 
    // Three hours: $1.5+2.0+3.0 = $6.5
    assertEquals( 3 * 60 /*minutes*/ , rs.calculateTime(650) ); 
  }
  /** Test four hours */
  @Test public void testFourHour() { 
    // Three hours: $1.5+2.0+ 2 * 3.0 = $9.5
    assertEquals( 4 * 60 /*minutes*/ , rs.calculateTime(950) ); 
  }
}
