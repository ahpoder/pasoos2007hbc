package paystation.domain;

/** The factory for creating the objects that configure
    a pay station for the particular town to operate in.

    Author: (c) Henrik Bærbak Christensen 2006
*/

public interface PayStationFactory {
  /** Create an instance of the rate strategy to use. */
  public RateStrategy createRateStrategy();

  /** Create an instance of the receipt.
      @param the number of minutes parking time the receipt is valid for.
  */
  public Receipt createReceipt( int parkingTime );
}
