package paystation.domain;

/** A decorator of a PayStation that logs coin entries.

    author: (c) Henrik Bærbak Christensen 2006
*/

public class LoggingPayStation implements PayStation {
  private PayStation paystation;
  public LoggingPayStation( PayStation ps ) {
    paystation = ps;
  }
  public void addPayment( int coinValue ) throws IllegalCoinException {
    System.out.println( ""+coinValue+" cents. Time (now)" );
    paystation.addPayment( coinValue );
  }
  public int readDisplay() { return paystation.readDisplay(); }
  public Receipt pushBuyButton() { return paystation.pushBuyButton(); }
  public void pushCancelButton() { paystation.pushCancelButton(); }
  public int amountEarned() { return paystation.amountEarned(); }
  public void empty() { paystation.empty(); }
}

