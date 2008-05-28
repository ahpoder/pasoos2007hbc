package hottargui.client;

import hottargui.domain.*;

import java.rmi.*;
import java.rmi.server.*;
import java.net.*;

public class ClientUpdater extends UnicastRemoteObject implements StatusListener {

	public ClientUpdater() throws RemoteException
	{
	    super();
	}

	public void update(StatusEvent e) throws RemoteException {

	}
}
