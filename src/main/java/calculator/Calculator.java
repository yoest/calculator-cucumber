package calculator;

import real.Rounding;
import visitor.Evaluator;

import java.math.BigDecimal;



/**
 * This class represents the core logic of a Calculator.
 * It can be used to print and evaluate artihmetic expressions.
 *
 * @author tommens
 */
public class Calculator {
    private int PRECISION;
    private Rounding ROUNDING;

    public Calculator() {
        this.PRECISION = 10;
        this.ROUNDING = Rounding.ROUND_HALF_UP;
    }

    public Calculator(int precision, Rounding rounding) {
        this.PRECISION = precision;
        this.ROUNDING = rounding;
    }

    /*
     For the moment the calculator only contains a print method and an eval method
     It would be useful to complete this with a read method, so that we would be able
     to implement a full REPL cycle (Read-Eval-Print loop) such as in Scheme, Python, R and other languages.
     To do so would require to implement a method with the following signature, converting an input string
     into an arithmetic expression:
     public Expression read(String s)
    */

    /**
     * Prints an arithmetic expression provided as input parameter.
     * @param e the arithmetic Expression to be printed
     * @see #printExpressionDetails(Expression)
     */
    public void print(Expression e){
        System.out.println("The result of evaluating expression " + e);
        System.out.println("is: " + eval(e) + ".");
        System.out.println();
    }

    /**
     * Prints verbose details of an arithmetic expression provided as input parameter.
     * @param e the arithmetic Expression to be printed
     * @see #print(Expression)
     */
    public void printExpressionDetails(Expression e){
        print(e);
        System.out.print("It contains " + e.countDepth() + " levels of nested expressions, ");
        System.out.print(e.countOps() + " operations");
        System.out.println(" and " + e.countNbs() + " numbers.");
        System.out.println();
    }

    /**
     * Evaluates an arithmetic expression and returns its result
     * @param e the arithmetic Expression to be evaluated
     * @return The result of the evaluation
     */


    public Number eval(Expression e) {
        // create a new visitor to evaluate expressions
        Evaluator v = new Evaluator();
        // and ask the expression to accept this visitor to start the evaluation process
        e.accept(v);
        // and return the result of the evaluation at the end of the process
        Number temp = v.getResult();

        if (temp instanceof BigDecimal) {
            return ((BigDecimal) temp).setScale(PRECISION, ROUNDING.toRoundingMode());
        }
        return temp;
    }

    /*
     We could also have other methods, e.g. to verify whether an expression is syntactically correct
     public Boolean validate(Expression e)
     or to simplify some expression
     public Expression simplify(Expression e)
    */

    public int getPrecision() {
        return PRECISION;
    }

    public void setPrecision(int precision) {
        this.PRECISION = precision;
    }

    public Rounding getRounding() {
        return ROUNDING;
    }

    public void setRounding(Rounding rounding) {
        this.ROUNDING = rounding;
    }
}
