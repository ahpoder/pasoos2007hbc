package hottargui.framework;

/**
 * 
 * Create the products for the different game variants
 *  
 */

public interface GameFactory {

	// Create a board for the game   
	Board createBoard();

	// Creates the strategy for player turn
	PlayerTurnStrategy createTurnStrategy();
	
	// Creates the strategy for evaluating move validity
	MoveValidationStrategy createMoveValidationStrategy();
	
	public WinnerStrategy createWinnerStrategy();

	public PutUnitsStrategy createPutUnitsStrategy();

	public AttackStrategy createAttackStrategy();
}
