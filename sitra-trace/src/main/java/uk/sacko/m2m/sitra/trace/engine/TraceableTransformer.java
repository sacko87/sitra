package uk.sacko.m2m.sitra.trace.engine;

import java.util.List;

import uk.sacko.m2m.sitra.engine.Transformer;
import uk.sacko.m2m.sitra.trace.TraceableElement;

/**
 * The basic definition for a traceable transformer.
 * 
 * We expect all traceable transformers to be context aware such that the transformer
 * concentrates on the transformation alone.
 * 
 * @author John T. Saxon
 * @author David H. Akehurst
 * @author Behzad Bordbar
 *
 */
public interface TraceableTransformer extends Transformer {
	/**
	 * The list of invocations that the transformer has made.
	 * 
	 * @return an ordered list of invocations.
	 */
	public List<TraceableElement> getTransformationTrace(); 
}
