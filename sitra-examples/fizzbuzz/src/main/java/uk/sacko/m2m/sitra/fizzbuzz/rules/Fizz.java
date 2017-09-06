package uk.sacko.m2m.sitra.fizzbuzz.rules;

import uk.sacko.m2m.sitra.Rule;
import uk.sacko.m2m.sitra.engine.Transformer;

/**
 * A rule that returns the string "Fizz" if the integer is a multiple of three.
 * 
 * @author John T. Saxon
 * 
 */
public class Fizz implements Rule<Integer, String> {
	@Override
	public boolean check(Integer source) {
		return (source % 3) == 0;
	}

	@Override
	public String build(Integer source, Transformer tx) {
		return "Fizz";
	}

	@Override
	public void setProperties(String target, Integer source, Transformer tx) {
		// no binding necessary
	}
}
