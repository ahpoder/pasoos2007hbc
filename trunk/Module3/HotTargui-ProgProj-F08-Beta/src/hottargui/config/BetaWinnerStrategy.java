package hottargui.config;

import hottargui.framework.*;
import java.util.*;

public class BetaWinnerStrategy implements WinnerStrategy {
  private Game game;
  	
	public BetaWinnerStrategy(Game game) {
		this.game = game;
	}

	public PlayerColor getWinner(){
		// Get revenues for each player
		int redPlayer = getRevenue(PlayerColor.Red);
		int bluePlayer = getRevenue(PlayerColor.Blue);
		int greenPlayer = getRevenue(PlayerColor.Green);
		int yellowPlayer = getRevenue(PlayerColor.Yellow);
		
//    System.out.println("Revenues: Red = " + redPlayer + ", Blue = " + bluePlayer + ", Green = " + greenPlayer + ", Yellow = " + yellowPlayer);

		// Create a winner array and add the winners to it
		PlayerColor[] winners = new PlayerColor[4];
		int i = 0;
		// Is redPlayer a winner?
		if ((redPlayer >= bluePlayer) && (redPlayer >= greenPlayer) && (redPlayer >= yellowPlayer) ) {
			winners[i] = PlayerColor.Red;
			i++;
		}
		// Is bluePlayer a winner?
		if ((bluePlayer >= redPlayer) && (bluePlayer >= greenPlayer) && (bluePlayer >= yellowPlayer)) {
			winners[i] = PlayerColor.Blue;
			i++;
		}
		// Is greenPlayer a winner?
		if ((greenPlayer >= redPlayer) && (greenPlayer >= bluePlayer) && (greenPlayer >= yellowPlayer)) {
			winners[i] = PlayerColor.Green;
			i++;
		}
		// Is yellowPlayer a winner?
		if ((yellowPlayer >= redPlayer) && (yellowPlayer >= bluePlayer) && (yellowPlayer >= greenPlayer)) {
			winners[i] = PlayerColor.Yellow;
			i++;
		}
		
		//No Winner found
		if (winners[0] == null){
			return PlayerColor.None;
		}  
		//Only one winner found
		if ((winners[0] != null) && (winners[1] == null)){
			return winners[0];
		}
		//More winners found - the owner of the saltmine is the winner
		Tile saltMine = game.getTile(new Position(3, 3));
    return saltMine.getOwnerColor(); 
	}
	
	private int getRevenue( PlayerColor playerColor ) {

		int revenue = 0;
		Iterator<? extends Tile> i = game.getBoardIterator();

		while (i.hasNext()) {
			Tile t = i.next();
			if (t.getOwnerColor()==playerColor) {
				revenue += t.getEconomicValue();
			}
		}	   

		return revenue;
	}
}
