package hottargui.config;

import hottargui.framework.*;

public class AlphaGameFactory implements GameFactory{

	public Board createBoard() {
		return new Board(new AlphaBoardFactory());
	}

	public PlayerTurnStrategy createTurnStrategy() {
		return new SimpleTurnStrategy();
	}

	public WinnerStrategy createWinnerStrategy() {
		return new AlphaWinnerStrategy();
	}

	public PutUnitsStrategy createPutUnitsStrategy() {
		return new AlphaPutUnitsStrategy();
	}

	public AttackStrategy createAttackStrategy() {
		return new AlphaAttackStrategy();
	}
}

