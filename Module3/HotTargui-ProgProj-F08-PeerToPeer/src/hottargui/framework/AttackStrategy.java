package hottargui.framework;

import java.rmi.Remote;

/*
*	Responsibility: Handle attack
*
*	Notes:
*	Input dieValue is only used in AlphaTargui
*	Input noAttackUnits is only used in BetaTarGui
*
*	attack()
*/  

public interface AttackStrategy extends Remote {

	public void attack(Tile tFrom,Tile tTo, int dieValue, int noOfAttackUnits);

}
