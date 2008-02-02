package paystation.domain;

import java.util.*;

/** Implementation of the receipt with a bar code.

    The bar code is faked in this implementation.

    author: (c) Henrik Bærbak Christensen 2006
*/

public class BarCodeReceipt implements Receipt {
  private int value;
  public BarCodeReceipt(int value) { this.value = value; }
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
    // A "fake-it" bar code on the receipt
    System.out.println( "||  ||| | || | | || | ||| ||||| | ||| ||  || | ||");
    System.out.println( "-------------------------------------------------");
  }
}

