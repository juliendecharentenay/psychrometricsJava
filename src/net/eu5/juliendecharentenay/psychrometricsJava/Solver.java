/**
 * 
 */
package net.eu5.juliendecharentenay.psychrometricsJava;

/**
 * @author juliendecharentenay
 *
 */
public enum Solver {
	INSTANCE;

	public interface FunctionObject {
		public Double evaluate(Double v) throws Exception;
	}

	public Double bisection(Double vmin, Double vmax, Double target, Double err, FunctionObject func) throws Exception {
		  Double fmin = func.evaluate(vmin); 
		  Double fmax = func.evaluate(vmax);
		  if ((target - fmin)*(target-fmax) > 0) { throw new Exception("Bisection solver error: target value " + target + " is not within interval vmin|vmax [" + fmin + ";" + fmax + "]. Function may not be monotonous"); }
		  if (Math.abs(fmin - target) <= err) { return vmin; }
		  if (Math.abs(fmax - target) <= err) { return vmax; }

		  Double v = 0.5*(vmin+vmax); Double f = func.evaluate(v);
		  while (Math.abs(f-target)>err) {
		    if ((target-f)*(target-fmin) < 0) { // Target is between vmin and v. Update vmax/fmax
		      vmax = v; fmax = f;
		    } else if ((target-f)*(target-fmax) < 0) { // Target is between v and vmax. Update vmin/fmin
		      vmin = v; fmin = f;
		    } else {
		      throw new Exception("Bisection solver error: Can find updated interval");
		    }
		    v = 0.5*(vmin+vmax); f = func.evaluate(v);
		  };
		  return v;		
	}
	
	public Double gradient(Double vmin, Double vmax, Double vInit, Double target, 
			Double delta, Double err, FunctionObject func) throws Exception {
		  Double v = vInit; Double f = func.evaluate(v); 
		  Integer it=0; 
		  Double f1 = null; Double alpha=0.3;
		  while ((Math.abs(f-target)>err) && (it < 100)) {
		    f1 = func.evaluate(v+delta);
		    if (Math.abs(f1-f)<1e-8) {throw new Exception("Gradient solver: Increase delta to get more meaningfull answer");}
		    v = v + alpha*(target-f)/(f1-f)*delta;
		    v = (v < vmin) ? vmin : ((v > vmax) ? vmax : v);
		    f = func.evaluate(v);
		    ++it;
		  }
		  if (Math.abs(f-target)>err) {throw new Exception("Failed to converge gradient solver");}
		  return v;
		};


}
