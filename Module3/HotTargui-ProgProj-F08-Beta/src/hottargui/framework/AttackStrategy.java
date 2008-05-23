package hottargui.framework;

/*
*	Responsibility: Handle attack
*/  

public interface AttackStrategy {

	public State moveAttack(Tile tFrom, Tile tTo);
	
	public State dieRolled(int dieValue);
	
	public State givingUp();
}
