/** Implementation of the pay station.

    Author: (c) Henrik Bærbak Christensen 2006
*/

public class PayStationImpl implements PayStation {

  public void addPayment( int coinValue ) throws IllegalCoinException {
  }
  
  public int readDisplay() {
    return 0;
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

