package paystation.domain;

/** Implementation of a Parking Pay Station.

    author: (c) Henrik Bærbak Christensen 2006
*/

public class PayStationImpl implements PayStation {
  private int insertedSoFar;
  private int timeBought;
  
  /** the strategy for rate calculations */
  private RateStrategy rateStrategy;
  /** the factory that defines strategies */
  private PayStationFactory factory;

  /** Construct a pay station.
      @param factory the factory to produce 
      strategies and receipts
  */
  public PayStationImpl( PayStationFactory factory ) {
    this.factory = factory;
    this.rateStrategy = factory.createRateStrategy();
  }
  public void addPayment( int coinValue ) throws IllegalCoinException {
    switch ( coinValue ) {
    case 5: 
    case 10: 
    case 25: break;
    default: 
      throw new IllegalCoinException("Invalid coin: "+coinValue+" cent.");
    }
    insertedSoFar += coinValue;
    timeBought = rateStrategy.calculateTime(insertedSoFar);
  } 
  public int readDisplay() {
    return timeBought;
  }
  public Receipt pushBuyButton() {
    Receipt r = factory.createReceipt(timeBought);
    timeBought = insertedSoFar = 0;
    return r;
  }
  public void pushCancelButton() {
    timeBought = insertedSoFar = 0;
  }
  public int amountEarned() {
    return 0;
  }
  public void empty() {
  }
}

