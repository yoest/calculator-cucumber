package junit5tests;

import real.Rounding;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import calculator.*;

public class TestRadian {

    private MyNumber valueInRad;
    private MyNumber valueInDeg;

    private Calculator calc;

    private final int precision = 2;

    private final Rounding rounding = Rounding.ROUND_HALF_UP;

    @BeforeEach
    void setUp() {
        valueInRad = new MyNumber("56.87");
        valueInDeg = new MyNumber("3258.41");
        calc = new Calculator(precision, rounding);
    }

    /**
     * Test the conversion from radian to degree
     */
    @Test
    void testRadianToDegree() {
        assertEquals(calc.eval(valueInDeg), calc.eval(valueInRad.toDegree()));
    }

    /**
     * Test the conversion from degree to radian
     */
    @Test
    void testDegreeToRadian() {
        assertEquals(calc.eval(valueInRad), calc.eval(valueInDeg.toRadian()));
    }
}
