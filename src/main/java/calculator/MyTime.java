package calculator;

import visitor.Visitor;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class MyTime implements Expression {

    private final long value;
    private final String STR_REPRESENTATION;

    /** getter method to obtain the value contained in the object
     *
     * @return The long number contained in the object representing a number of seconds
     */
    public long getValue() { return value; }

    /** Constructor method
     *
     * @param v The long value representing a number of seconds
     * @param stringRepresentation to represent the object when there is a called to 'toString()'
     */
    public /*constructor*/ MyTime(long v, String stringRepresentation) {
        value=v;
        STR_REPRESENTATION = stringRepresentation;
    }

    /** Create a MyTime object from a date string which has to be on the ISO format, can use the
     *  24-hour or 12-hour time formats, and can contain a timezone
     *
     * @param v a date as a string
     * @return the MyTime object from the date
     */
    public static MyTime getAsDate(String v) {
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String time = "00:00:00";
        String timeFormat = null;
        String timezone = "+00:00";

        // Parse the string date to be sure it respects the right format
        String[] partOfText = v.split(" ");
        for(String text : partOfText) {
            // Date
            if((text.length() - text.replace("-", "").length()) == 2)
                date = text;

            // Time
            if(text.contains(":") && !(text.startsWith("+") || text.startsWith("-"))) {
                int numberOfParts = text.split(":").length;
                if(numberOfParts == 2)
                    time = text + ":00";
                else
                    time = text;
            }

            // 12 or 24 hours format
            if(text.equalsIgnoreCase("am") || text.equalsIgnoreCase("pm")) {
                timeFormat = text.toLowerCase();
            }

            // Timezone
            if(text.charAt(0) == '+' || text.charAt(0) == '-')
                timezone = text;
        }

        // Put together all the part to create the final date
        String finalDate, formatter;
        if(timeFormat == null) {
            finalDate = date + " " + time + " " + timezone;
            formatter = "yyyy-MM-dd HH:mm:ss z";
        } else {
            finalDate = date + " " + time + " " + timeFormat + " " + timezone;
            formatter = "yyyy-MM-dd hh:mm:ss a z";
        }

        ZonedDateTime associatedDate = ZonedDateTime.parse(finalDate, DateTimeFormatter.ofPattern(formatter));
        return new MyTime(associatedDate.toEpochSecond(), associatedDate.toString());
    }

    /** Get a MyTime object from a string on the format hh:mm:ss
     *
     * @param v a time (hours, minutes, seconds) in a string format
     * @return a MyTime object obtained from the string
     */
    public static MyTime getAsHours(String v) {
        boolean hasPM = v.contains("PM");
        String[] partText = v.split(":");
        if(hasPM)
            partText[partText.length - 1] = partText[partText.length - 1].substring(0, 2);

        long hours = Integer.parseInt(partText[0]);
        if(hasPM)
            hours += 12;

        long minutes = Integer.parseInt(partText[1]);
        long seconds = 0;
        if(partText.length == 3)
            seconds = Integer.parseInt(partText[2]);

        long associatedTime = seconds + minutes * 60 + hours * 3600;
        return new MyTime(associatedTime, hours + " hours; " + minutes + " minutes; " + seconds + " seconds");
    }

    /** Get a MyTime object from a number of days
     *
     * @param v a value representing the number of days
     * @return a MyTime object obtained from the number of days
     */
    public static MyTime getAsDays(int v) {
        long associatedTime = v * 24 * 60 * 60L;
        return new MyTime(associatedTime, v + " days");
    }

    /**
     * accept method to implement the visitor design pattern to traverse arithmetic expressions.
     * Each time will pass itself to the visitor object to get processed by the visitor.
     *
     * @param v	The visitor object
     */
    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    /** The depth of a number expression is always 0
     *
     * @return The depth of a number expression
     */
    @Override
    public int countDepth() {
        return 0;
    }

    /** The number of operations contained in a number expression is always 0
     *
     * @return The number of operations contained in a number expression
     */
    @Override
    public int countOps() {
        return 0;
    }

    /** The number of numbers contained in a number expression is always 1
     *
     * @return The number of numbers contained in  a number expression
     */
    @Override
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
        return this.STR_REPRESENTATION;
    }
}
