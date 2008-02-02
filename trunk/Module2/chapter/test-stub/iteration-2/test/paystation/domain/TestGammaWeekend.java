package paystation.domain;

import org.junit.*;
import static org.junit.Assert.*;

/** Testcases for the Gammatown rate strategy on a weekend.
    Author: (c) Henrik Bærbak Christensen 2007 */

public class TestGammaWeekend {
  /** Test for 500 cent */
  @Test public void test500centWeekend() { 
    RateStrategy rs;
    rs = new AlternatingRateStrategy( new LinearRateStrategy(),
                                      new ProgressiveRateStrategy(),
                                      new FixedDecisionStrategy(true) );
    assertEquals( 2 * 60 + 30 /*minutes*/ , rs.calculateTime(500) ); 
  }
}
