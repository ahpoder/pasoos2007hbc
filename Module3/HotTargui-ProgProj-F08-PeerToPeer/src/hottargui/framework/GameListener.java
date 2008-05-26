package hottargui.framework;

import java.rmi.Remote;

/** The Observer role on a Game instance.

    Author: Henrik Bærbak Christensen.
*/


public interface GameListener extends Remote {
  /** invoked when the contents of a tile has been changed 
   * @param t the tile that has been changed
   */
  public void updateTile( Tile t );

  /** invoked when the die has been rolled.
   * @param value the new die value in interval 1..6
   */
  public void updateDie( int value );

  /** invoked when the turn changes from one
   * player to the next or the contents of a
   * player's treasury changes.
   * @param color the color of the player whose next in turn.
   */
  public void updatePlayer( PlayerColor color );

  /** invoked when the game instance wants to report
   * some text information to the user, typically to
   * report why a given move is not valid or some
   * other information that has relevance for the
   * game progression.
   * @param text the text that the user interface should
   * show to the user.
   */
  public void report(String text);
}

