package uk.sacko.m2m.sitra.trace.context;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import uk.sacko.m2m.sitra.Rule;
import uk.sacko.m2m.sitra.trace.Invocation;

public class SimpleTraceableContext implements TraceableContext {
	private final Map<Entry<?, Class<? extends Rule<?, ?>>>, Invocation<?, ?>> cache =
			new HashMap<Map.Entry<?,Class<? extends Rule<?,?>>>, Invocation<?,?>>();
	
	private List<Invocation<?, ?>> transformationTrace = new LinkedList<Invocation<?,?>>();

	@Override
	public Map<Entry<?, Class<? extends Rule<?, ?>>>, Invocation<?, ?>> getCache() {
		return this.cache;
	}

	@Override
	public List<Invocation<?, ?>> getTransformationTrace() {
		return this.transformationTrace;
	}
}
