
public class PredefinedDieRollStrategy implements DieRollStrategy {
	public boolean dieRoll()
	{
		// Dice rool is hard coded to [1,2], [3,2], [4,4], [3,2]
		return (++turnCount == 3);
	}
	private int turnCount = 0;
}
