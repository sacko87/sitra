package uk.sacko.m2m.sitra.engine;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import uk.sacko.m2m.sitra.Rule;

/**
 * A simple transformer that provides a mechanism to synchronise the list of
 * transformation rules added to the engine with the instance map. It also
 * delegates the dynamic transformation method, such that when no rule is found
 * it finds the first rule that is valid and returns a non-null value.
 * 
 * @author John T. Saxon
 * @author David H. Akehurst
 * @author Behzad Bordbar
 * @author Kyriakos Anastasakis
 *
 */
public abstract class SimpleAbstractTransformer implements Transformer {
	/**
	 * A set of rule definitions for this transformer.
	 */
	private final Set<Class<? extends Rule<?, ?>>> transformationRules = new LinkedHashSet<Class<? extends Rule<?, ?>>>();

	/**
	 * A set of rule instances for this transformer.
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

	protected Map<Class<? extends Rule<?, ?>>, Rule<?, ?>> getRuleInstances() {
		return ruleInstances;
	}

	/**
	 * The setup command to synchronise the internal rule instance map with the
	 * set of rule definitions.
	 * 
	 * Before each transformation this command should be completed
	 *
	 * <strong>TODO</strong>: this would be better completed with an observable
	 * list, such that any changes within the list of rule definitions it
	 * triggers a new instance to be added or deleted from the rule instance
	 * map.
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
