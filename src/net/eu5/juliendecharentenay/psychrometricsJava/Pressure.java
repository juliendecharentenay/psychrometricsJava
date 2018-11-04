/**
 * 
 */
package net.eu5.juliendecharentenay.psychrometricsJava;

/**
 * Define pressure
 * @author juliendecharentenay
 *
 */
public class Pressure {
	public static final String PASCAL = "Pa";
	public static final String KILOPASCAL = "kPa";
	public static final String POUNDSPERSQINCH = "lbf/in2";
	
	private Double _valueInPa;

	private Pressure(Double valueInPa) { _valueInPa = valueInPa; }
	protected Pressure(Pressure p) {_valueInPa = p._valueInPa; }
	
	public static Pressure makeFromPascal(Double value) {return new Pressure(value);}
	public Double toPascal() {return _valueInPa;}
	
	public static Pressure makeFromKilopascal(Double value) {return new Pressure(value*1000.0);}
	public Double toKilopascal() {return _valueInPa/1000.0;}

	public static Pressure makeFromPoundsPerSquareInch(Double value) {return new Pressure(value*6894.757293168);}
	public Double toPoundsPerSquareInch() {return _valueInPa/6894.757293168;}
}
