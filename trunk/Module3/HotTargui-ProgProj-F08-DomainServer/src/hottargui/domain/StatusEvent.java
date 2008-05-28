package hottargui.domain;

import java.io.*;

import hottargui.framework.*;

/** An event data object containing the status of hottargui.
*/

public class StatusEvent implements Serializable {
  /** construct a properly initialized status event object */
  public StatusEvent(State state) {
    this.state = state;
  }
  /** the state of the game */
  public State state;
}

