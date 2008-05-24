package hottargui.standard;

import hottargui.framework.*;

/*
* OBS! Code only in skeleton form
*/

public class DefendState implements MoveAttackState {

    public State moveAttack(MoveAttackStrategy context) {
        // moves are not expected in this state
        return null;
    }

    public State dieRolled(MoveAttackStrategy context) {
        State nextState = State.attack;
        return nextState;
    }

    public State givingUp(MoveAttackStrategy context) {
        // Giving up is not expected in this state
        return null;
    }
}