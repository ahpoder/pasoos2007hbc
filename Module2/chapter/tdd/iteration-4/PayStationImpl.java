/** Implementation of the pay station.
    Author: (c) Henrik Bærbak Christensen 2006 */
public class PayStationImpl implements PayStation {
  private int insertedSoFar;
  public void addPayment( int coinValue ) throws IllegalCoinException {
    switch ( coinValue ) {
    case 5: 
    case 10: 
    case 25: break;  
    default: 
      throw new IllegalCoinException("Invalid coin: "+coinValue+" cent.");
    }
    insertedSoFar += coinValue;
  }
  public int readDisplay() {
    return insertedSoFar / 5 * 2;
  }
  public Receipt buy() {
    return new Receipt() {
        public int value() { return (5+10+25)/5*2; }
        public void show() {}
      };
  }
  public void cancel() {
  }
  public int timeBought() {
    return 0;
  }
}

