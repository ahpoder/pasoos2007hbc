/** The set of states that a backgammon game can be in 

  Author: Henrik Bærbak Christensen 2006
*/

public enum BackgammonState {
  StartState, // Game not started
  DiceShakeState, // Dice must be thrown
  RedMoveState, // Red must move checker
  BlackMoveState, // Black must move checker
  EndState, // Game has been won
}
  
