package paystation.domain;

/** Factory for Betatown.

    Author: (c) Henrik B�rbak Christensen 2006
*/

public class BetaTownFactory implements PayStationFactory {
  public Receipt createReceipt( int parkingTime ) { 
    return new BarCodeReceipt(parkingTime); 
  }
  public RateStrategy createRateStrategy() { 
    return new ProgressiveRateStrategy(); 
  }
}

