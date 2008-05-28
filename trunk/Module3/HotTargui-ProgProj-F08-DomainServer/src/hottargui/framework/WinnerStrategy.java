package hottargui.framework;

import java.rmi.Remote;

/*
*	Responsibility: Find winner
*
*	getWinner() returns the color of the player
*/

public interface WinnerStrategy extends Remote {
	// TODO - we could also give it a TurnStrategy reference
	PlayerColor getWinner(int round);
}
