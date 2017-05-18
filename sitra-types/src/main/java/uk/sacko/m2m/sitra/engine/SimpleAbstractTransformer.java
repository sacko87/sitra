package uk.sacko.m2m.sitra.engine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import uk.sacko.m2m.sitra.Rule;

/**
 * 
 * @author John T. Saxon
 *
 */
public abstract class SimpleAbstractTransformer implements Transformer {
	/**
	 * 
	 */
	private final Set<Class<? extends Rule<?, ?>>> transformationRules = new LinkedHashSet<Class<? extends Rule<?, ?>>>();

	/**
	 * 
	 */
	private final Map<Class<? extends Rule<?, ?>>, Rule<?, ?>> ruleInstances = new HashMap<Class<? extends Rule<?, ?>>, Rule<?, ?>>();

	@Override
	public <S, T> T transform(S source) {
		// sync the rule definitions and their instances
		this.setup();

		// loop through each rule instance 
		for (Class<? extends Rule<?, ?>> with : this.getTransformationRules()) {
			try {
				@SuppressWarnings("unchecked")
				T result = (T) this.transform(source, (Class<? extends Rule<S, T>>) with);
				if (result != null) {
					return result;
				}
			} catch (ClassCastException e) {
				// not applicable
				// i.e. Rule<X, ?> S /= X
			}
		}

		return null;
	}

	@Override
	public Set<Class<? extends Rule<?, ?>>> getTransformationRules() {
		return this.transformationRules;
	}

	/**
	 * 
	 * @return
	 */
	protected Map<Class<? extends Rule<?, ?>>, Rule<?, ?>> getRuleInstances() {
		return ruleInstances;
	}

	/**
	 * 
	 */
	protected void setup() {
		// if our rule instances are not in sync with our definitions...
		if (!this.getRuleInstances().keySet().equals(this.getTransformationRules())) {
			// retain the already instantiated rules
			this.getRuleInstances().keySet().retainAll(this.getTransformationRules());

			// get the list of those that need instantiating.
			Set<Class<? extends Rule<?, ?>>> missing = new HashSet<Class<? extends Rule<?, ?>>>(
					this.getTransformationRules());
			missing.removeAll(this.getRuleInstances().keySet());

			// iterate through each missing definition
			for (Class<? extends Rule<?, ?>> rule : missing) {
				try {
					// create an instance of the missing rule
					this.getRuleInstances().put(rule, rule.newInstance());
				} catch (IllegalAccessException e) {
					throw new RuntimeException(e);
				} catch (InstantiationException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
}
