package uk.sacko.m2m.sitra.trace;

import uk.sacko.m2m.sitra.Rule;

/**
 * The record of a transformation of &lt;S&gt; to &lt;T&gt; using a particular
 * rule definition.
 * 
 * @author John T. Saxon
 * @author Kyriakos Anastasakis
 * 
 * @param <S>
 *            the source type.
 * @param <T>
 *            the target type.
 */
public interface Invocation<S, T> extends TraceableElement {
	/**
	 * 
	 * @return the source model element.
	 */
	public S getSource();

	/**
	 * 
	 * @return the target model element.
	 */
	public T getTarget();

	/**
	 * 
	 * @return the rule used to create the target from the source.
	 */
	public Class<? extends Rule<S, T>> getUsing();
}
