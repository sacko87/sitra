package uk.sacko.m2m.sitra.fizzbuzz.rules;

import uk.sacko.m2m.sitra.Rule;
import uk.sacko.m2m.sitra.engine.Transformer;

/**
 * A rule that returns the string "Buzz" if the integer is a multiple of five.
 * 
 * @author John T. Saxon
 * 
 */
public class Buzz implements Rule<Integer, String> {
	@Override
	public boolean check(Integer source) {
		return (source % 5) == 0;
	}

	@Override
	public String build(Integer source, Transformer tx) {
		return "Buzz";
	}

	@Override
	public void setProperties(String target, Integer source, Transformer tx) {
		// no binding necessary
	}
}
