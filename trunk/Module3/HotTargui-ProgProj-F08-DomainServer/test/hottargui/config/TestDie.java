package hottargui.config;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import hottargui.framework.Die;

public class TestDie extends UnicastRemoteObject implements Die {

	public TestDie() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getValueReturnValue = 0;
	public boolean getValueCalled = false;
	public int getValue() throws RemoteException {
		
		// TODO Auto-generated method stub
		return getValueReturnValue;
	}

	public void rollDie() throws RemoteException {
		// TODO Auto-generated method stub

	}

	public int setValueVal = 0;
	public boolean setValueCalled = false;
	public void setValue(int val) throws RemoteException {
		if (setValueCalled) // We only allow one move at a time
			throw new RemoteException();
		setValueCalled = true;
		setValueVal = val;
	}

}
