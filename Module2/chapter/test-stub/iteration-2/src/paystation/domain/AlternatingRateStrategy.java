package paystation.domain;

/** An alternating rate calculation strategy that
    uses two rate strategies, one for weekdays and
    another for weekends.

    @author: (c) Henrik Bærbak Christensen 2006
*/

public class AlternatingRateStrategy implements RateStrategy {
  RateStrategy weekendStrategy, weekdayStrategy, currentState;
  WeekendDecisionStrategy decisionStrategy;
  public AlternatingRateStrategy( RateStrategy weekdayStrategy,
                                  RateStrategy weekendStrategy,
                                  WeekendDecisionStrategy decisionStrategy ) {
    this.weekdayStrategy = weekdayStrategy;
    this.weekendStrategy = weekendStrategy;
    this.decisionStrategy = decisionStrategy;
    this.currentState = null;
  }
  public int calculateTime( int amount ) {
    if ( decisionStrategy.isWeekend() ) {
      currentState = weekendStrategy; 
    } else {
      currentState = weekdayStrategy;
    }
    return currentState.calculateTime( amount );
  }
}
