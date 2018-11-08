/**
 * 
 */
package net.eu5.juliendecharentenay.psychrometricsJava;

/**
 * Define a psychrometrics state
 * @author juliendecharentenay
 *
 */
public class PsychrometricsState {
	private DryBulbTemperature _dbt = null;
	private DewPointTemperature _dpt = null;
	private Enthalpy _h = null;
	private HumidityRatio _hr = null;
	private RelativeHumidity _rh = null;
	private StandardPressure _p = null;
	private WetBulbTemperature _wbt = null;
	
	public PsychrometricsState(StandardPressure p, DryBulbTemperature dbt, WetBulbTemperature wbt) { this(dbt, wbt, p); }
	public PsychrometricsState(StandardPressure p, WetBulbTemperature wbt, DryBulbTemperature dbt) { this(dbt, wbt, p); }
	public PsychrometricsState(WetBulbTemperature wbt, DryBulbTemperature dbt, StandardPressure p) { this(dbt, wbt, p); }
	public PsychrometricsState(DryBulbTemperature dbt, WetBulbTemperature wbt, StandardPressure p) {
		_dbt = dbt; _wbt = wbt; _p = p;
	}
	
	public DryBulbTemperature getDryBulbTemperature() {
		return _dbt;
	}
	
	public DewPointTemperature getDewPointTemperature() {
		if (_dpt == null) {
			_dpt = DewPointTemperature.makeFromCelsius(PsychrometricsState.getHumidityRatioFromDbtWbt(_dbt.toCelsius(), wbt.toCelsius(), p.toPascal()));
		}
		return _dpt;
	}
	
	public Enthalpy getEnthalpy() {
		if (_h == null) {
			_h = Enthalpy.makeFromKJKgdryair(PsychrometricsState.getEnthalpy(_dbt.toCelsius(), getHumidityRatio().toKgKgdryair()));
		}
		return _h;
	}
	
	public HumidityRatio getHumidityRatio() {
		if (_hr == null) {
			_hr = HumidityRatio.makeFromKgKgdryair(PsychrometricsState.getHumidityRatioFromDbtWbt(_dbt.toCelsius(), _wbt.toCelsius(), _p.toPascal()));
		}
		return _hr;
	}
	
	public RelativeHumidity getRelativeHumidity() {
		if (_rh == null) {
			_rh = RelativeHumidity.makeFromNormalized(PsychrometricsState.getRelativeHumidityFromDbtWbt(_dbt.toCelsius(), _wbt.toCelsius(), _p.toPascal()));			
		}
		return _rh;
	}
	
	public StandardPressure getStandardPressure() {
		return _p;
	}
	
	public WetBulbTemperature getWetBulbTemperature() {
		return _wbt;
	}

	private static final Double RDA = 287.042; // Gas constant for dry-air, J/kgda.K
	private static final Double RW = 461.524;  // Gas constant for water, J/kgw.K
	private static final Double G = 9.80665;   // Gravity constant, m/s2
	private static final Double TMIN = -100.0; // Minimum temperature
	private static final Double TMAX = 200.0;  // Maximum temperature
	private static final Double WMIN = 1e-5;   // Minimum humidity ratio
	

	/**
	 * Calculate Water vapor saturation pressure over ice and liquid water
	 * Uses ASHRAE Handbook Fundamentals 2013 
	 * Chapter 1 Psycrhometrics: Equations (5) and (6)
	 *
	 * Input:
	 *   t: temperature in Celsius
	 * return 
	 *   p: in pascal
	 */
	private static Double getWaterVaporSaturationPressure(Double t) {
	  if ((t < TMIN) || (t > TMAX)) {
	    throw new Exception("Water vaport saturation pressure only valid for temperature between -100.0 and +200.0 Celsius. Temperature provided is: " + t);
	  }

	  // Constants for -100 to 0 (over ice)
	  Double c1 = -5.6745359e+03;
	  Double c2 = 6.3925247;
	  Double c3 = -9.6778430e-3;
	  Double c4 = 6.2215701e-7;
	  Double c5 = 2.0747825e-09;
	  Double c6 = -9.4840240e-13;
	  Double c7 = 4.1635019e00;
	  // Constants for 0 to 200 (over liquid)
	  Double c8 = -5.8002206e3;
	  Double c9 = 1.3914993;
	  Double c10 = -4.8640239e-2;
	  Double c11 = 4.1764768e-5;
	  Double c12 = -1.4452093e-8;
	  Double c13 = 6.5459673e0;

	  Double tk = Temperature.makeFromCelsius(t).toKelvin();
	  return (t < 0.0) ? Math.pow(Math.E, c1/tk + c2 + c3*tk + c4*Math.pow(tk,2) + c5*Math.pow(tk,3) + c6*Math.pow(tk,4) + c7*Math.log(tk)) : Math.pow(Math.E, c8/tk + c9 + c10*tk + c11*Math.pow(tk,2) + c12*Math.pow(tk,3) + c13*Math.log(tk));
	};

	/**
	 * Calculate humidity rato
	 * Uses ASHRAE Handbook Fundamentals 2013 
	 * Chapter 1 Psycrhometrics: Equations (22/23)
	 *
	 * Input:
	 *   p: air pressure in Pa
	 *   pw: water vapor pressure in Pa
	 * return 
	 *   W: in kg water / kg dry-air
	 */
	private static Double getHumidityRatio(Double p, Double pw) {
	  return 0.621945*pw/(p-pw);
	};

	/**
	 * Calculate humidity rato from dry-bulb, wet-bulb temperature and pressure
	 * Uses ASHRAE Handbook Fundamentals 2013 
	 * Chapter 1 Psycrhometrics: Equations (35/36)
	 *
	 * input:
	 *  dbt: dry-bulb temperature (C)
	 *  wbt: wet-bulb temperature (C)
	 *  p: pressure (Pa)
	 * return 
	 *   W: in kg water / kg dry-air
	 */
	private static Double getHumidityRatioFromDbtWbt(Double dbt, Double wbt, Double p) {
	  Double pws_star = getWaterVaporSaturationPressure(wbt);
	  Double ws_star = getHumidityRatio(p, pws_star);
	  Double w = null;
	  if (dbt >= 0.0) {
	    w = ((2501.0 - 2.326*wbt)*ws_star - 1.006*(dbt-wbt))/(2501.0+1.86*dbt-4.186*wbt);
	  } else {
	    w = ((2830.0 - 0.24*wbt)*ws_star - 1.006*(dbt-wbt))/(2830.0+1.86*dbt-2.1*wbt);
	  }
	  return (w >= WMIN) ? w : WMIN;
	};

	/**
	 * Calculate relative humidity from wet-bulb and dry-bulb temperature
	 * Uses ASHRAE Handbook Fundamentals 2013 
	 *
	 * Input:
	 *  dbt: dry-bulb temperature (C)
	 *  wbt: wet-bulb temperature (C)
	 *  p: pressure (Pa)
	 * return:
	 *   relative humidity: between 0 and 1
	 */
	private static Double getRelativeHumidityFromDbtWbt(Double dbt, Double wbt, Double p) {
	  Double w = getHumidityRatioFromDbtWbt(dbt, wbt, p); 
	  return getRelativeHumidityFromDbtW(dbt, w, p);
	};

	/**
	 * Calculate relative humidity from dry-bulb temperature and humidity ratio
	 * Uses ASHRAE Handbook Fundamentals 2013 
	 * Chapter 1 Psycrhometrics: Equations (5), (6), (22), (23), (12), (25)
	 *
	 * Input:
	 *  dbt: dry-bulb temperature (C)
	 *  w: humidity ratio (kg/kgda)
	 *  p: pressure (Pa)
	 * return:
	 *   relative humidity: between 0 and 1
	 */
	private static Double getRelativeHumidityFromDbtW(Double dbt, Double w, Double p) {
	  Double pws = getWaterVaporSaturationPressure(dbt);  // eq (5) and (6)
	  Double ws = getHumidityRatio(p, pws);               // eq (22)
	  Double mu = w/ws;                                   // eq (12)
	  return mu / (1.0 - (1.0 - mu)*(pws/p));             // eq (25)
	};

	/**
	 * Calculate dew-point temperature from humidity ratio and pressure
	 * Uses ASHRAE Handbook Fundamentals 2013 
	 * Chapter 1 Psychrometrics: Equations (39/40)
	 *
	 * Input:
	 *  w: humidity ratio (kg/kgda)
	 *  p: pressure (Pa)
	 * return 
	 *  dpt: temperature (C)
	 */
	private static Double getDewPointTemperature(Double w, Double p) {
	  Double pw = p/1000.0*w/(0.621945+w);
	  Double alpha = Math.log(pw);
	  Double c14 = 6.54;
	  Double c15 = 14.526;
	  Double c16 = 0.7389;
	  Double c17 = 0.09486;
	  Double c18 = 0.4569;
	  Double dpt = c14+alpha*(c15+alpha*(c16+alpha*c17))+c18*Math.pow(pw, 0.1984);
	  dpt = (dpt >= 0.0) ? dpt : 6.09 + alpha*(12.608 + 0.4959*alpha);
	  return dpt;
	};

	/**
	 * Calculate humidity ratio from dew-point temperature ratio and pressure
	 * Uses the characteristic that at dew-point, the dew-point temperature is the 
	 * same as the wet-bulb and dry-bulb temperature.
	 *
	 * Input:
	 *  dpt: temperature (C)
	 *  p: pressure (Pa)
	 * return 
	 *  w: humidity ratio (kg/kgda)
	 */
	private static Double getHumidityRatioFromDpt(Double dpt, Double p) {
	  return getHumidityRatioFromDbtWbt(dpt, dpt, p);
	};

	/**
	 * Calculate enthalpy from dry-bulb temperature and humidity ratio
	 * Uses ASHRAE Handbook Fundamentals 2013 
	 * Chapter 1 Psycrhometrics: Equations (32)
	 *
	 * Input:
	 *  dbt: dry-bulb temperature (C)
	 *  w: humidity ratio (kg/kgda)
	 * return 
	 *  h: enthalpy (kJ/kgda)
	 */
	private static Double getEnthalpy(Double dbt, Double w) {
	  return 1.006*dbt + w*(2501.0 + 1.86*dbt);
	};

	/**
	 * Calculate dry-bulb temperature from humidity ratio and enthalpy
	 * Uses ASHRAE Handbook Fundamentals 2013 
	 * Chapter 1 Psycrhometrics: Equations (32)
	 *
	 * Input:
	 *  h: enthalpy (kJ/kgda)
	 *  w: humidity ratio (kg/kgda)
	 * return 
	 *  dbt: dry-bulb temperature (C)
	 */
	private static Double getDryBulbTemperatureFromHW(Double h, Double w) {
	  return (h - w*2501.0)/(1.006 + w*1.86);
	};

	/**
	 * Calculate humidity ratio from dry-bulb temperature and enthalpy
	 * Uses ASHRAE Handbook Fundamentals 2013 
	 * Chapter 1 Psycrhometrics: Equations (32)
	 *
	 * Input:
	 *  dbt: dry-bulb temperature (C)
	 *  h: enthalpy (kJ/kgda)
	 * return 
	 *  w: humidity ratio (kg/kgda)
	 */
	private static Double getHumidityRatioFromDbtH(Double dbt, Double h) {
	  return (h - 1.006*dbt)/(2501.0 + 1.86*dbt);
	};

	/**
	 * Calculate wet-bulb temperature from dry-bulb temperature, humidity ratio and pressure
	 * Uses numerical solver
	 *
	 * Input:
	 *  dbt: dry-bulb temperature (C)
	 *  w: humidity ratio (kg/kgda)
	 *  p: pressure (Pa)
	 * return 
	 *  wbt: wet-bulb temperature (C)
	 */
	private static Double getWetBulbTemperatureFromDbtWP(Double dbt, Double w, Double p) {
	  return PsychrometricsJS.solveBisection(PsychrometricsJS.TMIN, dbt, w, 1e-8,
	                        function(wbt) {
	                          return PsychrometricsJS.SI.getHumidityRatioFromDbtWbt(this.dbt, wbt, this.p);
	                        }.bind({'dbt': dbt, 'p': p}));
	};

	/**
	 * Calculate dry-bulb temperature from wet-bulb temperature, relative humidity and pressure
	 * Uses numerical solver
	 *
	 * Input:
	 *  wbt: wet-bulb temperature (C)
	 *  rh: relative humidity (0-1)
	 *  p: pressure (Pa)
	 * return 
	 *  dbt: dry-bulb temperature (C)
	 */
	private static Double getDryBulbTemperatureFromWbtRhP(Double wbt, Double rh, Double p) {
	  return PsychrometricsJS.solveGradient(PsychrometricsJS.TMIN, PsychrometricsJS.TMAX, wbt, rh, 0.1, 1e-8,
	                        function(dbt) {
	                          return PsychrometricsJS.SI.getRelativeHumidityFromDbtWbt(dbt, this.wbt, this.p);
	                        }.bind({'wbt': wbt, 'p': p}));
	};

	/**
	 * Calculate wet-bulb temperature from dry-bulb temperature, relative humidity and pressure
	 * Uses numerical solver
	 *
	 * Input:
	 *  dbt: dry-bulb temperature (C)
	 *  rh: relative humidity (0-1)
	 *  p: pressure (Pa)
	 * return 
	 *  wbt: wet-bulb temperature (C)
	 */
	private static Double getWetBulbTemperatureFromDbtRhP(Double dbt, Double rh, Double p) {
	  return PsychrometricsJS.solveBisection(PsychrometricsJS.TMIN, dbt, rh, 1e-8,
	                        function(wbt) {
	                          return PsychrometricsJS.SI.getRelativeHumidityFromDbtWbt(this.dbt, wbt, this.p);
	                        }.bind({'dbt': dbt, 'p': p}));
	};

	/**
	 * Numerical Solver: Using bisection method
	 */
	PsychrometricsJS.solveBisection = function(vmin, vmax, target, err, func) {
	  var fmin = func(vmin); var fmax = func(vmax);
	  if ((target - fmin)*(target-fmax) > 0) { throw "Bisection solver error: target value " + target + " is not within interval vmin|vmax [" + fmin + ";" + fmax + "]. Function may not be monotonous"; }
	  if (Math.abs(fmin - target) <= err) { return vmin; }
	  if (Math.abs(fmax - target) <= err) { return vmax; }

	  var v = 0.5*(vmin+vmax); var f = func(v);
	  while (Math.abs(f-target)>err) {
	    if ((target-f)*(target-fmin) < 0) { // Target is between vmin and v. Update vmax/fmax
	      vmax = v; fmax = f;
	    } else if ((target-f)*(target-fmax) < 0) { // Target is between v and vmax. Update vmin/fmin
	      vmin = v; fmin = f;
	    } else {
	      throw "Bisection solver error: Can find updated interval";
	    }
	    v = 0.5*(vmin+vmax); f = func(v);
	  };
	  return v;
	};

	/**
	 * Numerical Solver: Using gradient method
	 */
	PsychrometricsJS.solveGradient = function(vmin, vmax, vInit, target, delta, err, func) {
	  var v = vInit; var f = func(v); var it=0; var f1 = null; var alpha=0.3;
	  while ((Math.abs(f-target)>err) && (it < 100)) {
	    f1 = func(v+delta);
	    if (Math.abs(f1-f)<1e-8) {throw "Gradient solver: Increase delta to get more meaningfull answer";}
	    v = v + alpha*(target-f)/(f1-f)*delta;
	    v = (v < vmin) ? vmin : ((v > vmax) ? vmax : v);
	    f = func(v);
	    ++it;
	  }
	  if (Math.abs(f-target)>err) {throw "Failed to converge gradient solver";}
	  return v;
	};

}
