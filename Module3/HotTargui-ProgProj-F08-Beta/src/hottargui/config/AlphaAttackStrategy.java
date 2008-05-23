package hottargui.config;

import hottargui.framework.*;

public class AlphaAttackStrategy implements AttackStrategy{

	private Game game;
	
	public AlphaAttackStrategy(Game game) {
		this.game = game;
	}
	
	public State moveAttack(Tile tFrom, Tile tTo){
    	
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
	
	public State dieRolled(int dieValue) {
  	// Should never be invoked in Alpha variant
    return null;
  }
  
  public State givingUp() {
    // Should never be invoked in Alpha variant
    return null;
  }
	
	private boolean isAttackValid(Tile from, Tile to) {
		return from.getUnitCount() > to.getUnitCount();
	}

}


