package hottargui.config;

import hottargui.framework.*;

public class TestGameFactory implements GameFactory {

	public Game game;
	public TestGameFactory(Game game)
	{
		this.game = game; 
	}
	
	AlphaGameFactory agf;
	BoardFactory bf;
	public TestGameFactory(Game g, BoardFactory bf) {
		agf = new AlphaGameFactory(g);
		this.bf = bf;
	}

	@Override
	public Board createBoard() {
		return new AlphaBoard(bf);
	}

	@Override
	public PlayerTurnStrategy createTurnStrategy() {
		return agf.createTurnStrategy();
	}

	@Override
	public MoveValidationStrategy createMoveValidationStrategy() {
		// TODO Auto-generated method stub
		return agf.createMoveValidationStrategy();
	}
}
