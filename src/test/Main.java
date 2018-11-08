package test;

import net.eu5.juliendecharentenay.psychrometricsJava.Altitude;
import net.eu5.juliendecharentenay.psychrometricsJava.StandardPressure;

public class Main {

	public static void main(String[] args) {
		System.out.println("Testing psychrometricsJava");
		
		Altitude altitude = Altitude.makeFromMeter(0.0);
		System.out.println("Altitude: " + altitude.toMeter() + "m / " + altitude.toFeet() + "ft");

		StandardPressure staticPressure = StandardPressure.makeFromAltitude(altitude);
		System.out.println("Pressure: " + staticPressure.toKilopascal() + "kPa / " + staticPressure.toPoundsPerSquareInch() + "lbf/in2");
	}

}
