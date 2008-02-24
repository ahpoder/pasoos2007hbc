package dk.atisa.hs07.sensor;

public class RandomThermometerFactory implements SensorFactory {
	public Object createSensor() {
	    return new RandomThermometer();
	}
}
