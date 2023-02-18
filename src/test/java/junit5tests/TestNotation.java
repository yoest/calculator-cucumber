package junit5tests;

//Import Junit5 libraries for unit testing:
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import calculator.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


class TestNotation {

    /* This is an auxilary method to avoid code duplication in the next three tests.
       Could probably be improved further by writing a @ParameterizedTest
     */
	void testNotation(String s,Operation o,Notation n) {
		assertEquals(s, o.toString(n));
		o.notation = n;
		assertEquals(s, o.toString());
	}

	/* This is an auxilary method to avoid code duplication.
       Could probably be improved further by writing a @ParameterizedTest
     */
	void testNotations(String symbol,int value1,int value2,Operation op) {
		//prefix notation:
		testNotation(symbol +" (" + value1 + ", " + value2 + ")", op, Notation.PREFIX);
		//infix notation:
		testNotation("( " + value1 + " " + symbol + " " + value2 + " )", op, Notation.INFIX);
		//postfix notation:
		testNotation("(" + value1 + ", " + value2 + ") " + symbol, op, Notation.POSTFIX);
	}
	@Test
	void testPrefix() {
		int value1 = 8;
		int value2 = 6;
		List<Expression> params = new ArrayList<>(Arrays.asList(new MyNumber(value1),new MyNumber(value2)));
		try {
		  testNotations("*", value1, value2,new Times(params));
		  testNotations("+", value1, value2,new Plus(params));
		  testNotations("/", value1, value2,new Divides(params));
		  testNotations("-", value1, value2,new Minus(params));
		  }
		catch(IllegalConstruction e) { fail(); }
	}

}
