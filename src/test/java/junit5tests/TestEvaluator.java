package junit5tests;

//Import Junit5 libraries for unit testing:
import static junit.framework.TestCase.fail;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import calculator.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import real.Rounding;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class TestEvaluator {

    private Calculator calc;
    private int value1, value2;
    private Expression op;

    private int precision = 2;
    private Rounding rounding = Rounding.ROUND_HALF_UP;

    @BeforeEach
    void setUp() {
        calc = new Calculator(precision, rounding);
        value1 = 8;
        value2 = 6;
    }

    @Test
    void testEvaluatorMyNumber() throws IllegalConstruction {
        assertEquals( new BigDecimal(value1).setScale(precision, rounding.toRoundingMode()), calc.eval(new MyNumber(value1)));
    }

    @ParameterizedTest
    @ValueSource(strings = {"*", "+", "/", "-"})
    void testEvaluateOperations(String symbol) {
        List<Expression> params = Arrays.asList(new MyNumber(value1),new MyNumber(value2));
        try {
            //construct another type of operation depending on the input value
            //of the parameterised test
            BigDecimal v1= new BigDecimal(value1);
            BigDecimal v2= new BigDecimal(value2);
            switch (symbol) {
                case "+"	->	assertEquals( v1.add(v2).setScale(precision, rounding.toRoundingMode()), calc.eval(new Plus(params)));
                case "-"	->	assertEquals( v1.subtract(v2).setScale(precision, rounding.toRoundingMode()), calc.eval(new Minus(params)));
                case "*"	->	assertEquals( v1.multiply(v2).setScale(precision, rounding.toRoundingMode()), calc.eval(new Times(params)));
                case "/"	->	assertEquals( v1.divide(v2, precision, rounding.toRoundingMode()), calc.eval(new Divides(params)));
                default		->	fail();
            }
        } catch (IllegalConstruction e) {
            fail();
        }
    }

}
