package	hottargui.standard;

import hottargui.framework.*;

/** Standard Player implementation. */

public class StandardPlayer implements Player {
  private int coins;
  private PlayerColor color;
  public StandardPlayer(PlayerColor c) {
    color = c;
    coins = 10;
  }
  public PlayerColor getColor() { return color; }
  public int getCoins() { return coins; }
  /** withdraw from treasury.
    * PreCondition: amount >= coins
    */
  public void withdraw(int amount) {
    coins -= amount;
  }
  /** add to the treasury.*/
  public void add(int amount) {
    coins += amount;
  }
}
