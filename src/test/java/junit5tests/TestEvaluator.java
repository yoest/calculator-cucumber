package junit5tests;

//Import Junit5 libraries for unit testing:
import static junit.framework.TestCase.fail;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import calculator.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TestEvaluator {

    private Calculator calc;
    private int value1, value2;
    private Expression op;

    @BeforeEach
    void setUp() {
        calc = new Calculator();
        value1 = 8;
        value2 = 6;
    }

    @Test
    void testEvaluatorMyNumber() {
        assertEquals(0, BigInteger.valueOf(value1).compareTo(calc.eval(new MyNumber(value1))));
        //Fine because if I create a MyNumber with a int value, I can get the value back in bigInteger Format
        //TODO: Test with bigInteger
    }

    @ParameterizedTest
    @ValueSource(strings = {"*", "+", "/", "-", "/0"})
    void testEvaluateOperations(String symbol) {
        List<Expression> params = Arrays.asList(new MyNumber(value1),new MyNumber(value2));
        List<Expression> params2 = Arrays.asList(new MyNumber(value1),new MyNumber(0));
        try {
            //construct another type of operation depending on the input value
            //of the parameterised test
            switch (symbol) {
                case "+"	->	assertEquals(0, BigInteger.valueOf(value1 + value2).compareTo(calc.eval(new Plus(params))));
                case "-"	->	assertEquals(0, BigInteger.valueOf(value1 - value2).compareTo(calc.eval(new Minus(params))));
                case "*"	->	assertEquals(0, BigInteger.valueOf(value1 * value2).compareTo(calc.eval(new Times(params))));
                case "/"	->	assertEquals(0, BigInteger.valueOf(value1 / value2).compareTo(calc.eval(new Divides(params))));
                case "/0"   -> assertThrows(ArithmeticException.class, () -> calc.eval(new Divides(params2)));
                default		->	fail();
            }
        } catch (IllegalConstruction e) {
            fail();
        }
    }

}
