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
	
	// Create the rules for where the units can be put after they are bought   	
	PutUnitsStrategy createPutUnitsStrategy();

	// Create the rules for how attacks are carried out.   
	AttackStrategy createAttackStrategy();

	// Create the rules for how to find the winner of the game.    
	WinnerStrategy createWinnerStrategy();
}
