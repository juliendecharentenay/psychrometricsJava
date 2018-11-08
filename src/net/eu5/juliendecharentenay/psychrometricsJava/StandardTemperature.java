/**
 * 
 */
package net.eu5.juliendecharentenay.psychrometricsJava;

/**
 * @author juliendecharentenay
 *
 */
public class StandardTemperature extends Temperature {

	/**
	 * @param t
	 */
	public StandardTemperature(Temperature t) {super(t);}

	/*
	 * Static make functions derived from super class
	 */
	public static StandardTemperature makeFromCelsius(Double value) { return new StandardTemperature(Temperature.makeFromCelsius(value)); }
	public static StandardTemperature makeFromFahrenheit(Double value) { return new StandardTemperature(Temperature.makeFromFahrenheit(value)); }
	public static StandardTemperature makeFromKelvin(Double value) { return new StandardTemperature(Temperature.makeFromKelvin(value)); }

	/*
	 * Local functions
	 */
	public static StandardTemperature makeFromAltitude(Altitude z) {
		return StandardTemperature.makeFromCelsius(15.0 - 0.0065*z.toMeter());
	}
	public Altitude toAltitude() {
		return Altitude.makeFromMeter((15.0-toCelsius())/0.0065);
	}
}
