package	hottargui.standard;

import hottargui.framework.*;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.*;

/** StandardGame implementation.
 */

public class StandardGame extends UnicastRemoteObject implements Game, RoundObserver {

  GameRepository gameRepository;
  public StandardGame(GameRepository gameRepository) throws RemoteException {
		super();
	}
  
  public void newGame() {
	  gameRepository.getTurnStrategy().removeRoundDoneObserver(this);

	  currentPlayer = gameRepository.getTurnStrategy().nextPlayer();
	  gameRepository.getTurnStrategy().addRoundDoneObserver(this);
	  currentState = State.move;
  }

  /** return a specific tile */
  public Tile getTile( Position p ) {
    return gameRepository.getBoard().getTile(p);
  }

  private PlayerColor currentPlayer = PlayerColor.Red;
  public Player getPlayerInTurn() {
    return gameRepository.getBoard().getPlayer(currentPlayer);
  }

  State currentState = State.move;
  public State getState() {
    return currentState;
  }

  public boolean move(Position from, Position to, int count) {
	MoveAttemptResult res = gameRepository.getMoveValidationStrategy().validateMove(from, to, getPlayerInTurn().getColor());
	if (res == MoveAttemptResult.MOVE_VALID)
    {
    	// Perform move
		Tile tFrom = gameRepository.getBoard().getTile(from);
		Tile tTo = gameRepository.getBoard().getTile(to);
		tFrom = gameRepository.getBoard().updateUnitsOnTile(tFrom, tFrom.getUnitCount() - count);
		tTo = gameRepository.getBoard().updateUnitsOnTile(tTo, tTo.getUnitCount() + count);
		tTo = gameRepository.getBoard().updateOwnership(tTo, tFrom.getOwnerColor());
		currentState = State.buy;
		return true;
    }
    else if (res == MoveAttemptResult.ATTACK_NEEDED)
    {
		Tile tFrom = gameRepository.getBoard().getTile(from);
		Tile tTo = gameRepository.getBoard().getTile(to);
		gameRepository.getAttackStrategy().attack(tFrom, tTo, 0, 0);
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
	    Tile t = gameRepository.getBoard().getTile(deploy);
	    if (gameRepository.getPutUnitsStrategy().isPutValid(p, t, count))
	    {
	      p = gameRepository.getBoard().updatePlayerUnits(p, p.getCoins() - count);
	      t = gameRepository.getBoard().updateUnitsOnTile(t, t.getUnitCount() + count);
	      currentState = State.move;
	      do
	      {
	    	  currentPlayer = gameRepository.getTurnStrategy().nextPlayer();
	      }
	      while (!gameRepository.getBoard().hasPlayer(currentPlayer));
	  	  return true;
	    }
	}
    return false;
  }

  private void calculateRevenue() {
	  Iterator<PlayerColor> playerItt = gameRepository.getBoard().getPlayers();
	  while (playerItt.hasNext())
	  {
		  PlayerColor pc = playerItt.next();
		  Iterator<? extends Tile> tiles = gameRepository.getBoard().getBoardIterator();
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
			  Player p = gameRepository.getBoard().getPlayer(pc);
			  p = gameRepository.getBoard().updatePlayerUnits(p, p.getCoins() + revenue);
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
    return gameRepository.getBoard().getBoardIterator();
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
		return gameRepository.getWinnerStrategy().getWinner(gameRepository.getTurnStrategy().getRoundCount());
	}

	public void roundDone() {
		calculateRevenue();
	}
	
	public Board getBoard() {
		return gameRepository.getBoard();
	}
}

