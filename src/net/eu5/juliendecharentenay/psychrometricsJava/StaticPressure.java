/**
 * 
 */
package net.eu5.juliendecharentenay.psychrometricsJava;

/**
 * static pressure
 * @author juliendecharentenay
 *
 */
public class StaticPressure extends Pressure {
	public StaticPressure(Pressure p) {super(p);}
	
	public static Pressure makeFromAltitude(Altitude altitude) {
		return makeFromKilopascal(101.325*Math.pow(1.0-2.25577e-5*altitude.toMeter(),5.2559));
	}
	
	public Altitude toAltitude() {
		return new Altitude(Altitude.makeFromMeter((Math.pow(toKilopascal()/101.325,-5.2559)-1.0)/2.25577e-5));
	}
}
