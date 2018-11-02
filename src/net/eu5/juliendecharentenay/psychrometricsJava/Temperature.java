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
	
	public Temperature(Double valueInC) {_valueInC = valueInC;}
	public Temperature(Double value, String unit) {
		switch (unit) {
		case CELSIUS:
			_valueInC = makeFromCelsius(value);
			break;
		case FAHRENHEIT:
			_valueInC = makeFromFahrenheit(value);
			break;
		case KELVIN:
			_valueInC = makeFromKelvin(value);
			break;
		default:
			_valueInC = null;
			break;
		}
	}

	private static Double makeFromCelsius(Double value) { return value; }
	public Double toCelsius() {return _valueInC;}
	
	private static Double makeFromFahrenheit(Double value) {return (value - 32.0) * 5.0/9.0; }
	public Double toFahrenheit() {return _valueInC * 9.0/5.0 + 32.0;}
	
	private static Double makeFromKelvin(Double value) {return (value - 273.15);}
	public Double toKelvin() {return _valueInC + 273.15; }
	
}
