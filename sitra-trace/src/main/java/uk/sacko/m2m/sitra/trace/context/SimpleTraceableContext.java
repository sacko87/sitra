package uk.sacko.m2m.sitra.trace.context;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import uk.sacko.m2m.sitra.Rule;
import uk.sacko.m2m.sitra.trace.Invocation;
import uk.sacko.m2m.sitra.trace.TraceableElement;

/**
 * An implementation of a traceable transformation Context.
 * 
 * @author John T. Saxon
 */
public class SimpleTraceableContext implements TraceableContext {
	private final Map<Entry<?, Class<? extends Rule<?, ?>>>, Invocation<?, ?>> cache =
			new HashMap<Map.Entry<?,Class<? extends Rule<?,?>>>, Invocation<?,?>>();
	
	private final Map<Object, Object> configuration = new HashMap<Object, Object>();
	
	private List<TraceableElement> transformationTrace = new LinkedList<TraceableElement>();

	@Override
	public Map<Entry<?, Class<? extends Rule<?, ?>>>, Invocation<?, ?>> getCache() {
		return this.cache;
	}

	@Override
	public Map<Object, Object> getConfiguration() {
		return configuration;
	}
	
	@Override
	public List<TraceableElement> getTransformationTrace() {
		return this.transformationTrace;
	}
}
