package ddist;

import java.rmi.*;
import java.rmi.server.*;
import java.net.*;
import java.io.*;

public class EchoServer extends UnicastRemoteObject implements Echo {
	static final long serialVersionUID = -1L;
	int i = 0;
	
	public EchoServer() throws RemoteException {
		super();
	}
	
	public String echo(String message) throws RemoteException {
		message += " " + ++i;
		System.err.println("Echo: " + message);
		return message;
	}
	
	public static void main (String[] args) throws Exception {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		String host = "localhost";
		if (args.length > 0) {
			host = args[0];
		}
		System.out.println("Starting EchoServer");
		EchoServer echo = new EchoServer();
		System.out.println("//" + host + "/EchoServer");
		Naming.rebind("//" + host + "/EchoServer", echo); 
	}
}
