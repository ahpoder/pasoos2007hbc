import java.util.ArrayList;

public class PredefinedRandomStrategy implements RandomStrategy {
	public PredefinedRandomStrategy()
	{
		values.add(1);
		values.add(2);
		values.add(3);
		values.add(2);
		values.add(4);
		values.add(4);
		values.add(3);
		values.add(2);
	}
	public int nextInt(int n)
	{
		// Dice rool is hard coded to [1,2], [3,2], [4,4], [3,2]
		int temp = values.get(0) - 1;
		values.remove(0);
		return temp;
	}
	private ArrayList<Integer> values = new ArrayList<Integer>();
}
