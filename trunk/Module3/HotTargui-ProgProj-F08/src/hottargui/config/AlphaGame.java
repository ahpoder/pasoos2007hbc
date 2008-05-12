package	hottargui.config;

import hottargui.framework.*;

import java.util.*;

/** AlphaGame implementation.
    Presently simply a temporary test stub to be expanded
    by a test-driven process.
 */

public class AlphaGame implements Game, RoundObserver {

  private Board board = null;
  private GameFactory gameFactory;
  private PlayerTurnStrategy turnStrategy;
  private MoveValidationStrategy moveValidationStrategy;
  int roundsCompleted = 0;
  public AlphaGame() { }
  
  public void setGameFactory(GameFactory gameFactory)
  {
	  this.gameFactory = gameFactory;
  }
  
  public void newGame() {
	  board = new AlphaBoard(new AlphaBoardFactory());
	  moveValidationStrategy = gameFactory.createMoveValidationStrategy();
	  turnStrategy = gameFactory.createTurnStrategy();
	  currentPlayer = turnStrategy.nextPlayer();
	  turnStrategy.AddRoundDoneObserver(this);
	  currentState = State.move;
  }

  /** return a specific tile */
  public Tile getTile( Position p ) {
    return board.getTile(p);
  }

  private PlayerColor currentPlayer = PlayerColor.Red;
  public Player getPlayerInTurn() {
    return board.getPlayer(currentPlayer);
  }

  State currentState = State.move;
  public State getState() {
    return currentState;
  }

  public boolean move(Position from, Position to, int count) {
	MoveAttemptResult res = moveValidationStrategy.validateMove(from, to, getPlayerInTurn().getColor());
	if (res == MoveAttemptResult.MOVE_VALID)
    {
    	// Perform move
		Tile tFrom = board.getTile(from);
		Tile tTo = board.getTile(to);
		board.updateUnitsOnTile(tFrom, tFrom.getUnitCount() - count);
		board.updateUnitsOnTile(tTo, tFrom.getUnitCount() + count);
		board.updateOwnership(tTo, tFrom.getOwnerColor());
		currentState = State.buy;
		return true;
    }
    else if (res == MoveAttemptResult.ATTACK_NEEDED)
    {
    	// Perform attack
		Tile tFrom = board.getTile(from);
		Tile tTo = board.getTile(to);
		if (tFrom.getUnitCount() > tTo.getUnitCount())
		{
			board.updateUnitsOnTile(tTo, tFrom.getUnitCount() - tTo.getUnitCount());
			board.updateUnitsOnTile(tFrom, 0);
			board.updateOwnership(tTo, tFrom.getOwnerColor());
		}
		else
		{
			board.updateUnitsOnTile(tTo, tTo.getUnitCount() - tFrom.getUnitCount());
			board.updateUnitsOnTile(tFrom, 0);
		}
		currentState = State.buy;
		return true;
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
	      board.updatePlayerUnits(p, p.getCoins() - count);
	      board.updateUnitsOnTile(t, t.getUnitCount() + count);
	      currentState = State.move;
	      currentPlayer = this.turnStrategy.nextPlayer();
	  	  return true;
	    }
	}
    return false;
  }

  private void calculateRevenue() {
	  Iterator<? extends Player> playerItt = board.getPlayers();
	  while (playerItt.hasNext())
	  {
		  Player p = playerItt.next();
		  Iterator<? extends Tile> tiles = board.getBoardIterator();
		  boolean hasSettlement = false;
		  int revenue = 0;
		  while (tiles.hasNext())
		  {
			  Tile t = tiles.next();
			  if (t.getOwnerColor() == p.getColor())
			  {
				  if (t.getType() == TileType.Settlement)
				  {
					  hasSettlement = true;
				  }
				  revenue += t.getEcconomicValue();
			  }
		  }
		  if (hasSettlement)
		  {
			  board.updatePlayerUnits(p, p.getCoins() + revenue);
		  }
	  }
  }

public PlayerColor turnCard() {
    return PlayerColor.None;
  }

  public void rollDie() {
  }

  public int getDieValue() {
    return 1;
  }
  
  public Iterator<? extends Tile> getBoardIterator() {
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

	public PlayerColor getWinner() {
		if (turnStrategy.getRoundCount() >= 25)
		{
			currentState = State.newGame;
			Tile t = board.getTile(new Position(3,3));
			return t.getOwnerColor();
		}
		return PlayerColor.None;
	}

	public void roundDone() {
		calculateRevenue();
	}
}

