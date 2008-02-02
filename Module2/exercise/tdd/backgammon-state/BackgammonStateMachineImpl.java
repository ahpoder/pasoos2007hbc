/** A statemachine for handling backgammon.

   Author: Henrik Bærbak Christensen 2006
*/

public class BackgammonStateMachineImpl implements BackgammonStateMachine {
  public boolean newGame() { return true; }
  public boolean rollDice() { return true; }
  public boolean moveChecker( boolean isRedPlayer ) {
    return true;
  }
  public BackgammonState getState() {
    return BackgammonState.StartState;
  }
}
