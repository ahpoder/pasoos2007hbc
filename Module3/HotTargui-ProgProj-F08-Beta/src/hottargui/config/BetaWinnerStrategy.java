package hottargui.config;

import hottargui.framework.*;

public class BetaWinnerStrategy implements WinnerStrategy {
	
	public BetaWinnerStrategy() {
	}

	public PlayerColor getWinner(){
		return PlayerColor.None;
	}
}
