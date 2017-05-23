package uk.sacko.m2m.sitra.context;

import java.util.Map;
import java.util.Map.Entry;

import uk.sacko.m2m.sitra.Rule;

/**
 * A context for a transformation.
 * 
 * A building block for a single instance of a transformer. At its centre is a
 * mapping of (r, s) &gt; r(s).
 * 
 * @author John T. Saxon
 */
public interface Context {
	/**
	 * The internal mapping of sources to targets with relation to a specific
	 * transformation rule.
	 * 
	 * (r, s) -&gt; t where t = r(s)
	 * 
	 * @return the internal trace of this engine.
	 */
	public Map<Entry<?, Class<? extends Rule<?, ?>>>, ? extends Object> getCache();
}
