package hottargui.framework;

import java.rmi.*;

public interface GameRepository extends Remote {
	// Create a board for the game   
	Board getBoard() throws RemoteException;

	// Creates the strategy for player turn
	PlayerTurnStrategy getTurnStrategy() throws RemoteException;
	
	// Creates the strategy for evaluating move validity
	MoveValidationStrategy getMoveValidationStrategy() throws RemoteException;
	
	WinnerStrategy getWinnerStrategy() throws RemoteException;

	PutUnitsStrategy getPutUnitsStrategy() throws RemoteException;

	AttackStrategy getAttackStrategy() throws RemoteException;

	Die getDieStrategy() throws RemoteException;

	void reinitialize() throws RemoteException;
	
	// TODO - consider if set-methods should be included.
}
