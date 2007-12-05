package paystation.domain;

/** The business logic of a Parking Pay Station.

    Responsibilities:

    1) Accept payment;
    2) Calculate parking time based on payment;
    3) Know earning, parking time bought;
    4) Issue receipts;
    5) Handle buy and cancel events.

    The interface also acts as a Facade of the pay station
    domain handling system with regards to the graphical
    user interface / hardware side of the station.

    Author: (c) Henrik Bærbak Christensen 2006
*/

public interface PayStation {
  /** Insert coin into the pay station and adjusts state accordingly.
   *  @param coinValue is an integer value representing the coin in
   *  cent. That is a quarter is coinValue=25, etc.
   */
  public void addPayment( int coinValue ) throws IllegalCoinException;
  
  /** Read the machine's display showing the amount of parking time
   *  bought so far based on inserted payment.
   * @return the number to display on the pay station display
   */
  public int readDisplay();
  
  /** Buy parking time. Terminate the ongoing transaction and
   * return a parking receipt. A non-null object is always returned.
   * @return a valid parking receipt object. 
   */
  public Receipt buy();

  /** Cancel the present transaction. Resets the machine for a
   *  new transaction. 
   */
  public void cancel();
  
  /** Return the time bought so far in the present transaction.
   * @return time bought so far in minutes.
   */
  public int timeBought();

}

