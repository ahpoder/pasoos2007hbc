package hottargui.strategy;

import hottargui.framework.*;

public class SimpleAttackStrategy implements AttackStrategy {

	private Game game;
	public SimpleAttackStrategy(Game game) {
		this.game = game;
	}
	
	public void attack(Tile tFrom, Tile tTo, int dieValue, int noOfAttackUnits){
    	// Perform attack
		Board board = game.getBoard();
		if (isAttackValid(tFrom, tTo))
		{
			tTo = board.updateUnitsOnTile(tTo, tFrom.getUnitCount() - tTo.getUnitCount());
			tFrom = board.updateUnitsOnTile(tFrom, 0);
			tTo = board.updateOwnership(tTo, tFrom.getOwnerColor());
		}
		else
		{
			tTo = board.updateUnitsOnTile(tTo, tTo.getUnitCount() - tFrom.getUnitCount());
			tFrom = board.updateUnitsOnTile(tFrom, 0);
		}
	}
	
	private boolean isAttackValid(Tile from, Tile to) {
		return from.getUnitCount() > to.getUnitCount();
	}
}
