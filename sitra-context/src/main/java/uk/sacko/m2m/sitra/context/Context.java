package uk.sacko.m2m.sitra.context;

import java.util.Map;
import java.util.Map.Entry;

import uk.sacko.m2m.sitra.Rule;

public interface Context {
	public Map<Entry<?, Class<? extends Rule<?, ?>>>, ? extends Object> getCache(); 
}
