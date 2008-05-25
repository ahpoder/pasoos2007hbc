package hottargui.framework;

import java.rmi.Remote;

public interface Die extends Remote {
    public void rollDie();

	public void setValue(int val);

    public int getValue();
}