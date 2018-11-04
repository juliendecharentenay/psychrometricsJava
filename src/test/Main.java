package test;

import net.eu5.juliendecharentenay.psychrometricsJava.Altitude;
import net.eu5.juliendecharentenay.psychrometricsJava.StaticPressure;

public class Main {

	public static void main(String[] args) {
		System.out.println("Testing psychrometricsJava");
		
		Altitude altitude = new Altitude(Altitude.makeFromMeter(0.0));
		System.out.println("Altitude: " + altitude.toMeter() + "m / " + altitude.toFeet() + "ft");

		StaticPressure staticPressure = new StaticPressure(StaticPressure.makeFromAltitude(altitude));
		System.out.println("Pressure: " + staticPressure.toKilopascal() + "kPa / " + staticPressure.toPoundsPerSquareInch() + "lbf/in2");
	}

}
