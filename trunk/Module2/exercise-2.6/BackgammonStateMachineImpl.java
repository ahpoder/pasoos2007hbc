/** A statemachine for handling backgammon.

   Author: Henrik Bærbak Christensen 2006
*/

public class BackgammonStateMachineImpl implements BackgammonStateMachine {
  public BackgammonStateMachineImpl(DieRollStrategy drs) { state=BackgammonState.StartState; dieRoller = drs; }
  public boolean newGame() 
  { 
	state = BackgammonState.DiceShakeState; 
	diceRollCount = 0; 
	lastMoveWasRed = false; 
	return true; 
  }
  public boolean rollDice() 
  { 
	boolean isInDiceShakeState = (getState() == BackgammonState.DiceShakeState);
	if (isInDiceShakeState)
	{
		state = (lastMoveWasRed ? BackgammonState.BlackMoveState : BackgammonState.RedMoveState);
		
		identicalDice = dieRoller.dieRoll();
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
  
  private DieRollStrategy dieRoller;
  private boolean lastMoveWasRed;
  private boolean identicalDice;
  private BackgammonState state;
}
