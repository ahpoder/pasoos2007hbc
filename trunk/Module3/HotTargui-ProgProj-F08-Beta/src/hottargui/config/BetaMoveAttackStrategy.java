package hottargui.config;

import hottargui.framework.*;
import hottargui.standard.*;

/**
 * Skeleton for concrete MoveStrategy implementaton (Beta Variant).
 */

public class BetaMoveAttackStrategy implements MoveAttackStrategy {

    public final static MoveAttackState STATE_MOVE = new MoveState();
    public final static MoveAttackState STATE_ATTACK = new AttackState();
    public final static MoveAttackState STATE_DEFEND = new DefendState();
    public final static MoveAttackState STATE_BUY = new BuyState();

    private MoveAttackState moveAttackState;

    public BetaMoveAttackStrategy() {
        moveAttackState = STATE_MOVE;
    }

    public boolean moveAttack(Tile tFrom,Tile tTo) {
        return setMoveAttackState(moveAttackState.moveAttack(this));
    }

    public boolean dieRolled(int dieValue) {
        return setMoveAttackState(moveAttackState.dieRolled(this));
    }

    public boolean givingUp() {
        return setMoveAttackState(moveAttackState.givingUp(this));
    }

    private boolean setMoveAttackState(State newState) {
        moveAttackState = STATE_ATTACK;
        return false;
    }
}