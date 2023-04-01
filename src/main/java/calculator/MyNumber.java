package calculator;

import visitor.Visitor;
import java.math.BigDecimal;

import java.math.BigInteger;
import java.util.Objects;

/**
 * MyNumber is a concrete class that represents arithmetic numbers,
 * which are a special kind of Expressions, just like operations are.
 *
 * @see Expression
 * @see Operation
 */
public class MyNumber implements Expression
{
  private final Number value;

  private int radix;

    /** getter method to obtain the value contained in the object
     *
     * @return The integer number contained in the object
     */

  public Number getValue() {
      return value; //Return the value in radix 10
  }

    /**
     * Constructor method
     *
     * @param v The integer value to be contained in the object
     */

  public /*constructor*/ MyNumber(String v, Integer radix){
    this.value= new BigInteger(v, radix);
    if (radix > 36) {
        throw new IllegalArgumentException("The radix must be less than 36");
    }
    this.radix = radix;
  }

  public  MyNumber(Integer v) {
    this.value= new BigInteger(Integer.toString(v));
    this.radix = 10;
  }

  public MyNumber(String v) {
    value=new BigDecimal(v);
  }

  /*

  public  MyNumber(BigDecimal v) {
  value=v;
  }

  public MyNumber(int v) {
      value=BigDecimal.valueOf(v);
  }

  public  MyNumber(double v) {
      value=BigDecimal.valueOf(v);
  }

  public  MyNumber(long v) {
      value=BigDecimal.valueOf(v);
  }

  public  MyNumber(float v) {
      value=BigDecimal.valueOf(v);
  }

  */

    /**
     * accept method to implement the visitor design pattern to traverse arithmetic expressions.
     * Each number will pass itself to the visitor object to get processed by the visitor.
     *
     * @param v	The visitor object
     */
  public void accept(Visitor v) {
      v.visit(this);
  }

    /** The depth of a number expression is always 0
     *
     * @return The depth of a number expression
     */
  public int countDepth() {
	  return 0;
  }

    /** The number of operations contained in a number expression is always 0
     *
     * @return The number of operations contained in a number expression
     */
  public int countOps() {
	  return 0;
  }

    /** The number of numbers contained in a number expression is always 1
     *
     * @return The number of numbers contained in  a number expression
     */
  public int countNbs() {
	  return 1;
  }

    /**
     * Convert a number into a String to allow it to be printed.
     *
     * @return	The String that is the result of the conversion.
     */
  @Override
  public String toString() {
    if (value instanceof BigDecimal) {
        return value.toString();
    }
	  return ((BigInteger) value).toString(radix); //Display the value in the specified radix
  }

  /** Two MyNumber expressions are equal if the values they contain are equal
   *
   * @param o The object to compare to
   * @return  A boolean representing the result of the equality test
   */
  @Override
  public boolean equals(Object o) {
      // No object should be equal to null (not including this check can result in an exception if a MyNumber is tested against null)
      if (o == null) return false;

      // If the object is compared to itself then return true
      if (o == this) {
          return true;
      }

      // If the object is of another type then return false
      if (!(o instanceof MyNumber)) {
            return false;
      }

      return Objects.equals(this.value, ((MyNumber) o).value);

      // Used == since the contained value is a primitive value
      // If it had been a Java object, .equals() would be needed
  }

    /** The method hashCode needs to be overridden it the equals method is overridden;
     * 	otherwise there may be problems when you use your object in hashed collections
     * 	such as HashMap, HashSet, LinkedHashSet.
     *
     * @return	The result of computing the hash.
     */
  @Override
  public int hashCode() {
		return value.hashCode();
  }

  public int getRadix() {
      return radix;
    }

  public void setRadix(int radix) {
    //If the radix is greater than 36, throw an exception
    if (radix > 36) {
      throw new IllegalArgumentException("The radix must be less than 36");
    }
    this.radix = radix;
  }

    /**
     * this method is used to convert numbers from degrees to radians
     *
     * @return the value of the number in radians
     */
    public MyNumber toRadians() {
        return new MyNumber(((BigDecimal) value).multiply(BigDecimal.valueOf(Math.PI / 180)).toPlainString());
    }

    /**
     * this method is used to convert numbers from radians to degrees
     *
     * @return the value of the number in degrees
     */
    public MyNumber toDegrees() {
        return new MyNumber(((BigDecimal) value).multiply(BigDecimal.valueOf(180 / Math.PI)).toPlainString());
    }

}
