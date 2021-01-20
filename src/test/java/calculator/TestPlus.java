package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;


public class TestPlus implements TestInterface {

	private int value1, value2;
	private MyNumber number1, number2;
	private Plus op;
	private List<Expression> params;

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
	public void testConstructor1() {
		// It should not be possible to create a Plus expression without null parameter list
		assertThrows(IllegalConstruction.class, () -> op = new Plus(null));
	}

	@Test
	public void testConstructor2() {
		// A Times expression should not be the same as a Plus expression
		try {
			assertNotEquals(op, new Times(new ArrayList<>()));
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
			Plus e = new Plus(p, Notation.INFIX);
			assertEquals(op, e);
			assertEquals(e, e);
			assertNotEquals(e, new Plus(new ArrayList<>(Arrays.asList(new MyNumber(5), new MyNumber(4))), Notation.INFIX));
			assertEquals(e.hashCode(), op.hashCode());
//			assertDoesNotThrow(() -> { e.equals(null); }); // Direct way to to test if the null case is handled. Unnecessary given the try-catch clause
		}
		catch(IllegalConstruction | NullPointerException e) { fail(); }
	}

	@Test
	public void testNullParamList() {
		params = null;
		assertThrows(IllegalConstruction.class, () -> op = new Plus(params));
	}

	@Test
	public void testCompute() {
		assertEquals(value1+value2, op.compute().intValue());
		assertEquals(14, op.compute().intValue());
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
		assertEquals("( 8 + 6 )", op.toString());
	}

	@Test
	public void testPrefix() {
		assertEquals("+ (8, 6)", op.toString(Notation.PREFIX));
		op.notation = Notation.PREFIX;
		assertEquals("+ (8, 6)", op.toString());
	}

	@Test
	public void testInfix() {
		assertEquals("( 8 + 6 )", op.toString(Notation.INFIX));
		op.notation = Notation.INFIX;
		assertEquals("( 8 + 6 )", op.toString());
	}

	@Test
	public void testPostfix() {
		assertEquals("(8, 6) +", op.toString(Notation.POSTFIX));
		op.notation = Notation.POSTFIX;
		assertEquals("(8, 6) +", op.toString());
	}

}
