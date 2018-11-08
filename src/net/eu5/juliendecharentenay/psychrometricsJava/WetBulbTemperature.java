/**
 * 
 */
package net.eu5.juliendecharentenay.psychrometricsJava;

/**
 * Wet-bulb temperature
 * @author juliendecharentenay
 *
 */
public class WetBulbTemperature extends Temperature {

	/**
	 * @param t
	 */
	public WetBulbTemperature(Temperature t) {
		super(t);
	}
	
	/*
	 * Static make functions derived from super class
	 */
	public static WetBulbTemperature makeFromCelsius(Double value) { return new WetBulbTemperature(Temperature.makeFromCelsius(value)); }
	public static WetBulbTemperature makeFromFahrenheit(Double value) { return new WetBulbTemperature(Temperature.makeFromFahrenheit(value)); }
	public static WetBulbTemperature makeFromKelvin(Double value) { return new WetBulbTemperature(Temperature.makeFromKelvin(value)); }

}
