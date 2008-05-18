package hottargui.config;

import hottargui.framework.*;

public class TestGameFactory implements GameFactory {

	AlphaGameFactory bgf;
	BoardFactory bf;
	public TestGameFactory(Game g, BoardFactory bf) {
		bgf = new AlphaGameFactory(g);
		this.bf = bf;
		pts = bgf.createTurnStrategy();
	}

	PlayerTurnStrategy pts = null;
	public TestGameFactory(Game g, BoardFactory bf, PlayerTurnStrategy ts) {
		bgf = new AlphaGameFactory(g);
		this.bf = bf;
		this.pts = ts;
	}

	public Board createBoard() {
		return new AlphaBoard(bf);
	}

	public PlayerTurnStrategy createTurnStrategy() {
		return pts;
	}
	
	public MoveValidationStrategy createMoveValidationStrategy() {
		return bgf.createMoveValidationStrategy();
	}
	
	public PutUnitsStrategy createPutUnitsStrategy() {
		return bgf.createPutUnitsStrategy();
	}
	
	public AttackStrategy createAttackStrategy() {
		return bgf.createAttackStrategy();
	}
	
	public WinnerStrategy createWinnerStrategy() {
		return bgf.createWinnerStrategy();
	}
}
