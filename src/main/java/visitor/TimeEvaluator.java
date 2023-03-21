package visitor;

import calculator.Expression;
import calculator.MyNumber;
import calculator.MyTime;
import calculator.Operation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class TimeEvaluator extends Visitor {

    /** The result of the evaluation will be stored in this private variable */
    private long computedValue;

    /** getter method to obtain the result of the evaluation
     *
     * @return an long object containing the result of the evaluation
     */
    public long getResult() { return computedValue; }

    /** Use the visitor design pattern to visit a time.
     *
     * @param t The time being visited
     */
    public void visit(MyTime t) {
        computedValue = t.getValue();
    }

    /** Cannot use this visitor to visit a number.
     *
     * @param n The number that cannot be visited
     */
    @Override
    public void visit(MyNumber n) {
        throw new RuntimeException("Cannot use TimeEvaluator with MyNumber");
    }

    /** Use the visitor design pattern to visit an operation
     *
     * @param o The operation being visited
     */
    public void visit(Operation o) {
        ArrayList<Long> evaluatedArgs = new ArrayList<>();

        // If the request is unary, add the current date as a first param
        if(o.args.size() == 1) {
            String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss"));
            o.args.add(0, MyTime.getAsDate(currentDate));
        }

        //first loop to recursively evaluate each subexpression
        for(Expression a:o.args) {
            a.accept(this);
            evaluatedArgs.add(computedValue);
        }
        //second loop to accumulate all the evaluated subresults
        long temp = evaluatedArgs.get(0);
        int max = evaluatedArgs.size();
        for(int counter=1; counter<max; counter++) {
            temp = o.op(temp, evaluatedArgs.get(counter));
        }
        // store the accumulated result
        computedValue = temp;
    }

}
