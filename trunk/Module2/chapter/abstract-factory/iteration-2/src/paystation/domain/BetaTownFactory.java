package paystation.domain;

/** The factory for BetaTown.
    Author: (c) Henrik Bærbak Christensen 2006
*/

public class BetaTownFactory implements PayStationFactory {
  public RateStrategy createRateStrategy() {
    return new ProgressiveRateStrategy();
  }

  public Receipt createReceipt( int parkingTime ) {
    return new StandardReceipt(parkingTime, true);
  }
}
