package paystation.domain;

import java.util.*;

/** An alternating rate calculation strategy that
    uses two rate strategies, one for weekdays and
    another for weekends.

    @author: (c) Henrik Bærbak Christensen 2006
*/

public class AlternatingRateStrategy implements RateStrategy {
  RateStrategy weekendStrategy, weekdayStrategy, currentState;
  public AlternatingRateStrategy( RateStrategy weekdayStrategy,
                                  RateStrategy weekendStrategy ) {
    this.weekdayStrategy = weekdayStrategy;
    this.weekendStrategy = weekendStrategy;
    this.currentState = null;
  }
  public int calculateTime( int amount ) {
    if ( isWeekend() ) {
      currentState = weekendStrategy; 
    } else {
      currentState = weekdayStrategy;
    }
    return currentState.calculateTime( amount );
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
