package paystation.domain;

import org.junit.*;
import static org.junit.Assert.*;

/** Testcases for the Gammatown alternating rate strategy.
    Author: (c) Henrik Bærbak Christensen 2007 */

public class TestAlternatingRateStrategy {

  /** Test for 500 cent during weekends */
  @Test public void test500centWeekend() { 
    RateStrategy rs;
    rs = new AlternatingRateStrategy( new LinearRateStrategy(),
                                      new ProgressiveRateStrategy(),
                                      new FixedDecisionStrategy(true) );
    assertEquals( 2 * 60 + 30 /*minutes*/ , rs.calculateTime(500) ); 
  }

  /** Test for 500 cent during weekdays */
  @Test public void test500centWeekday() { 
    RateStrategy rs;
    rs = new AlternatingRateStrategy( new LinearRateStrategy(),
                                      new ProgressiveRateStrategy(),
                                      new FixedDecisionStrategy(false) );
    assertEquals( 500 / 5 * 2 /*minutes*/ , rs.calculateTime(500) ); 
  }
}
