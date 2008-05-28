package hottargui.config;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Iterator;

import hottargui.framework.*;

public class TestGame extends UnicastRemoteObject implements Game {
	public TestGame() throws RemoteException {
		super();
		// TODO Auto-generated constructor stub
	}

	public void addGameListener(GameListener observer) throws RemoteException {
		// TODO Auto-generated method stub

	}

	public boolean buyCalled = false;
	public int buyCount;
	public Position buyDeploy;
	public boolean buyResult = true;
	public boolean buy(int count, Position deploy) throws RemoteException {
		if (buyCalled) // We only allow one move at a time
			throw new RemoteException();
		buyCalled = true;
		buyCount = count;
		buyDeploy = deploy;
		return buyResult;
	}

	public Board getBoard() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public Iterator<? extends Tile> getBoardIterator() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean getDieValueCalled = false;
	public int getDieValueReturnValue = 0;
	public int getDieValue() throws RemoteException {
		if (getDieValueCalled) // We only allow one move at a time
			throw new RemoteException();
		getDieValueCalled = true;
		// TODO Auto-generated method stub
		return getDieValueReturnValue;
	}

	public Player getPlayerInTurn() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public State getState() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public Tile getTile(Position p) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public PlayerColor getWinner() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean moveCalled = false;
	public Position moveFrom;
	public Position moveTo;
	public int moveCount;
	public boolean moveResult = true;
	public boolean move(Position from, Position to, int count)
			throws RemoteException {
		if (moveCalled) // We only allow one move at a time
			throw new RemoteException();
		moveCalled = true;
		moveFrom = from;
		moveTo = to;
		moveCount = count;
		return moveResult;
	}

	public boolean newGameCalled = false; 
	public void newGame() throws RemoteException {
		newGameCalled = true;
	}

	public boolean rollDieCalled = false;
	public void rollDie() throws RemoteException {
		if (rollDieCalled) // We only allow one move at a time
			throw new RemoteException();
		rollDieCalled = true;
	}

	public PlayerColor turnCard() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean validateAllFalse() {
		return !(newGameCalled || moveCalled || rollDieCalled || getDieValueCalled || buyCalled);
	}
}
