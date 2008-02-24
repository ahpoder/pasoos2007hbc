package dk.atisa.hs07.actuator;

/**
 * An HS07 radiator that may be turned on and off
 * 
 * @author Klaus Marius Hansen, klaus.m.hansen@daimi.au.dk
 *
 */
public class Radiator {
	/* Temperature spans a centered with .5 degrees variance */
	public static final double TEMPERATURE_VARIANCE = 0.5;

	private boolean state = false;
	
	/**
	 * Run the control algorithm upon notification of temperature change
	 * 
	 * @param _temperature
	 */
	public void notify(String _temperature, String _targetTemperature) {
		double currentTarget = Double.parseDouble(_targetTemperature);
		double temperature = Double.parseDouble(_temperature);
		if (temperature < currentTarget - TEMPERATURE_VARIANCE) {
			System.out.println("Turn on radiator");
			setState(true);
		} else if (temperature > currentTarget + TEMPERATURE_VARIANCE) {
			System.out.println("Turn off radiator");
			setState(false);
		} 
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public boolean getState() {
		return state;
	}
}
