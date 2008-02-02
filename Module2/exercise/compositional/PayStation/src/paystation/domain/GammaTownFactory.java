package paystation.domain;

/** Factory for Gammatown.

    Author: (c) Henrik Bærbak Christensen 2006
*/

public class GammaTownFactory implements PayStationFactory {
  public Receipt createReceipt( int parkingTime ) { 
    return new ReceiptImpl(parkingTime); 
  }
  public RateStrategy createRateStrategy() { 
    RateStrategy rs = 
      new CompositeRateStrategy( new LinearRateStrategy(),
                                 new ProgressiveRateStrategy() );
    return rs; 
  }
}

