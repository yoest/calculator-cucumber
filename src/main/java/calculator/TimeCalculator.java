package calculator;

import visitor.TimeEvaluator;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalUnit;

public class TimeCalculator {

    /**
     * Prints an arithmetic expression provided as input parameter.
     * @param e the arithmetic Expression to be printed
     */
    public void print(Expression e) {
        System.out.println("The result of evaluating expression " + e);
        System.out.println("is: ");

        long result = eval(e);
        System.out.println("  - As date: " + getResultAsDate(result));
        System.out.println("  - As a complete string: " + getResultAsCompleteString(result));
        System.out.println("  - As days: " + getResultAsFractionalDays(result));
        System.out.println("  - As hours: " + getResultAsFractionalHours(result));
        System.out.println("  - As minutes: " + getResultAsFractionalMinutes(result));
        System.out.println("  - As seconds: " + getResultAsFractionalSeconds(result));
    }

    /**
     * Evaluates an arithmetic expression and returns its result
     * @param e the arithmetic Expression to be evaluated
     * @return The result of the evaluation
     */
    public long eval(Expression e) {
        // create a new time visitor to evaluate expressions
        TimeEvaluator v = new TimeEvaluator();
        // and ask the expression to accept this visitor to start the evaluation process
        e.accept(v);
        // and return the result of the evaluation at the end of the process
        return v.getResult();
    }

    /** Get the result as a date
     *
     * @param res result of the evaluation
     * @return a ZonedDateTime object representing the date of the evaluation of the result
     */
    public ZonedDateTime getResultAsDate(long res) {
        Instant instant = Instant.ofEpochSecond(res);
        return LocalDateTime.ofInstant(instant, ZoneId.of("Etc/UTC")).atZone(ZoneId.of("Etc/UTC"));
    }

    /** Get the result as a complete string
     *
     * @param res result of the evaluation
     * @return complete string with the number of days, hours, ...
     */
    public String getResultAsCompleteString(long res) {
        long seconds = res % 60;
        long minutes = res % 3600 / 60;
        long hours = res % 86400 / 3600;
        long days = res / 86400;
        return days + " days, " + hours + " hours, " + minutes + " minutes and " + seconds + " seconds";
    }

    /** Get the result as days with a fractional part
     *
     * @param res result of the evaluation
     * @return string with the evaluation as a number of days
     */
    public String getResultAsFractionalDays(long res) {
        return (res / 86400.) + " days";
    }

    /** Get the result as hours with a fractional part
     *
     * @param res result of the evaluation
     * @return string with the evaluation as a number of hours
     */
    public String getResultAsFractionalHours(long res) {
        return (res / 3600.) + " hours";
    }

    /** Get the result as minutes with a fractional part
     *
     * @param res result of the evaluation
     * @return string with the evaluation as a number of minutes
     */
    public String getResultAsFractionalMinutes(long res) {
        return (res / 60.) + " minutes";
    }

    /** Get the result as seconds with a fractional part
     *
     * @param res result of the evaluation
     * @return string with the evaluation as a number of seconds
     */
    public String getResultAsFractionalSeconds(long res) {
        return res + " seconds";
    }
}
