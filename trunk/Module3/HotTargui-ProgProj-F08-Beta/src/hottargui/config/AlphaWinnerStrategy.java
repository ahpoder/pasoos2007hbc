package hottargui.config;

import hottargui.framework.*;

public class AlphaWinnerStrategy implements WinnerStrategy {
	
	public AlphaWinnerStrategy() {
	}

	public PlayerColor getWinner(){
		return PlayerColor.None;
	}
}
