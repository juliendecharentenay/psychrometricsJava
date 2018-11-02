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
	 * @param valueInC
	 */
	public DewPointTemperature(Double valueInC) {
		super(valueInC);
	}

	/**
	 * @param value
	 * @param unit
	 */
	public DewPointTemperature(Double value, String unit) {
		super(value, unit);
	}

}
