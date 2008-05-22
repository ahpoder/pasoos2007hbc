package hottargui.config;

import hottargui.framework.*;

public class AlphaAttackStrategy implements AttackStrategy{

	private Game game;
	
	public AlphaAttackStrategy(Game game) {
		this.game = game;
	}
	
	public State attack(Tile tFrom, Tile tTo, int dieValue, int noOfAttackUnits){
    	
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
		return State.buy;
	}
	
	private boolean isAttackValid(Tile from, Tile to) {
		return from.getUnitCount() > to.getUnitCount();
	}

}


