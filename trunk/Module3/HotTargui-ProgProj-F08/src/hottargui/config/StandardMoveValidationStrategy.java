package hottargui.config;

import hottargui.framework.*;

public class StandardMoveValidationStrategy implements MoveValidationStrategy {

	private Game game;
	public StandardMoveValidationStrategy(Game game)
	{
		this.game = game;
	}
	
	@Override
	public MoveAttemptResult validateMove(Position from, Position to,
			PlayerColor player) {

	if (game.getState() != State.move)
	{
		return MoveAttemptResult.INVALID_MOVE;
	}
	
	if (game.getPlayerInTurn().getColor() != player)
	{
		return MoveAttemptResult.INVALID_MOVE;
	}
	
	Tile fromTile = game.getTile(from);
	Tile toTile = game.getTile(to);
	if (to.equals(from) ||
		toTile.getType() == TileType.Saltlake || 
		player != fromTile.getOwnerColor() ||
		fromTile.getUnitCount() <= 0 ||
		java.lang.Math.abs(fromTile.getPosition().getColumn() - toTile.getPosition().getColumn()) > 1 ||
		java.lang.Math.abs(fromTile.getPosition().getRow() - toTile.getPosition().getRow()) > 1)
	{
		return MoveAttemptResult.INVALID_MOVE;
	}
	else if (toTile.getOwnerColor() != PlayerColor.None && 
	    toTile.getOwnerColor() != fromTile.getOwnerColor() && 
		toTile.getUnitCount() != 0) {
		return MoveAttemptResult.ATTACK_NEEDED;
	}
	return MoveAttemptResult.MOVE_VALID;
  }
}
