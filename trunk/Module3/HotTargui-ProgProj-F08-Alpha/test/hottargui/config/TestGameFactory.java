package hottargui.config;

import hottargui.framework.*;

public class TestGameFactory implements GameFactory {

	AlphaGameFactory agf;
	BoardFactory bf;
	public TestGameFactory(Game g, BoardFactory bf) {
		agf = new AlphaGameFactory(g);
		this.bf = bf;
		pts = agf.createTurnStrategy();
	}

	PlayerTurnStrategy pts = null;
	public TestGameFactory(Game g, BoardFactory bf, PlayerTurnStrategy ts) {
		agf = new AlphaGameFactory(g);
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
		return agf.createMoveValidationStrategy();
	}
}
