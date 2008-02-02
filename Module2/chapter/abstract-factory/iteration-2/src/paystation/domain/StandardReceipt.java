package paystation.domain;

import java.io.PrintStream;
import java.util.*;

/** Implementation of Receipt. 
    Paramerized via a special constructor to enable
    (fake) bar code printing.

    Author: (c) Henrik Bærbak Christensen 2006
*/

public class StandardReceipt implements Receipt {
  private int value;
  private boolean doPrintBarCode;
  /** Create a receipt.
   * @param value the number of minutes this receipt is valid for.
   * @param doPrintBarCode if true then an additional bar code
   * is printed on the receipt 
   */
  public StandardReceipt(int value, boolean doPrintBarCode) 
	{ this.value = value; this.doPrintBarCode = doPrintBarCode; }
  public StandardReceipt(int value) { this(value, false); }
  public int value() { return value;}
  public void show(PrintStream stream) {
    String valuestring = ""+value;
    if ( valuestring.length() == 1 ) { valuestring = "00"+valuestring; }
    if ( valuestring.length() == 2 ) { valuestring = "0"+valuestring; }
    Calendar now = GregorianCalendar.getInstance();
    String hour = ""+now.get(Calendar.HOUR_OF_DAY);
    if ( hour.length() == 1 ) { hour = "0"+hour; }
    String min = ""+now.get(Calendar.MINUTE);
    if ( min.length() == 1 ) { min = "0"+min; }
    String nowstring = hour+":"+min;

    stream.println("-------------------------------------------------");
    stream.println("-------  P A R K I N G   R E C E I P T    -------");
    stream.println("                Value "+valuestring
                   +" minutes.               ");
    stream.println("              Car parked at "+nowstring);
    if ( doPrintBarCode ) {
      stream.println( "||  ||| | || || |||| || ||| | || | | |||| | || |");
    }
    stream.println("-------------------------------------------------");
  }
}
