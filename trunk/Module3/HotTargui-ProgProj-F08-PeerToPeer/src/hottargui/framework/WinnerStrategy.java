package hottargui.framework;

/*
*	Responsibility: Find winner
*
*	getWinner() returns the color of the player
*/

public interface WinnerStrategy {
	// TODO - we could also give it a TurnStrategy reference
	PlayerColor getWinner(int round);
}
