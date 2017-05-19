package uk.sacko.m2m.sitra.trace.context;

import java.util.List;

import uk.sacko.m2m.sitra.context.Context;
import uk.sacko.m2m.sitra.trace.Invocation;

public interface TraceableContext extends Context {
	public List<? extends Invocation<?, ?>> getTransformationTrace();
}
