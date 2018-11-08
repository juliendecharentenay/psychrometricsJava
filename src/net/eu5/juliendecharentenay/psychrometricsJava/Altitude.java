/**
 * 
 */
package net.eu5.juliendecharentenay.psychrometricsJava;

/**
 * Altitude is a special kind of length
 * @author juliendecharentenay
 *
 */
public class Altitude extends Length {
	public Altitude(Length l) {super(l);}
	
	/*
	 * Static make functions derived from super class
	 */
	public static Altitude makeFromMeter(Double value) {return new Altitude(Length.makeFromMeter(value));}
	public static Altitude makeFromMillimeter(Double value) {return new Altitude(Length.makeFromMillimeter(value));}
	public static Altitude makeFromFeet(Double value) {return new Altitude(Length.makeFromFeet(value));}

}
