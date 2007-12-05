package paystation.scenario;

import paystation.domain.*;
import paystation.gui.*;
import paystation.monitor.*;
import java.rmi.*;
import java.rmi.server.*;
import java.net.*;

public class Scenario1 {
  public static void main(String[] args) {
	if (System.getSecurityManager() == null) {
		System.setSecurityManager(new RMISecurityManager());
	}
    String host = args[1];
	int serial = Integer.parseInt(args[2]);

    System.out.println( "Scenario 1: Supervising 4 pay stations." );

	if (args[0].equals("PAYSTATION"))
	{
		PayStationGUI g1;

		g1 = new PayStationGUI(10,10);
		try
		{
			StatusObservable so = (StatusObservable)g1.getPayStation();
			Naming.rebind("//" + host + "/PayStation" + serial, so); 
		}
		catch (MalformedURLException mue)
		{
			System.out.println(mue);
		}
		catch (RemoteException re)
		{
			System.out.println(re);
		}

    }
	else
	{
		StatusFrame f1 = new StatusFrame(580,10);
		try
		{
		  StatusObservable obs = (StatusObservable)Naming.lookup("//" + host + "/PayStation" + serial);
		  obs.addStatusListener(f1.getStatusListener());
		}
		catch (RemoteException re)
		{
			System.out.println(re);
		}
		catch (MalformedURLException mue)
		{
			System.out.println(mue);
		}
		catch (NotBoundException nbe)
		{
			System.out.println(nbe);
		}
    }
	System.out.println( "Ready...");
  }
}

