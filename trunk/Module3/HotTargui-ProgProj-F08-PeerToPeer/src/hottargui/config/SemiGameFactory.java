package hottargui.config;

import hottargui.factory.*;
import hottargui.framework.*;
import hottargui.standard.*;
import hottargui.strategy.*;

public class SemiGameFactory implements GameFactory {

	Game game;
	public SemiGameFactory(Game game) {
		this.game = game;
	}
	public Board createBoard() {
		RandomOrderBoardFactory rbf = new RandomOrderBoardFactory();
		return new StandardBoard(rbf);
	}

	public MoveValidationStrategy createMoveValidationStrategy() {
		return new StandardMoveValidationStrategy(game);
	}

	public PlayerTurnStrategy createTurnStrategy() {
		return new SimpleTurnStrategy();
	}

	public AttackStrategy createAttackStrategy() {
		return new DieRollAttackStrategy();
	}

	public PutUnitsStrategy createPutUnitsStrategy() {
		return new AllTilePutUnitsStrategy();
	}

	public WinnerStrategy createWinnerStrategy() {
		return new RevenueWinnerStrategy();
	}
}
