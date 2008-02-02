package paystation.domain;

import java.io.PrintStream;
import java.util.*;

/** Implementation of Receipt.

    Author: (c) Henrik Bærbak Christensen 2006
*/

public class ReceiptImpl implements Receipt {
  private int value;
  public ReceiptImpl(int value) { this.value = value; }
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
    stream.println("-------------------------------------------------");
  }
}
