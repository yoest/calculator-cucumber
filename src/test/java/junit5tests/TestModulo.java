package junit5tests;

import calculator.*;
import integerArithmetics.Modulo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestModulo {

  private final int value1 = 8;
  private final int value2 = 6;
  private Modulo op;
  private List<Expression> params;

  @BeforeEach
  void setUp() {
    params = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
    try {
      op = new Modulo(params);
      op.notation = Notation.INFIX; // reset the notation to infix (which is the default) before each test
    }
    catch(IllegalConstruction e) { fail(); }
  }

  @Test
  void testConstructor1() {
    // It should not be possible to create an expression without null parameter list
    assertThrows(IllegalConstruction.class, () -> op = new Modulo(null));
  }

  @SuppressWarnings("AssertBetweenInconvertibleTypes")
  @Test
  void testConstructor2() {
    // A Times expression should not be the same as a Divides expression
    try {
      assertNotSame(op, new Times(new ArrayList<>()));
    } catch (IllegalConstruction e) {
      fail();
    }
  }

  @Test
  void testEquals() {
    // Two similar expressions, constructed separately (and using different constructors) should be equal
    List<Expression> p = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
    try {
      Modulo d = new Modulo(p, Notation.INFIX);
      assertEquals(op, d);
    }
    catch(IllegalConstruction e) { fail(); }
  }

  @Test
  void testNull() {
    assertDoesNotThrow(() -> op==null); // Direct way to to test if the null case is handled.
  }

  @Test
  void testHashCode() {
    // Two similar expressions, constructed separately (and using different constructors) should have the same hashcode
    List<Expression> p = Arrays.asList(new MyNumber(value1), new MyNumber(value2));
    try {
      Modulo e = new Modulo(p, Notation.INFIX);
      assertEquals(e.hashCode(), op.hashCode());
    }
    catch(IllegalConstruction e) { fail(); }
  }

  @Test
  void testNullParamList() {
    params = null;
    assertThrows(IllegalConstruction.class, () -> op = new Modulo(params));
  }
}
