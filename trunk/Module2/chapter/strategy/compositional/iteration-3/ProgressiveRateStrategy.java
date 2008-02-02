/** A progressive rate calculation strategy.
    Author: (c) Henrik Bærbak Christensen 2006 */
public class ProgressiveRateStrategy implements RateStrategy {
  public int calculateTime( int amount ) {
    int time = 0;
    if ( amount >= 150 ) { // from 1st to 2nd hour
      amount -= 150;
      time = 60 /*min*/ + amount * 3 / 10;
    } else { // up to 1st hour
      time = amount * 2 / 5;
    }
    return time;
  }
}

