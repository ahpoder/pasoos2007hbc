/** A statemachine for handling backgammon.

   Author: Henrik Bærbak Christensen 2006
*/

public class BackgammonStateMachineImpl implements BackgammonStateMachine {
  public BackgammonStateMachineImpl() { state=BackgammonState.StartState; }
  public boolean newGame() 
  { 
	state = BackgammonState.DiceShakeState; 
	diceRollCount = 0; 
	lastMoveWasRed = false; 
	turnCount = 0;
	return true; 
  }
  public boolean rollDice() 
  { 
	boolean isInDiceShakeState = (getState() == BackgammonState.DiceShakeState);
	if (isInDiceShakeState)
	{
		state = (lastMoveWasRed ? BackgammonState.BlackMoveState : BackgammonState.RedMoveState);
		
		// Dice rool is hard coded to [1,2], [3,2], [4,4], [3,2]
		if (++turnCount == 3)
		{
			identicalDice = true;
		}
		else
		{
			identicalDice = false;
		}
	}
	return isInDiceShakeState;
  }

  int diceRollCount;
  public boolean moveChecker( boolean isRedPlayer ) {
	if (!((isRedPlayer && (state == BackgammonState.RedMoveState)) || 
	    (!isRedPlayer && (state == BackgammonState.BlackMoveState))))
	{
		return false;
	}
	if (++diceRollCount >= (identicalDice ? 4 : 2))
	{
		lastMoveWasRed = (state == BackgammonState.RedMoveState);
		state = BackgammonState.DiceShakeState;
		diceRollCount = 0;
	}
	return true;
  }
  public BackgammonState getState() {
    return state;
  }
  
  private boolean lastMoveWasRed;
  private boolean identicalDice;
  private int turnCount;
  private BackgammonState state;
}
