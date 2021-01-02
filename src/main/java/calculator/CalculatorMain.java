package calculator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/***************************************
 * A very simple Calculator in Java 11 *
 * Tom Mens, January 2021              *
 * University of Mons - UMONS          *
 * Département d'Informatique          *
 * Faculté des Sciences                *
 ***************************************/

/* Version 2b:
   ***********
   Compatible with Java 11 and JUnit 4.12. Runnable with Maven.
   Implementation of extra methods in interface: countDepth(), countOps(), countNbs().
   Use of "String toString()" instead of "void print()" composite design pattern.
   Use of Java functional programming capabilities in methods of class Operation.
   Possibility to print output in INFIX, PREFIX or POSTFIX notation.
   Addition of unit tests.
   Creation of a maven pom.xml file to execute with Maven. */

/* Version 2.A:
   ***********
   n-ary operations (instead of only binary operations) are allowed, with n>=2.
   This requires a more complex variant of a Composite design pattern.
   Abstract class Expression has been turned into an interface.
   Generic types are used to deal with List<Expression>. */

/* Version 1.0:
   ***********
   Arbitrary nested binary operations are allowed.
   An abstract superclass Expression is introduced for MyNumber and Operation.
   Makes use of a Composite design pattern.

/* Version 0.0:
   ***********
   Only binary non-nested expressions are allowed. */


public class CalculatorMain
{

  private static void displayResult(Expression e) {
	System.out.println("The result of evaluating expression " + e);
	System.out.println("is: " + e.compute() + ".");
	System.out.println("It contains " + e.countDepth() + " levels of nested expressions,");
	System.out.print(e.countOps() + " operations");
	System.out.println(" and " + e.countNbs() + " numbers.");
	
		System.out.println();
	}	

// Here is an example of how to use the calculator:
public static void main(String args[]) {
	try{
	displayResult(new MyNumber(8));
	
	List<Expression> params = new ArrayList<>();
	Collections.addAll(params, new MyNumber(3), new MyNumber(4), new MyNumber(5));
	displayResult(new Plus(params,Notation.PREFIX));
	
	List<Expression> params2 = new ArrayList<>();
	Collections.addAll(params2, new MyNumber(5), new MyNumber(4));
	displayResult(new Minus(params2, Notation.INFIX));
	
	List<Expression> params3 = new ArrayList<>();
	Collections.addAll(params3, new Plus(params), new Minus(params2));
	displayResult(new Times(params3));

	List<Expression> params4 = new ArrayList<>();
	Collections.addAll(params4, new Plus(params), new Minus(params2), new MyNumber(7));
	displayResult(new Divides(params4,Notation.POSTFIX));
	}
	
	catch(IllegalConstruction e) {
		System.out.println("cannot create operations without parameters");
		}
 	}

}
