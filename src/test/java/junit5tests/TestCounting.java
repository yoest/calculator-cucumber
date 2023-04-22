package junit5tests;

//Import Junit5 libraries for unit testing:

import calculator.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import visitor.CountingEvaluator;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TestCounting {

    private int value1, value2;
    private Expression e;

    @BeforeEach
    void setUp() {
        value1 = 8;
        value2 = 6;
        e = null;
    }

    @Test
    void testNumberCounting() {
        e = new MyNumber(value1);

        CountingEvaluator countingEvaluator = new CountingEvaluator();
        countingEvaluator.count(e);

        //test whether a number has zero depth (i.e. no nested expressions)
        assertEquals(0, countingEvaluator.getCountDepth());
        //test whether a number contains zero operations
        assertEquals(0, countingEvaluator.getCountOps());
        //test whether a number contains 1 number
        assertEquals(1, countingEvaluator.getCountNumber());
    }

    @ParameterizedTest
    @ValueSource(strings = {"*", "+", "/", "-"})
    void testOperationCounting(String symbol) {
        List<Expression> params = Arrays.asList(new MyNumber(value1),new MyNumber(value2));
        //Operation op = null;
        try {
            //construct another type of operation depending on the input value
            //of the parameterised test
            switch (symbol) {
                case "+"	->	e = new Plus(params);
                case "-"	->	e = new Minus(params);
                case "*"	->	e = new Times(params);
                case "/"	->	e = new Divides(params);
                default		->	Assertions.fail();
            }
        } catch (IllegalConstruction e) {
            Assertions.fail();
        }

        CountingEvaluator countingEvaluator = new CountingEvaluator();
        countingEvaluator.count(e);

        //test whether a binary operation has depth 1
        assertEquals(1, countingEvaluator.getCountDepth(), "counting depth of an Operation");
        //test whether a binary operation contains 1 operation
        assertEquals(1, countingEvaluator.getCountOps());
        //test whether a binary operation contains 2 numbers
        assertEquals(2, countingEvaluator.getCountNumber());
    }

}
