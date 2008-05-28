package hottargui.domain;

import java.rmi.*;
import java.rmi.server.*;
import java.net.*;

/** The subject role of the observer pattern: a StatusObservable
    instance can be observed for hottargui state changes.
*/

public interface StatusObservable extends Remote {
  /** Add a status listener to this instance */
  public void addStatusListener(StatusListener listener) throws RemoteException;
}

