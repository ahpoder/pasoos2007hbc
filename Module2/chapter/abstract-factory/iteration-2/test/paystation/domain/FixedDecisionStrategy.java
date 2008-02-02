package paystation.domain;

import java.util.*;

/** A weekend decicion strategy test stub.
    Author: (c) Henrik Bærbak Christensen 2007
*/

public class FixedDecisionStrategy implements WeekendDecisionStrategy {
  private boolean isWeekend;
  public FixedDecisionStrategy(boolean isWeekend) {
    this.isWeekend = isWeekend;
  }
  public boolean isWeekend() {
    return isWeekend;
  }
}

