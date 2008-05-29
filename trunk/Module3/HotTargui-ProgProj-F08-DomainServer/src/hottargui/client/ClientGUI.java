package hottargui.client;

import hottargui.domain.*;
import hottargui.framework.*;
/*
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
*/
import java.rmi.*;
import java.rmi.server.*;
import java.net.*;

import java.util.Iterator;

//public class ClientGUI extends JFrame implements Game {
public class ClientGUI implements Game {
	private StatusListener myListener;
	private State gameState;
	
	/** The domain game that the gui interacts with */
	Game game;

	public ClientGUI() {
		super();
		try
		{
			myListener = new ClientUpdater(gameState);
		}
		catch (RemoteException re)
		{
			System.out.println(re);
		}
	}

	public StatusListener getStatusListener() {
		return myListener;
	}

	public Game getGame() {
		return game;
	}

	public Board getBoard() throws RemoteException
	{
		// TODO Auto-generated method stub
		return null;
	}

	public PlayerColor getWinner() throws RemoteException
	{
		// TODO Auto-generated method stub
		return null;
	}

	public int getDieValue() throws RemoteException
	{
		// TODO Auto-generated method stub
		return 0;
	}

	public Player getPlayerInTurn() throws RemoteException
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Tile getTile(Position p) throws RemoteException
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	public Iterator<? extends Tile> getBoardIterator() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public State getState() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public void addGameListener(GameListener observer) throws RemoteException {
		// TODO Auto-generated method stub

	}

	public void rollDie() throws RemoteException {
		// TODO Auto-generated method stub
	}

	public PlayerColor turnCard() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean buy(int count, Position deploy) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean move(Position from, Position to, int count) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	public void newGame() throws RemoteException {
		// TODO Auto-generated method stub
	}
}
