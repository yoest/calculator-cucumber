package calculator;

import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class TestMinus implements TestInterface {

	int value1, value2;
	MyNumber number1, number2;
	Minus minus;
	
	@Before
	public void setUp() throws Exception {
		  value1 = 8;
		  value2 = 6;
		  number1 = new MyNumber(value1);
		  number2 = new MyNumber(value2);
		  List<Expression> params = new ArrayList<>();
		  Collections.addAll(params, number1, number2);
		  minus = new Minus(params);
	}

	@Test
	public void test_compute() {
		assertEquals(value1-value2, minus.compute().intValue());
	}

	@Test
	public void test_countDepth() {
		assertEquals(Integer.valueOf(1), minus.countDepth());
	}

	@Test
	public void test_countOps() {
		assertEquals(Integer.valueOf(1), minus.countOps());
	}

	@Test
	public void test_countNbs() {
		assertEquals(Integer.valueOf(2), minus.countNbs());
	}

	@Test
	public void test_toString() {
		// default printing notation is infix
		assertEquals("( 8 - 6 )", minus.toString());
	}

	@Test
	public void test_prefix() {
		minus.notation = Notation.PREFIX;
		assertEquals("- (8, 6)", minus.toString());
	}

	@Test
	public void test_infix() {
		minus.notation = Notation.INFIX;
		assertEquals("( 8 - 6 )", minus.toString());
	}

	@Test
	public void test_postfix() {
		minus.notation = Notation.POSTFIX;
		assertEquals("(8, 6) -", minus.toString());
	}

}
