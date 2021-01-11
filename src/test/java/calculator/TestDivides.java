package calculator;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestDivides implements TestInterface {

	private int value1, value2;
	private MyNumber number1, number2;
	private Divides op;
	private List<Expression> params;

	@BeforeEach
	public void setUp() {
		  value1 = 8;
		  value2 = 6;
		  number1 = new MyNumber(value1);
		  number2 = new MyNumber(value2);
		  params = new ArrayList<>();
		  Collections.addAll(params, number1, number2);
		  try {
		  	op = new Divides(params);
			op.notation = Notation.INFIX; // reset the notation to infix (which is the default) before each test
		  }
		  catch(IllegalConstruction e) { fail(); }
	}

	@Test
	public void testConstructor1() {
		// It should not be possible to create an expression without null parameter list
		assertThrows(IllegalConstruction.class, () -> op = new Divides(null));
	}

	@Test
	public void testConstructor2() {
		// A Times expression should not be the same as a Divides expression
		try {
			assertNotEquals(op, new Times(new ArrayList<Expression>()));
		} catch (IllegalConstruction e) {
			fail();
		}
	}

	@Test
	public void testEquals() {
		// Two similar expressions, constructed separately (and using different constructors) should be equal
		ArrayList<Expression> p = new ArrayList<>();
		p.add(new MyNumber(8));
		p.add(new MyNumber(6));
		try {
			Divides d = new Divides(p, Notation.INFIX);
			assertEquals(op, d);
		}
		catch(IllegalConstruction e) { fail(); }
	}

	@Test
	public void testNullParamList() {
		params = null;
		assertThrows(IllegalConstruction.class, () -> {op = new Divides(params);});
	}

	@Test
	public void testCompute() {
		assertEquals(value1/value2, op.compute().intValue());
		assertEquals(1, op.compute().intValue());
	}

	@Test
	public void testCountDepth() {
		assertEquals(Integer.valueOf(1), op.countDepth());
	}

	@Test
	public void testCountOps() {
		assertEquals(Integer.valueOf(1), op.countOps());
	}

	@Test
	public void testCountNbs() {
		assertEquals(Integer.valueOf(2), op.countNbs());
	}

	@Test
	public void testToString() {
		// default printing notation is infix
		assertEquals("( 8 / 6 )", op.toString());
	}

	@Test
	public void testPrefix() {
		assertEquals("/ (8, 6)", op.toString(Notation.PREFIX));
		op.notation = Notation.PREFIX;
		assertEquals("/ (8, 6)", op.toString());
	}

	@Test
	public void testInfix() {
		assertEquals("( 8 / 6 )", op.toString(Notation.INFIX));
		op.notation = Notation.INFIX;
		assertEquals("( 8 / 6 )", op.toString());
	}

	@Test
	public void testPostfix() {
		assertEquals("(8, 6) /", op.toString(Notation.POSTFIX));
		op.notation = Notation.POSTFIX;
		assertEquals("(8, 6) /", op.toString());
	}

}
