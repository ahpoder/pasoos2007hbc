/** Implementation of the pay station.
    Author: (c) Henrik Bærbak Christensen 2006 */
public class PayStationImpl implements PayStation {
  private int insertedSoFar;
  private int timeBought;

  public void addPayment( int coinValue ) throws IllegalCoinException {
    switch ( coinValue ) {
    case 5: 
    case 10: 
    case 25: break;  
    default: 
      throw new IllegalCoinException("Invalid coin: "+coinValue+" cent.");
    }
    insertedSoFar += coinValue;
    timeBought = insertedSoFar / 5 * 2;
  }
  public int readDisplay() {
    return timeBought;
  }
  public Receipt buy() {
    Receipt r = new ReceiptImpl(timeBought);
    timeBought = insertedSoFar = 0;
    return r;
  }
  public void cancel() {
  }
  public int timeBought() {
    return 0;
  }
}

