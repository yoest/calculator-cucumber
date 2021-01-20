package calculator;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class TestMyNumber implements TestInterface {

	private final int value =8;
	private MyNumber number;
	
	@BeforeEach
	public void setUp() {
		number = new MyNumber(value);
	}

	@Test
	public void testEquals() {
		// Two distinct MyNumber, constructed separately (using a different constructor) but containing the same value should be equal
		assertEquals(new MyNumber(value), number);
		// Two MyNumbers containing a distinct value should not be equal:
		int otherValue = 7;
		assertNotEquals(new MyNumber(otherValue),number);
		assertEquals(number, number); // Identity check (for coverage, as this should always be true)
		assertNotEquals(number, value);
		try {
			assertNotEquals(new Times(new ArrayList<>()), number);
		}
		catch (IllegalConstruction e) {fail();}
	}

	@Test
	public void testCompute() {
		//test whether the value returned by compute is the same as the value stored
		assertEquals(value, number.compute().intValue());
	}

	@Test
	public void testCountDepth() {
		//test whether a number has zero depth (i.e. no nested expressions)
		assertEquals(Integer.valueOf(0), number.countDepth());
	}

	@Test
	public void testCountOps() {
		//test whether a number contains zero operations
		assertEquals(Integer.valueOf(0), number.countOps());
	}

	@Test
	public void testCountNbs() {
		//test whether a number contains 1 number
		assertEquals(Integer.valueOf(1), number.countNbs());
	}

	@Test
	public void testToString() {
		assertEquals(Integer.toString(value), number.toString());
	}

}
