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
	private StandardPressure _p = null;

	private DryBulbTemperature _dbt = null;
	private DewPointTemperature _dpt = null;
	private Enthalpy _h = null;
	private HumidityRatio _hr = null;
	private RelativeHumidity _rh = null;
	private WetBulbTemperature _wbt = null;

	/** These 2 are using a time marching algorithm */
	public PsychrometricsState(Enthalpy h, RelativeHumidity rh,  StandardPressure p) throws Exception { }

	/** These 2 are using a time marching algorithm */
	public PsychrometricsState(HumidityRatio hr, RelativeHumidity rh,  StandardPressure p) throws Exception { }

	public PsychrometricsState(StandardPressure p,  RelativeHumidity rh, DewPointTemperature dpt) throws Exception { this(dpt, rh, p); }
	public PsychrometricsState(StandardPressure p, DewPointTemperature dpt,  RelativeHumidity rh) throws Exception { this(dpt, rh, p); }
	public PsychrometricsState(RelativeHumidity rh, DewPointTemperature dpt, StandardPressure p) throws Exception { this(dpt, rh, p); }
	public PsychrometricsState(DewPointTemperature dpt, RelativeHumidity rh, StandardPressure p) throws Exception { 
		this(HumidityRatio.makeFromKgKgdryair(PsychrometricsState.getHumidityRatioFromDpt(dpt.toCelsius(), p.toPascal())),
				rh, p);
		_dpt = dpt;
	}	
	
	public PsychrometricsState(StandardPressure p, WetBulbTemperature wbt, HumidityRatio hr) throws Exception { this(hr, wbt, p); } 
	public PsychrometricsState(StandardPressure p, HumidityRatio hr, WetBulbTemperature wbt) throws Exception { this(hr, wbt, p); } 
	public PsychrometricsState(WetBulbTemperature wbt, HumidityRatio hr, StandardPressure p) throws Exception { this(hr, wbt, p); } 
	public PsychrometricsState(HumidityRatio hr, WetBulbTemperature wbt, StandardPressure p) throws Exception { 
		this(DewPointTemperature.makeFromCelsius(PsychrometricsState.getDewPointTemperature(hr.toKgKgdryair(), p.toPascal())), wbt, p);
		_hr = hr;
	}
	
	public PsychrometricsState(StandardPressure p, DewPointTemperature dpt, WetBulbTemperature wbt) throws Exception { this (dpt, wbt, p); }
	public PsychrometricsState(StandardPressure p, WetBulbTemperature wbt, DewPointTemperature dpt) throws Exception { this (dpt, wbt, p); }
	public PsychrometricsState(WetBulbTemperature wbt, DewPointTemperature dpt, StandardPressure p) throws Exception { this (dpt, wbt, p); }
	public PsychrometricsState(DewPointTemperature dpt,  WetBulbTemperature wbt, StandardPressure p) throws Exception { 
		this(DryBulbTemperature.makeFromCelsius(PsychrometricsState.getDryBulbTemperatureFromWbtDptP(wbt.toCelsius(), dpt.toCelsius(), p.toPascal())), wbt, p);
		_dpt = dpt;
	}	
	
	public PsychrometricsState(StandardPressure p, DryBulbTemperature dbt, Enthalpy h) throws Exception { this(dbt, h, p); }
	public PsychrometricsState(StandardPressure p, Enthalpy h, DryBulbTemperature dbt) throws Exception { this(dbt, h, p); }
	public PsychrometricsState(Enthalpy h, DryBulbTemperature dbt, StandardPressure p) throws Exception { this(dbt, h, p); }
	public PsychrometricsState(DryBulbTemperature dbt,  Enthalpy h, StandardPressure p) throws Exception { 
		this(dbt, HumidityRatio.makeFromKgKgdryair(PsychrometricsState.getHumidityRatioFromDbtH(dbt.toCelsius(), h.toKJKgdryair())), p);
		_h = h;
	}

	public PsychrometricsState(StandardPressure p, Enthalpy h, DewPointTemperature dpt) throws Exception { this(dpt, h, p); }
	public PsychrometricsState(StandardPressure p, DewPointTemperature dpt, Enthalpy h) throws Exception { this(dpt, h, p); }
	public PsychrometricsState(Enthalpy h, DewPointTemperature dpt,  StandardPressure p) throws Exception { this(dpt, h, p); }
	public PsychrometricsState(DewPointTemperature dpt,  Enthalpy h, StandardPressure p) throws Exception { 
		this(DryBulbTemperature.makeFromCelsius(
					PsychrometricsState.getDryBulbTemperatureFromHW(h.toKJKgdryair(), 
											PsychrometricsState.getHumidityRatioFromDpt(dpt.toCelsius(), p.toPascal()))),
				h, 
				p);
		_dpt = dpt;
	}


	public PsychrometricsState(StandardPressure p, HumidityRatio hr,  Enthalpy h) throws Exception { this(h, hr, p); }
	public PsychrometricsState(StandardPressure p,  Enthalpy h, HumidityRatio hr) throws Exception { this(h, hr, p); }
	public PsychrometricsState(HumidityRatio hr,  Enthalpy h, StandardPressure p) throws Exception { this(h, hr, p); }
	public PsychrometricsState(Enthalpy h, HumidityRatio hr,  StandardPressure p) throws Exception { 
		this(DryBulbTemperature.makeFromCelsius(PsychrometricsState.getDryBulbTemperatureFromHW(h.toKJKgdryair(), hr.toKgKgdryair())), hr, p);
		_h = h;
	}
	
	
	public PsychrometricsState(StandardPressure p, DryBulbTemperature dbt, WetBulbTemperature wbt) throws Exception { this(dbt, wbt, p); }
	public PsychrometricsState(StandardPressure p, WetBulbTemperature wbt, DryBulbTemperature dbt) throws Exception { this(dbt, wbt, p); }
	public PsychrometricsState(WetBulbTemperature wbt, DryBulbTemperature dbt, StandardPressure p) throws Exception { this(dbt, wbt, p); }
	public PsychrometricsState(DryBulbTemperature dbt, WetBulbTemperature wbt, StandardPressure p) throws Exception {
		_dbt = dbt; _wbt = wbt; _p = p;
	}
	
	public PsychrometricsState(StandardPressure p, DryBulbTemperature dbt, RelativeHumidity rh) throws Exception { this(dbt, rh, p); }
	public PsychrometricsState(StandardPressure p, RelativeHumidity rh, DryBulbTemperature dbt) throws Exception { this(dbt, rh, p); }
	public PsychrometricsState(RelativeHumidity rh, DryBulbTemperature dbt, StandardPressure p) throws Exception { this(dbt, rh, p); }
	public PsychrometricsState(DryBulbTemperature dbt, RelativeHumidity rh, StandardPressure p) throws Exception {
		_dbt = dbt; _rh = rh; _p = p;
		_wbt = WetBulbTemperature.makeFromCelsius(getWetBulbTemperatureFromDbtRhP(_dbt.toCelsius(), _rh.toNormalized(), _p.toPascal()));
	}
	
	public PsychrometricsState(StandardPressure p, DryBulbTemperature dbt, HumidityRatio hr) throws Exception { this(dbt, hr, p); }
	public PsychrometricsState(StandardPressure p, HumidityRatio hr, DryBulbTemperature dbt) throws Exception { this(dbt, hr, p); }
	public PsychrometricsState(HumidityRatio hr, DryBulbTemperature dbt, StandardPressure p) throws Exception { this(dbt, hr, p); }
	public PsychrometricsState(DryBulbTemperature dbt, HumidityRatio hr, StandardPressure p) throws Exception {
		_dbt = dbt; _hr = hr; _p = p;
		_wbt = WetBulbTemperature.makeFromCelsius(getWetBulbTemperatureFromDbtWP(_dbt.toCelsius(), _hr.toKgKgdryair(), _p.toPascal()));
	}

	public PsychrometricsState(StandardPressure p, WetBulbTemperature wbt, RelativeHumidity rh) throws Exception { this(wbt, rh, p); }
	public PsychrometricsState(StandardPressure p, RelativeHumidity rh, WetBulbTemperature wbt) throws Exception { this(wbt, rh, p); }
	public PsychrometricsState(RelativeHumidity rh, WetBulbTemperature wbt, StandardPressure p) throws Exception { this(wbt, rh, p); }
	public PsychrometricsState(WetBulbTemperature wbt, RelativeHumidity rh, StandardPressure p) throws Exception {
		_wbt = wbt; _rh = rh; _p = p;
		_dbt = DryBulbTemperature.makeFromCelsius(getDryBulbTemperatureFromWbtRhP(_wbt.toCelsius(), _rh.toNormalized(), _p.toPascal()));
	}
	
	public PsychrometricsState(StandardPressure p, DryBulbTemperature dbt, DewPointTemperature dpt) throws Exception { this(dbt, dpt, p); }
	public PsychrometricsState(StandardPressure p,  DewPointTemperature dpt, DryBulbTemperature dbt) throws Exception { this(dbt, dpt, p); }
	public PsychrometricsState(DewPointTemperature dpt, DryBulbTemperature dbt, StandardPressure p) throws Exception { this(dbt, dpt, p); }
	public PsychrometricsState(DryBulbTemperature dbt,  DewPointTemperature dpt, StandardPressure p) throws Exception {
		this(dbt, HumidityRatio.makeFromKgKgdryair(getHumidityRatioFromDpt(dpt.toCelsius(), p.toPascal())), p);
		_dpt = dpt;
	}


	
	public DryBulbTemperature getDryBulbTemperature() {
		return _dbt;
	}
	
	public DewPointTemperature getDewPointTemperature() {
		try {
			if (_dpt == null) {
				_dpt = DewPointTemperature.makeFromCelsius(PsychrometricsState.getDewPointTemperature(PsychrometricsState.getHumidityRatioFromDbtWbt(_dbt.toCelsius(), _wbt.toCelsius(), _p.toPascal()), _p.toPascal()));
			}
			return _dpt;
		} catch (Exception e) {			
		}
		return null;
	}
	
	public Enthalpy getEnthalpy() {
		try {
			if (_h == null) {
				_h = Enthalpy.makeFromKJKgdryair(PsychrometricsState.getEnthalpy(_dbt.toCelsius(), getHumidityRatio().toKgKgdryair()));
			}
			return _h;
		} catch (Exception e) {			
		}
		return null;
	}
	
	public HumidityRatio getHumidityRatio() {
		try {
			if (_hr == null) {
				_hr = HumidityRatio.makeFromKgKgdryair(PsychrometricsState.getHumidityRatioFromDbtWbt(_dbt.toCelsius(), _wbt.toCelsius(), _p.toPascal()));
			}
			return _hr;
		} catch (Exception e) {			
		}
		return null;
	}
	
	public RelativeHumidity getRelativeHumidity() {
		try {
			if (_rh == null) {
				_rh = RelativeHumidity.makeFromNormalized(PsychrometricsState.getRelativeHumidityFromDbtWbt(_dbt.toCelsius(), _wbt.toCelsius(), _p.toPascal()));			
			}
			return _rh;
		} catch (Exception e) {			
		}
		return null;
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
	 * @throws Exception 
	 */
	private static Double getWaterVaporSaturationPressure(Double t) throws Exception {
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
	private static Double getHumidityRatio(Double p, Double pw) throws Exception {
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
	 * @throws Exception 
	 */
	private static Double getHumidityRatioFromDbtWbt(Double dbt, Double wbt, Double p) throws Exception {
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
	private static Double getRelativeHumidityFromDbtWbt(Double dbt, Double wbt, Double p) throws Exception {
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
	private static Double getRelativeHumidityFromDbtW(Double dbt, Double w, Double p) throws Exception {
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
	private static Double getDewPointTemperature(Double w, Double p) throws Exception {
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
	private static Double getHumidityRatioFromDpt(Double dpt, Double p) throws Exception {
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
	private static Double getEnthalpy(Double dbt, Double w) throws Exception {
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
	private static Double getDryBulbTemperatureFromHW(Double h, Double w) throws Exception {
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
	private static Double getHumidityRatioFromDbtH(Double dbt, Double h) throws Exception {
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
	private static class GetWetBulbTemperatureFromDbtWPFunctionObject implements Solver.FunctionObject {
		private Double _dbt;
		private Double _p;
		public GetWetBulbTemperatureFromDbtWPFunctionObject(Double dbt, Double p) {
			_dbt = dbt; _p = p;
		}
		
		@Override
		public Double evaluate(Double v) throws Exception {
			return getHumidityRatioFromDbtWbt(_dbt, v, _p);
		}
	}
	private static Double getWetBulbTemperatureFromDbtWP(Double dbt, Double w, Double p) throws Exception {
	  return Solver.INSTANCE.bisection(TMIN, dbt, w, 1e-8,
			  new GetWetBulbTemperatureFromDbtWPFunctionObject(dbt, p));
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
	private static class GetDryBulbTemperatureFromWbtRhPFunctionObject implements Solver.FunctionObject {
		private Double _wbt;
		private Double _p;
		public GetDryBulbTemperatureFromWbtRhPFunctionObject(Double wbt, Double p) {
			_wbt = wbt; _p = p;
		}
		
		@Override
		public Double evaluate(Double v) throws Exception {
			return getRelativeHumidityFromDbtWbt(v, _wbt, _p);
		}
	}
	private static Double getDryBulbTemperatureFromWbtRhP(Double wbt, Double rh, Double p) throws Exception {
	  return Solver.INSTANCE.gradient(TMIN, TMAX, wbt, rh, 0.1, 1e-8,
			  new GetDryBulbTemperatureFromWbtRhPFunctionObject(wbt, p));
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
	private static class GetWetBulbTemperatureFromDbtRhPFunctionObject implements Solver.FunctionObject {
		private Double _dbt;
		private Double _p;
		public GetWetBulbTemperatureFromDbtRhPFunctionObject(Double dbt, Double p) {
			_dbt = dbt; _p = p;
		}

		@Override
		public Double evaluate(Double v) throws Exception {
			return getRelativeHumidityFromDbtWbt(_dbt, v, _p);
		}		
	}
	private static Double getWetBulbTemperatureFromDbtRhP(Double dbt, Double rh, Double p) throws Exception {
	  return Solver.INSTANCE.bisection(TMIN, dbt, rh, 1e-8,
			  new GetWetBulbTemperatureFromDbtRhPFunctionObject(dbt, p));
	};

	/**
	 * Calculate dry-bulb temperature from wet-bulb temperature, dew-point temperature and pressure
	 * Uses numerical solver
	 *
	 * Input:
	 *  wbt: dry-bulb temperature (C)
	 *  dpt: dew point temperature (C)
	 *  p: pressure (Pa)
	 * return 
	 *  dbt: wet-bulb temperature (C)
	 */
	private static class GetDryBulbTemperatureFromWbtDptPFunctionObject implements Solver.FunctionObject {
		private Double _wbt;
		private Double _p;
		public GetDryBulbTemperatureFromWbtDptPFunctionObject(Double wbt, Double p) {
			_wbt = wbt; _p = p;
		}

		@Override
		public Double evaluate(Double v) throws Exception {
			Double w = PsychrometricsState.getHumidityRatioFromDbtWbt(v, _wbt, _p);
			return PsychrometricsState.getDewPointTemperature(w, _p);
		}		
	}
	private static Double getDryBulbTemperatureFromWbtDptP(Double wbt, Double dpt, Double p) throws Exception {
	  return Solver.INSTANCE.gradient(TMIN, TMAX, wbt, dpt, 0.1, 1e-8,
			  new GetDryBulbTemperatureFromWbtDptPFunctionObject(wbt, p));
	};
	
	/** Following functions are using a marching algorithm. TODO convert from JS to Java
	
	PsychrometricsJS.State.prototype.from_hrh = function(h, rh, p) {
		  this.h = h; this.rh = rh; this.p = p;
		  
		  var dbt = null; var dbtp = null; var wbt = null; var wbtp = null; var w;
		  var it = 0; var err = 1e-4;

		  dbt = PsychrometricsJS.SI.getDryBulbTemperatureFromHW(this.h.to_kjkgda(), 2.0*PsychrometricsJS.WMIN); // initial guess with little moisture
		  while ((dbtp == null) || (wbtp == null) || ((it < 100) && ((Math.abs(dbt-dbtp)>err) || (Math.abs(wbt-wbtp)>err)))) {
		    dbtp = dbt; wbtp = wbt;
		    w = PsychrometricsJS.SI.getHumidityRatioFromDbtH(dbt, this.h.to_kjkgda());
		    wbt = PsychrometricsJS.SI.getWetBulbTemperatureFromDbtWP(dbt, w, this.p.to_pascal());
		    dbt = PsychrometricsJS.SI.getDryBulbTemperatureFromWbtRhP(wbt, this.rh.to_zerotoone(), this.p.to_pascal());
		    it ++;
		  }
		  if ((Math.abs(dbt-dbtp)>err) || (Math.abs(wbt-wbtp)>err)) { throw "Failed to converge marching algorithm..."; }

		  this.dbt = new PsychrometricsJS.Temperature(dbt, PsychrometricsJS.Units.CELSIUS);
		  this.wbt = new PsychrometricsJS.Temperature(wbt, PsychrometricsJS.Units.CELSIUS);
		};

		PsychrometricsJS.State.prototype.from_wrh = function(w, rh, p) {
		  this.w = w; this.rh = rh; this.p = p;
		  
		  var dbt = null; var dbtp = null; var wbt = null; var wbtp = null;
		  var it = 0; var err = 1e-4;

		  wbt = PsychrometricsJS.SI.getDewPointTemperature(this.w.to_kgkgda(), this.p.to_pascal());  
		  while ((dbtp == null) || (wbtp == null) || ((it < 100) && ((Math.abs(dbt-dbtp)>err) || (Math.abs(wbt-wbtp)>err)))) {
		    dbtp = dbt; wbtp = wbt;
		    dbt = PsychrometricsJS.SI.getDryBulbTemperatureFromWbtRhP(wbt, this.rh.to_zerotoone(), this.p.to_pascal());
		    wbt = PsychrometricsJS.SI.getWetBulbTemperatureFromDbtWP(dbt, this.w.to_kgkgda(), this.p.to_pascal());
		    it ++;
		  }
		  if ((Math.abs(dbt-dbtp)>err) || (Math.abs(wbt-wbtp)>err)) { throw "Failed to converge marching algorithm..."; }

		  this.dbt = new PsychrometricsJS.Temperature(dbt, PsychrometricsJS.Units.CELSIUS);
		  this.wbt = new PsychrometricsJS.Temperature(wbt, PsychrometricsJS.Units.CELSIUS);
		};
		*/


}
