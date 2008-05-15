package hottargui.config;

import hottargui.framework.*;

public class AlphaWinnerStrategy implements WinnerStrategy {
/*    private Board board;

    public AlphaWinnerStrategy(Board board) {
        this.board = board;
    }
*/	
	public AlphaWinnerStrategy() {
	}

	public PlayerColor getWinner(){
//			Tile saltMine = board.getTile(new Position(3, 3));
//      return saltMine.getOwnerColor();
		return PlayerColor.None;
	}
}
