package uk.sacko.m2m.sitra.trace.engine;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import uk.sacko.m2m.sitra.Rule;
import uk.sacko.m2m.sitra.engine.SimpleAbstractTransformer;
import uk.sacko.m2m.sitra.trace.Invocation;
import uk.sacko.m2m.sitra.trace.SimpleInvocation;
import uk.sacko.m2m.sitra.trace.context.SimpleTraceableContext;
import uk.sacko.m2m.sitra.trace.engine.TraceableTransformer;

/**
 * A simple implementation of a transformer with a linear trace. 
 * 
 * @author John T. Saxon
 * @author David Akehurst
 * @author Behzad Bordbar
 * @author Kyriakos Anastasakis
 *
 */
public class SimpleTraceableTransformer extends SimpleAbstractTransformer implements TraceableTransformer {
	private SimpleTraceableContext ctx;

	@SuppressWarnings("unchecked")
	@Override
	public <S, T> T transform(S source, Class<? extends Rule<S, T>> with) {
		// sync rule defs with instances
		this.setup();

		T target = null;

		// get the rule instance
		Rule<S, T> using = (Rule<S, T>) this.getRuleInstances().get(with);
		if (using == null) {
			return null; // this rule is not in the engine
		}

		Map<Entry<?, Class<? extends Rule<?, ?>>>, Invocation<?, ?>> cache = (Map<Entry<?, Class<? extends Rule<?, ?>>>, Invocation<?, ?>>) this.getContext().getCache();
		// generate a cache key
		final Entry<?, Class<? extends Rule<?, ?>>> cacheKey = new AbstractMap.SimpleEntry<S, Class<? extends Rule<?, ?>>>(
				source, with);

		// check whether this has been transformed before
		if (cache.containsKey(cacheKey)) {
			// return the previously instantiated objects
			target = (T) cache.get(cacheKey).getTarget();
		} else {
			// guard
			if (using.check(source)) {
				// instantiate
				target = using.build(source, this);
				if (target != null) {
					// record an invocation
					Invocation<S, T> inv = new SimpleInvocation<S, T>(source, target, with);
					this.getTransformationTrace().add(inv);
					// retain an internal lookup
					cache.put(cacheKey, inv);
					
					// bind
					using.setProperties(target, source, this);
				}
			}
		}

		return target;
	}

	@Override
	public SimpleTraceableContext getContext() {
		if(this.ctx == null) {
			this.setContext(new SimpleTraceableContext());
		}
		
		return this.ctx;
	}
	
	public void setContext(SimpleTraceableContext ctx) {
		this.ctx = ctx;
	}

	@Override
	public List<Invocation<?, ?>> getTransformationTrace() {
		return this.getContext().getTransformationTrace();
	}
}
