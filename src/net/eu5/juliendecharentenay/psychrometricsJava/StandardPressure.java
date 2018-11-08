/**
 * 
 */
package net.eu5.juliendecharentenay.psychrometricsJava;

/**
 * standard pressure
 * @author juliendecharentenay
 *
 */
public class StandardPressure extends Pressure {
	public StandardPressure(Pressure p) {super(p);}
	
	/*
	 * Static make functions derived from super class
	 */
	public static StandardPressure makeFromPascal(Double value) {return new StandardPressure(Pressure.makeFromPascal(value));}
	public static StandardPressure makeFromKilopascal(Double value) {return new StandardPressure(Pressure.makeFromKilopascal(value));}
	public static StandardPressure makeFromPoundsPerSquareInch(Double value) {return new StandardPressure(Pressure.makeFromPoundsPerSquareInch(value));}

	/*
	 * Local functions
	 */
	public static StandardPressure makeFromAltitude(Altitude altitude) {
		return makeFromKilopascal(101.325*Math.pow(1.0-2.25577e-5*altitude.toMeter(),5.2559));
	}
	
	public Altitude toAltitude() {
		return Altitude.makeFromMeter((Math.pow(toKilopascal()/101.325,-5.2559)-1.0)/2.25577e-5);
	}
}
