package hottargui.strategy;

import hottargui.framework.*;

public class RevenueWinnerStrategy implements WinnerStrategy {
	
	public RevenueWinnerStrategy() {
	}

	public PlayerColor getWinner(int count){
		return PlayerColor.None;
	}
}
