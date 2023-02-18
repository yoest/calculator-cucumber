package junit5tests;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import calculator.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TestMinus {

	private final int value1 = 8;
	private final int value2 = 6;
	private Minus op;
	private List<Expression> params;

	@BeforeEach
	void setUp() {
		  params = new ArrayList<>(Arrays.asList(new MyNumber(value1),new MyNumber(value2)));
		  try { op = new Minus(params); }
		  catch(IllegalConstruction e) { fail(); }
	}

	@Test
	void testConstructor1() {
		// It should not be possible to create an expression without null parameter list
		assertThrows(IllegalConstruction.class, () -> op = new Minus(null));
	}

	@SuppressWarnings("AssertBetweenInconvertibleTypes")
	@Test
	void testConstructor2() {
		// A Times expression should not be the same as a Minus expression
		try {
			assertNotSame(op, new Times(new ArrayList<>()));
		} catch (IllegalConstruction e) {
			fail();
		}
	}

	@Test
	void testEquals() {
		// Two similar expressions, constructed separately (and using different constructors) should not be equal
		ArrayList<Expression> p = new ArrayList<>(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
		try {
			Minus e = new Minus(p, Notation.INFIX);
			assertEquals(op, e);
		}
		catch(IllegalConstruction e) { fail(); }
	}

	@SuppressWarnings("ConstantConditions")
	@Test
	void testNull() {
		assertDoesNotThrow(() -> op==null); // Direct way to to test if the null case is handled.
	}

	@Test
	void testHashCode() {
		// Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
		ArrayList<Expression> p = new ArrayList<>(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
		try {
			Minus e = new Minus(p, Notation.INFIX);
			assertEquals(e.hashCode(), op.hashCode());
		}
		catch(IllegalConstruction e) { fail(); }
	}

	@Test
	void testNullParamList() {
		params = null;
		assertThrows(IllegalConstruction.class, () -> op = new Minus(params));
	}

	@Test
	void testCountDepth() {
		assertEquals(Integer.valueOf(1), op.countDepth());
	}

	@Test
	void testCountOps() {
		assertEquals(Integer.valueOf(1), op.countOps());
	}

	@Test
	void testCountNbs() {
		assertEquals(Integer.valueOf(2), op.countNbs());
	}

	@Test
	void testPrefix() {
		String prefix = "- (" + value1 + ", " + value2 + ")";
		assertEquals(prefix, op.toString(Notation.PREFIX));
		op.notation = Notation.PREFIX;
		assertEquals(prefix, op.toString());
	}

	@Test
	void testInfix() {
		String infix = "( " + value1 + " - " + value2 + " )";
		assertEquals(infix, op.toString(Notation.INFIX));
		op.notation = Notation.INFIX;
		assertEquals(infix, op.toString());
	}

	@Test
	void testPostfix() {
		String postfix = "(" + value1 + ", " + value2 + ") -";
		assertEquals(postfix, op.toString(Notation.POSTFIX));
		op.notation = Notation.POSTFIX;
		assertEquals(postfix, op.toString());
	}

}
