package uk.sacko.m2m.sitra.trace;

import uk.sacko.m2m.sitra.Rule;

public class SimpleInvocation<S, T> implements Invocation<S, T> {
	private final S source;
	private final T target;
	private final Class<? extends Rule<S, T>> using;
	
	public SimpleInvocation(S source, T target, Class<? extends Rule<S, T>> using) {
		this.source = source;
		this.target = target;
		this.using = using;
	}

	@Override
	public S getSource() {
		return this.source;
	}

	@Override
	public T getTarget() {
		return this.target;
	}

	@Override
	public Class<? extends Rule<S, T>> getUsing() {
		return this.using;
	}

}
