package paystation.domain;

/** Abstract Factory for the Pay Station.

    Author: (c) Henrik Bærbak Christensen 2006
*/

public interface PayStationFactory {
  /** Create instances of Receipts.
      @param parkingTime of minutes parking time the receipt is valid for.
  */
  public Receipt createReceipt( int parkingTime );
    
  /** Create instance of rate strategy
  */
  public RateStrategy createRateStrategy();
}

