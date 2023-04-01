package calculator;


import java.math.BigInteger;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/** This class represents the arithmetic division operation "/".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * @see Operation
 * @see Minus
 * @see Times
 * @see Plus
 */
public final class Divides extends Operation
{

  /**
   * Class constructor specifying a number of Expressions to divide.
   *
   * @param elist The list of Expressions to divide
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Divides(List<Expression>,Notation)
   */
  public /*constructor*/ Divides(List<Expression> elist) throws IllegalConstruction {
	this(elist, null);
  }

  /**
   * Class constructor specifying a number of Expressions to divide,
   * as well as the notation used to represent the operation.
   *
   * @param elist The list of Expressions to divide
   * @param n The Notation to be used to represent the operation
   * @throws IllegalConstruction  If an empty list of expressions if passed as parameter
   * @see #Divides(List<Expression>)
   * @see Operation#Operation(List<Expression>,Notation)
   */
  public Divides(List<Expression> elist, Notation n) throws IllegalConstruction {
	super(elist,n);
	symbol = "/";
	neutral = new BigInteger("1");
  }

  private void verify(BigDecimal l, BigDecimal r) throws IllegalConstruction {
        if (r.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalConstruction("Division by zero");
        }
  }

  /**
   * Abstract method representing the actual binary arithmetic operation to compute
   *
   * @param l first argument of the binary operation
   * @param r second argument of the binary operation
   * @return result of computing the binary operation
   */
  public Number op(Number l, Number r)
    {
        if (l instanceof BigInteger && r instanceof BigInteger)
            return op((BigInteger)l, (BigInteger)r);
        else if (l instanceof BigDecimal && r instanceof BigDecimal)
            return op((BigDecimal)l, (BigDecimal)r);
        else
            throw new IllegalArgumentException("Unsupported number type");
    }

    public BigInteger op(BigInteger l, BigInteger r) {
        if (r.equals(BigInteger.ZERO))
            throw new ArithmeticException("Division by zero");
        else
            return (l.divide(r));
  }
    public BigDecimal op(BigDecimal l, BigDecimal r){
        if (r.equals(BigDecimal.ZERO))
            throw new ArithmeticException("Division by zero");
        else
            return (l.divide(r, 10, RoundingMode.HALF_UP));
    }
}
