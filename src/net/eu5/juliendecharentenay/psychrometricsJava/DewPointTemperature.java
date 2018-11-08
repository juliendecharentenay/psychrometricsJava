/**
 * 
 */
package net.eu5.juliendecharentenay.psychrometricsJava;

/**
 * Dew point temperature
 * @author juliendecharentenay
 *
 */
public class DewPointTemperature extends Temperature {

	/**
	 * @param t
	 */
	public DewPointTemperature(Temperature t) {
		super(t);
	}

	/*
	 * Static make functions derived from super class
	 */
	public static DewPointTemperature makeFromCelsius(Double value) { return new DewPointTemperature(Temperature.makeFromCelsius(value)); }
	public static DewPointTemperature makeFromFahrenheit(Double value) { return new DewPointTemperature(Temperature.makeFromFahrenheit(value)); }
	public static DewPointTemperature makeFromKelvin(Double value) { return new DewPointTemperature(Temperature.makeFromKelvin(value)); }
	
}
