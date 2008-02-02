/** Implementation of the pay station with a progressive
 *  rate policy.
 *
 *  Author: (c) Henrik Bærbak Christensen 2006 */

public class PayStationProgressiveRate extends PayStationImpl {
  protected int calculateTime(int paidSoFar) {
    int time = 0;
    if ( paidSoFar >= 150+200 ) { // from 2nd hour onwards
      paidSoFar -= 350;
      time = 120 /*min*/ + paidSoFar / 5;
    } else if ( paidSoFar >= 150 ) { // from 1st to 2nd hour
      paidSoFar -= 150;
      time = 60 /*min*/ + paidSoFar * 3 / 10;
    } else { // up to 1st hour
      time = paidSoFar * 2 / 5;
    }
    return time;
  } 
}

