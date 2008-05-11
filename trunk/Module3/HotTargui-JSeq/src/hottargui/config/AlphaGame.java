package	hottargui.config;

import hottargui.framework.*;

import java.util.*;

/** AlphaGame implementation.
    Presently simply a temporary test stub to be expanded
    by a test-driven process.
 */

public class AlphaGame implements Game {

  public AlphaGame() {
  }

  public void newGame() {
  }

  /** return a specific tile */
  public Tile getTile( Position p ) {
    return null;
  }

  public Player getPlayerInTurn() {
    return null;
  }

  public State getState() {
    return null;
  }

  public boolean move(Position from, Position to, int count) {
    return true;
  }

  public boolean buy(int count, Position deploy) {
    return true;
  }

  public PlayerColor turnCard() {
    return PlayerColor.None;
  }

  public void rollDie() {
  }

  public int getDieValue() {
    return 1;
  }
  
  public Iterator<Tile> getBoardIterator() {
    return null;
  }

  public void addGameListener( GameListener observer ) {
  }

  public void report(String s) {
  }
}

