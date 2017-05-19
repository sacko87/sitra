package uk.sacko.m2m.sitra.trace.engine;

import java.util.List;

import uk.sacko.m2m.sitra.engine.ContextAware;
import uk.sacko.m2m.sitra.engine.Transformer;
import uk.sacko.m2m.sitra.trace.Invocation;

public interface TraceableTransformer extends Transformer, ContextAware {
	public List<Invocation<?, ?>> getTransformationTrace(); 
}
