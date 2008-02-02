package paystation.domain;

import java.io.PrintStream;

/** This interface models the receipt that the customer receives from
    the pay station.

    Author: (c) Henrik Bærbak Christensen 2006
*/

public interface Receipt {
  /** return the number of minutes this receipt is valid for.
   *  @return number of minutes parking time
   */
  int value();
  
  /** prints the receipt on the given PrintStream.
   */
  void show(PrintStream stream);
}

