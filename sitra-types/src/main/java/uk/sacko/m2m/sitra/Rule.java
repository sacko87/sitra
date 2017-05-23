package uk.sacko.m2m.sitra;

import uk.sacko.m2m.sitra.engine.Transformer;

/**
 * An interface that defines a transformation rule from &lt;S&gt; to &lt;T&gt;.
 * 
 * @author John T. Saxon
 * @author David H. Akehurst
 * @author Behzad Bordbar
 * 
 * @param <S>
 *            the source type.
 * @param <T>
 *            the target type.
 */
public interface Rule<S, T> {
	/**
	 * The transformation rule's guard.
	 * 
	 * @param source
	 *            the input model.
	 * @return <code>true</code> if the rule applies to the input, otherwise
	 *         <code>false</code>.
	 */
	public boolean check(S source);

	/**
	 * The transformation rule's instantiator.
	 * 
	 * This method creates the target object.
	 * 
	 * @param source
	 *            the source model.
	 * @param tx
	 *            the transformer that is invoking the rule.
	 * @return the new target model.
	 */
	public T build(S source, Transformer tx);

	/**
	 * The transformation rule's binder.
	 * 
	 * This method binds attributes and sets relationships.
	 * 
	 * @param target
	 *            the target model.
	 * @param source
	 *            the source model.
	 * @param tx
	 *            the transformer that is invoking the rule.
	 */
	public void setProperties(T target, S source, Transformer tx);
}
