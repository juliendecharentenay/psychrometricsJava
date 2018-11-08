/**
 * 
 */
package net.eu5.juliendecharentenay.psychrometricsJava;

/**
 * @author juliendecharentenay
 *
 */
public class HumidityRatio {
	public static final String KGPERKGDRYAIR = "kg/kg.da";
	
	private Double _valueInKgKgda;
	
	private HumidityRatio(Double valueInKgKgda) {_valueInKgKgda = valueInKgKgda;}
	protected HumidityRatio(HumidityRatio v) {_valueInKgKgda = v._valueInKgKgda;}
	
	public static HumidityRatio makeFromKgKgdryair(Double value) {return new HumidityRatio(value);}
	public Double toKgKgdryair() {return _valueInKgKgda;}

}
