package calculator;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestMyNumber implements TestInterface {

	int value;
	MyNumber number;
	
	@Before
	public void setUp() {
		value = 8;
		number = new MyNumber(value);
	}

	@Test
	public void test_compute() {
		//test whether the value returned by compute is the same as the value stored
		assertEquals(value, number.compute().intValue());
	}

	@Test
	public void test_countDepth() {
		//test whether a number has zero depth (i.e. no nested expressions)
		assertEquals(Integer.valueOf(0), number.countDepth());
	}

	@Test
	public void test_countOps() {
		//test whether a number contains zero operations
		assertEquals(Integer.valueOf(0), number.countOps());
	}

	@Test
	public void test_countNbs() {
		//test whether a number contains 1 number
		assertEquals(Integer.valueOf(1), number.countNbs());
	}

	@Test
	public void test_toString() {
		assertEquals(Integer.toString(value), number.toString());
	}

}
