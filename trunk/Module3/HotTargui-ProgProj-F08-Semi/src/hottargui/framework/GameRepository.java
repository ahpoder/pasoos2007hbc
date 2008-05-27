package hottargui.framework;

public interface GameRepository {
	// Create a board for the game   
	Board getBoard();

	// Creates the strategy for player turn
	PlayerTurnStrategy getTurnStrategy();
	
	// Creates the strategy for evaluating move validity
	MoveValidationStrategy getMoveValidationStrategy();
	
	WinnerStrategy getWinnerStrategy();

	PutUnitsStrategy getPutUnitsStrategy();

	AttackStrategy getAttackStrategy();
	
	Die getDieStrategy();

	void reinitialize();
	
	// TODO - consider if set-methods should be included.
}
