/**
 * 
 */
package net.eu5.juliendecharentenay.psychrometricsJava;

/**
 * @author juliendecharentenay
 *
 */
public class Enthalpy {
	public static final String KJPERKGDRYAIR = "kJ/kg.da";
	
	private Double _valueInKJKgda;
	
	private Enthalpy(Double valueInKJKgda) {_valueInKJKgda = valueInKJKgda;}
	protected Enthalpy(Enthalpy h) {_valueInKJKgda = h._valueInKJKgda;}
	
	public static Enthalpy makeFromKJKgdryair(Double value) {return new Enthalpy(value);}
	public Double toKJKgdryair() {return _valueInKJKgda;}

}
