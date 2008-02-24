package dk.atisa.hs07.model;

import java.io.IOException;
import java.net.MalformedURLException;

/**
 * The model of the system as seen from an external source
 * 
 * @author Jesper Dalberg, jdalberg@daimi.au.dk
 *
 */
public class Model {
	/* Target Temperature - as controlled by the user through the web interface */
	private double targetTemperature = 20.0;
	
	/* Store the average temperature for the web interface */
	private double avgTemperature = 0;
		
	/* externally accessable functions */

	/* Gets the current target temperature from the gateway */
	public double getTargetTemperature() throws MalformedURLException, IOException {
		System.out.println("getTargetTemperature: " + targetTemperature);
		return targetTemperature;
	}
	
	public double getAverageTemperature() throws MalformedURLException, IOException {
		System.out.println("getAverageTemperature: " + avgTemperature);
		return avgTemperature;
	}
	
	/* Only invoked from the gateway */
	public void setAverageTemperature( String _avgTemperature ) throws MalformedURLException, IOException {
		System.out.println("setAverageTemperature: "+_avgTemperature);
		avgTemperature = Double.parseDouble( _avgTemperature );
	}

	public void setTargetTemperature( String _targetTemperature ) throws MalformedURLException, IOException {
		System.out.println("setTargetTemperature: "+_targetTemperature);
		targetTemperature = Double.parseDouble(_targetTemperature);
	}
}
