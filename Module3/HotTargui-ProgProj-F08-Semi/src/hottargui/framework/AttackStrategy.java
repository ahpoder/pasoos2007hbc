package hottargui.framework;

/*
*	Responsibility: Handle attack
*
*	Notes:
*	Input dieValue is only used in AlphaTargui
*	Input noAttackUnits is only used in BetaTarGui
*
*	attack()
*/  

public interface AttackStrategy {

	public void attack(Tile tFrom,Tile tTo, int dieValue, int noOfAttackUnits);

}
