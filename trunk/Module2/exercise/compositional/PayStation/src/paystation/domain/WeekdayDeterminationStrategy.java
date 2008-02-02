package paystation.domain;

import java.util.*;

/** The strategy that determines whether it is weekend or not.

    author: (c) Henrik Bærbak Christensen 2006
*/

public interface WeekdayDeterminationStrategy {
  /** return true if it is presently a weekend day */
  boolean isWeekend();
}
