package calculator;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

public class TestMyNumber implements TestInterface {

	private int value;
	private MyNumber number;
	
	@BeforeEach
	public void setUp() {
		value = 8;
		number = new MyNumber(value);
	}

	@Test
	public void testEquals() {
		// Two distinct MyNumber, constructed separately (using a different constructor) but containing the same value should be equal
		assertEquals(new MyNumber(8), number);
		// Two MyNumbers containing a distinct value should not be equal:
		assertNotEquals(new MyNumber(7),number);
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
