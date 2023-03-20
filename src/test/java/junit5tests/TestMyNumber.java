package junit5tests;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import calculator.*;

import java.util.ArrayList;

class TestMyNumber {

	private final int value =8;
	private MyNumber number;
	
	@BeforeEach
	void setUp() {
		number = new MyNumber(value);
	}

	@Test
	void testEquals() {
		// Two distinct MyNumber, constructed separately (using a different constructor) but containing the same value should be equal
		assertEquals(new MyNumber(value), number);
		// Two MyNumbers containing a distinct value should not be equal:
		int otherValue = 7;
		assertNotEquals(new MyNumber(otherValue),number);
		assertEquals(number, number); // Identity check (for coverage, as this should always be true)
		assertNotEquals(number, value); // number is of type MyNumber, while value is of type int, so not equal
		try {
			assertNotEquals(new Times(new ArrayList<>()), number);
		}
		catch (IllegalConstruction e) {fail();}
	}

	@Test
	void testToString() {
		assertEquals(Integer.toString(value), number.toString());
	}

	@Test
	void testRadixConversion() {
		int radix = 2;
		number = new MyNumber(Integer.toString(value, radix), radix);
		assertEquals(Integer.toString(value, radix), number.toString());
		number.setRadix(10);
		assertEquals(Integer.toString(value), number.toString());
	}

	@Test
	void testRadixException() {
		int radix = 37;
		assertThrows(IllegalArgumentException.class, () -> number.setRadix(radix));
		assertThrows(IllegalArgumentException.class, () -> new MyNumber("1", radix));
	}
}
