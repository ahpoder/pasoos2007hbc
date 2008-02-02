/** This interface models a receipt defining the amount of 
    parking time bought.

    author: (c) Henrik Bærbak Christensen 2005
*/

public interface Receipt {
  /** return the number of minutes this receipt is valid for.
      @return number of minutes parking time
  */
  int value();
  
  /** "shows" the receipt by writing text to the 
      shell/prompt.
  */
  void show();
}

