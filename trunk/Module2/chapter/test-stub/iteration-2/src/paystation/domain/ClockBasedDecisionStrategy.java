package paystation.domain;

import java.util.*;

/** A weekend decicion strategy based on the operating system clock.
    Author: (c) Henrik Bærbak Christensen 2007
*/

public class ClockBasedDecisionStrategy implements WeekendDecisionStrategy {
  public boolean isWeekend() {
    Date d = new Date();
    Calendar c = new GregorianCalendar();
    c.setTime(d);
    int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
    return ( dayOfWeek == Calendar.SATURDAY 
             || 
             dayOfWeek == Calendar.SUNDAY);
  }
}

