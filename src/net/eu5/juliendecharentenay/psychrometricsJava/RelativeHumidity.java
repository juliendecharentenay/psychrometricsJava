/**
 * 
 */
package net.eu5.juliendecharentenay.psychrometricsJava;

/**
 * @author juliendecharentenay
 *
 */
public class RelativeHumidity {
	public static final String PERCENTAGE = "%";
	public static final String NORMALIZE = "";
	
	private Double _valueNormalized;
	
	private RelativeHumidity(Double valueNormalized) {_valueNormalized = valueNormalized;}
	protected RelativeHumidity(RelativeHumidity r) {_valueNormalized = r._valueNormalized;}
	
	public static RelativeHumidity makeFromPercentage(Double value) {return new RelativeHumidity(value/100.0);}
	public Double toPercentage() {return _valueNormalized*100.0;}

	public static RelativeHumidity makeFromNormalized(Double value) {return new RelativeHumidity(value);}
	public Double toNormalized() {return _valueNormalized;}
}
