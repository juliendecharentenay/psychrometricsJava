/**
 * 
 */
package net.eu5.juliendecharentenay.psychrometricsJava;

/**
 * Generic temperature class
 * @author juliendecharentenay
 *
 */
public class Temperature {
	public static final String CELSIUS = "\u00b0C";
	public static final String FAHRENHEIT = "\u00b0F";
	public static final String KELVIN = "\u00b0K";

	private Double _valueInC;
	
	private Temperature(Double valueInC) {_valueInC = valueInC;}
	protected Temperature(Temperature t) {_valueInC = t._valueInC; }
	
	private static Temperature make(Double value, String unit) {
		switch (unit) {
		case CELSIUS:
			return makeFromCelsius(value);
		case FAHRENHEIT:
			return makeFromFahrenheit(value);
		case KELVIN:
			return makeFromKelvin(value);
		default:
			return null;
		}
	}

	private static Temperature makeFromCelsius(Double value) { return new Temperature(value); }
	public Double toCelsius() {return _valueInC;}
	
	private static Temperature makeFromFahrenheit(Double value) {return new Temperature ((value - 32.0) * 5.0/9.0); }
	public Double toFahrenheit() {return _valueInC * 9.0/5.0 + 32.0;}
	
	private static Temperature makeFromKelvin(Double value) {return new Temperature(value - 273.15);}
	public Double toKelvin() {return _valueInC + 273.15; }
	
}
