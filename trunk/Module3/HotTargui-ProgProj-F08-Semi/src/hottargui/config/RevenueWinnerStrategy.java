package hottargui.config;

import hottargui.framework.*;

public class RevenueWinnerStrategy implements WinnerStrategy {
	
	public RevenueWinnerStrategy() {
	}

	public PlayerColor getWinner(){
		return PlayerColor.None;
	}
}
