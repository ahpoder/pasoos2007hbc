package hottargui.framework;

import hottargui.standard.StandardTile;

/**
 * Handles move/attack actions.
 * Acts as Strategy role in the move/attack strategy pattern.
 * Acts as Context role in the move/attack state pattern.
 */

public interface MoveAttackStrategy {

    boolean moveAttack(Tile tFrom, Tile tTo);

    boolean dieRolled(int dieValue);

    boolean givingUp();

}