package hottargui.framework;

import java.rmi.Remote;


public interface MoveValidationStrategy extends Remote {
  MoveAttemptResult validateMove(Position from, Position to, PlayerColor player);
}
