package hottargui.config;

import hottargui.framework.*;

public class AlphaGameFactory implements GameFactory {
	private Game game;
	public AlphaGameFactory(Game g)
	{
		game = g;
	}
	
	public Board createBoard() {
		AlphaBoardFactory abf = new AlphaBoardFactory();
		return new AlphaBoard(abf);
	}

	public PlayerTurnStrategy createTurnStrategy() {
		return new SimpleTurnStrategy();
	}

	public MoveValidationStrategy createMoveValidationStrategy() {
		return new StandardMoveValidationStrategy(game);
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
