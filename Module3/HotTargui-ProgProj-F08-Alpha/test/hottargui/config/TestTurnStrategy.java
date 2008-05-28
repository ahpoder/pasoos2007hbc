package hottargui.config;

import hottargui.framework.PlayerColor;
import hottargui.framework.PlayerTurnStrategy;
import hottargui.framework.RoundObserver;

public class TestTurnStrategy implements PlayerTurnStrategy {

	private PlayerTurnStrategy baseStrategy = new  SimpleTurnStrategy();
	public void addRoundDoneObserver(RoundObserver observer) {
		baseStrategy.addRoundDoneObserver(observer);
	}

	public int roundReturnValue = 0;
	public int getRoundCount() {
		return roundReturnValue;
	}

	public PlayerColor nextPlayer() {
		return baseStrategy.nextPlayer();
	}

	public void removeRoundDoneObserver(RoundObserver observer) {
		baseStrategy.removeRoundDoneObserver(observer);
	}

}
