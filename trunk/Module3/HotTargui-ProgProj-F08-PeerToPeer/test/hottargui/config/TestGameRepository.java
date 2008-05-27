package hottargui.config;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import hottargui.framework.AttackStrategy;
import hottargui.framework.Board;
import hottargui.framework.Die;
import hottargui.framework.GameRepository;
import hottargui.framework.MoveValidationStrategy;
import hottargui.framework.PlayerTurnStrategy;
import hottargui.framework.PutUnitsStrategy;
import hottargui.framework.WinnerStrategy;

public class TestGameRepository extends UnicastRemoteObject implements GameRepository {

	public TestGameRepository() throws RemoteException
	{
		super();
	}
	
	public AttackStrategy getAttackStrategy() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public Board getBoard() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean getDieStrategyCalled = false;
	public Die getDieReturnValue = null;
	public Die getDieStrategy() throws RemoteException {
		if (getDieStrategyCalled) // We only allow one move at a time
			throw new RemoteException();
		getDieStrategyCalled = true;
		return getDieReturnValue;
	}

	public MoveValidationStrategy getMoveValidationStrategy()
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public PutUnitsStrategy getPutUnitsStrategy() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public PlayerTurnStrategy getTurnStrategy() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public WinnerStrategy getWinnerStrategy() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public void reinitialize() throws RemoteException {
		// TODO Auto-generated method stub

	}

}
