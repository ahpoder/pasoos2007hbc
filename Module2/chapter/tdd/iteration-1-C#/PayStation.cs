/** The business logic of a Parking Pay Station

  Responsibilities:

 * Accept payment 
 * Calculate parking time based on payment 
 * Know earning, parking time bought 
 * Print receipts 
 * Handle buy and cancel transactions 

  author: (c) Henrik Bærbak Christensen 2005
*/

public interface PayStation
{
  /** Insert coin into the pay station and adjusts state accordingly.
      @param coinValue is an integer value representing the coin in
      cent. That is a quarter is coinValue=25, etc.
  */
  void addPayment( int coinValue );
    
  /** Read the machine's display showing the amount of parking time
      bought so far based on inserted payment.
      @return the number to display on the pay station display
  */
  int readDisplay();
  
  /** Return a parking receipt.
      @return a valid parking receipt object. 
  */
  Receipt buy();

  /** Cancel the present transaction. Resets the machine for a
      new transaction. 
  */
  void cancel();
    
  /** Return the amount of payment entered since last call of
      empty().
  */
  int amountEarned();

  /* Empty the machine. The amount earned is reset */
  void empty();

}

