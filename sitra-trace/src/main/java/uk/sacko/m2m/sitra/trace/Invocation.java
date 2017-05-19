package uk.sacko.m2m.sitra.trace;

import uk.sacko.m2m.sitra.Rule;

public interface Invocation<S, T> extends TraceableElement {
	public S getSource();
	public T getTarget();
	public Class<? extends Rule<S, T>> getUsing();
}
