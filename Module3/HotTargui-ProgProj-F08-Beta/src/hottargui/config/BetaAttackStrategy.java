package hottargui.config;

import hottargui.framework.*;

public class BetaAttackStrategy implements AttackStrategy{
	private Game game;
	private Board board;
	private Tile tFrom;
	private Tile tTo;
	
	public BetaAttackStrategy(Game game) {
		this.game = game;
		this.board = game.getBoard();
	}
	
	public State moveAttack(Tile tFrom,Tile tTo){
		this.tFrom = tFrom;
		this.tTo = tTo;
		return State.attack;
	}
	
	public State dieRolled(int dieValue) {
    // Perform attack
    int deadUnits;
		State currentState = game.getState();
		
		switch (currentState){
		
			case attack:
				
				deadUnits = noOfDeadUnits(tFrom, tTo, dieValue);
				if (isDefendAllowed(deadUnits))
				{
					tTo = board.updateUnitsOnTile(tTo, tTo.getUnitCount() - deadUnits);
					currentState = State.defend;
				}
				else
				{
					tTo = board.updateUnitsOnTile(tTo, tFrom.getUnitCount() - deadUnits);
					tFrom = board.updateUnitsOnTile(tFrom, 0);
					tTo = board.updateOwnership(tTo, tFrom.getOwnerColor());
					currentState = State.buy;
				}
				break;
			
			case defend:
				
				deadUnits = noOfDeadUnits(tTo, tFrom, dieValue);
				if (isReAttackAllowed(deadUnits))
				{
					currentState = State.attack;
				}
				else
				{
					currentState = State.buy;
				}
				tFrom = board.updateUnitsOnTile(tFrom, tFrom.getUnitCount() - deadUnits);
				break;
		}
		return currentState;
  }
  
  public State givingUp(){
		return State.buy;
	}

	private boolean isDefendAllowed(int deadUnits) {
		return (tTo.getUnitCount() - deadUnits > 0);
	}
	
	private boolean isReAttackAllowed(int deadUnits) {
		return (tFrom.getUnitCount() - deadUnits > 0);
	}
	
	// Find number of dead units (camels)
	private int noOfDeadUnits(Tile fromTile, Tile toTile, int dieValue){	
		int deadUnits = Math.round((dieValue + fromTile.getStrategicValue())/2);
		if (toTile.getUnitCount() < deadUnits) {
			deadUnits = toTile.getUnitCount(); 
		}
		return deadUnits;	
	}
	
}

