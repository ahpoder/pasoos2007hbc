package paystation.domain;

import javax.swing.*;

/** A WeekdayDeterminationStrategy that is under testing control.

    author: (c) Henrik Bærbak Christensen 2006
*/

public class AskUserWeekdayDeterminationStrategy 
  implements WeekdayDeterminationStrategy {

  public boolean isWeekend() {
    int value = JOptionPane.showConfirmDialog(null, 
                                              "Is today Weekend?", 
                                              "choose one", 
                                              JOptionPane.YES_NO_OPTION);
    
    return value == JOptionPane.YES_OPTION; 
  }

}


