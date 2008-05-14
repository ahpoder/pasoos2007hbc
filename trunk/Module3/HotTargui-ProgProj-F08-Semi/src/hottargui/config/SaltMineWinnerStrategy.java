package hottargui.config;

import hottargui.framework.*;

public class SaltMineWinnerStrategy implements WinnerStrategy {
	
	public SaltMineWinnerStrategy() {
	}

	public PlayerColor getWinner(){
		return PlayerColor.None;
	}
}
