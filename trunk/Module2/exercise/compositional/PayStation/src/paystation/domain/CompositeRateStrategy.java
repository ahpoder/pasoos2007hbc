package paystation.domain;

import java.util.*;

/** A composite rate calculation strategy that
    uses two rate strategies, one for weekdays and
    another for weekends.

    Author: (c) Henrik Bærbak Christensen 2006
*/

public class CompositeRateStrategy implements RateStrategy {
  RateStrategy weekendStrategy, weekdayStrategy;
  public CompositeRateStrategy( RateStrategy weekdayStrategy,
                                RateStrategy weekendStrategy ) {
    this.weekdayStrategy = weekdayStrategy;
    this.weekendStrategy = weekendStrategy;
  }
  public int calculateTime( int amount ) {
    int time;
    if ( isWeekend() ) {
      time = weekendStrategy.calculateTime( amount );
    } else {
      time = weekdayStrategy.calculateTime( amount );
    }
    return time;
  }
  private boolean isWeekend() {
    Date d = new Date();
    Calendar c = new GregorianCalendar();
    c.setTime(d);
    int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
    return ( dayOfWeek == Calendar.SATURDAY 
             || 
             dayOfWeek == Calendar.SUNDAY);
  }
}
