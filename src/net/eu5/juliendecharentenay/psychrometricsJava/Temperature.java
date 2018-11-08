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
	
	public static Temperature makeFromCelsius(Double value) { return new Temperature(value); }
	public Double toCelsius() {return _valueInC;}
	
	public static Temperature makeFromFahrenheit(Double value) {return new Temperature ((value - 32.0) * 5.0/9.0); }
	public Double toFahrenheit() {return _valueInC * 9.0/5.0 + 32.0;}
	
	public static Temperature makeFromKelvin(Double value) {return new Temperature(value - 273.15);}
	public Double toKelvin() {return _valueInC + 273.15; }
	
}
