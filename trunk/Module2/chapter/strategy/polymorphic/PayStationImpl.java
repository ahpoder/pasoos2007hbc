/** Implementation of the pay station.
    Author: (c) Henrik Bærbak Christensen 2006 */
public class PayStationImpl implements PayStation {
  private int insertedSoFar;
  private int timeBought;

  public PayStationImpl() {
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
    timeBought = calculateTime(insertedSoFar);
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

  /** calculate the parking time equivalent to the amount of 
      cents paid so far
      @param paidSoFar the amount of cents paid so far
      @return the parking time this amount qualifies for
  */
  protected int calculateTime(int paidSoFar) {
    return paidSoFar * 2 / 5;
  } 
  
}

