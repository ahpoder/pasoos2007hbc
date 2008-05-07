package	hottargui.view;

import hottargui.framework.*;
import hottargui.standard.*;

import java.util.*;

/** Fake-it implementation of Game interface. This implementation does
    not represent any valid implementation at all; it merely provides
    minimal implemenation, just enough to demonstrate that
    state changes are properly reflected on the GUI via the
    GameListener attached. As such, it serves as a stub to facilitate
    GUI testing.

    Characteristics:
    There are two players, red, and green and turns alternate between them.
    No statemachine is implemented. The board is all Erg except for
    a saltmine, settlement and an oasis. Rolling die simply go over
    1-2-3-4-5-6 and over again. All moves are legel no matter the
    distance or who owns the tile EXCEPT (4,4).
    
    Author: Henrik Bærbak Christensen.
 */

public class StubGame1 implements Game {

  StandardTile settlement, saltmine;
  StandardPlayer pRed, pGreen, inTurn;
  List<Tile> l;
  
  public StubGame1() {
    settlement = new StandardTile(TileType.Settlement, PlayerColor.Red,
                                  0,0 );
    settlement.changeUnitCount(23);
    saltmine = new StandardTile(TileType.Saltmine, PlayerColor.Blue,
                                3,3 );
    saltmine.changeUnitCount(43);
    pRed = new StandardPlayer(PlayerColor.Red);
    pGreen = new StandardPlayer(PlayerColor.Green);
    // Give green a lot in treasury
    pGreen.add(25);
    inTurn = pRed;

    defineList();
  }

  public void fakeMove(int r1, int c1, PlayerColor col1, int u1,
                  int r2, int c2, PlayerColor col2, int u2 ) {
    
    changeTile(r1,c1, col1, u1);
    changeTile(r2,c2, col2, u2);
  }
  
  private void changeTile(int r, int c, PlayerColor col, int u) {
    StandardTile t = (StandardTile) l.get(r*7+c);
    t.changeUnitCount(u);
    t.changePlayerColor(col);
    _notifyTileChange(t);
  }

  /** fake-it move; position to == (4,4) is illegal to test illegal
      moves. */
  public boolean move(Position from, Position to, int count) {
    if ( to.getRow() == 4 && to.getColumn() == 4 ) {
      _notifyMessage( "(4,4) is an illegal move" );
      return false;
    } 
    
    _notifyMessage("Move made from "+from+" to "+ to );
    Tile f = getTile(from);
    Tile t = getTile(to);
    int source = f.getUnitCount();
    int dest = t.getUnitCount();
    changeTile( f.getPosition().getRow(), f.getPosition().getColumn(),
                f.getOwnerColor(), source-count );
    changeTile( t.getPosition().getRow(), t.getPosition().getColumn(),
                f.getOwnerColor(), dest+count );
    return true;
  }

  public boolean buy(int count, Position deploy) {
    Tile t = getTile(deploy);
    int source = t.getUnitCount();
    changeTile(t.getPosition().getRow(), t.getPosition().getColumn(),
               t.getOwnerColor(), source+count ); 
    inTurn.withdraw(count);
    _notifyMessage("Buy made of "+count+" units for player "+
                   inTurn.getColor() + " Treasury now: "+ inTurn.getCoins() );
    _notifyPlayer();
    return true;
  }

  int dieValue = 1;
  public void rollDie() {
    dieValue++;
    if (dieValue==6) dieValue = 1;
    _notifyDie();
    _notifyMessage("Die rolled, new dieValue: "+dieValue );
  }

  public void newGame() {
  }

  /** return a specific tile */
  public Tile getTile( Position p ) {
    return l.get(p.getRow()*7+p.getColumn());
  }
  
  public Player getPlayerInTurn() {
    return inTurn;
  }

  public State getState() {
    return State.move;
  }

  public int getDieValue() {
    return dieValue;
  }

  public PlayerColor turnCard() {
    if (inTurn == pRed) {
      inTurn = pGreen;
    } else {
      inTurn = pRed;
    }
    _notifyPlayer();
    return inTurn.getColor();
  }

  public Iterator<Tile> getBoardIterator() {
    return l.iterator();
  }

  public GameListener listener = null;
  public void addGameListener( GameListener observer ) {
    if ( listener != null ) 
      throw new RuntimeException("AlphaGame only supports one listener." );
    listener = observer;
  }
  
  private void _notifyTileChange(Tile t) {
    listener.updateTile(t);
  }

  private void _notifyMessage(String s ) {
    listener.report(s);
  }

  private void _notifyDie() {
    listener.updateDie( getDieValue() );
  }

  private void _notifyPlayer() {
    listener.updatePlayer( inTurn.getColor() );
  }

  private void defineList() {
    l = new ArrayList<Tile>();
    for ( int r = 0; r < 7; r++ ) {
      for ( int c = 0; c < 7; c++ ) {
        if ( r ==0 && c == 0 ) {
          l.add( settlement );
        } else if ( r == 3 && c == 3 ) {
          l.add( saltmine );
         } else if ( r == 4 && c == 4 ) {
          l.add(  new StandardTile(TileType.Saltlake, PlayerColor.None,
                                   r, c) );
        } else if( r == 5 && c == 2 ) {
          l.add( new StandardTile(TileType.Oasis, PlayerColor.None,
                                  r, c) );
        } else { 
          l.add( new StandardTile(TileType.Erg, PlayerColor.None,
                                  r, c) );
        }
      }
    }
  }
}
