package dk.atisa.hs07.sensor;

import java.net.URL;

import dk.atisa.hs07.Invoker;
import dk.atisa.hs07.Service;

/**
 * Thermometer service. A home may have a number of thermometer services
 * 
 * @author Klaus Marius Hansen, klaus.m.hansen@daimi.au.dk
 *
 */
public class ThermometerService extends Service {
	
	public Object getController() {
		return sensorFactory.createSensor();
	}
	
	/**
	 * Create a thermometer service and register with the gateway
	 * @param gatewayLocation
	 * @param thisLocation
	 * @throws Exception
	 */
	public ThermometerService(String gatewayLocation, String thisLocation, SensorFactory sf) throws Exception {
		URL url = new URL(thisLocation);
		sensorFactory = sf;
		start(thisLocation);
		Invoker.invoke(gatewayLocation, "registerThermometer", "location", url.toString());
		System.out.println("Started thermometer service at " + url);
	}

	public static void main(String[] args) throws Exception {
		URL baseUrl = new URL(args[1]);
		SensorFactory sf = new RandomThermometerFactory();
		for (int i = 0; i < Integer.parseInt(args[2]); i++) {
			String location = "http://" + baseUrl.getHost() + ":" + (baseUrl.getPort() + i) + "/";
			new ThermometerService(args[0], location, sf);
		}
	}
	private SensorFactory sensorFactory;
}
