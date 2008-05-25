package hottargui.config;

import hottargui.factory.*;
import hottargui.framework.*;
import hottargui.standard.*;
import hottargui.strategy.*;

public class DeltaGameFactory implements GameFactory {
	private Game game;
	public DeltaGameFactory(Game g)
	{
		game = g;
	}
	
	public Board createBoard() {
		RandomOrderBoardFactory rbf = new RandomOrderBoardFactory();
		return new StandardBoard(rbf);
	}

	public PlayerTurnStrategy createTurnStrategy() {
		return new SimpleTurnStrategy();
	}

	public MoveValidationStrategy createMoveValidationStrategy() {
		return new StandardMoveValidationStrategy(game);
	}

	public AttackStrategy createAttackStrategy() {
		return new SimpleAttackStrategy(game);
	}

	public PutUnitsStrategy createPutUnitsStrategy() {
		// TODO Auto-generated method stub
		return new SettlementOnlyPutUnitsStrategy();
	}

	public WinnerStrategy createWinnerStrategy() {
		// TODO Auto-generated method stub
		return new RevenueWinnerStrategy();
	}
}
