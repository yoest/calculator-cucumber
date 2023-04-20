package timeCalculatorParser;


import calculator.Expression;
import calculator.TimeCalculator;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class TimeParserMain {
    public static void main(String[] argv) throws Exception{
        try {
            String selectedText = "_+_10:00";

            // Handle unary request
            if(selectedText.length() > 0) {
                String first = selectedText.substring(0, 3);
                if(first.equals("_+_") || first.equals("_-_")) {
                    String currentDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss", Locale.UK));
                    selectedText = currentDate + ' ' + selectedText;
                }
            }

            System.out.println(selectedText);

            parser p = new parser(new lexer(new java.io.StringReader(selectedText)));
            Object result = p.parse().value;
            Expression e = (Expression) result;
            TimeCalculator c = new TimeCalculator();
            c.print(e);
            long res = c.eval(e);
            System.out.println(c.getResultAsCompleteString(res));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
