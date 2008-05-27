package hottargui.net;

import java.rmi.RemoteException;
import java.util.*;

import hottargui.framework.*;

public class GameNetworkDecorator implements Game {
	private Game game;
	public GameNetworkDecorator(Game gp)
	{
		this.game = gp;
	}
	
	HashMap<Game,GameRepository> remoteGames = new HashMap<Game,GameRepository>();
	public void addGame(Game gameProxy, GameRepository gameRepositoryProxy)
	{
		remoteGames.put(gameProxy, gameRepositoryProxy);
	}
	
	public void addGameListener(GameListener observer) throws RemoteException {
		game.addGameListener(observer);
	}
	public boolean buy(int count, Position deploy) throws RemoteException {
		boolean res = game.buy(count, deploy);
		if (res)
		{
			Set<Game> coll = remoteGames.keySet();
			Iterator<Game> itt = coll.iterator();
			while (itt.hasNext())
			{
				Game g = itt.next();
				g.buy(count, deploy); // No reason to look at return value
			}
		}
		return res;
	}
	public Board getBoard() throws RemoteException {
		return game.getBoard();
	}
	public Iterator<? extends Tile> getBoardIterator() throws RemoteException {
		return game.getBoardIterator();
	}
	public int getDieValue() throws RemoteException {
		return game.getDieValue();
	}
	public Player getPlayerInTurn() throws RemoteException {
		return game.getPlayerInTurn();
	}
	public State getState() throws RemoteException {
		return game.getState();
	}
	public Tile getTile(Position p) throws RemoteException {
		return game.getTile(p);
	}
	public PlayerColor getWinner() throws RemoteException {
		return game.getWinner();
	}
	public boolean move(Position from, Position to, int count) throws RemoteException {
		boolean res = game.move(from, to, count);
		if (res)
		{
			Set<Game> coll = remoteGames.keySet();
			Iterator<Game> itt = coll.iterator();
			while (itt.hasNext())
			{
				Game g = itt.next();
				g.move(from, to, count); // No reason to look at return value
			}
		}
		return res;
	}
	public void newGame() throws RemoteException {
		game.newGame();
		Set<Game> coll = remoteGames.keySet();
		Iterator<Game> itt = coll.iterator();
		while (itt.hasNext())
		{
			Game g = itt.next();
			g.newGame(); // No reason to look at return value
		}
	}
	public void rollDie() throws RemoteException {
		game.rollDie();
		int value = game.getDieValue();

		Set<Game> coll = remoteGames.keySet();
		Iterator<Game> itt = coll.iterator();
		while (itt.hasNext())
		{
			Game g = itt.next();
			GameRepository gr = remoteGames.get(g);
			Die ds = null;
			try {
				ds = gr.getDieStrategy();
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// First we actually roll the die to ensure propper state change
			g.rollDie(); 
			// The we manually override its value with the value we want
			ds.setValue(value);
		}
	}
	public PlayerColor turnCard() throws RemoteException {
		PlayerColor pc = game.turnCard();
		Set<Game> coll = remoteGames.keySet();
		Iterator<Game> itt = coll.iterator();
		while (itt.hasNext())
		{
			Game g = itt.next();
			// Here we would have to setup the deck to return
			// the correct card.
			
			// This is only included for completeness and 
			// serves no actual purpose.
			g.turnCard(); 
		}
		return pc;
	}
}
