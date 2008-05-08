package hottargui.config;

import hottargui.framework.*;

public class BetaGameFactory implements GameFactory{

	public Board createBoard() {
		return new Board(new AlphaBoardFactory());
	}

	public WinnerStrategy createWinnerStrategy() {
		return new BetaWinnerStrategy();
	}

	public PutUnitsStrategy createPutUnitsStrategy() {
		return new BetaPutUnitsStrategy();
	}

	public AttackStrategy createAttackStrategy() {
		return new BetaAttackStrategy();
	}
}

