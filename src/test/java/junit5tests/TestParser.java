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
  void testParserSimpleAddition() {
    try {
      String input = "1+1";
      parser p = new parser(new lexer(new java.io.StringReader(input)));
      Object result = p.parse().value;
      Expression e = (Expression) result;
      assertEquals(new BigInteger(String.valueOf(2)), calc.eval(e));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void testParserSimpleMinus() {
    try {
      String input = "5-4";
      parser p = new parser(new lexer(new java.io.StringReader(input)));
      Object result = p.parse().value;
      Expression e = (Expression) result;
      assertEquals(new BigInteger(String.valueOf(1)), calc.eval(e));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void testParserSimpleTimes() {
    try {
      String input = "10*10";
      parser p = new parser(new lexer(new java.io.StringReader(input)));
      Object result = p.parse().value;
      Expression e = (Expression) result;
      assertEquals(new BigInteger(String.valueOf(100)), calc.eval(e));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void testParserSimpleDivide() {
    try {
      String input = "4/2";
      parser p = new parser(new lexer(new java.io.StringReader(input)));
      Object result = p.parse().value;
      Expression e = (Expression) result;
      assertEquals(new BigInteger(String.valueOf(2)), calc.eval(e));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void testParserPriority() {
    try {
      String input = "1+4/2";
      parser p = new parser(new lexer(new java.io.StringReader(input)));
      Object result = p.parse().value;
      Expression e = (Expression) result;
      assertEquals(new BigInteger(String.valueOf(3)), calc.eval(e));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void testParserComplex() {
    try {
      String input = "((1+2)*6)/(2*3)";
      parser p = new parser(new lexer(new java.io.StringReader(input)));
      Object result = p.parse().value;
      Expression e = (Expression) result;
      assertEquals(new BigInteger(String.valueOf(3)), calc.eval(e));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

    @Test
    void testParserParenthesis() {
      try {
        String input = "(2+4)/3";
        parser p = new parser(new lexer(new java.io.StringReader(input)));
        Object result = p.parse().value;
        Expression e = (Expression) result;
        assertEquals(new BigInteger(String.valueOf(2)), calc.eval(e));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    @Test
    void testPreFixSimple(){
      try{
        String input = "/ 4 2";
        parser p = new parser(new lexer(new java.io.StringReader(input)));
        Object result = p.parse().value;
        Expression e = (Expression) result;
        assertEquals(new BigInteger(String.valueOf(2)), calc.eval(e));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

     @Test
      void testPreFixComposed() {
        try {
          String input = "* + 1 1 2";
          parser p = new parser(new lexer(new java.io.StringReader(input)));
          Object result = p.parse().value;
          Expression e = (Expression) result;
          assertEquals(new BigInteger(String.valueOf(4)), calc.eval(e));
        } catch (Exception e) {
          e.printStackTrace();
        }
      }

  @Test
  void testPostFixSimple() {
    try {
      String input = "1 1 +";
      parser p = new parser(new lexer(new java.io.StringReader(input)));
      Object result = p.parse().value;
      Expression e = (Expression) result;
      assertEquals(new BigInteger(String.valueOf(2)), calc.eval(e));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void testPostFixComposed() {
    try {
      String input = "1 1 2 4 + + +";
      parser p = new parser(new lexer(new java.io.StringReader(input)));
      Object result = p.parse().value;
      Expression e = (Expression) result;
      assertEquals(new BigInteger(String.valueOf(8)), calc.eval(e));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void testPostFixParenthesisSimple() {
    try {
      String input = "( 1 1 +)";
      parser p = new parser(new lexer(new java.io.StringReader(input)));
      Object result = p.parse().value;
      Expression e = (Expression) result;
      assertEquals(new BigInteger(String.valueOf(2)), calc.eval(e));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void testPostFixParenthesisComplex() {
    try {
      String input = "(10 (4 3 ( 1 1 +) * +) -)";
      parser p = new parser(new lexer(new java.io.StringReader(input)));
      Object result = p.parse().value;
      Expression e = (Expression) result;
      assertEquals(new BigInteger(String.valueOf(0)), calc.eval(e));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void testMultipleNotation() {
    try {
      String input = "1 1 + 2 *";
      parser p = new parser(new lexer(new java.io.StringReader(input)));
      assertThrows(Exception.class, p::parse);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

}
