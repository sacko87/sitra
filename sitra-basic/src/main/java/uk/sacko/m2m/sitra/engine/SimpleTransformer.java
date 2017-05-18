package uk.sacko.m2m.sitra.engine;

import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import uk.sacko.m2m.sitra.Rule;

public class SimpleTransformer extends SimpleAbstractTransformer {
	/**
	 * (r, s) -&gt; t where t = r(s)
	 */
	private final Map<Entry<?, Class<? extends Rule<?, ?>>>, Object> cache = new HashMap<Entry<?, Class<? extends Rule<?, ?>>>, Object>();

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

		// generate a cache key
		final Entry<?, Class<? extends Rule<?, ?>>> cacheKey = new AbstractMap.SimpleEntry<S, Class<? extends Rule<?, ?>>>(
				source, with);

		// check whether this has been transformed before
		if (this.getCache().containsKey(cacheKey)) {
			// return the previously instantiated objects
			target = (T) this.getCache().get(cacheKey);
		} else {
			// guard
			if (using.check(source)) {
				// instantiate
				target = using.build(source, this);
				if (target != null) {
					this.getCache().put(cacheKey, target);
					// bind
					using.setProperties(target, source, this);
				}
			}
		}

		return target;
	}

	/**
	 * The internal mapping of sources to targets with relation to a specific
	 * transformation rule.
	 * 
	 * (r, s) -&gt; t where t = r(s)
	 * 
	 * @return the internal trace of this engine.
	 */
	private Map<Entry<?, Class<? extends Rule<?, ?>>>, Object> getCache() {
		return cache;
	}
}
