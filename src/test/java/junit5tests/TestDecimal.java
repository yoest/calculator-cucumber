package junit5tests;

import calculator.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import real.Rounding;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class TestDecimal {

    private final String value = "2.3";
    private final String value2 = "7.8";

    private final int precision = 3;

    private Rounding rounding = Rounding.ROUND_HALF_UP;

    private List<Expression> params;
    private Calculator calc;

    @BeforeEach
    /**
     * Set up the calculator and the parameters for the tests
     */
    void setUp() {
        params = Arrays.asList(new MyNumber(value), new MyNumber(value2));
        calc = new Calculator(precision, rounding);
    }

    @Test
    /**
     * Test the precision of the calculator
     */
    void testPrecisions() {
        calc.setPrecision(0);
        assertEquals(calc.eval(params.get(0)), new MyNumber("2").getValue());
        calc.setPrecision(1);
        assertEquals(calc.eval(params.get(0)), new MyNumber("2.3").getValue());
        calc.setPrecision(2);
        assertEquals(calc.eval(params.get(0)), new MyNumber("2.30").getValue());
        calc.setPrecision(3);
        assertEquals(calc.eval(params.get(0)), new MyNumber("2.300").getValue());
    }

    @Test
    /**
     * Test the rounding of the calculator
     */
    void testRounding() {
        calc.setPrecision(1);
        MyNumber number = new MyNumber("2.36");
        rounding = Rounding.ROUND_HALF_UP;
        calc.setRounding(rounding);
        assertEquals(calc.eval(number), new MyNumber("2.4").getValue());
        rounding = Rounding.ROUND_HALF_DOWN;
        calc.setRounding(rounding);
        assertEquals(calc.eval(number), new MyNumber("2.4").getValue());
        rounding = Rounding.ROUND_HALF_EVEN;
        calc.setRounding(rounding);
        assertEquals(calc.eval(number), new MyNumber("2.4").getValue());
        rounding = Rounding.ROUND_UP;
        calc.setRounding(rounding);
        assertEquals(calc.eval(number), new MyNumber("2.4").getValue());
        rounding = Rounding.ROUND_DOWN;
        calc.setRounding(rounding);
        assertEquals(calc.eval(number), new MyNumber("2.3").getValue());
        rounding = Rounding.ROUND_CEILING;
        calc.setRounding(rounding);
        assertEquals(calc.eval(number), new MyNumber("2.4").getValue());
        rounding = Rounding.ROUND_FLOOR;
        calc.setRounding(rounding);
        assertEquals(calc.eval(number), new MyNumber("2.3").getValue());
    }


    @Test
    /**
     * Test the plus operation with decimal numbers
     */
    void testPlus() throws IllegalConstruction {
        Expression exp = new Plus(params);
        assertEquals("10.100", calc.eval(exp).toString());
    }

    @Test
    /**
     * Test the minus operation with decimal numbers
     */
    void testMinus() throws IllegalConstruction {
        Expression exp = new Minus(params);
        assertEquals("-5.500", calc.eval(exp).toString());
    }

    @Test
    /**
     * Test the times operation with decimal numbers
     */
    void testTime() throws IllegalConstruction {
        Expression exp = new Times(params);
        assertEquals("17.940", calc.eval(exp).toString());
    }

    @Test
    /**
     * Test the divide operation with decimal numbers
     */
    void testDivide() throws IllegalConstruction {
        Expression exp = new Divides(params);
        assertEquals("0.295", calc.eval(exp).toString());
    }

    @Test
    /**
     * Test the divide by zero operation with decimal numbers
     */
    void testDivideByZero() throws IllegalConstruction {
        params = Arrays.asList(new MyNumber(value), new MyNumber("0"));
        Expression exp = new Divides(params);
        assertThrows(ArithmeticException.class, () -> calc.eval(exp));
    }
}
