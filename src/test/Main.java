package test;

import net.eu5.juliendecharentenay.psychrometricsJava.Altitude;
import net.eu5.juliendecharentenay.psychrometricsJava.DewPointTemperature;
import net.eu5.juliendecharentenay.psychrometricsJava.DryBulbTemperature;
import net.eu5.juliendecharentenay.psychrometricsJava.PsychrometricsState;
import net.eu5.juliendecharentenay.psychrometricsJava.StandardPressure;
import net.eu5.juliendecharentenay.psychrometricsJava.WetBulbTemperature;

public class Main {

	public static void main(String[] args) {
		System.out.println("Testing psychrometricsJava");
		
		Altitude altitude = Altitude.makeFromMeter(100.0);
		System.out.println("Altitude: " + altitude.toMeter() + "m / " + altitude.toFeet() + "ft");

		StandardPressure staticPressure = StandardPressure.makeFromAltitude(altitude);
		System.out.println("Pressure: " + staticPressure.toKilopascal() + "kPa / " + staticPressure.toPoundsPerSquareInch() + "lbf/in2");
		
		PsychrometricsState state;
		try {
			state = new PsychrometricsState(DryBulbTemperature.makeFromCelsius(30.0),
								DewPointTemperature.makeFromCelsius(20.0),
								staticPressure);
			System.out.println("State: \n" + 
							"dbt: " + state.getDryBulbTemperature().toCelsius() + "C; \n" +
							"wbt: " + state.getWetBulbTemperature().toCelsius() + "C; \n" +
							"dpt: " + state.getDewPointTemperature().toCelsius() + "C; \n" +
							"w: " + state.getHumidityRatio().toKgKgdryair() + "kg/kg.da; \n" +
							"rh: " + state.getRelativeHumidity().toPercentage() + "%; \n" +
							"h: " + state.getEnthalpy().toKJKgdryair() + "KJ/kg.da");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
