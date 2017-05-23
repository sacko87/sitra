package uk.sacko.m2m.sitra.engine;

import java.util.Set;

import uk.sacko.m2m.sitra.Rule;

/**
 * The basic definition of a transformation engine.
 * 
 * @author John T. Saxon 
 * @author David H. Akehurst
 * @author Behzad Bordbar
 * 
 */
public interface Transformer {
	/**
	 * Transform a source object dynamically.
	 * 
	 * This iteratively calls {@link #transform(Object, Class) transform}
	 * 
	 * @param <S>
	 *            the source type.
	 * @param <T>
	 *            the target type.
	 * @param source
	 *            The source object.
	 * @return The target object or <em>null</em> if no applicable rule has been
	 *         found.
	 * @see #getTransformationRules()
	 */
	public <S, T> T transform(S source);

	/**
	 * Transform a source object using a specific type of rule.
	 * 
	 * @param <S>
	 *            the source type.
	 * @param <T>
	 *            the target type.
	 * @param source
	 *            The source object.
	 * @param with
	 *            The type of rule to be used.
	 * @return The target object or <em>null</em> if no applicable rule has been
	 *         found.
	 */
	public <S, T> T transform(S source, Class<? extends Rule<S, T>> with);

	/**
	 * Get the list of rule definitions related to this transformation instance.
	 * 
	 * @return An ordered collection of transformation rule definitions.
	 */
	public Set<Class<? extends Rule<?, ?>>> getTransformationRules();
}
