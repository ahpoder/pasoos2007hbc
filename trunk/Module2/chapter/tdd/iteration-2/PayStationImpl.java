/** Implementation of the pay station.
    Author: (c) Henrik Bærbak Christensen 2006 */
public class PayStationImpl implements PayStation {
  private int insertedSoFar;
  public void addPayment( int coinValue ) throws IllegalCoinException {
    insertedSoFar += coinValue;
  }
  public int readDisplay() {
    return insertedSoFar / 5 * 2;
  }
  public Receipt buy() {
    return null;
  }
  public void cancel() {
  }
  public int timeBought() {
    return 0;
  }
}

