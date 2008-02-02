/** A one-cent-one-minute rate calculation strategy to
    simplify testing the responsibilities of the pay
    station that are not related to rate calculation.

    Author: (c) Henrik Bærbak Christensen 2006
*/

public class One2OneRateStrategy implements RateStrategy {
  public int calculateTime( int amount ) {
    return amount;
  }
}

