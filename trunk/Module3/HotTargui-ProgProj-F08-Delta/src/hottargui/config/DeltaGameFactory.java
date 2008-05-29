package hottargui.config;

import hottargui.framework.*;

/**
 * The factory for creating the DeltaTargui game
 */
public class DeltaGameFactory implements GameFactory {

    private Game game;

    public DeltaGameFactory(Game game) {
        this.game = game;
    }

    public Board createBoard() {
        DeltaBoardFactory boardFactory = new DeltaBoardFactory();
        return new DeltaBoard(boardFactory);
    }

    public PlayerTurnStrategy createTurnStrategy() {
        return new SimpleTurnStrategy();
    }

    public MoveValidationStrategy createMoveValidationStrategy() {
        return new StandardMoveValidationStrategy(game);
    }

    public WinnerStrategy createWinnerStrategy() {
        return new DeltaWinnerStrategy(game);
    }
}
