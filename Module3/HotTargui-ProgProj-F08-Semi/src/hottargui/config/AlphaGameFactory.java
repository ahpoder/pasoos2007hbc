package hottargui.config;

import hottargui.framework.*;
import hottargui.standard.*;

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
		return new SimpleAttackStrategy();
	}

	@Override
	public PutUnitsStrategy createPutUnitsStrategy() {
		// TODO Auto-generated method stub
		return new SettlementOnlyPutUnitsStrategy();
	}

	@Override
	public WinnerStrategy createWinnerStrategy() {
		// TODO Auto-generated method stub
		return new SaltMineWinnerStrategy();
	}
}
