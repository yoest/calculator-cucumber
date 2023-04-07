package junit5tests;

//Import Junit5 libraries for unit testing:

import calculator.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TestTimeEvaluator {

    private TimeCalculator calc;
    private long value1, value2;

    @BeforeEach
    void setUp() {
        calc = new TimeCalculator();

        // 15:30:00
        value1 = 15 * 3600 + 30 * 60;

        // 16:45:00
        value2 = 16 * 3600 + 45 * 60;
    }

    @Test
    void testEvaluatorMyTime() {
        assertEquals(value1, calc.eval(MyTime.getAsHours("15:30")));
    }

    @ParameterizedTest
    @ValueSource(strings = {"*", "+", "/", "-"})
    void testEvaluateOperations(String symbol) {
        List<Expression> params = Arrays.asList(MyTime.getAsHours("15:30"), MyTime.getAsHours("16:45"));

        try {
            //construct another type of operation depending on the input value
            //of the parameterised test
            switch (symbol) {
                case "+"	->	assertEquals( value1 + value2, calc.eval(new Plus(params)));
                case "-"	->	assertEquals( value1 - value2, calc.eval(new Minus(params)));
                case "*"	->	assertThrows( RuntimeException.class, () -> calc.eval(new Times(params)));
                case "/"	->	assertThrows( RuntimeException.class, () -> calc.eval(new Divides(params)));
                default		->	Assertions.fail();
            }
        } catch (IllegalConstruction e) {
            Assertions.fail();
        }
    }

    @Test
    void testPrintToConsole() {
        // Change the output of println
        PrintStream originalOut = System.out;
        ByteArrayOutputStream newOut = new ByteArrayOutputStream();

        System.setOut(new PrintStream(newOut));

        // Test the printing method
        try {
            List<Expression> params = Arrays.asList(MyTime.getAsHours("6:00"), MyTime.getAsHours("12:00"));
            Expression e = new Plus(params);
            calc.print(e);

            String expectedOutput = "The result of evaluating expression " + e + "\n" +
                    "is: \n" +
                    "  - As date: 1970-01-01T18:00Z[Etc/UTC]\n" +
                    "  - As a complete string: 0 days, 18 hours, 0 minutes and 0 seconds\n" +
                    "  - As days: 0.75 days\n" +
                    "  - As hours: 18.0 hours\n" +
                    "  - As minutes: 1080.0 minutes\n" +
                    "  - As seconds: 64800 seconds\n";
            assertEquals(expectedOutput, newOut.toString());
        } catch (IllegalConstruction e) {
            Assertions.fail();
        }


        // Restore the output of println
        System.setOut(originalOut);
    }

}
