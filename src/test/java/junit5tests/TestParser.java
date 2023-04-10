package junit5tests;

import calculator.*;
import calculatorParser.lexer;
import calculatorParser.parser;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.math.BigInteger;

public class TestParser {

  private Calculator calc;

  @BeforeEach
  void setUp() {
    calc = new Calculator();
  }
  @Test
  void testParserSimpleAddition() throws Exception {
      String input = "1+1";
      parser p = new parser(new lexer(new java.io.StringReader(input)));
      Object result = p.parse().value;
      Expression e = (Expression) result;
      assertEquals(new BigInteger(String.valueOf(2)), calc.eval(e));
  }

  @Test
  void testParserSimpleMinus() throws Exception {
      String input = "5-4";
      parser p = new parser(new lexer(new java.io.StringReader(input)));
      Object result = p.parse().value;
      Expression e = (Expression) result;
      assertEquals(new BigInteger(String.valueOf(1)), calc.eval(e));
  }

  @Test
  void testParserSimpleTimes() throws Exception {
      String input = "10*10";
      parser p = new parser(new lexer(new java.io.StringReader(input)));
      Object result = p.parse().value;
      Expression e = (Expression) result;
      assertEquals(new BigInteger(String.valueOf(100)), calc.eval(e));
  }

  @Test
  void testParserSimpleDivide() throws Exception {
      String input = "4/2";
      parser p = new parser(new lexer(new java.io.StringReader(input)));
      Object result = p.parse().value;
      Expression e = (Expression) result;
      assertEquals(new BigInteger(String.valueOf(2)), calc.eval(e));
  }

  @Test
  void testParserPriority() throws Exception {
      String input = "1+4/2";
      parser p = new parser(new lexer(new java.io.StringReader(input)));
      Object result = p.parse().value;
      Expression e = (Expression) result;
      assertEquals(new BigInteger(String.valueOf(3)), calc.eval(e));
  }

  @Test
  void testParserComplex() throws Exception {
      String input = "((1+2)*6)/(2*3)";
      parser p = new parser(new lexer(new java.io.StringReader(input)));
      Object result = p.parse().value;
      Expression e = (Expression) result;
      assertEquals(new BigInteger(String.valueOf(3)), calc.eval(e));
  }

  @Test
  void testParserParenthesis() throws Exception {
      String input = "(2+4)/3";
      parser p = new parser(new lexer(new java.io.StringReader(input)));
      Object result = p.parse().value;
      Expression e = (Expression) result;
      assertEquals(new BigInteger(String.valueOf(2)), calc.eval(e));
  }

  @Test
  void testPreFixSimple() throws Exception {
      String input = "/ 4 2";
      parser p = new parser(new lexer(new java.io.StringReader(input)));
      Object result = p.parse().value;
      Expression e = (Expression) result;
      assertEquals(new BigInteger(String.valueOf(2)), calc.eval(e));
  }

   @Test
    void testPreFixComposed() throws Exception {
        String input = "* + 1 1 2";
        parser p = new parser(new lexer(new java.io.StringReader(input)));
        Object result = p.parse().value;
        Expression e = (Expression) result;
        assertEquals(new BigInteger(String.valueOf(4)), calc.eval(e));
    }

  @Test
  void testPreFixParenthesisSimple() throws Exception {
      String input = "(* 2 5)";
      parser p = new parser(new lexer(new java.io.StringReader(input)));
      Object result = p.parse().value;
      Expression e = (Expression) result;
      assertEquals(new BigInteger(String.valueOf(10)), calc.eval(e));
  }

  @Test
  void testPreFixParenthesisComplex1() throws Exception {
    String input = "+ (* 2 5) 1 ";
    parser p = new parser(new lexer(new java.io.StringReader(input)));
    Object result = p.parse().value;
    Expression e = (Expression) result;
    assertEquals(new BigInteger(String.valueOf(11)), calc.eval(e));
  }

  @Test
  void testPreFixParenthesisComplex2() throws Exception {
    String input = "+ 1 (* 2 5)";
    parser p = new parser(new lexer(new java.io.StringReader(input)));
    Object result = p.parse().value;
    Expression e = (Expression) result;
    assertEquals(new BigInteger(String.valueOf(11)), calc.eval(e));
  }

  @Test
  void testPostFixSimple() throws Exception {
      String input = "1 1 +";
      parser p = new parser(new lexer(new java.io.StringReader(input)));
      Object result = p.parse().value;
      Expression e = (Expression) result;
      assertEquals(new BigInteger(String.valueOf(2)), calc.eval(e));
  }

  @Test
  void testPostFixComposed() throws Exception {
      String input = "1 1 2 4 + + +";
      parser p = new parser(new lexer(new java.io.StringReader(input)));
      Object result = p.parse().value;
      Expression e = (Expression) result;
      assertEquals(new BigInteger(String.valueOf(8)), calc.eval(e));
  }

  @Test
  void testPostFixParenthesisSimple() throws Exception {
      String input = "( 1 1 +)";
      parser p = new parser(new lexer(new java.io.StringReader(input)));
      Object result = p.parse().value;
      Expression e = (Expression) result;
      assertEquals(new BigInteger(String.valueOf(2)), calc.eval(e));
  }

  @Test
  void testPostFixParenthesisComplex() throws Exception {
      String input = "(10 (4 3 ( 1 1 +) * +) -)";
      parser p = new parser(new lexer(new java.io.StringReader(input)));
      Object result = p.parse().value;
      Expression e = (Expression) result;
      assertEquals(new BigInteger(String.valueOf(0)), calc.eval(e));
  }

  @Test
  void testPostFixParenthesisComplex2() throws Exception {
    String input = "( ( 1 1 +) 2 -)";
    parser p = new parser(new lexer(new java.io.StringReader(input)));
    Object result = p.parse().value;
    Expression e = (Expression) result;
    assertEquals(new BigInteger(String.valueOf(0)), calc.eval(e));
  }

  @Test
  void testMultipleNotation() {
      String input = "1 1 + 2 *";
      parser p = new parser(new lexer(new java.io.StringReader(input)));
      assertThrows(Exception.class, p::parse);
  }

}
