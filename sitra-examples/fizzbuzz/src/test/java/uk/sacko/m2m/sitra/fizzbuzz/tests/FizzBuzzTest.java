package uk.sacko.m2m.sitra.fizzbuzz.tests;

import org.junit.Test;
import static org.junit.Assert.*;

import uk.sacko.m2m.sitra.engine.SimpleTransformer;
import uk.sacko.m2m.sitra.fizzbuzz.rules.*;

/**
 * Test the rules that we care about.
 * 
 * @author John T. Saxon
 *
 */
public class FizzBuzzTest {
	@Test
	public void testFizz() {
		// prepare transformer
		SimpleTransformer tx = new SimpleTransformer();
		tx.getTransformationRules().add(Fizz.class);

		assertEquals("Fizz", tx.transform(3) /* would be fizz */);
		assertNull(tx.transform(4) /* would be 4 */);
		assertNull(tx.transform(5) /* would be buzz */);
		assertEquals("Fizz", tx.transform(15) /* would be fizz buzz */);
	}
	
	@Test
	public void testBuzz() {
		// prepare transformer
		SimpleTransformer tx = new SimpleTransformer();
		tx.getTransformationRules().add(Buzz.class);

		assertNull(tx.transform(3) /* would be fizz */);
		assertNull(tx.transform(4) /* would be 4 */);
		assertEquals("Buzz", tx.transform(5) /* would be buzz */);
		assertEquals("Buzz", tx.transform(15) /* would be fizz buzz */);
	}
	
	@Test
	public void testFizzBuzz() {
		// prepare transformer
		SimpleTransformer tx = new SimpleTransformer();
		tx.getTransformationRules().add(FizzBuzz.class);
		
		assertNull(tx.transform(3) /* would be fizz */);
		assertNull(tx.transform(4) /* would be 4 */);
		assertNull(tx.transform(5) /* would be buzz */);
		assertEquals("FizzBuzz", tx.transform(15) /* would be fizz buzz */);
	}
	
	@Test
	public void testNeither() {
		// prepare transformer
		SimpleTransformer tx = new SimpleTransformer();
		tx.getTransformationRules().add(Neither.class);
		
		assertNull(tx.transform(3) /* would be fizz */);
		assertEquals("4", tx.transform(4) /* would be 4 */);
		assertNull(tx.transform(5) /* would be buzz */);
		assertNull(tx.transform(15) /* would be fizz buzz */);
	}
	
	@Test
	public void test() {
		// prepare transformer
		SimpleTransformer tx = new SimpleTransformer();
		tx.getTransformationRules().add(FizzBuzz.class);
		tx.getTransformationRules().add(Fizz.class);
		tx.getTransformationRules().add(Buzz.class);
		tx.getTransformationRules().add(Neither.class);
		
		// for numbers 0..49
		for(Integer i = 0; i < 50; i++) {
			// transform the number
			String result = tx.transform(i);

			// if fizz
			if((i % 3) == 0) {
				// also buzz
				if((i % 5) == 0) {
					assertEquals("FizzBuzz", result);
				} else {
					assertEquals("Fizz", result);
				}
			// if buzz
			} else if((i % 5) == 0) {
				assertEquals("Buzz", result);
			} else {
				assertEquals(i.toString(), result);
			}
		}
	}
}
