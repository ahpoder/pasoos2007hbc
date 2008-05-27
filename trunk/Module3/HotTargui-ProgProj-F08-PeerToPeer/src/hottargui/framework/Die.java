package hottargui.framework;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Die extends Remote {
    public void rollDie() throws RemoteException;

	public void setValue(int val) throws RemoteException;

    public int getValue() throws RemoteException;
}