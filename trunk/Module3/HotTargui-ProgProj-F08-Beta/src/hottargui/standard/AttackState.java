package hottargui.standard;

import hottargui.framework.*;

/*
* OBS! Code only in skeleton form
*/

public class AttackState implements MoveAttackState {

    public State moveAttack(MoveAttackStrategy context) {
        // moves are not expected in this state
        return null;
    }

    public State dieRolled(MoveAttackStrategy context) {
        State nextState = State.defend;
        return nextState;
    }

    public State givingUp(MoveAttackStrategy context) {
        return State.buy;
    }
}