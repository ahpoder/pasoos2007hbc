package hottargui.domain;

import java.rmi.*;
import java.rmi.server.*;
import java.net.*;

/** The observer role of the observer pattern: a
    StatusListener instance are updated whenever
    a pay station changes state.

    The protocol is a 'push' protocol in that
    the listener carries the state of the game
    as part of the updateChanges method call.
*/

public interface StatusListener extends Remote {
  /** invoked whenever a game changes state. */
  public void updateChanges( StatusEvent event ) throws RemoteException;
}

