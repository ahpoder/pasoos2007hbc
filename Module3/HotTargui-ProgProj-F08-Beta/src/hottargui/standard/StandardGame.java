package	hottargui.standard;

import hottargui.framework.*;

import java.util.*;

/** AlphaGame implementation.
    Presently simply a temporary test stub to be expanded
    by a test-driven process.
 */

public class StandardGame implements Game, RoundObserver {

  private Board board = null;
  private GameFactory gameFactory;
  private PlayerTurnStrategy turnStrategy = null;
  private MoveValidationStrategy moveValidationStrategy;
  // The strategy for putting units after buy
	private PutUnitsStrategy putUnitsStrategy;
	// The strategy for attack
	private AttackStrategy attackStrategy;
	// The strategy for finding the winner
	private WinnerStrategy winnerStrategy;
	private Die die;
	
  int roundsCompleted = 0;
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
	  putUnitsStrategy = gameFactory.createPutUnitsStrategy();
		attackStrategy = gameFactory.createAttackStrategy();
		winnerStrategy = gameFactory.createWinnerStrategy();

	  currentPlayer = turnStrategy.nextPlayer();
	  turnStrategy.addRoundDoneObserver(this);
	  currentState = State.move;
	  
	  die = new StandardDie();
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
  
  public void setState(State state) {
  	currentState = state;
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
    	// Perform attack
			Tile tFrom = board.getTile(from);
			Tile tTo = board.getTile(to);
    	currentState = attackStrategy.moveAttack(tFrom, tTo);
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
//	    if ((p.getCoins() >= count) && t.getOwnerColor() == p.getColor() && (t.getType() == TileType.Settlement || count == 0))
	    if ((p.getCoins() >= count && putUnitsStrategy.isPutValid(p, t)) || count == 0)
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
				  revenue += t.getEconomicValue();
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
 		switch (currentState) {
      case attack:
      case defend:
      	die.rollDie();

        break;
      default:
	      throw new IllegalActionException(currentState);
      }
  }

  public int getDieValue() {
  	return die.getValue();
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
			return winnerStrategy.getWinner();
/*			Iterator<? extends Tile> itt = board.getBoardIterator();
			while (itt.hasNext())
			{
				Tile t = itt.next();
				if (t.getType() == TileType.Saltmine)
				{
					return t.getOwnerColor();
				}
			}
*/
		}
		return PlayerColor.None;
	}

	public void roundDone() {
		calculateRevenue();
	}
	
	public Board getBoard() {
		return board;
	}
	
	public boolean dieRolled(int dieValue) {
//  	die.setValue(dieValue);
  	currentState = attackStrategy.dieRolled(dieValue);
    return true;
  }
  
  public boolean givingUp() {
//  	die.setValue(dieValue);
  	currentState = attackStrategy.givingUp();
    return true;
  }
	
}

