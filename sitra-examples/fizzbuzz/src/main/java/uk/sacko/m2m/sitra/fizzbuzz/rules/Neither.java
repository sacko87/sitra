package uk.sacko.m2m.sitra.fizzbuzz.rules;

import uk.sacko.m2m.sitra.Rule;
import uk.sacko.m2m.sitra.engine.Transformer;

/**
 * A rule that returns the string representation of an integer iff it is not a
 * multiple of three or five.
 * 
 * @author John T. Saxon
 * 
 */
public class Neither implements Rule<Integer, String> {
	@Override
	public boolean check(Integer source) {
		return !((source % 3) == 0 || (source % 5) == 0);
	}

	@Override
	public String build(Integer source, Transformer tx) {
		return source.toString();
	}

	@Override
	public void setProperties(String target, Integer source, Transformer tx) {
		// no binding necessary
	}
}
