package hottargui.framework;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

/** Facade pattern that represents the Targui game as
    central model component to outside clients.

    Responsibilities:
    A) to know and provide access to the game's state.
    B) to allow manipulating state according to the game's rules.
    C) acts as Subject in the observer pattern.

    Author: Henrik Bærbak Christensen.
*/


public interface Game extends Remote {

  /** start the game all over. */
  public void newGame() throws RemoteException;

  // === Game manipulations

  /** make a move on the game. 
   * PreCondition: The game instance is in State.move state.
   * @param from the position that is moved from
   * @param to the position that is moved to
   * @param count the number of units moved
   * @return true iff the move is valid on the given game state, false
   * otherwise. Any explanation of why the move was not valid is
   * provided by a notification to the associated GameListener instances.
   * @throws IllegalActionException a runtime exception that is thrown
   * if the game instance is not in the State.move state.
   */
  public boolean move(Position from, Position to, int count) throws RemoteException;


  /** buy units from the coins in the treasury.
   * PreCondition: The game instance is in State.buy state.
   * @param count the number of units to buy and of course less than 
   * number of coins in the treasury. 0 is a legal value.
   * @param deploy the position where the units should be deployed.
   * @return true iff the buy is legal. Any explanation of why the
   * transaction was illegal is reported to associated GameListener
   * instances.
   * @throws IllegalActionException a runtime exception is thrown if
   * the game is not in the State.buy state.
  */
  public boolean buy(int count, Position deploy) throws RemoteException;

  /** turn a card on the tribecard deck. This is equal to signal
   * that it is the next player that should start his/her turn.
   * @return PlayerColor the color of the player that is now in turn.
   * @throws IllegalActionException a runtime exception is thrown if
   * the game is not in the State.endTurn, State.endRound state or
   * State.newGame state..
   */
  public PlayerColor turnCard() throws RemoteException;

  /** roll the die, this you do when A) determining turn length B)
   * attack or C) defend. The outcome you can inspect by the
   * getDieValue() method; it also gives rise to an 'updateDie'
   * event in the GameListeners.
   * @throws IllegalActionException a runtime exception is thrown
   * if the game is not in a state where rolling the die is possible.
   */
  public void rollDie() throws RemoteException;
 
  // === Observer Pattern Subject role methods.

  /** add a listener on the game events.
   * @param observer the listener (observer) of this game. 
  */
  public void addGameListener( GameListener observer ) throws RemoteException;
  
  // === Game inspections
    
  /** get the state that this game instance is in. The game state
   * dictates valid actions on the game instance, for instance when this
   * method returns State.endTurn then the next action that is valid
   * is to turn a card on the tribecard deck (go on to next player.)
   * @return the state that the game is in.*/
  public State getState() throws RemoteException;

  /** return an interator that can number all the board's tiles.
   * @return the iterator of all board tiles.
   */
  public Iterator<? extends Tile> getBoardIterator() throws RemoteException;

  /** return a specific tile.
   * PreCondition: Position p is valid on the board.
   * @param p the position on the board that must be returned.
   * @return the tile at position p.
   */
  public Tile getTile( Position p ) throws RemoteException;

  /** return the player that is "in turn".
   * @return the player instance of the player that may perform actions on
   * game instance. Null is returned in case the game is in newGame state.
   * For game states endTurn and endRound it should return the player
   * that last executed an action.
  */
  public Player getPlayerInTurn() throws RemoteException;

  /** return the value of the last rolled die.
   * @return the integer value of the die, in the interval 1..6
   */
  public int getDieValue() throws RemoteException;
  
  public PlayerColor getWinner() throws RemoteException;
  
  public Board getBoard() throws RemoteException;
}
