package hottargui.framework;

import java.rmi.Remote;

/** Player represents one of the players in the game.

    Responsibilities:
    1) Know its color
    2) Know number of coins in the treasury

 */
public interface Player extends Remote {
  /** return the color of the player */
  public PlayerColor getColor();
  /** return the number of coins in the treasury */
  public int getCoins();
}
