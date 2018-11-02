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
	 * @param valueInC
	 */
	public WetBulbTemperature(Double valueInC) {
		super(valueInC);
	}

	/**
	 * @param value
	 * @param unit
	 */
	public WetBulbTemperature(Double value, String unit) {
		super(value, unit);
	}

}
