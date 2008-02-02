/** A statemachine for handling backgammon.

    Responsibility: To define the sequence of states a game goes
    through during play.

    Author: Henrik Bærbak Christensen 2006
*/

public interface BackgammonStateMachine {
  /** start a new game */
  boolean newGame();
  
  /** roll the dice */
  boolean rollDice();

  /** move a checker */
  boolean moveChecker( boolean isRedPlayer );

  /** return the state the machine is in */
  BackgammonState getState();
}
