package calculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


public class TestPlus implements TestInterface {

	int value1, value2;
	MyNumber number1, number2;
	Plus op;
	List<Expression> params;

	@BeforeEach
	public void setUp() {
		  value1 = 8;
		  value2 = 6;
		  number1 = new MyNumber(value1);
		  number2 = new MyNumber(value2);
		  params = new ArrayList<>();
		  Collections.addAll(params, number1, number2);
		  try { op = new Plus(params); }
		  catch(IllegalConstruction e) { fail(); }
	}

	@Test
	public void testNullParamList() {
		params = null;
		assertThrows(IllegalConstruction.class, () -> {op = new Plus(params);});
	}

	@Test
	public void test_compute() {
		assertEquals(value1+value2, op.compute().intValue());
		assertEquals(14, op.compute().intValue());
	}

	@Test
	public void test_countDepth() {
		assertEquals(Integer.valueOf(1), op.countDepth());
	}

	@Test
	public void test_countOps() {
		assertEquals(Integer.valueOf(1), op.countOps());
	}

	@Test
	public void test_countNbs() {
		assertEquals(Integer.valueOf(2), op.countNbs());
	}

	@Test
	public void test_toString() {
		// default printing notation is infix
		assertEquals("( 8 + 6 )", op.toString());
	}

	@Test
	public void test_prefix() {
		op.notation = Notation.PREFIX;
		assertEquals("+ (8, 6)", op.toString());
	}

	@Test
	public void test_infix() {
		op.notation = Notation.INFIX;
		assertEquals("( 8 + 6 )", op.toString());
	}

	@Test
	public void test_postfix() {
		op.notation = Notation.POSTFIX;
		assertEquals("(8, 6) +", op.toString());
	}

}
