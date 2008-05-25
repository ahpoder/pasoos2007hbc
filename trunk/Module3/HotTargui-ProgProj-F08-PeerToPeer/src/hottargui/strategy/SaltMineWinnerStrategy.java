package hottargui.strategy;

import java.util.Iterator;

import hottargui.framework.*;

public class SaltMineWinnerStrategy implements WinnerStrategy {
	
	private Game game;
	public SaltMineWinnerStrategy(Game game) {
		this.game = game;
	}

	public PlayerColor getWinner(int round){
		if (round >= 25)
		{
			Iterator<? extends Tile> itt = game.getBoardIterator();
			while (itt.hasNext())
			{
				Tile t = itt.next();
				if (t.getType() == TileType.Saltmine)
				{
					return t.getOwnerColor();
				}
			}
		}
		return PlayerColor.None;
	}
}
