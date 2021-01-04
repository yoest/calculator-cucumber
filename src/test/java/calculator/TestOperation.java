package calculator;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestOperation implements TestInterface {

	Operation o;
	
	@BeforeEach
	public void setUp() throws Exception {
		List<Expression> params = new ArrayList<>();
		Collections.addAll(params, new MyNumber(3), new MyNumber(4), new MyNumber(5));
		
		List<Expression> params2 = new ArrayList<>();
		Collections.addAll(params2, new MyNumber(5), new MyNumber(4));

		List<Expression> params3 = new ArrayList<>();
		Collections.addAll(params3, new Plus(params), new Minus(params2), new MyNumber(7));
		o = new Divides(params3);

	}

	@Test
	public void test_compute() {
		assertEquals(Integer.valueOf(1), o.compute());
	}

	@Test
	public void test_countDepth() {
		assertEquals(Integer.valueOf(2), o.countDepth());
	}

	@Test
	public void test_countOps() {
		assertEquals(Integer.valueOf(3), o.countOps());
	}

	@Test
	public void test_countNbs() {
		assertEquals(Integer.valueOf(6), o.countNbs());
	}

	@Disabled
	public void test_toString() {
		//test not implemented for this class
	}

}
