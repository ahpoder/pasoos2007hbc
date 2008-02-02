/** This interface models the receipt that the customer receives from
    the pay station.

    Author: (c) Henrik Bærbak Christensen 2006
*/

public interface Receipt {
  /** return the number of minutes this receipt is valid for.
   *  @return number of minutes parking time
   */
  int value();
  
  /** "shows" the receipt by writing text to the 
   *  shell/prompt.
   */
  void show();
}

