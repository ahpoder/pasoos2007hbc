package	hottargui.config;

import hottargui.framework.*;
import hottargui.standard.StandardPlayer;
import hottargui.standard.StandardTile;

import java.util.*;

/** DeltaGame implementation.
    Presently simply a temporary test stub to be expanded
    by a test-driven process.
 */

public class DeltaGame implements Game {

  private Board board = null;
  int roundsCompleted = 0;
  public DeltaGame() {
	  board = new Board(new DeltaBoardFactory());
  }

  public void newGame() {
	  board = new Board(new DeltaBoardFactory());
	  currentPlayer = 0;
	  currentState = State.move;
  }

  /** return a specific tile */
  public Tile getTile( Position p ) {
    return board.getTile(p);
  }

  private int currentPlayer = 0;
  public Player getPlayerInTurn() {
    return board.getPlayer(currentPlayer);
  }

  State currentState = State.move;
  public State getState() {
    return currentState;
  }

  public boolean move(Position from, Position to, int count) {
	if (getState() == State.move)
	{
		Board.MoveAttemptResult res = board.validateMove(from, to, getPlayerInTurn().getColor());
		if (res == Board.MoveAttemptResult.MOVE_VALID)
	    {
	    	// Perform move
			Tile tFrom = board.getTile(from);
			Tile tTo = board.getTile(to);
			((DeltaTile)tFrom).changeUnitCount(tFrom.getUnitCount() - count);
			((DeltaTile)tTo).changeUnitCount(tFrom.getUnitCount() + count);
			((DeltaTile)tTo).changePlayerColor(getPlayerInTurn().getColor());
			// todo Can the count ever be more than the number of camels???
			currentState = State.buy;
			return true;
	    }
	    else if (res == Board.MoveAttemptResult.ATTACK_NEEDED)
	    {
	    	// Perform attack
			Tile tFrom = board.getTile(from);
			Tile tTo = board.getTile(to);
			if (tFrom.getUnitCount() > tTo.getUnitCount())
			{
				((DeltaTile)tTo).changeUnitCount(tFrom.getUnitCount() - tTo.getUnitCount());
				((DeltaTile)tFrom).changeUnitCount(0);
				((DeltaTile)tTo).changePlayerColor(getPlayerInTurn().getColor());
			}
			else
			{
				((DeltaTile)tTo).changeUnitCount(tTo.getUnitCount() - tFrom.getUnitCount());
				((DeltaTile)tFrom).changeUnitCount(0);
			}
			board.updateBoard();
			currentState = State.buy;
			return true;
	    }
	}
	return false;
  }

  public boolean buy(int count, Position deploy) {
	// It is allowed to buy without having moved, but the turn goes to the next player
	if (getState() == State.buy || getState() == State.move)
	{
	    Player p = getPlayerInTurn();
	    Tile t = board.getTile(deploy);
	    if ((p.getCoins() >= count) && t.getOwnerColor() == p.getColor())
	    {
	      ((StandardPlayer)p).withdraw(count);
	      ((DeltaTile)t).changeUnitCount(t.getUnitCount() + count);
	      currentState = State.move;
	      currentPlayer++;
	      if (currentPlayer >= board.getPlayerCount())
	      {
	    	  currentPlayer = 0;
	    	  calculateRevenue();
	      }
	  	  return true;
	    }
	}
    return false;
  }

  private void calculateRevenue() {
	  Iterator<Tile> tiles = board.getBoardIterator();
	  int redRevenue = 0;
	  boolean redHasSettlement = false;
	  int greenRevenue = 0;
	  boolean greenHasSettlement = false;
	  int blueRevenue = 0;
	  boolean blueHasSettlement = false;
	  int yellowRevenue = 0;
	  boolean yellowHasSettlement = false;
	  while (tiles.hasNext())
	  {
		  Tile t = tiles.next();
		  if (t.getOwnerColor() == PlayerColor.Red)
		  {
			  if (t.getType() == TileType.Settlement)
			  {
				  redHasSettlement = true;
			  }
			  redRevenue += getEcconomicValue(t);
		  }
		  else if (t.getOwnerColor() == PlayerColor.Green)
		  {
			  if (t.getType() == TileType.Settlement)
			  {
				  greenHasSettlement = true;
			  }
			  greenRevenue += getEcconomicValue(t);
		  }
		  else if (t.getOwnerColor() == PlayerColor.Blue)
		  {
			  if (t.getType() == TileType.Settlement)
			  {
				  blueHasSettlement = true;
			  }
			  blueRevenue += getEcconomicValue(t);
		  }
		  else if (t.getOwnerColor() == PlayerColor.Yellow)
		  {
			  if (t.getType() == TileType.Settlement)
			  {
				  yellowHasSettlement = true;
			  }
			  yellowRevenue += getEcconomicValue(t);
		  }
	  }
	  for (int i = 0; i < board.getPlayerCount(); ++i)
	  {
		  Player p = board.getPlayer(i);
		  if (p.getColor() == PlayerColor.Red && redHasSettlement)
		  {
			  ((StandardPlayer)p).add(redRevenue);
		  }
		  else if (p.getColor() == PlayerColor.Green && greenHasSettlement)
		  {
			  ((StandardPlayer)p).add(greenRevenue);
		  }
		  else if (p.getColor() == PlayerColor.Blue && blueHasSettlement)
		  {
			  ((StandardPlayer)p).add(blueRevenue);
		  }
		  else if (p.getColor() == PlayerColor.Yellow && yellowHasSettlement)
		  {
			  ((StandardPlayer)p).add(yellowRevenue);
		  }
	  }

	++roundsCompleted;
	if (roundsCompleted == 25)
	{
		currentState = State.newGame;
		Tile t = board.getTile(new Position(3,3));
		report("GAME OVER - " + t.getOwnerColor() + " WON");
	}
  }

	private int getEcconomicValue(Tile t) {
		TileType tt = t.getType();
		if (tt == TileType.Settlement)
		{
			return 4;
		}
		else if (tt == TileType.Saltmine)
		{
			return 5;
		}
		else if (tt == TileType.Oasis)
		{
			return 3;
		}
		else if (tt == TileType.Erg)
		{
			return 1;
		}
		else if (tt == TileType.Reg)
		{
			return 2;
		}
		return 0;
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
    return board.getBoardIterator();
  }

  private ArrayList<GameListener> listeners = new ArrayList<GameListener>();
  public void addGameListener( GameListener observer ) {
	  listeners.add(observer);
  }

  public void report(String s) {
	  Iterator<GameListener> itt = listeners.iterator();
	  while (itt.hasNext())
	  {
		  GameListener obs = itt.next();
		  obs.report(s);
	  }
  }
}