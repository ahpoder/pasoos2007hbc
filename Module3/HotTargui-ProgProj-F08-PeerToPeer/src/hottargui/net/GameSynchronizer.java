package hottargui.net;

import java.util.*;

import hottargui.framework.*;

public class GameSynchronizer implements Game {
	private Game game;
	public GameSynchronizer(Game gp)
	{
		this.game = gp;
	}
	
	HashMap<Game,GameRepository> remoteGames = new HashMap<Game,GameRepository>();
	public void addGame(Game gameProxy, GameRepository gameRepositoryProxy)
	{
		remoteGames.put(gameProxy, gameRepositoryProxy);
	}
	
	public void addGameListener(GameListener observer) {
		game.addGameListener(observer);
	}
	public boolean buy(int count, Position deploy) {
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
	public Board getBoard() {
		return game.getBoard();
	}
	public Iterator<? extends Tile> getBoardIterator() {
		return game.getBoardIterator();
	}
	public int getDieValue() {
		return game.getDieValue();
	}
	public Player getPlayerInTurn() {
		return game.getPlayerInTurn();
	}
	public State getState() {
		return game.getState();
	}
	public Tile getTile(Position p) {
		return game.getTile(p);
	}
	public PlayerColor getWinner() {
		return game.getWinner();
	}
	public boolean move(Position from, Position to, int count) {
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
	public void newGame() {
		game.newGame();
		Set<Game> coll = remoteGames.keySet();
		Iterator<Game> itt = coll.iterator();
		while (itt.hasNext())
		{
			Game g = itt.next();
			g.newGame(); // No reason to look at return value
		}
	}
	public void rollDie() {
		game.rollDie();
		Set<Game> coll = remoteGames.keySet();
		Iterator<Game> itt = coll.iterator();
		while (itt.hasNext())
		{
			Game g = itt.next();
			GameRepository gr = remoteGames.get(g);
			Die ds = gr.getDieStrategy();
			// First we actually roll the die to ensure propper state change
			g.rollDie(); 
			// The we manually override its value with the value we want
			ds.setValue(game.getDieValue());
		}
	}
	public PlayerColor turnCard() {
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
