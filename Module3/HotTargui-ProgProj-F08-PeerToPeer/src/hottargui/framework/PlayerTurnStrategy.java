package hottargui.framework;

import java.rmi.Remote;

/*
*	Responsibility: Player turn ordering
*/  

public interface PlayerTurnStrategy extends Remote {
	public PlayerColor nextPlayer();
	public int getRoundCount();
	public void addRoundDoneObserver(RoundObserver observer);
	public void removeRoundDoneObserver(RoundObserver observer);
}
