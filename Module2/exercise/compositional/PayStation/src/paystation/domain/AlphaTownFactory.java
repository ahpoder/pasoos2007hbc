package paystation.domain;

/** Factory for Alphatown.

    Author: (c) Henrik Bærbak Christensen 2006.
*/

public class AlphaTownFactory implements PayStationFactory {
  public Receipt createReceipt( int parkingTime ) { 
    return new ReceiptImpl(parkingTime); 
  }
  public RateStrategy createRateStrategy() { 
    return new LinearRateStrategy(); 
  }
}

