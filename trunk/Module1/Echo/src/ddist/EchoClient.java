package ddist;

import java.rmi.*;

public class EchoClient {
	public static void main(String[] args) throws Exception {
		if (System.getSecurityManager() == null) {
			System.setSecurityManager(new RMISecurityManager());
		}
		String host = "localhost";
		if (args.length > 0) {
			host = args[0];
		}
		
		System.out.println("Lookup echo service");
		Echo echo = (Echo) Naming.lookup("//" + host + "/EchoServer");
		System.out.println("Invoke to echo service");
		System.out.println(echo.echo("Hello, Echo!"));
	}
}
