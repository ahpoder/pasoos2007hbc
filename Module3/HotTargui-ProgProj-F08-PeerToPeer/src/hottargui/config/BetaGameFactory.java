package hottargui.config;

import hottargui.factory.*;
import hottargui.framework.*;
import hottargui.standard.*;
import hottargui.strategy.*;

public class BetaGameFactory implements GameFactory{

	private Game game;
	public BetaGameFactory(Game g)
	{
		game = g;
	}
	
	public Board createBoard() {
		StaticBoardFactory abf = new StaticBoardFactory();
		return new StandardBoard(abf);
	}
	
	public PlayerTurnStrategy createTurnStrategy() {
		return new SimpleTurnStrategy();
	}

	public MoveValidationStrategy createMoveValidationStrategy() {
		return new StandardMoveValidationStrategy(game);
	}

	public WinnerStrategy createWinnerStrategy() {
		return new RevenueWinnerStrategy();
	}

	public PutUnitsStrategy createPutUnitsStrategy() {
		return new AllTilePutUnitsStrategy();
	}

	public AttackStrategy createAttackStrategy() {
		return new DieRollAttackStrategy();
	}
}

