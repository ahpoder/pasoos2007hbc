package hottargui.strategy;

import hottargui.framework.*;

public class DieRollAttackStrategy implements AttackStrategy{

	public void attack(Tile tFrom,Tile tTo, int dieValue, int noOfAttackUnits){
		int deadCamels = Math.round((dieValue + tFrom.getStrategicValue())/2);
		if (tTo.getUnitCount() < deadCamels) {
			deadCamels = tTo.getUnitCount(); 
		}
		//return deadCamels;
	}
}

