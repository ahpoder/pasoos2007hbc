package hottargui.framework;

/** IllegalActionException is thrown when a method call is attempted on
    the game instance (like move or turnCard) when the game instance
    is not in a state where this action is expected (like moving when
    you are supposed to turn a card on the tribecard deck).

    You should purely throw this exception as a way to signal defects
    in the programming logic.
*/

public class IllegalActionException extends RuntimeException {
  public IllegalActionException(State s) {
    super( "Game did not expect this action as it is in state: "+s );
  }
}
