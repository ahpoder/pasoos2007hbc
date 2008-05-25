package hottargui.config;

import hottargui.factory.*;
import hottargui.framework.*;
import hottargui.standard.*;
import hottargui.strategy.*;

public class AlphaGameFactory implements GameFactory {
	private Game game;
	public AlphaGameFactory(Game g)
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

	public AttackStrategy createAttackStrategy() {
		return new SimpleAttackStrategy(game);
	}

	public PutUnitsStrategy createPutUnitsStrategy() {
		return new SettlementOnlyPutUnitsStrategy();
	}

	public WinnerStrategy createWinnerStrategy() {
		return new SaltMineWinnerStrategy(game);
	}

	@Override
	public Die createDieStrategy() {
		// TODO Auto-generated method stub
		return null;
	}
}
