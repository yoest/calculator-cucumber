package junit5tests;

import calculator.Expression;
import calculator.TimeCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import timeCalculatorParser.lexer;
import timeCalculatorParser.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TestTimeParser {

    private TimeCalculator calc;

    @BeforeEach
    void setUp() {
        calc = new TimeCalculator();
    }
    @Test
    void testParserSimpleAddition() throws Exception {
          String input = "10:00_+_3:00";
          parser p = new parser(new lexer(new java.io.StringReader(input)));
          Object result = p.parse().value;
          Expression e = (Expression) result;

          String expected = "13.0 hours";
          String res = calc.getResultAsFractionalHours(calc.eval(e));
          assertEquals(expected, res);
    }

    @Test
    void testParserSimpleMinus() throws Exception {
        String input = "10:00_-_3:00";
        parser p = new parser(new lexer(new java.io.StringReader(input)));
        Object result = p.parse().value;
        Expression e = (Expression) result;

        String expected = "7.0 hours";
        String res = calc.getResultAsFractionalHours(calc.eval(e));
        assertEquals(expected, res);
    }

    @Test
    void testParserComplex() throws Exception {
        String input = "10:00_-_3:08_+_4:09:57";
        parser p = new parser(new lexer(new java.io.StringReader(input)));
        Object result = p.parse().value;
        Expression e = (Expression) result;

        String expected = "11.0325 hours";
        String res = calc.getResultAsFractionalHours(calc.eval(e));
        assertEquals(expected, res);
    }

    @Test
    void testParserParenthesis() throws Exception {
        String input = "10:00_-_(3:08_+_4:09:51)";
        parser p = new parser(new lexer(new java.io.StringReader(input)));
        Object result = p.parse().value;
        Expression e = (Expression) result;

        String expected = "2.7025 hours";
        String res = calc.getResultAsFractionalHours(calc.eval(e));
        assertEquals(expected, res);
    }

    @Test
    void testPreFixSimple() throws Exception {
        String input = "_+_10:00 2:00";
        parser p = new parser(new lexer(new java.io.StringReader(input)));
        Object result = p.parse().value;
        Expression e = (Expression) result;

        String expected = "12.0 hours";
        String res = calc.getResultAsFractionalHours(calc.eval(e));
        assertEquals(expected, res);
    }

    @Test
    void testPostFixSimple() throws Exception {
        String input = "10:00 2:00_+_";
        parser p = new parser(new lexer(new java.io.StringReader(input)));
        Object result = p.parse().value;
        Expression e = (Expression) result;

        String expected = "12.0 hours";
        String res = calc.getResultAsFractionalHours(calc.eval(e));
        assertEquals(expected, res);
    }

    @Test
    void testMultipleNotation() {
        String input = "1 1 + 2 *";
        parser p = new parser(new lexer(new java.io.StringReader(input)));
        assertThrows(Exception.class, p::parse);
    }
}
