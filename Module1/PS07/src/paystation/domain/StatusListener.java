package paystation.domain;

import java.rmi.*;
import java.rmi.server.*;
import java.net.*;

/** The observer role of the observer pattern: a
    StatusListener instance are updated whenever
    a pay station changes state.

    The protocol is a 'push' protocol in that
    the listener carries the state of the
    pay station as part of the update method
    call.

    Author: (c) Henrik Bærbak Christensen 2007
*/

public interface StatusListener extends Remote {
  /** invoked whenever a paystation changes state. */
  public void update( StatusEvent event ) throws RemoteException;
}

