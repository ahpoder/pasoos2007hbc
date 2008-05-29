package hottargui.client;

import hottargui.domain.*;
import hottargui.framework.*;

import java.rmi.*;
import java.rmi.server.*;
import java.net.*;

public class ClientUpdater extends UnicastRemoteObject implements StatusListener {
	private State gameState;
	public ClientUpdater(State gameState) throws RemoteException
	{
	    super();
		this.gameState = gameState;
	}

	public void updateChanges(StatusEvent e) throws RemoteException {
		//To do
	}
}
