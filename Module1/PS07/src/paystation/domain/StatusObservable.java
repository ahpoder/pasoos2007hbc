package paystation.domain;

import java.rmi.*;
import java.rmi.server.*;
import java.net.*;

/** The subject role of the observer pattern: a StatusObservable
    instance can be observed for pay station state changes.

    Author: (c) Henrik Bærbak Christensen 2007
*/

public interface StatusObservable extends Remote {
  /** Add a status listener to this instance */
  public void addStatusListener(StatusListener listener) throws RemoteException;
}

