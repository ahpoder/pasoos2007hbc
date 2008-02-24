package dk.atisa.hs07.model;

import java.net.URL;

import dk.atisa.hs07.Service;

/**
 * 
 * Radiator service. A home may have a number of radiator services
 * 
 * @author Jesper Dalberg, jdalberg@daimi.au.dk
 *
 */
public class ModelService extends Service {
	public Object getController() {
		return new Model();
	}
	
	/**
	 * Construct a radiator service and register the service with the gateway
	 * as an observer
	 * 
	 * @param gatewayLocation
	 * @param thisLocation
	 * @throws Exception
	 */
	public ModelService(String thisLocation) throws Exception {
		super(thisLocation);
		URL url = new URL(thisLocation);
		System.out.println("Started model service at " + url);
	}

	public static void main(String[] args) throws Exception {
		URL baseUrl = new URL(args[0]);
		String location = "http://" + baseUrl.getHost() + ":" + baseUrl.getPort() + "/";
		new ModelService(location);
	}
}
