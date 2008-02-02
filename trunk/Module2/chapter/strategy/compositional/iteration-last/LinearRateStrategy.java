/** A linear rate calculation strategy.

    Author: (c) Henrik Bærbak Christensen 2006
*/

public class LinearRateStrategy implements RateStrategy {
  public int calculateTime( int amount ) {
    return amount * 2 / 5;
  }
}

