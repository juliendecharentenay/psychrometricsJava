package net.eu5.juliendecharentenay.psychrometricsJava;

/**
 * Dry-bulb temperature class
 * @author juliendecharentenay
 *
 */
public class DryBulbTemperature extends Temperature {
	public DryBulbTemperature(Temperature t) { super(t); }
	/*
	 * Static make functions derived from super class
	 */
	public static DryBulbTemperature makeFromCelsius(Double value) { return new DryBulbTemperature(Temperature.makeFromCelsius(value)); }
	public static DryBulbTemperature makeFromFahrenheit(Double value) { return new DryBulbTemperature(Temperature.makeFromFahrenheit(value)); }
	public static DryBulbTemperature makeFromKelvin(Double value) { return new DryBulbTemperature(Temperature.makeFromKelvin(value)); }
}
