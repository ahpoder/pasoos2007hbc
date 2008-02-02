package paystation.domain;

/** The factory for AlphaTown.
    Author: (c) Henrik Bærbak Christensen 2006
*/

public class AlphaTownFactory implements PayStationFactory {
  public RateStrategy createRateStrategy() {
    return new LinearRateStrategy();
  }

  public Receipt createReceipt( int parkingTime ) {
    return new StandardReceipt(parkingTime);
  }
}
