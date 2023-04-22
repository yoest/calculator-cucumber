package visitor;

import calculator.*;

import java.util.List;

public class CountingEvaluator extends Visitor {

    private int countNumber = 0;
    private int countOps = 0;
    private int countDepth = 0;

    /** Count the number of number, operations, and depth of a given expression
     *
     * @param expression: expression to visit
     */
    public void count(Expression expression) {
        if(expression instanceof MyNumber)
            this.visit((MyNumber) expression);
        else if(expression instanceof MyTime)
            this.visit((MyTime) expression);
        else if(expression instanceof Operation)
            this.visit((Operation) expression);
        else
            throw new RuntimeException("Cannot count in a expression which is not a number, a time or an operation");
    }

    @Override
    public void visit(MyNumber n) {
        countNumber += 1;
    }

    @Override
    public void visit(MyTime t) {
        countNumber += 1;
    }

    @Override
    public void visit(Operation o) {
        List<Expression> expressions = o.getArgs();

        countOps += 1;
        if(countDepth == 0)
            countDepth += 1;

        for (Expression expression : expressions) {

            Expression s = expression;

            int currentDepth = computeDepthRec(s);
            countDepth = Math.max(countDepth, currentDepth);
            this.count(s);
        }
    }

    /** Compute the depth of an expression recursively
     *
     * @param expression: expression from which to compute the depth
     * @return depth of the current expression
     */
    private int computeDepthRec(Expression expression) {
        if(expression instanceof MyTime || expression instanceof MyNumber)
            return 0;
        else {
            Operation operation = (Operation) expression;
            int currentDepth = 1;
            for(Expression e : operation.getArgs())
                currentDepth = Math.max(currentDepth, computeDepthRec(e));
            return 1 + currentDepth;
        }
    }

    /** Getter for the number of numbers in the expression. */
    public int getCountNumber() {
        return countNumber;
    }

    /** Getter for the number of operations in the expression. */
    public int getCountOps() {
        return countOps;
    }

    /** Getter for the depth of the expression. */
    public int getCountDepth() {
        return countDepth;
    }
}
