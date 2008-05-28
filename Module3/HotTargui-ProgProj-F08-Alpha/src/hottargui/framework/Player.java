package hottargui.framework;

/** Player represents one of the players in the game.

    Responsibilities:
    1) Know its color
    2) Know number of coins in the treasury

 */
public interface Player {
  /** return the color of the player */
  public PlayerColor getColor();
  /** return the number of coins in the treasury */
  public int getCoins();
}
