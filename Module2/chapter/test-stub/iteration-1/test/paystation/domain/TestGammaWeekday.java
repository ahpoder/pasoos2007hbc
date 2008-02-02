package paystation.domain;

import org.junit.*;
import static org.junit.Assert.*;

/** Testcases for the Gammatown rate strategy on a weekday.
    Author: (c) Henrik Bærbak Christensen 2007 */

public class TestGammaWeekday {
  RateStrategy rs;
  @Before
  public void setUp() {
    rs = new AlternatingRateStrategy( new LinearRateStrategy(),
                                      new ProgressiveRateStrategy(),
                                      new ClockBasedDecisionStrategy() );
  }

  /** Test for 500 cent */
  @Test public void test500cent() { 
    assertEquals( 500 / 5 * 2 /*minutes*/ , rs.calculateTime(500) ); 
  }
}
