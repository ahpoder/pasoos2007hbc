package paystation.domain;

import java.util.*;

/** Implementation of the receipt interface.

    author: (c) Henrik Bærbak Christensen 2006
*/

public class ReceiptImpl implements Receipt {
  private int value;
  public ReceiptImpl(int value) { this.value = value; }
  public int value() { return value;}
  public void show() {
    System.out.println( "-------------------------------------------------");
    System.out.println( "-------  P A R K I N G   R E C E I P T  ---------");
    System.out.println( "    Value "+ value+" minutes." );
    Calendar now = GregorianCalendar.getInstance();
    now.add(Calendar.MINUTE, value);
    String end = 
      "" + now.get(Calendar.HOUR_OF_DAY) + 
      ":" + now.get(Calendar.MINUTE);
    System.out.println( "    Valid until "+ end );
    System.out.println( "-------------------------------------------------");
    
  }
}

