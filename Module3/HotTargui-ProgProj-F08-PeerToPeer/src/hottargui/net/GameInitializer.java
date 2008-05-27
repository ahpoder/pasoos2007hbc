package hottargui.net;

import java.net.*;
import java.rmi.*;

import hottargui.framework.*;

public class GameInitializer {
	public Game initialize(String color, Game localGame, GameRepository localRepository) throws MalformedURLException, RemoteException
	{
		PlayerColor pc = stringToColor(color);
		if (pc == PlayerColor.None)
		{
			throw new IllegalArgumentException("Only Red, green, blue and yellow allowed");
		}
		// Expose game class
		Naming.rebind("//localhost/" + color.toUpperCase() + "HotTarguiGame", localGame);
		// Expose GameRepository class
		Naming.rebind("//localhost/" + color.toUpperCase() + "HotTarguiGameRepository", localRepository);

		GameSynchronizer gs;
		// Retrieve the other games
		Pair[] g = new Pair[3];
		int i = 0;
		if (pc != PlayerColor.Red)
		{
			g[i++] = getRemote("RED");
		}
		else if (pc != PlayerColor.Green)
		{
			g[i++] = getRemote("GREEN");
		}
		else if (pc != PlayerColor.Blue)
		{
			g[i++] = getRemote("BLUE");
		}
		else if (pc != PlayerColor.Yellow)
		{
			g[i++] = getRemote("YELLOW");
		}
		// i now has to be 3.
		gs = new GameSynchronizer(localGame);
		for (i = 0; i < 3; ++i)
		{
			gs.addGame(g[i].game, g[i].gameRepository);
		}
		return gs;
	}
	
	class Pair
	{
		public Pair(Game g, GameRepository gr) {
			game = g;
			gameRepository = gr;
		}
		public Game game;
		public GameRepository gameRepository;
	}
	
	private Pair getRemote(String color) throws MalformedURLException {
		do {
			try {
				Game g = (Game)Naming.lookup("//localhost/" + color + "HotTarguiGame");
				GameRepository gr = (GameRepository)Naming.lookup("//localhost/" + color + "HotTarguiGameRepository");
				return new Pair(g, gr);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NotBoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} while (true);
	}

	private PlayerColor stringToColor(String color)
	{
		if (color.toUpperCase().equals("RED"))
		{
			return PlayerColor.Red;
		}
		else if (color.toUpperCase().equals("GREEN"))
		{
			return PlayerColor.Green;
		}
		else if (color.toUpperCase().equals("BLUE"))
		{
			return PlayerColor.Blue;
		}
		else if (color.toUpperCase().equals("YELLOW"))
		{
			return PlayerColor.Yellow;
		}
		return PlayerColor.None;
	}
}
