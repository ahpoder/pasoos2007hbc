package	hottargui.standard;

import hottargui.framework.*;

import java.util.*;

/** BetaGame implementation.
    Presently simply a temporary test stub to be expanded
    by a test-driven process.
 */

public class StandardGame implements Game, RoundObserver {

  private Board board = null;
  private GameFactory gameFactory;
  private PlayerTurnStrategy turnStrategy = null;
  private MoveValidationStrategy moveValidationStrategy;
  private WinnerStrategy winnerStrategy;
  private PutUnitsStrategy putUnitsStrategy;
  private AttackStrategy attackStrategy;
  public StandardGame() { }
  
  public void setGameFactory(GameFactory gameFactory)
  {
	  this.gameFactory = gameFactory;
  }
  
  public void newGame() {
	  if (turnStrategy != null)
	  {
		  turnStrategy.removeRoundDoneObserver(this);
	  }
	  board = gameFactory.createBoard();
	  moveValidationStrategy = gameFactory.createMoveValidationStrategy();
	  turnStrategy = gameFactory.createTurnStrategy();
	  currentPlayer = turnStrategy.nextPlayer();
	  turnStrategy.addRoundDoneObserver(this);
	  winnerStrategy = gameFactory.createWinnerStrategy();
	  putUnitsStrategy = gameFactory.createPutUnitsStrategy();
	  attackStrategy = gameFactory.createAttackStrategy();
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
		tFrom = board.updateUnitsOnTile(tFrom, tFrom.getUnitCount() - count);
		tTo = board.updateUnitsOnTile(tTo, tTo.getUnitCount() + count);
		tTo = board.updateOwnership(tTo, tFrom.getOwnerColor());
		currentState = State.buy;
		return true;
    }
    else if (res == MoveAttemptResult.ATTACK_NEEDED)
    {
		Tile tFrom = board.getTile(from);
		Tile tTo = board.getTile(to);
    	attackStrategy.attack(tFrom, tTo, 0, 0);
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
	    if (putUnitsStrategy.isPutValid(p, t, count))
	    {
	      p = board.updatePlayerUnits(p, p.getCoins() - count);
	      t = board.updateUnitsOnTile(t, t.getUnitCount() + count);
	      currentState = State.move;
	      do
	      {
	    	  currentPlayer = this.turnStrategy.nextPlayer();
	      }
	      while (!board.hasPlayer(currentPlayer));
	  	  return true;
	    }
	}
    return false;
  }

  private void calculateRevenue() {
	  Iterator<PlayerColor> playerItt = board.getPlayers();
	  while (playerItt.hasNext())
	  {
		  PlayerColor pc = playerItt.next();
		  Iterator<? extends Tile> tiles = board.getBoardIterator();
		  boolean hasSettlement = false;
		  int revenue = 0;
		  while (tiles.hasNext())
		  {
			  Tile t = tiles.next();
			  if (t.getOwnerColor() == pc)
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
			  Player p = board.getPlayer(pc);
			  p = board.updatePlayerUnits(p, p.getCoins() + revenue);
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
		return winnerStrategy.getWinner(turnStrategy.getRoundCount());
	}

	public void roundDone() {
		calculateRevenue();
	}
	
	public Board getBoard() {
		return board;
	}
}

