package hottargui.config;

import hottargui.framework.*;

public class BetaAttackStrategy implements AttackStrategy{
	private Game game;
	
	public BetaAttackStrategy(Game game) {
		this.game = game;
	}
	
	public void attack(Tile tFrom,Tile tTo, int dieValue, int noOfAttackUnits){
		int deadCamels = Math.round((dieValue + tFrom.getStrategicValue())/2);
		if (tTo.getUnitCount() < deadCamels) {
			deadCamels = tTo.getUnitCount(); 
		}
//		return deadCamels;
	}
}

