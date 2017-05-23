package uk.sacko.m2m.sitra.context;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import uk.sacko.m2m.sitra.Rule;

/**
 * An implementation of a transformation Context.
 * 
 * @author John T. Saxon
 */
public class SimpleContext implements Context {
	private final Map<Entry<?, Class<? extends Rule<?, ?>>>, Object> cache = new HashMap<Entry<?, Class<? extends Rule<?, ?>>>, Object>();
	
	private final Map<Object, Object> configuration = new HashMap<Object, Object>();

	@Override
	public Map<Entry<?, Class<? extends Rule<?, ?>>>, Object> getCache() {
		return this.cache;
	}

	@Override
	public Map<Object, Object> getConfiguration() {
		return configuration;
	}
}
