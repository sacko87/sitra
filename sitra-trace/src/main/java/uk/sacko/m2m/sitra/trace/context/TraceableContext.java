package uk.sacko.m2m.sitra.trace.context;

import java.util.List;

import uk.sacko.m2m.sitra.context.Context;
import uk.sacko.m2m.sitra.trace.Invocation;

/**
 * A context for a traceable transformation.
 * 
 * A building block for a single instance of a transformer. At its centre is a
 * mapping of (r, s) &gt; r(s). However, we retain an invocation containing the
 * tuple (r, s, r(s)).
 * 
 * @author John T. Saxon
 */
public interface TraceableContext extends Context {
	/**
	 * The list of invocations that the transformer has made.
	 * 
	 * @return an ordered list of invocations.
	 */
	public List<? extends Invocation<?, ?>> getTransformationTrace();
}
