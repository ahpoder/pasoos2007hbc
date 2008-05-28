package hottargui.framework;


public interface MoveValidationStrategy {
  MoveAttemptResult validateMove(Position from, Position to, PlayerColor player);
}
