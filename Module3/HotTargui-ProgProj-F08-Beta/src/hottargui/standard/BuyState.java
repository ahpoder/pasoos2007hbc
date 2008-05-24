package hottargui.standard;

import hottargui.framework.*;

/*
* OBS! Code only in skeleton form
*/

public class BuyState implements MoveAttackState {

    public State moveAttack(MoveAttackStrategy context) {
        return State.buy;
    }

    public State dieRolled(MoveAttackStrategy context) {
        // Roll die is not expected in this state
        return null;
    }

    public State givingUp(MoveAttackStrategy context) {
        // Giving up is not expected in this state
        return null;
    }
}