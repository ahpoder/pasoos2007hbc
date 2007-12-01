package paystation.domain;

import java.util.*;
import java.rmi.*;
import java.rmi.server.*;
import java.net.*;

/** Implementation of the pay station covering 25 parking lots.
    Author: (c) Henrik Bærbak Christensen 2006 */
public class PayStationImpl extends UnicastRemoteObject implements PayStation {
  // AHP Is this required and if so, why?
  static final long serialVersionUID = -1L;

  private int insertedSoFar;
  private int timeBought;
  private int earned, vacant;
  private List<StatusListener> listeners;

  public PayStationImpl() throws RemoteException {
	super();
    insertedSoFar = timeBought = 0;
    listeners = new ArrayList<StatusListener>();
    vacant = 25;
    earned = 0;
	}

  public void addPayment( int coinValue ) throws IllegalCoinException {
    switch ( coinValue ) {
    case 5: 
    case 10: 
    case 25: break;  
    default: 
      throw new IllegalCoinException("Invalid coin: "+coinValue+" cent.");
    }
    insertedSoFar += coinValue;
    timeBought = insertedSoFar / 5 * 2;
  }
  public int readDisplay() {
    return timeBought;
  }
  public Receipt buy() {
    Receipt r = new ReceiptImpl(timeBought);
    earned += insertedSoFar;
    vacant --; // NOTE: Fake-it implementation!!!
    timeBought = insertedSoFar = 0;
    _notify();
    return r;
  }
  public void cancel() {
    timeBought = insertedSoFar = 0;
  }
  public int timeBought() {
    return timeBought;
  }

  public void addStatusListener(StatusListener listener) throws RemoteException {
    listeners.add(listener);
  }

  private void _notify() {
    StatusEvent e = new StatusEvent(vacant, earned);
    for( StatusListener l : listeners ) {
	    try
		{
	      l.update(e);
		}
		catch (RemoteException re)
		{
		}
    }
  }
      
}

