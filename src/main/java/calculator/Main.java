package calculator;

import real.Rounding;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A very simple calculator in Java
 * University of Mons - UMONS
 * Département d'Informatique
 * Faculté des Sciences
 *
 * @author tommens
 */
public class Main {

	/**
	 * This is the main method of the application.
	 * It provides examples of how to use it to construct and evaluate arithmetic expressions.
	 *
	 * @param args	Command-line parameters are not used in this version
	 */
	public static void main(String[] args) {

  	Expression e;
  	Calculator c = new Calculator(15, Rounding.ROUND_HALF_UP);

	try{

		e = new MyNumber(8);
		c.print(e);
		c.eval(e);

	    List<Expression> params = new ArrayList<>();
	    Collections.addAll(params, new MyNumber(3), new MyNumber(4), new MyNumber(5));
	    e = new Plus(params,Notation.PREFIX);
		c.printExpressionDetails(e);
		c.eval(e);
	
		List<Expression> params2 = new ArrayList<>();
		Collections.addAll(params2, new MyNumber(5), new MyNumber(3));
		e = new Minus(params2, Notation.INFIX);
		c.print(e);
		c.eval(e);

		List<Expression> params3 = new ArrayList<>();
		Collections.addAll(params3, new Plus(params), new Minus(params2));
		e = new Times(params3);
		c.printExpressionDetails(e);
		c.eval(e);

		List<Expression> params4 = new ArrayList<>();
		Collections.addAll(params4, new Plus(params), new Minus(params2), new MyNumber(5));
		e = new Divides(params4,Notation.POSTFIX);
		c.print(e);
		c.eval(e);

		List<Expression> params5 = new ArrayList<>();
		Collections.addAll(params5, new MyNumber(6.7), new MyNumber(3.4));
		e = new Divides(params5,Notation.INFIX);
		c.print(e);
		c.eval(e);

		e = new MyNumber(602200000000000000000000.0);
		c.print(e);
		c.eval(e);

		e = new MyNumber(0.000000000000000000000000000000000016);
		c.print(e);
		c.eval(e);

		e = new MyNumber("6.022E+23");
		c.print(e);
		c.eval(e);

		e = new MyNumber("1.6E-35");
		c.print(e);
		c.eval(e);

	}

	catch(IllegalConstruction exception) {
		System.out.println("cannot create operations without parameters");
		}
 	}

}
