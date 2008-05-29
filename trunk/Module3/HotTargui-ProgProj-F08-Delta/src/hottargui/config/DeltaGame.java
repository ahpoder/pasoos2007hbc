package hottargui.config;

import hottargui.framework.*;
import hottargui.standard.StandardPlayer;
import hottargui.standard.StandardTile;

import java.util.*;

/**
 * DeltaGame implementation.
 * Presently simply a temporary test stub to be expanded
 * by a test-driven process.
 */

public class DeltaGame implements Game {

    /**
     * Strategy for moving
     */
    private MoveValidationStrategy moveValidationStrategy;

    /**
     * Strategy for turns
     */
    private PlayerTurnStrategy turnStrategy;

    /**
     * Define the strategy to use for initializing the board
     */
    private BoardLayoutStrategy boardLayoutStrategy;

    /**
     * Define the strategy used to determine winner
     */
    private WinnerStrategy winnerStrategy;

    /**
     * The Board to use
     */
    private Board board = null;

    /**
     * The Gamefactory to use
     */
    private DeltaGameFactory gameFactory;

    int roundsCompleted = 0;

    public DeltaGame() {
    }

    public void setGameFactory(DeltaGameFactory gameFactory) {
        this.gameFactory = gameFactory;
    }

    public void newGame() {
        board = gameFactory.createBoard();
        moveValidationStrategy = gameFactory.createMoveValidationStrategy();
        turnStrategy = gameFactory.createTurnStrategy();
        winnerStrategy = gameFactory.createWinnerStrategy();
    }

    public boolean move(Position from, Position to, int count) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public boolean buy(int count, Position deploy) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PlayerColor turnCard() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void rollDie() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public void addGameListener(GameListener observer) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public State getState() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public Iterator<? extends Tile> getBoardIterator() {
        return board.getBoardIterator();
    }

    public Tile getTile(Position p) {
        return board.getTile(p);
    }

    public Player getPlayerInTurn() {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getDieValue() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public PlayerColor getWinner() {
        return winnerStrategy.getWinner();
    }

}