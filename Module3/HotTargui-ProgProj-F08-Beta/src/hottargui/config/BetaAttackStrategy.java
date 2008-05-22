package hottargui.config;

import hottargui.framework.*;

public class BetaAttackStrategy implements AttackStrategy{
	private Game game;
	
	public BetaAttackStrategy(Game game) {
		this.game = game;
	}
	
	public State attack(Tile tFrom,Tile tTo, int dieValue, int noOfAttackUnits){
		// Find number of dead units (camels)
		dieValue = game.getDieValue();
		int deadUnits = Math.round((dieValue + tFrom.getStrategicValue())/2);
		if (tTo.getUnitCount() < deadUnits) {
			deadUnits = tTo.getUnitCount(); 
		}

    // Perform attack
		Board board = game.getBoard();
		if (isDefendAllowed(tTo, deadUnits))
		{
	    System.out.println("BetaAttack, defend allowed: " + deadUnits + " dead units");
			tTo = board.updateUnitsOnTile(tTo, tTo.getUnitCount() - deadUnits);
			return State.defend;
		}
		else
		{
	    System.out.println("BetaAttack successfull: " + deadUnits + " dead units (dieValue=" + dieValue + ", strategicValue=" + tFrom.getStrategicValue() +")");
	    
      System.out.println(tFrom.getOwnerColor() + " on " + tFrom.getType() + " has " + tFrom.getUnitCount() + " units");
      System.out.println(tTo.getOwnerColor() + " on " + tTo.getType() + " has " + tTo.getUnitCount() + " units");

			tTo = board.updateUnitsOnTile(tTo, tFrom.getUnitCount() - deadUnits);
			tFrom = board.updateUnitsOnTile(tFrom, 0);
			tTo = board.updateOwnership(tTo, tFrom.getOwnerColor());

	    System.out.println("After update......");
     	System.out.println(tFrom.getOwnerColor() + " on " + tFrom.getType() + " has " + tFrom.getUnitCount() + " units");
      System.out.println(tTo.getOwnerColor() + " on " + tTo.getType() + " has " + tTo.getUnitCount() + " units");

			return State.buy;
		}
	}

	private boolean isAttackSuccesfull(Tile tFrom, Tile tTo) {
		return tFrom.getUnitCount() > tTo.getUnitCount();
	}
	
	private boolean isDefendAllowed(Tile tTo, int deadUnits) {
		return (tTo.getUnitCount() - deadUnits > 0);
	}
}

