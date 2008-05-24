package hottargui.framework;

/** Handles moove/attack actions according to the current move/attack state.
 *  Acts as State role in the move/battle state pattern.  
 */

public interface MoveAttackState {

  
	State moveAttack(MoveAttackStrategy context);

        
    	State dieRolled(MoveAttackStrategy context);

       
    	State givingUp(MoveAttackStrategy context);
}