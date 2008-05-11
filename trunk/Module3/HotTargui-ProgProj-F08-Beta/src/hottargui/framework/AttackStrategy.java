package hottargui.framework;

/*
*	Responsibility: Handle attack
*
*	Notes:
*	Input dieValue is only used in AlphaTargui
*	Input noAttackUnits is only used in BetaTarGui
*
*	attack() returns the number of surviving units???
*/  

public interface AttackStrategy {


	public int attack(Tile tFrom,Tile tTo, int dieValue, int noOfAttackUnits);

}
