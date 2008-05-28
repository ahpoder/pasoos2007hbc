package hottargui.config;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import hottargui.framework.Game;
import hottargui.framework.Position;

import java.net.MalformedURLException;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;

public class TestDomainServerTargui {
	
	public TestDomainServerTargui() throws RemoteException, MalformedURLException
	{
		System.out.println(System.getProperty("java.security.policy"));

        if (System.getSecurityManager() == null) {
	          System.setSecurityManager(new RMISecurityManager());
		}

		System.out.println();
	}

	public static void main(String[] args) {
		try {
			TestDomainServerTargui p = new TestDomainServerTargui();
			p.runTransitionTour();
			p.finalize();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Killing");
        System.exit(0);
	}
	
	protected void finalize() throws Throwable
	{
		System.out.println();
		System.out.println("Cleaning up");

		super.finalize(); //not necessary if extending Object.¨
	}
	  
	public void runTransitionTour() throws RemoteException
	{
		System.out.println("Beginning test");
		System.out.println();

		System.out.println("Test completed");
	}  
}
