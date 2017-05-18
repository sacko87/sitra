package uk.sacko.m2m.sitra.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import uk.sacko.m2m.sitra.Rule;

public class SimpleContext implements Context {
	private final Map<Entry<?, Class<? extends Rule<?, ?>>>, Object> cache = new HashMap<Entry<?, Class<? extends Rule<?, ?>>>, Object>();

	/**
	 * The internal mapping of sources to targets with relation to a specific
	 * transformation rule.
	 * 
	 * (r, s) -&gt; t where t = r(s)
	 * 
	 * @return the internal trace of this engine.
	 */
	@Override
	public Map<Entry<?, Class<? extends Rule<?, ?>>>, Object> getCache() {
		return this.cache;
	}
}
