package paystation.domain;

/** A linear rate calculation strategy.

    author: (c) Henrik B�rbak Christensen 2006
*/

public class LinearRateStrategy implements RateStrategy {
  public int calculateTime( int amount ) {
    return amount * 2 / 5;
  }
}

