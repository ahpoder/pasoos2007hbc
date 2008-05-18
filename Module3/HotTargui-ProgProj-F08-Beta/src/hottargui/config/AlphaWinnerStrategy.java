package hottargui.config;

import hottargui.framework.*;
import java.util.*;

public class AlphaWinnerStrategy implements WinnerStrategy {
  private Game game;

  public AlphaWinnerStrategy(Game game) {
  	this.game = game;
	}

	public PlayerColor getWinner(){
			Tile saltMine = game.getTile(new Position(3, 3));
      return saltMine.getOwnerColor();  
	}
}
