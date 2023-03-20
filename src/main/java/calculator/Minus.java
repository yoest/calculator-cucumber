package calculator;

import java.math.BigDecimal;
import java.util.List;

/** This class represents the arithmetic operation "-".
 * The class extends an abstract superclass Operation.
 * Other subclasses of Operation represent other arithmetic operations.
 * @see Operation
 * @see Plus
 * @see Times
 * @see Divides
 */
public final class Minus extends Operation
 {

  /**
   * Class constructor specifying a number of Expressions to subtract.
   *
   * @param elist The list of Expressions to subtract
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Minus(List<Expression>,Notation)
   */
  public /*constructor*/ Minus(List<Expression> elist) throws IllegalConstruction {
  	this(elist, null);
  }

  /**
   * Class constructor specifying a number of Expressions to subtract,
   * as well as the Notation used to represent the operation.
   *
   * @param elist The list of Expressions to subtract
   * @param n The Notation to be used to represent the operation
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #Minus(List<Expression>)
   * @see Operation#Operation(List<Expression>,Notation)
   */
  public Minus(List<Expression> elist, Notation n) throws IllegalConstruction {
  	super(elist,n);
  	symbol = "-";
  	neutral = 0;
  }

     /**
      * Abstract method representing the actual binary arithmetic operation to compute
      *
      * @param l first argument of the binary operation
      * @param r second argument of the binary operation
      * @return result of computing the binary operation
      */
     @Override
     public BigDecimal op(BigDecimal l, BigDecimal r) {
                return (l.subtract(r));
     }
}
