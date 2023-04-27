package calculator;

import java.math.BigInteger;
import java.math.BigDecimal;
import java.util.List;

/** This class represents the arithmetic multiplication operation "*".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * @see Operation
 * @see Minus
 * @see Plus
 * @see Divides
 */
public final class Times extends Operation
 {
  /**
   * Class constructor specifying a number of Expressions to multiply.
   *
   * @param elist The list of Expressions to multiply
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Times(List<Expression>,Notation)
   */
  public /*constructor*/ Times(List<Expression> elist) throws IllegalConstruction {
  	this(elist, null);
  }

  /**
   * Class constructor specifying a number of Expressions to multiply,
   * as well as the Notation used to represent the operation.
   *
   * @param elist The list of Expressions to multiply
   * @param n The Notation to be used to represent the operation
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Times(List<Expression>)
   * @see Operation#Operation(List<Expression>,Notation)
   */
  public Times(List<Expression> elist, Notation n) throws IllegalConstruction {
  	super(elist,n);
  	symbol = "*";
  	neutral = new BigInteger("1");
  }

  public Number op(Number l, Number r) {
  	if (l instanceof BigInteger && r instanceof BigInteger) {
  		return op((BigInteger)l, (BigInteger)r);
  	} else if (l instanceof BigDecimal && r instanceof BigDecimal) {
  		return op((BigDecimal)l, (BigDecimal)r);
  	} else {
  		throw new IllegalArgumentException("Unsupported type");
  	}
  }

  /**
   * The actual computation of the (binary) arithmetic multiplication of two integers
   * @param l The first integer
   * @param r The second integer that should be multiplied with the first
   * @return The integer that is the result of the multiplication
   */
  public BigInteger op(BigInteger l, BigInteger r) {
  	return (l.multiply(r));
  }
   /**
    * Abstract method representing the actual binary arithmetic operation to compute
    *
    * @param l first argument of the binary operation
    * @param r second argument of the binary operation
    * @return result of computing the binary operation
    */
   public BigDecimal op(BigDecimal l, BigDecimal r) {
              return (l.multiply(r));
   }

  public int op(int l, int r)
    { return (l*r); }

   /**
    * The actual computation of the (binary) arithmetic multiplication of two times
    * @param l The first time
    * @param r The second time that should be multiplied with the first
    * @return nothing because the multiplication of two times cannot be done
    */
     public long op(long l, long r) {
       throw new RuntimeException("Cannot use multiplication with time");
   }
 }

