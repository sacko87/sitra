package uk.sacko.m2m.sitra.engine;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Map.Entry;

import uk.sacko.m2m.sitra.Rule;
import uk.sacko.m2m.sitra.context.SimpleContext;

public class SimpleTransformer extends SimpleAbstractTransformer implements ContextAware {
	private SimpleContext ctx;

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

		Map<Entry<?, Class<? extends Rule<?, ?>>>, Object> cache = this.getContext().getCache();
		// generate a cache key
		final Entry<?, Class<? extends Rule<?, ?>>> cacheKey = new AbstractMap.SimpleEntry<S, Class<? extends Rule<?, ?>>>(
				source, with);

		// check whether this has been transformed before
		if (cache.containsKey(cacheKey)) {
			// return the previously instantiated objects
			target = (T) cache.get(cacheKey);
		} else {
			// guard
			if (using.check(source)) {
				// instantiate
				target = using.build(source, this);
				if (target != null) {
					cache.put(cacheKey, target);
					// bind
					using.setProperties(target, source, this);
				}
			}
		}

		return target;
	}

	@Override
	public SimpleContext getContext() {
		if(this.ctx == null) {
			this.setContext(new SimpleContext());
		}
		
		return this.ctx;
	}

	public void setContext(SimpleContext ctx) {
		this.ctx = ctx;
	}
}
