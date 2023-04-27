package junit5tests;

import calculator.*;
import calculatorParser.lexer;
import calculatorParser.parser;
import static org.junit.jupiter.api.Assertions.*;

import gui.MainCalculatorPane;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.math.BigInteger;

public class TestParser {

  private Calculator calc;

  @BeforeEach
  void setUp() {
    calc = new Calculator();
    MainCalculatorPane.IS_INTEGER_MODE = true;
    MainCalculatorPane.INPUT_RADIX = 10;
    MainCalculatorPane.OUTPUT_RADIX = 10;
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
  void testParserSimpleModulo() throws Exception {
    String input = "45 % 23";
    parser p = new parser(new lexer(new java.io.StringReader(input)));
    Object result = p.parse().value;
    Expression e = (Expression) result;
    assertEquals(new BigInteger(String.valueOf(22)), calc.eval(e));
  }

  @Test
  void testParserSimpleModuloInv() throws Exception {
    String input = "15 $ 4";
    parser p = new parser(new lexer(new java.io.StringReader(input)));
    Object result = p.parse().value;
    Expression e = (Expression) result;
    assertEquals(new BigInteger(String.valueOf(3)), calc.eval(e));
  }

  @Test
  void testParserImpModuloInv() throws Exception{
      String input = "15 $ 3";
      parser p = new parser(new lexer(new java.io.StringReader(input)));
      Object result = p.parse().value;
      Expression e = (Expression) result;
      assertThrows(Exception.class, () -> calc.eval(e));
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
      String input = "(10 ((4 0 +) 3 ( 1 1 +) * +) -)";
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

  @Test
  void testIntegerMode2Input() {
    MainCalculatorPane.INPUT_RADIX = 2;
    String input = "0101101";
    parser p = new parser(new lexer(new java.io.StringReader(input)));
      Object result = null;
      try {
          result = p.parse().value;
          MyNumber e = (MyNumber) result;
          assertEquals(2, e.getRadix());
          assertEquals(new BigInteger(String.valueOf(45)), calc.eval(e));

      } catch (Exception e) {
          throw new RuntimeException(e);
      }
  }

    @Test
    void testIntegerMode2WrongInput() {
        MainCalculatorPane.INPUT_RADIX = 2;
        String input = "12a4t6";
        parser p = new parser(new lexer(new java.io.StringReader(input)));
        assertThrows(Exception.class, p::parse);

    }

  @Test
  void testIntegerMode() throws Exception {
      String input = "1";
      parser p = new parser(new lexer(new java.io.StringReader(input)));
      Object result = null;
      try {
        result = p.parse().value;
        MyNumber e = (MyNumber) result;
        //assert that e.getvalue is an instance of biginteger
        assertEquals(e.getValue().getClass(), BigInteger.class);

      } catch (Exception e) {
        throw new RuntimeException(e);
      }
  }

  @Test
  void testDecimalMode() throws Exception {
    String input = "1.5";
    MainCalculatorPane.IS_INTEGER_MODE = false;
    parser p = new parser(new lexer(new java.io.StringReader(input)));
    Object result = null;
    try {
      result = p.parse().value;
      MyNumber e = (MyNumber) result;
      assertEquals(e.getValue().getClass(), BigDecimal.class);

    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}
