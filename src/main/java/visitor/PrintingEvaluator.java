package visitor;

import calculator.*;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

public class PrintingEvaluator extends Visitor {

    private String strExpression = "";
    private Notation n = Notation.INFIX;

    /** Print a given expression by visiting each of its arguments.
     *
     * @param expression: expression to visit
     * @param notation: in what notation should we output the expression
     * @return the expression as a string in the given notation
     */
    public String print(Expression expression, Notation notation) {
        n = notation;

        if(expression instanceof MyNumber)
            this.visit((MyNumber) expression);
        else if(expression instanceof MyTime)
            this.visit((MyTime) expression);
        else if(expression instanceof Operation)
            this.visit((Operation) expression);
        else
            throw new RuntimeException("Cannot print a expression which is not a number, a time or an operation");

        return this.strExpression;
    }

    @Override
    public void visit(MyNumber n) {
        if (n.getValue() instanceof BigDecimal)
            strExpression += n.getValue().toString();
        else
            strExpression += ((BigInteger) n.getValue()).toString(n.getRadix()); // Display the value in the specified radix
    }

    @Override
    public void visit(MyTime t) {
        strExpression += t.STR_REPRESENTATION;
    }

    @Override
    public void visit(Operation o) {
        List<Expression> expressions = o.getArgs();

        switch (n) {
            case INFIX -> {
                strExpression += "( ";
                for(int i = 0; i < expressions.size() - 1; i++) {
                    Expression s = expressions.get(i);

                    this.print(s, n);
                    strExpression += " ";
                    strExpression += o.getSymbol();
                    strExpression += " ";
                }
                Expression s = expressions.get(expressions.size() - 1);
                this.print(s, n);
                strExpression += " )";
            }
            case PREFIX -> {
                strExpression += o.getSymbol();
                strExpression += " (";
                loopAndSeparate(expressions);
                Expression s = expressions.get(expressions.size() - 1);
                this.print(s, n);
                strExpression += ")";
            }
            case POSTFIX -> {
                strExpression += "(";
                loopAndSeparate(expressions);
                Expression s = expressions.get(expressions.size() - 1);
                this.print(s, n);
                strExpression += ") ";
                strExpression += o.getSymbol();
            }
        }
    }

    /** Loop over a list of expressions and add a comma between two expression.
     *
     * @param expressions: list of expression to separate with a comma
     */
    private void loopAndSeparate(List<Expression> expressions) {
        for(int i = 0; i < expressions.size() - 1; i++) {
            Expression s = expressions.get(i);

            this.print(s, n);
            strExpression += ", ";
        }
    }

}
