/** Implementation of the pay station.
    Author: (c) Henrik Bærbak Christensen 2006 */
public class PayStationImpl implements PayStation {
  private int insertedSoFar;
  private int timeBought;

  /** the strategy for rate calculations */
  private RateStrategy rateStrategy;

  public PayStationImpl(RateStrategy rateStrategy) {
    this.rateStrategy = rateStrategy;
    resetTransaction();
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
  public Receipt buy() {
    Receipt r = new ReceiptImpl(timeBought);
    resetTransaction();
    return r;
  }
  public void cancel() {
    resetTransaction();
  }
  public int timeBought() {
    return timeBought;
  }

  /** resets the present transaction */
  private void resetTransaction() {
    timeBought = insertedSoFar = 0;
  }    
}

