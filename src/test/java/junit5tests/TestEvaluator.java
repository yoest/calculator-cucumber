package junit5tests;

//Import Junit5 libraries for unit testing:
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import calculator.*;

import java.util.Arrays;

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
        assertEquals( value1,
                      calc.eval(new MyNumber(value1)));
    }

    @Test
    void testEvaluatorDivides() {
        try { op = new Divides(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
          assertEquals( value1 / value2,
                        calc.eval(op) );
          }
        catch(IllegalConstruction e) {
            fail();
        }
    }

    @Test
    void testEvaluatorPlus() {
        try { op = new Plus(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
            assertEquals( value1 + value2,
                    calc.eval(op) );
        }
        catch(IllegalConstruction e) {
            fail();
        }
    }

    @Test
    void testEvaluatorMinus() {
        try { op = new Minus(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
            assertEquals( value1 - value2,
                    calc.eval(op) );
        }
        catch(IllegalConstruction e) {
            fail();
        }
    }

    @Test
    void testEvaluatorTimes() {
        try { op = new Times(Arrays.asList(new MyNumber(value1), new MyNumber(value2)));
            assertEquals( value1 * value2,
                    calc.eval(op) );
        }
        catch(IllegalConstruction e) {
            fail();
        }
    }

}
