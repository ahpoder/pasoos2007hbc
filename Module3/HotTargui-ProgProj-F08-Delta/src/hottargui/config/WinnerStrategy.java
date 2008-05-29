package hottargui.config;

import hottargui.framework.PlayerColor;

/**
 * Defines responsability to determine winner.
 */
public interface WinnerStrategy {

    /**
     * Get the PlayerColor of the winner of the game
     * @return PlayerColor of the winner
     */
    PlayerColor getWinner();
    
}
