/**
 * 
 */
package net.eu5.juliendecharentenay.psychrometricsJava;

/**
 * Length distance to allow conversion
 * @author juliendecharentenay
 *
 */
public class Length {
	public static final String METER = "m";
	public static final String MILLIMETER = "mm";
	public static final String FEET = "ft";

	private Double _valueInM;

	private Length(Double valueInM) { _valueInM = valueInM; }
	protected Length(Length l) {_valueInM = l._valueInM; }
	
	public static Length makeFromMeter(Double value) {return new Length(value);}
	public Double toMeter() {return _valueInM;}
	
	public static Length makeFromMillimeter(Double value) {return new Length(value/1000.0);}
	public Double toMillimeter() {return _valueInM * 1000.0;}
	
	public static Length makeFromFeet(Double value) {return new Length(value*0.3048); }
	public Double toFeet() {return _valueInM/0.3048;}
	
}
