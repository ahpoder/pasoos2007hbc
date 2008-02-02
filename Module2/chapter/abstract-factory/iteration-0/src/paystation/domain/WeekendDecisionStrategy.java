package paystation.domain;

/** A strategy for deciding whether it is weekend or not.
    Author: (c) Henrik Bærbak Christensen 2007
*/

public interface WeekendDecisionStrategy {
  public boolean isWeekend();
}

