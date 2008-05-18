package hottargui.strategy;

import java.util.*;

import hottargui.framework.*;

public class SimpleTurnStrategy implements PlayerTurnStrategy{
	public SimpleTurnStrategy()
	{
		players = new PlayerColor[4];
		players[0] = PlayerColor.Red;
		players[1] = PlayerColor.Green;
		players[2] = PlayerColor.Blue;
		players[3] = PlayerColor.Yellow;
	}
	
	int roundsCompleted = 0;
	public int getRoundCount() {
		return roundsCompleted;
	}

	int playerTurn = 0;
	PlayerColor[] players;
	public PlayerColor nextPlayer() {
		if (playerTurn >= players.length)
		{
			Iterator<RoundObserver> itt = observers.iterator();
			while (itt.hasNext())
			{
				itt.next().roundDone();
			}
			roundsCompleted = 0;
			playerTurn = 0;
		}
		PlayerColor currentTurn = players[playerTurn++];
		return currentTurn;
	}

	List<RoundObserver> observers = new ArrayList<RoundObserver>();
	public void addRoundDoneObserver(RoundObserver observer) {
		observers.add(observer);
	}

	public void removeRoundDoneObserver(RoundObserver observer) {
		observers.remove(observer);
	}
}



