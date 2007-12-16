package paystation.scenario;

import paystation.domain.*;
import paystation.gui.*;
import paystation.monitor.*;
import java.rmi.*;
import java.rmi.server.*;
import java.net.*;
import java.util.StringTokenizer;

public class Scenario1 {
  public static void main(String[] args) {
	if (System.getSecurityManager() == null) {
		System.setSecurityManager(new RMISecurityManager());
	}
	String host = args[1];

    System.out.println( "Scenario 1: Supervising 4 pay stations." );

	if (args[0].equals("PAYSTATION"))
	{
		int serial = Integer.parseInt(args[2]);
		PayStationGUI g1;
		System.out.println("Creating Paystation GUI and exposing at: " + "//" + host + "/PayStation" + serial);

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
        String content = args[2].substring(1);
		content = content.substring(0, content.length() - 1);
		StatusFrame f1 = new StatusFrame(580,10);
		try
		{
		  StringTokenizer collection = new StringTokenizer(content, ";");
          System.out.println("Creating Paystation Monitor:");
		  while (collection.hasMoreTokens())
		  {
			int serial = Integer.parseInt(collection.nextToken());
			System.out.println("\tConnecting to: " + "//" + host + "/PayStation" + serial);
			StatusObservable obs = (StatusObservable)Naming.lookup("//" + host + "/PayStation" + serial);
			obs.addStatusListener(f1.getStatusListener());
		  }
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

