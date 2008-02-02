/** Implementation of the pay station.
    Author: (c) Henrik Bærbak Christensen 2006 */

public class PayStationImpl implements PayStation {
  private int insertedSoFar;
  private int timeBought;

  public enum Town { ALPHATOWN, BETATOWN }
  private Town town;

  public PayStationImpl( Town town ) {
    this.town = town;
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
    if ( town == Town.ALPHATOWN ) {
      timeBought = insertedSoFar / 5 * 2;
    } else if ( town == Town.BETATOWN ) {
      int amount = insertedSoFar;
      timeBought = 0;
      if ( amount >= 150+200 ) { // from 2nd hour onwards
        amount -= 350;
        timeBought = 120 /*min*/ + amount / 5;
      } else if ( amount >= 150 ) { // from 1st to 2nd hour
        amount -= 150;
        timeBought = 60 /*min*/ + amount * 3 / 10;
      } else { // up to 1st hour
        timeBought = amount * 2 / 5;
      }
    }
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

