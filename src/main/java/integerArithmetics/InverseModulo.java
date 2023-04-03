package integerArithmetics;

import calculator.Expression;
import calculator.IllegalConstruction;
import calculator.Notation;
import calculator.Operation;

import java.math.BigInteger;
import java.util.List;

public class InverseModulo extends Operation {
  /**
   * Class constructor specifying a number of Expressions to add.
   *
   * @param elist The list of Expressions to add
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #InverseModulo(List < Expression >, Notation )
   */
  public /*constructor*/ InverseModulo(List<Expression> elist) throws IllegalConstruction {
    this(elist, null);
  }

  /**
   * Class constructor specifying a number of Expressions to add,
   * as well as the Notation used to represent the operation.
   *
   * @param elist The list of Expressions to add
   * @param n The Notation to be used to represent the operation
   * @throws IllegalConstruction    If an empty list of expressions if passed as parameter
   * @see #InverseModulo(List<Expression>)
   */
  public InverseModulo(List<Expression> elist, Notation n) throws IllegalConstruction {
    super(elist,n);
    symbol = "mod-1";
    neutral = new BigInteger("0");
  }

  public Number op(Number l, Number r) {
  	return op((BigInteger)l, (BigInteger)r);
  }

  public long op(long l, long r){
    throw new RuntimeException("InverseModulo: op(long, long) not implemented");
  }

  /**
   * The actual computation of the (binary) arithmetic addition of two integers
   * @param l The first integer
   * @param r The second integer that should be added to the first
   * @return The integer that is the result of the addition
   */
  public BigInteger op(BigInteger l, BigInteger r) {
    return (l.modInverse(r));
  }
}
