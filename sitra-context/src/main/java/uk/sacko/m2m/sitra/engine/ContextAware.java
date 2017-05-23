package uk.sacko.m2m.sitra.engine;

import uk.sacko.m2m.sitra.context.Context;

/**
 * A mechanism to provide context aware model-to-model transformations.
 * 
 * @author John T. Saxon
 *
 */
public interface ContextAware {
	/**
	 * 
	 * @return
	 */
	public Context getContext();
}
