package paystation.domain;

import java.io.*;

/** An event data object containing the status of a pay station.

    Author: (c) Henrik Bærbak Christensen 2007
*/

public class StatusEvent implements Serializable {
  /** construct a properly initialized status event object */
  public StatusEvent(int vacant, int earned) {
    this.vacant = vacant;
    this.earned = earned;
  }

  /** the number of vacant parking lots that this
      pay station is associated with. */
  public int vacant;
  /** the amount of cents that this pay station has earned 
      since last being emptied. */
  public int earned;
}

