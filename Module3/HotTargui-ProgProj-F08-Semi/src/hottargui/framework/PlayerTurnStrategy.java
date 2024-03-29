package hottargui.framework;

/*
*	Responsibility: Player turn ordering
*/  

public interface PlayerTurnStrategy {
	public PlayerColor nextPlayer();
	public int getRoundCount();
	public void addRoundDoneObserver(RoundObserver observer);
	public void removeRoundDoneObserver(RoundObserver observer);
}
