package hottargui.standard;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import hottargui.framework.*;

public class StandardGameRepository extends UnicastRemoteObject implements GameRepository {

	public StandardGameRepository() throws RemoteException {
		super();
	}

	private Board board;
	private AttackStrategy attackStrategy;
	private MoveValidationStrategy moveValidationStrategy;
	private PutUnitsStrategy putUnitsStrategy;
	private PlayerTurnStrategy playerTurnStrategy;
	private WinnerStrategy winnerStrategy;
	private Die dieStrategy;
	
	private GameFactory gameFactory;
	private Game game;
	public void initialize(GameFactory gf, Game g) {
		gameFactory = gf;
		game = g;
		
		board = gf.createBoard();
		attackStrategy = gf.createAttackStrategy();
		moveValidationStrategy = gf.createMoveValidationStrategy();
		putUnitsStrategy = gf.createPutUnitsStrategy();
		playerTurnStrategy = gf.createTurnStrategy();
		winnerStrategy = gf.createWinnerStrategy();
		dieStrategy = gf.createDieStrategy();
	}
	
	public AttackStrategy getAttackStrategy() {
		return attackStrategy;
	}

	public Board getBoard() {
		return board;
	}

	public MoveValidationStrategy getMoveValidationStrategy() {
		return moveValidationStrategy;
	}

	public PutUnitsStrategy getPutUnitsStrategy() {
		return putUnitsStrategy;
	}

	public PlayerTurnStrategy getTurnStrategy() {
		return playerTurnStrategy;
	}

	public WinnerStrategy getWinnerStrategy() {
		return winnerStrategy;
	}

	public void reinitialize() {
		initialize(gameFactory, game);
	}

	public Die getDieStrategy() {
		return dieStrategy;
	}
}
